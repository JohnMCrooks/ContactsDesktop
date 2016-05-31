package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
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

    public void onAdd(){
        Contact contact = new Contact(nameField.getText(),emailField.getText(),numberField.getText());
        contacts.add(contact);
        nameField.clear();
        emailField.clear();
        numberField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(contacts);
    }
}
