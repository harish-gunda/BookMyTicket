package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SceneController implements Initializable, SceneControllerInterface {
	
	private int userid;
	private Program program;
	
	@FXML private TextField usernameTxtField;
	@FXML private TextField passwordTxtField;
	@FXML private Label errorMsg;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			this.program = new Program();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void login(ActionEvent event) throws IOException {
		if (usernameTxtField.getText().equals("admin") && passwordTxtField.getText().equals("1234")) {
			adminLogin(event);
			return;
		}
		for (int i=0; i<program.getUsers().size(); i++) {
			User u = program.getUsers().get(i);
			if (usernameTxtField.getText().equals(u.getUsername()) && passwordTxtField.getText().equals(u.getPassword())) {
				userid = i;
				program.writeUsers();
				userLogin(event);
				return;
			}
		}
	}
	
	public void userLogin(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
		Parent root = loader.load();
		UserController controller = loader.getController();
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		controller.setUserId(userid);
		stage.show();
	}
	
	public void adminLogin(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();  
	}
	
	public void signUp(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();  
	}
}
