package application;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class User extends Person {
	
	private List<Event> events;
	private List<Integer> bookings;
	private List<Booking> bookingList; 
	
	public User(String username, String password, String firstname, String lastname) throws IOException {
		super(username, password, firstname, lastname);
		events = new ArrayList<>();
		bookings = new ArrayList();
		Loader p = new Loader();
	}
	
	public List<Integer> getBookings() {
		return bookings;
	}

	public void setBookings(List<Integer> bookings) {
		this.bookings = bookings;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}
