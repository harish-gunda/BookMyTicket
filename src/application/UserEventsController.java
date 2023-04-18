package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class UserEventsController implements Initializable {

	private Program program;
	private int selectedRow = -1;
	@FXML
	private TableView<Event> eventTable;

	@FXML
	private TableColumn<Event, Integer> idColumn;
	@FXML
	private TableColumn<Event, String> artistColumn;
	@FXML
	private TableColumn<Event, Date> dateColumn;
	@FXML
	private TableColumn<Event, String> venueColumn;
	@FXML
	private TableColumn<Event, Integer> totalTicketsColumn;
	@FXML
	private TableColumn<Event, Integer> remainingTicketsColumn;
	@FXML
	private Button deleteBtn;
	@FXML
	private TextField wordTxtField;
	@FXML
	private TextArea meaningTxtField;

	@FXML
	private TextField searchEventField;

	public UserEventsController() throws IOException {
		this.program = new Program();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		populateTable();
	}

	public void populateTable() {
		ObservableList<Event> events = FXCollections.observableArrayList();
		for (Event e : program.getEvents()) {
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

	public void populateTable(String venue) {
		ObservableList<Event> events = FXCollections.observableArrayList();
		for (Event e : program.getEvents()) {
			if (venue != null && venue != "") {
				if (e.getVenue().equalsIgnoreCase(venue)) {
					events.add(e);
				}
			} else {
				events.add(e);
			}
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

	// Event Listener on TableView[#eventTable].onMouseClicked
	@FXML
	public void selectRow(MouseEvent event) {
		selectedRow = eventTable.getSelectionModel().getSelectedIndex();
	}

	// Event Listener on Button.onAction
	@FXML
	public void searchEventClick(ActionEvent event) {
		// TODO Autogenerated
		String searchKey = searchEventField.getText();
		if (searchKey != null && searchKey != "")
			populateTable(searchKey);
		else {
			populateTable();
		}

	}

	// Event Listener on Button.onAction
	@FXML
	public void back(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button.onAction
	@FXML
	public void logoout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button.onAction
	@FXML
	public void viewEventClick(ActionEvent event) {
		// TODO Autogenerated
	}
}