package application;

import java.io.IOException;

import javafx.event.ActionEvent;

public interface SceneControllerInterface {
	
	public void login(ActionEvent event) throws IOException;
	
	public void userLogin(ActionEvent event) throws IOException;
	
	public void adminLogin(ActionEvent event) throws IOException;
	
	public void signUp(ActionEvent event) throws IOException;
	
}
