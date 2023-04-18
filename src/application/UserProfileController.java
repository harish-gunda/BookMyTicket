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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserProfileController implements Initializable {

	private Program program;
	private int userId;
	@FXML
	private TextField firstnameTxtField;
	@FXML
	private TextField lastnameTxtField;
	@FXML
	private TextField usernameTxtField;
	@FXML
	private TextField passwordTxtField;
	@FXML
	private TextField confirmTxtField;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			this.program = new Program();
			populateFields();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserProfileController() throws IOException {
		this.program = new Program();
	}

	private void populateFields() {
		User user = program.getUsers().get(UserController.userid);
		firstnameTxtField.setText(user.getFirstname());
		lastnameTxtField.setText(user.getLastname());
		usernameTxtField.setText(user.getUsername());
		// TODO Auto-generated method stub

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
		User user = program.getUsers().get(UserController.userid);
		user.setFirstname(usernameTxtField.getText());
		user.setLastname(passwordTxtField.getText());
		user.setUsername(firstnameTxtField.getText());
		user.setPassword(lastnameTxtField.getText());
		
		updateUsers();
		
		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
		a.setTitle("Updated");
		a.setHeaderText("Success.");
		a.setContentText("Profle is updated successfully");
		a.showAndWait();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
		Parent root = loader.load();
		UserController controller = loader.getController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		controller.setUserId(getUserId());
		stage.show();

	}

	public void backBtn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
		Parent root = loader.load();
		UserController controller = loader.getController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		controller.setUserId(userId);
		stage.show();
	}
}
