package ChatRoom.javaTest7;

import java.net.*;
import java.io.*;
import java.util.*;

public class ChatSever {

	ServerSocket ss = null; // 服务端套接字
	boolean status = false; // 服务器初始化操作循环标志
	List<Clint> clints = new ArrayList<Clint>(); 	//服务端集合
	
	public static void main(String[] args) {
		new ChatSever().start();
	}

	
	public void start() {
		
		try {
			ss = new ServerSocket(8888);
			status = true;
		} catch (BindException e) {
			System.out.println("Server is using now............");
			System.out.println("Please close the associated program and re-run the server");
			System.exit(0);
		} catch (Exception e) {
			try {
				ss.close();
				System.out.println("server is closed");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			while (status) {
				Socket s = ss.accept(); // 客户端连接
				Clint c = new Clint(s);
				clints.add(c);
				new Thread(c).start();
			}
		} catch (Exception e) {
				System.out.println("Clint disconnected!");
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Sever is closed");
			}
		}
	}
	
	
	private class Clint implements Runnable {
		
		private BufferedReader buffInt = null; // 取得客户端输入流
		private PrintStream out = null; //客户端输出流
		private Socket s = null; // 客户端套接字
		private String str = null; // 接受客户端发来的消息
		private boolean bConnected = false;
		
		public Clint(Socket s) {
			this.s = s;
			System.out.println("a clint connection! "); // 已经连接注释
			bConnected = true;
			try {
				buffInt = new BufferedReader(new InputStreamReader( // 初始化客户端输入流
						s.getInputStream()));
				out = new PrintStream(s.getOutputStream()); 	//客户端输出流转化为打印流
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		private void send(String str) {
			out.println(str);
		}
		
		
		public void run() {
			System.out.println("bConnected = " + bConnected);
			while (bConnected) { // 连接成功操作循环
				try {
					str = buffInt.readLine();
					System.out.println("server==" + str);
					
					if (str != null) {
						//发送到每个客户端
						for(int i = 0; i < clints.size(); i ++) {
							clints.get(i).send(str);
						}
						System.out.println(str); //将客户端发送信息打印到客户端控制台
					} else {
						bConnected = false;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
				buffInt.close();
				System.out.println("输入流关闭");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				s.close();
				System.out.println("客户端连接关闭");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
