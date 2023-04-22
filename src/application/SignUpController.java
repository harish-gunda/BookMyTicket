package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
	
	private Loader program;
	@FXML private TextField firstnameTxtField;
	@FXML private TextField lastnameTxtField;
	@FXML private TextField usernameTxtField;
	@FXML private TextField passwordTxtField;
	@FXML private TextField confirmTxtField;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
        	this.program = new Loader();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public boolean checkFields() {
		
		return true;
	}
	
	public void updateUsers() {
		try {
			program.writeUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createBtn(ActionEvent event) throws IOException {
		program.getUsers().add(new User(usernameTxtField.getText(), passwordTxtField.getText(), firstnameTxtField.getText(), lastnameTxtField.getText()));
		updateUsers();
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); 
	}
	
	public void backBtn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); 
	}
}
