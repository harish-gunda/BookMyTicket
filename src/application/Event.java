package application;

import java.util.Date;

import javax.print.event.PrintJobAttributeEvent;

public class Event {
	private static int count;
	private String artist;
	private String description;
	private Date date;
	private int totalTickets;
	private int remainingTickets;
	private int soldTickets;
	private float price;
	private int id;
	private String venue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTotalTickets() {
		return totalTickets;
	}
	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}
	public int getRemainingTickets() {
		return remainingTickets;
	}
	public void setRemainingTickets(int remainingTickets) {
		this.remainingTickets = remainingTickets;
	}
	public int getSoldTickets() {
		return soldTickets;
	}
	public void setSoldTickets(int soldTickets) {
		this.soldTickets = soldTickets;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
}
