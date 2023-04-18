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
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class EventViewController implements Initializable{
	public static int eventId;
	private List<Event> events;
	Program program;
	

    @FXML
    private TextField artisttf;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnModify;

    @FXML
    private TextField tfArtist;

    @FXML
    private TextField tfDate;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfPrice;

    @FXML
    private TextField tfRemainingTickets;

    @FXML
    private TextField tfSoldTickets;

    @FXML
    private TextField tfTotalTickets;

    @FXML
    void btnBackClick(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("AdminEvents.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); 
    }

    @FXML
    void btnDeleteClick(ActionEvent event) throws IOException {
    	for(Event e: events) {
    		if(e.getId()==eventId) {
    			events.remove(e);
    		}
    	}
    	try {
			program.writeEvents();
			Alert a = new Alert(Alert.AlertType.CONFIRMATION);
			a.setTitle("Modified");
			a.setHeaderText("Success.");
			a.setContentText("Successfully deleted the event");
			a.showAndWait();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Parent root = FXMLLoader.load(getClass().getResource("AdminEvents.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); 
    	
    }

    @FXML
    void btnModifyClick(ActionEvent event) {
    	events = program.getEvents();
		for(Event e: events) {
			if (e.getId() == eventId) {
				e.setArtist(tfArtist.getText());
				try {
					e.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(tfDate.getText()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				e.setDescription(tfDescription.getText());
				e.setId(Integer.parseInt(tfId.getText()));
				e.setPrice(Float.parseFloat(tfPrice.getText()));
				e.setRemainingTickets(Integer.parseInt(tfRemainingTickets.getText()));
				e.setSoldTickets(Integer.parseInt(tfSoldTickets.getText()));
				e.setRemainingTickets(Integer.parseInt(tfRemainingTickets.getText()));
				e.setTotalTickets(Integer.parseInt(tfTotalTickets.getText()));
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setTitle("Modified");
				a.setHeaderText("Success.");
				a.setContentText("Successfully modified the event");
				a.showAndWait();
				break;
			}
		}
		try {
			program.writeEvents();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println(eventId);
		populateFields();
	}
	
	public void populateFields() {
		try {
			program = new Program();
			events = program.getEvents();
			for(Event e: events) {
				if (e.getId() == eventId) {
					tfArtist.setText(e.getArtist());
					tfDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(e.getDate()));
					tfDescription.setText(e.getDescription());
					tfId.setText(Integer.toString(e.getId()));
					tfPrice.setText(Float.toString(e.getPrice()));
					tfRemainingTickets.setText(Integer.toString(e.getRemainingTickets()));
					tfSoldTickets.setText(Integer.toString(e.getSoldTickets()));
					tfTotalTickets.setText(Integer.toString(e.getTotalTickets()));
					break;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
