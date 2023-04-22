package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;


public class AdminUserController  implements Initializable {
	
	private Loader program;
	private int selectedRow = -1;
	@FXML private TableView<User> userTable;
    @FXML private TableColumn<User, String> firstnameColumn;
    @FXML private TableColumn<User, String> lastnameColumn;
    @FXML private TableColumn<User, String> usernameColumn;
    @FXML private TableColumn<User, String> passwordColumn;
    @FXML private Button deleteBtn;
    @FXML private TextField firstnameTxtField;
    @FXML private TextField lastnameTxtField;
    @FXML private TextField usernameTxtField;
    @FXML private TextField passwordTxtField;
	
	public AdminUserController() throws IOException {
		this.program = new Loader();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {

        // Fills in the values with all the words belonging to the level selected in the combo box
		populateTable();
		
		// Makes Cells editable
		userTable.setEditable(true);
        firstnameColumn.setCellFactory(TextFieldTableCell.<User, String>forTableColumn(new DefaultStringConverter()));
        lastnameColumn.setCellFactory(TextFieldTableCell.<User, String>forTableColumn(new DefaultStringConverter()));
        usernameColumn.setCellFactory(TextFieldTableCell.<User, String>forTableColumn(new DefaultStringConverter()));
        passwordColumn.setCellFactory(TextFieldTableCell.<User, String>forTableColumn(new DefaultStringConverter()));
	}
	
	public void populateTable() {
		ObservableList<User> users = FXCollections.observableArrayList();
        for (User u: program.getUsers()) {
        	users.add(u);
        }
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
		lastnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		userTable.setItems(users);
	}
	
	public void updateUsers() {
		try {
			program.writeUsers();
		} catch (Exception e) {
			System.out.println("Error writing to json in AdminUser");
			e.printStackTrace();
		}
	}
	
	public void addUser() throws IOException {
		program.getUsers().add(new User(usernameTxtField.getText(), passwordTxtField.getText(), firstnameTxtField.getText(), lastnameTxtField.getText()));
		usernameTxtField.clear();
		passwordTxtField.clear();
		firstnameTxtField.clear();
		lastnameTxtField.clear();
		updateUsers();
		populateTable();
	}
	
	public void deleteUser() {
		if (selectedRow<0) {
			return;
		}
		program.getUsers().remove(selectedRow);
		populateTable();
		updateUsers();
		selectedRow = -1;
	}
	
	public void selectRow() {
		selectedRow = userTable.getSelectionModel().getSelectedIndex();
	}
	
	public void changeWordCellEvent() {
		
	}
	
	public void changeFirstnameCellEvent(CellEditEvent<User, String> edittedCell) {
		int ind =  edittedCell.getTablePosition().getRow();
        program.getUsers().get(ind).setFirstname(edittedCell.getNewValue().toString());
        populateTable();
        updateUsers();
	}
	
	public void changeLastnameCellEvent(CellEditEvent<User, String> edittedCell) {
		int ind =  edittedCell.getTablePosition().getRow();
        program.getUsers().get(ind).setLastname(edittedCell.getNewValue().toString());
        populateTable();
        updateUsers();
	}
	
	public void changeUsernameCellEvent(CellEditEvent<User, String> edittedCell) {
		int ind =  edittedCell.getTablePosition().getRow();
        program.getUsers().get(ind).setUsername(edittedCell.getNewValue().toString());
        populateTable();
        updateUsers();
	}
	
	public void changePasswordCellEvent(CellEditEvent<User, String> edittedCell) {
		int ind =  edittedCell.getTablePosition().getRow();
        program.getUsers().get(ind).setPassword(edittedCell.getNewValue().toString());
        populateTable();
        updateUsers();
	}
	
	public void back(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();  
	}
	
	public void logoout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); 
	}
}
