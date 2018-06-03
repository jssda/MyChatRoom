package ChatRoom.javaTest7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.io.*;

import javax.swing.*;

public class ClientFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JButton jbSend = null; 	//发送按钮
	private Socket s = null; 	//客户端套接字
	private Container conClit = null; 	//窗口容器
	
	private JPanel jpBack = null; 	//聊天记录面板
	private JPanel jpOnLine = null; 	//在线人员面板
	private JPanel jpTalk = null; 	//聊天输入框与发送人员面板
	
	private JTextArea txtBack = null; 	//聊天记录显示区域
	
	private JTextArea txtAction = null; 	//聊天输入区域
	private JLabel jlTo = null;
	private JComboBox<String> jcbPersons = null;
	private PrintStream out = null; 	//客户端输出流
	private BufferedReader buffRead = null;	//客户端输入流
	private boolean bConnected = false; 	//是否已经连接
	
	//初始化, 开始显示窗口
	public ClientFrame() {
		super("Happy chatting 0.0");
		Toolkit tool = Toolkit.getDefaultToolkit();
		conClit = this.getContentPane();
		
		//初始化窗口布局
		BorderLayout layout = new BorderLayout();
		conClit.setLayout(layout);
		
		//聊天记录面板
		jpBack = new JPanel(new BorderLayout());
		jpBack.setBackground(Color.BLUE);
		
		//在线人员面板
		jpOnLine = new JPanel(new BorderLayout());
		jpOnLine.setBackground(Color.RED);
		
		//初始化聊天输入框与发送人员面板
		jpTalk = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 12));
		jpTalk.setBackground(Color.yellow);
		
		//初始化聊天记录区域, 并加入聊天记录面板
		txtBack = new JTextArea(40, 30);
		txtBack.setText("");
		txtBack.setLineWrap(true);
		txtBack.setEditable(false);
		jpBack.add(txtBack, BorderLayout.CENTER);
		
		//聊天记录滚动条
		JScrollPane jsBack = null; 	//聊天记录滚动条面板
		jsBack = new JScrollPane(jpBack, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
									JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//初始化聊天发送窗口, 并加入输入框面板
		txtAction = new JTextArea(3, 40);
		txtAction.setText("我是刘浩");
		txtAction.setLineWrap(true);
		JScrollPane jsTxtAction = new JScrollPane(txtAction, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jpTalk.add(jsTxtAction);
		
		//初始化发送按钮, 并加入在线人员面板
		jbSend = new JButton("send"); 	
		jbSend.setSize(50, 50);
		jpTalk.add(jbSend); 	//加入发送到窗口人员面板	
		
		jlTo = new JLabel("TO");
		jpTalk.add(jlTo);
		
		jcbPersons = new JComboBox<String>();
		jcbPersons.addItem("ALL");
		jpTalk.add(jcbPersons);
		
		//三个面板加入到窗体中
		conClit.add(jsBack, BorderLayout.CENTER);
		conClit.add(jpOnLine, BorderLayout.EAST);
		conClit.add(jpTalk, BorderLayout.SOUTH);
		pack();
		
		//通过toolkit工具包, 取得屏幕中央位置, 将窗口显示在中央
		int x = (int) ((tool.getScreenSize().getWidth() - 800) / 2);
		int y = (int) ((tool.getScreenSize().getHeight() - 800) / 2);
		
		//窗口位置, 令其显示
		this.setSize(800, 800);
		this.setLocation(x, y);
		
		//窗口监听
		this.addWindowListener(new WindowAdapter() {
			//窗口关闭监听器
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				disConnected();
				System.exit(1);
			}
		});
		
		
		
		jbSend.addActionListener(new jbSendListener());
		
		LoginDialogBox.connectionDB();
		this.setVisible(true);
		
		new LoginDialogBox(this);
		
//		connected();
		new Thread(new Recive()).start();
	}
	
	public void connected(String url) {
		try {
			s = new Socket(url, 8888);
			bConnected = true;
			out = new PrintStream(s.getOutputStream()); 	//得到客户端输出流
			buffRead = new BufferedReader(new InputStreamReader( 	//得到客户端输入流
					s.getInputStream()));
			System.out.println("connected!");
		} catch (UnknownHostException e) {
			System.out.println("服务器连接失败, 请检查服务器IP");
			System.out.println("客户端退出");
			disConnected();
			System.exit(0);
		} catch (ConnectException e1) {
			System.out.println("连接错误, 请检查服务器IP");
			System.out.println("客户端退出");
			disConnected();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disConnected() {
		try {
			if(out != null)
				out.close();
			if(s != null)
				s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class jbSendListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jbSend) {
				String str = txtAction.getText().trim();
				txtAction.setText("");
				
				out.println(str);
				out.flush();
			}
		}
	}
	
	private class Recive implements Runnable {
		
		private String str = null;

		public void run() {

			try {
				
				while(bConnected) {
					str = buffRead.readLine();
					if(str != null) {
						txtBack.append(str + "\n");
					} else {
						bConnected = false;
					}
				}
			} catch (IOException e) {
				System.out.println("this client is closed");
			} finally {
				try {
					buffRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new ClientFrame();
	}
}
