package ChatRoom.javaTest7;

import java.net.*;
import java.io.*;

public class ChatSever {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket(8888);
			Socket s = ss.accept();
System.out.println("a clint connection! ");
			BufferedInputStream buffInt = new BufferedInputStream(s.getInputStream());
			byte[] str = new byte[buffInt.available()];
			buffInt.read(str);
System.out.println(str.toString());
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
