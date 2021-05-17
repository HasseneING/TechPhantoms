package controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map.Entry;

public class Server_Thread extends Thread{
	 String UserName;
	 Socket socket;
	 Server_Socket Server_Instance;
	private DataInputStream dis;
	private DataOutputStream dos;
	//a variable Used to control the While Loop if i need to add some Control to it 
	private boolean ShouldRun = true;
	 
	 public Server_Thread(Socket socket,Server_Socket server_instance) throws IOException {
		 this.socket = socket;
		 this.Server_Instance = server_instance;
		 dis = new DataInputStream(socket.getInputStream());
		 dos = new DataOutputStream(socket.getOutputStream());
		 UserName = this.getName();
		
	 }
	 
	private void sendMessageToAll(String message) throws IOException {
		//a copy of the current Users
		Iterator<Entry<Socket, String>> mapIterator =  Server_Instance.Sockets.entrySet().iterator();
		while (mapIterator.hasNext()) {
			Entry<Socket, String> entry = mapIterator.next();
			new DataOutputStream(entry.getKey().getOutputStream()).writeUTF(UserName +" Said : "+ message);;
			
		}
	}
	
	public void run() {
		try {
			//Reads the first data that the User sent to the Server which is the "USERNAME"
			UserName = dis.readUTF();
			System.out.println("SERVER :: "+UserName+" is Connected to the Server");
			Server_Instance.addSocket(socket,UserName);
			System.out.println("SERVER :: "+Server_Instance.Sockets.values()+" Users in the server");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		while(ShouldRun) {
			System.out.println("SERVER :: is closed : "+socket.isClosed());

			try {
				 if(socket.isClosed()) 
				 {
					ShouldRun = false; 
				 	break;
				 }
				while(dis.available()==0) {
				
				}
				sendMessageToAll(dis.readUTF());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.Server_Instance.Sockets.remove(this.socket);
		System.out.println("SERVER :: "+UserName+" is disconnected from the Server");
		System.out.println("SERVER :: "+this.Server_Instance.Sockets.size()+"USers left");


	}
	
}
