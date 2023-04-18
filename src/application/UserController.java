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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class UserController implements Initializable {

	private int userid;
	private Program program;

	public UserController() throws IOException {
		this.program = new Program();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			this.program = new Program();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setUserId(int userid) {
		this.userid = userid;
		try {
//			program = new Program();
			program.writeUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btnEventsClick(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UserEvents.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void btnUsersClick(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
    void btnMyBookings(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UserBoookings.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

	
	@FXML
	public void logout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
