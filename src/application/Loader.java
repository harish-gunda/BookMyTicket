package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Loader {
	private List<User> users;
	private List<Event> events;
	
	public Loader() throws IOException {
		this.users = new ArrayList<>();
		this.events = new ArrayList<>();
		readUsers();
		readEvents();
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public void readUsers() throws IOException {
		String filepath = "data/users.json";
        Reader reader = Files.newBufferedReader(Paths.get(filepath));
        this.users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
        reader.close();
    }
	
	public void writeUsers() throws IOException {
		String filepath = "data/users.json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fw = new FileWriter(filepath);
        gson.toJson(users, fw);
        fw.close();
    }
	
	public void readEvents() throws IOException {
		String filepath = "data/events.json";
        Reader reader = Files.newBufferedReader(Paths.get(filepath));
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        this.events = gson.fromJson(reader, new TypeToken<List<Event>>() {}.getType());
        reader.close();
    }
	
	public void writeEvents() throws IOException {
		String filepath = "data/events.json";
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").setPrettyPrinting().create();
        FileWriter fw = new FileWriter(filepath);
        gson.toJson(events, fw);
        fw.close();
    }
	
}

