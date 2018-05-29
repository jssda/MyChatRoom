package ChatRoom.javaTest7;

import java.net.*;
import java.io.*;

public class ChatSever {

	public static void main(String[] args) {
		ServerSocket ss = null; // 服务端套接字
		BufferedReader buffInt = null; // 取得客户端输入流
		Socket s = null; // 客户端套接字
		String str = null; // 接受客户端发来的消息

		boolean status = false; // 服务器初始化操作循环标志
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

		while (status) {
			boolean bConnected = false; // 客户端连接成功操作循环标志
			try {
				s = ss.accept(); // 客户端连接
				System.out.println("a clint connection! "); // 已经连接注释
				buffInt = new BufferedReader(new InputStreamReader( // 初始化客户端输入流
						s.getInputStream()));
				bConnected = true;
				while (bConnected) { // 连接成功操作循环
					str = buffInt.readLine();
					if (str != null) {
						System.out.println(str); //将客户端发送信息打印到客户端控制台
					} else {
						bConnected = false;
					}
				}
			} catch (Exception e) {
				System.out.println("Clint closed");
			} finally {
				try {
					if (buffInt != null)
						buffInt.close();
					if (s != null)
						s.close();
					System.out.println("客户端关闭"); // 客户端关闭操作注释
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
