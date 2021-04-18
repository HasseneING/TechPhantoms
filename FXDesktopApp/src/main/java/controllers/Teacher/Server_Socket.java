package controllers.Teacher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Server_Socket extends Observable{
	//public static SimpleListProperty<String> ss = new SimpleListProperty<>();
	public Map<Socket,String> Sockets  = new HashMap<>();
	//public static Server_Socket Server;
	final static int PORT  = 6969;
	public static void main(String[] args) {
		new Server_Socket(PORT);
	}
	
	public Server_Socket(int PORT) {
		ServerSocket serverSocket = null;
	try {
			 serverSocket = new ServerSocket(PORT);
			System.out.println("SERVER :: Server is Listning in "+PORT+"...");
			while(true) {
				Socket socket = serverSocket.accept();
				setChanged();
				notifyObservers("NewUSer");
				Server_Thread ST = new Server_Thread(socket,this);
				ST.start();
			}
			
		} catch (IOException e) {
			System.out.println("SERVER :: Server Faild To connect...");
			e.printStackTrace();
		}finally {
			try {
				if(serverSocket !=null) serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//Synchronized just in case if there's any concurrence over this resource (Sockets list)
	public synchronized void addSocket (Socket socket,String UserNam) {
		Sockets.put(socket,UserNam);
	
	}
	
}
