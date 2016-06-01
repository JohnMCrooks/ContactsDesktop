package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

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
            writeToFile(contacts);
        }else  {
            System.out.println("You won't see this but You can't leave any fields blank!!! ");
        }
    }
    public void onRemove(){
        SelectionModel model = list.getSelectionModel();
        Contact contact = (Contact) model.getSelectedItem();
        contacts.remove(contact);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contacts = readfile();
        list.setItems(contacts);
    }

    public ObservableList<Contact> readfile(){
            File f = new File("Contacts.json");
            try {
                Scanner scanner = new Scanner(f);
                scanner.useDelimiter("\\Z");
                String contents = scanner.next();
                JsonParser parser = new JsonParser();

                ArrayList<HashMap<String, String>> list = parser.parse(contents);               //data is parsed in as an ArrayList of Hashmaps for whatever reason
                //ObservableList<Contact> contacts = FXCollections.observableArrayList();         //We want the data to be added to an Observable Array list for use
                for (HashMap<String, String> h: list){                                          //For every Hashmap in the Array List....
                    contacts.add(new Contact(h.get("name"), h.get("email"), h.get("phone")));   //Create A Contact object with variables drawn from each of the 3 Hashmaps
                }
                return contacts;
            }catch (Exception e) {
                e.printStackTrace();
                return contacts;
            }
    }

    public void writeToFile(ObservableList<Contact> contacts) throws IOException {
        String fileName = "Contacts.json";
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(contacts);
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();

    }
}
