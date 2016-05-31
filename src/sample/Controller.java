package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    @FXML
    TextField nameField;
    @FXML
    TextField numberField;
    @FXML
    TextField emailField;
    @FXML
    ListView list;

    public void onAdd() throws IOException {
        if (!nameField.getText().isEmpty() && !emailField.getText().isEmpty() && !numberField.getText().isEmpty()){
            Contact contact = new Contact(nameField.getText(),emailField.getText(),numberField.getText());
            contacts.add(contact);
            nameField.clear();
            emailField.clear();
            numberField.clear();
            writeToFile(contact);
        }else  {
            System.out.println(" You won't see this but You can't leave any fields blank!!! ");
        }

    }

    public void onRemove(){
        SelectionModel model = list.getSelectionModel();
        Contact contact = (Contact) model.getSelectedItem();
        contacts.remove(contact);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(contacts);
    }

    public void writeToFile(Contact contact) throws IOException {
        String fileName = "Contacts.json";
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(contact);
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();

    }
}
