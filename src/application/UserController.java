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
	
	@FXML private ComboBox<String> levelComboBox;
	private int userid;
	private Program program;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			Program program = new Program();
		} catch (Exception e) {
			e.printStackTrace();
		}
        // Fills values in combo box with 3 levels
        levelComboBox.getItems().addAll("Easy", "Medium", "Hard");
        levelComboBox.setValue("Easy");
	}
	
	public void setUserId(int userid) {
		this.userid = userid;
		try {
			program.writeUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void logout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
