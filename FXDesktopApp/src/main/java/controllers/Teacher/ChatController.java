package controllers.Teacher;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ChatController implements Initializable,Observer{
	
	@FXML Button btnConnect;
	@FXML Button btnSend;
	@FXML TextArea txtChatArea;
	@FXML TextField txtMessage;
	@FXML TextField txtPort;
	@FXML TextField txtServer;
	@FXML TextField txtUsername;
	@FXML ListView<String> listConnectedUsers;
	
	StringBuilder strbuilder ;
	DataInputStream dis= null;
	DataOutputStream dos = null;
	String Message ="";
	Socket socket = null;
	String Username = "";

    //public static SimpleListProperty<String> listProperty = new SimpleListProperty<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//listProperty.set(FXCollections.observableArrayList());
		//listProperty.set(FXCollections.observableArrayList());
		//listConnectedUsers.itemsProperty().bind(Server_Socket.ss);
		//listConnectedUsers.getItems().add("HIII :(");
	}
	
	@FXML 
	public void connectClick() {
		Username = txtUsername.getText();
		try {
			int port = Integer.parseInt(txtPort.getText());
			String server = txtServer.getText().isEmpty()?"localhost":txtServer.getText().trim();
			txtServer.setText(server);
			ConnectToServer(port,server);
		} catch (NumberFormatException e) {
			System.out.println("PORT>> Only numbers Accepted....");
		}
	}
	
	@FXML 
	public void sendClick() {
		try {
			
			sendMessage(txtMessage.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String Message) throws IOException {
		dos.writeUTF(Message);
		dos.flush();
	}
	
	private void ConnectToServer(int Port,String server) {
		try {
		
			socket = new Socket(server,Port);
			System.out.println("CLIENT :: <"+Username+">:: Connected Successfully");
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(Username);
			Client_Thread CT = new Client_Thread(dis,this);
			CT.setDaemon(true);
			CT.start();
			} catch (IOException e) {
			System.out.println("CLIENT :: Failed To connect");
			e.printStackTrace();

		}

	}
	
	public synchronized void addMessageToScreen(String Message){	
		txtChatArea.appendText(Message+'\n');
	}
	
	public synchronized void addUsersConnected(List<String> users){	
		
		for (String user : users) {
			
			listConnectedUsers.getItems().add(user);
		}
		
		//listProperty.set(FXCollections.observableArrayList(users));
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("hello"+arg);
	}


}
