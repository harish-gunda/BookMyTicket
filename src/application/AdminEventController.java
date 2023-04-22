package application;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

public class AdminEventController implements Initializable {
	
	private Loader program;
	private int selectedRow = -1;
	@FXML private TableView<Event> eventTable;
	private Event eve;
	
	@FXML private TableColumn<Event, Integer> idColumn;
    @FXML private TableColumn<Event, String> artistColumn;
    @FXML private TableColumn<Event, Date> dateColumn;
    @FXML private TableColumn<Event, String> venueColumn;
    @FXML private TableColumn<Event, Integer> totalTicketsColumn;
    @FXML private TableColumn<Event, Integer> remainingTicketsColumn;
    @FXML private Button deleteBtn;
    @FXML private TextField wordTxtField;
    @FXML private TextArea meaningTxtField;
	
	public AdminEventController() throws IOException {
		this.program = new Loader();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {

        // Fills values in combo box with 3 levels
        
		// Fills in the values with all the words belonging to the level selected in the combo box
		populateTable();
		
		// Makes Cells editable
//		wordTable.setEditable(true);
//        wordColumn.setCellFactory(TextFieldTableCell.<Word, String>forTableColumn(new DefaultStringConverter()));
//        meaningColumn.setCellFactory(TextFieldTableCell.<Word, String>forTableColumn(new DefaultStringConverter()));
	}
	
	public void populateTable() {
		ObservableList<Event> events = FXCollections.observableArrayList();
        for (Event e: program.getEvents()) {
        	events.add(e);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("artist"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Event, Date>("date"));
		venueColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("venue"));
		totalTicketsColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("totalTickets"));
		remainingTicketsColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("remainingTickets"));
		eventTable.getItems().clear();
		eventTable.setItems(events);
	}	
	
	public void updateEvents() {
		try {
			program.writeEvents();
		} catch (Exception e) {
			System.out.println("Error writing to json in AdminWord");
			e.printStackTrace();
		}
	}
	
	
	public void addEventClick(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EventAdd.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show(); 
	}
	
	public void viewEventClick(ActionEvent event) throws IOException {
		if (selectedRow!=-1) {
			EventViewController.eventId = selectedRow;
			eve = eventTable.getSelectionModel().getSelectedItem();
			EventViewController.eventId = eve.getId();
			Parent root = FXMLLoader.load(getClass().getResource("EventView.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show(); 
		}
		
	}
	
	public void selectRow() {
		selectedRow = eventTable.getSelectionModel().getSelectedIndex();
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
