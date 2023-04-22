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

	public static int userid;
	private Loader program;

	public UserController() throws IOException {
		this.program = new Loader();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			this.program = new Loader();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setUserId(int userid) {
		System.out.println(" uc " + userid);
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEvents.fxml"));
		Parent root = loader.load();
		UserEventsController controller = loader.getController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		controller.setUserid(userid);
		stage.show();
	}

	@FXML
	void btnUsersClick(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserProfile.fxml"));
		Parent root = loader.load();
		UserProfileController controller = loader.getController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		controller.setUserId(userid);
		stage.show();
	}

	@FXML
	void btnMyBookings(ActionEvent event) throws IOException {
		System.out.println(" ucb " + userid);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserBookings.fxml"));
		Parent root = loader.load();
		UserBookingsController controller = loader.getController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		controller.setUserId(userid);
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
