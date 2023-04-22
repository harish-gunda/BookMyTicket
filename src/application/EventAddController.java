package application;

import java.beans.EventSetDescriptor;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EventAddController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

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
    private TextField tfVenue;
    
    @FXML
    void btnAddClick(ActionEvent event) throws IOException {
    	Event e = new Event();
    	e.setArtist(tfArtist.getText());
		try {
			e.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(tfDate.getText()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.setVenue(tfVenue.getText());
		e.setDescription(tfDescription.getText());
		e.setId(Integer.parseInt(tfId.getText()));
		e.setPrice(Float.parseFloat(tfPrice.getText()));
		e.setRemainingTickets(Integer.parseInt(tfRemainingTickets.getText()));
		e.setSoldTickets(Integer.parseInt(tfSoldTickets.getText()));
		e.setRemainingTickets(Integer.parseInt(tfRemainingTickets.getText()));
		e.setTotalTickets(Integer.parseInt(tfTotalTickets.getText()));
		List<Event> events = new ArrayList<>();
		Loader program = new Loader();
		events = program.getEvents();
		events.add(e);
		program.writeEvents();
		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
		a.setTitle("Modified");
		a.setHeaderText("Success.");
		a.setContentText("Successfully added the event");
		a.showAndWait();
    }

    @FXML
    void btnBackClick(ActionEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("AdminEvents.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

}
