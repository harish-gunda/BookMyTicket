package application;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class UserEventBookController implements Initializable {
	public static int bookeventId;
	public int bookeventuserId;
	private List<Event> events;
	Program program;

	@FXML
	private Button btnBack;

	@FXML
	private TextField tfArtist;

	@FXML
	private TextField tfDate;

	@FXML
	private TextField tfDescription;

	@FXML
	private TextField tfPrice;

	@FXML
	private TextField tfRemainingTickets;

	@FXML
	private TextField tfcardCVV;

	@FXML
	private TextField tfcardNo;
	
	public UserEventBookController() throws IOException {
		this.program = new Program();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println(bookeventId+ " event");
		System.out.println(bookeventuserId + " user");
		populateFields();
	}
	
	


	@FXML
	void btnBackClick(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEvents.fxml"));
		Parent root = loader.load();
		UserEventsController controller = loader.getController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		controller.setUserid(bookeventuserId);
		stage.show();
	}

	@FXML
	void btnBookNow(ActionEvent event) {
		System.out.println("UserEventBookController book" + bookeventuserId);
		Event event2 = null;
		for (Event e : events) {
			if (e.getId() == bookeventId) {
				event2 = e;
				break;
			}
		}

		if (event2.getRemainingTickets() < 1) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setTitle("Booking failed");
			a.setHeaderText("");
			a.setContentText("No seats available");
			a.showAndWait();
		}

		try {
			Long creditcardNo = Long.valueOf(tfcardNo.getText());
			if (Util.isValid(creditcardNo)) {
				event2.setRemainingTickets(event2.getRemainingTickets() - 1);
				event2.setSoldTickets(event2.getSoldTickets() + 1);
				
				User urUser = program.getUsers().get(UserController.userid);
				urUser.getBookings().add(event2.getId());
				
				program.writeEvents();
				program.writeUsers();
				
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setTitle("Modified");
				a.setHeaderText("Success.");
				a.setContentText("Booking is Successful");
				a.showAndWait();
				populateFields();
			} else {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText("Error while booking ticket");
				a.setContentText("Please try again with correct details");
				a.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Error");
			a.setHeaderText("Error while booking ticket");
			a.setContentText("Please try again with correct details" + e.getMessage());
			a.showAndWait();
		}

	}
	

	public void populateFields() {
		try {
			program = new Program();
			events = program.getEvents();
			for (Event e : events) {
				if (e.getId() == bookeventId) {
					tfArtist.setText(e.getArtist());
					tfDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(e.getDate()));
					tfDescription.setText(e.getDescription());
					tfPrice.setText(Float.toString(e.getPrice()));
					tfRemainingTickets.setText(Integer.toString(e.getRemainingTickets()));
					break;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
