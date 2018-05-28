package ChatRoom.javaTest7;

import java.net.*;

public class ChatSever {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket(8888);
			Socket s = ss.accept();
System.out.println("a clint connection! ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
