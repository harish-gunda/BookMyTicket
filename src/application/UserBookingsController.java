package application;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

public class UserBookingsController implements Initializable {
	private int userid;
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
	private Button deleteBtn;
	@FXML
	private TextField wordTxtField;
	@FXML
	private TextArea meaningTxtField;

	public UserBookingsController() throws IOException {
		this.program = new Program();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			this.program = new Program();
			populateTable();
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

	public void populateTable() {
		ObservableList<Event> events = FXCollections.observableArrayList();
		System.out.println("UserBookingsController " + UserController.userid);
		User user = program.getUsers().get(UserController.userid);

		for (Event e : program.getEvents()) {
			if (user.getBookings().contains(e.getId()))
				events.add(e);
		}
		HashMap<Integer, Integer> hashMap = new HashMap();
		for(int i : user.getBookings()) {
			hashMap.put(i, hashMap.getOrDefault(i, 0)+1);
		}
		System.out.println(hashMap);
		
		
		
		
		System.out.println(user.getBookings());
		idColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
		artistColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("artist"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Event, Date>("date"));
		venueColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("venue"));
		totalTicketsColumn.setCellValueFactory(data -> {
			Event evnt = data.getValue();
//			HashMap<Integer, Integer> hashMap = new HashMap();
//			for (Event event : program.getEvents()) {
//				hashMap.put(event.getId(), 0);
//			}
//			for (int eId : program.getUsers().get(userid).getBookings()) {
//				hashMap.put(eId, hashMap.getOrDefault(eId, 0) + 1);
//			}
			System.out.println(hashMap);

			return new SimpleIntegerProperty(hashMap.get(evnt.getId())).asObject();
		});
//		totalTicketsColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("totalTickets"));
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

	public void selectRow() {
		selectedRow = eventTable.getSelectionModel().getSelectedIndex();
	}

	public void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
		Parent root = loader.load();
		UserController controller = loader.getController();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		controller.setUserId(userid);
		stage.show();
	}

	public void logoout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
