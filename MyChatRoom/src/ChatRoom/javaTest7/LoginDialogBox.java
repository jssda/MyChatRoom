package ChatRoom.javaTest7;

import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginDialogBox {
	
	//数据库连接初始化
	public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DBURL = 
			"jdbc:mysql://www.lovelybaobao.cn:3306/mysql?useSSL=false&encoding=utf8&serverTimezone=UTC";
	public static final String DBUSER = "jssd";
	public static final String DBPASS = "142536";

	private Connection conn = null; 		//数据库连接对象
	private PreparedStatement sta = null; 			//数据库操作对象
	
	
	private JPanel jpInput = null;		//输入面板
	private JPanel jpLogin = null;		//登陆注册面板
	private JLabel jlServerIP = null; 	//声明输入	
	private JLabel jlUserName = null;	
	private JLabel jlUserPass = null;
	private JTextField txtSerIP = null;
	private JTextField txtUserName = null;
	private JTextField txtUserPass = null;
	private JDialog login = null;
	private JButton jbLogin = null;	//声明按钮
	private JButton jbLogon = null;
	private JButton jbExit = null;
	
	public LoginDialogBox(JFrame jFrame) {
		
		//输入初始化
		jpInput = new JPanel();
		jpInput.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpInput.setBackground(Color.green);
		
		jlServerIP = new JLabel("Server IP:      ");
		jlUserName = new JLabel("Your Name:   ");
		jlUserPass = new JLabel("PASSWORD: ");
		
		txtSerIP = new JTextField("172.0.0.1", 12);
		txtSerIP.setSize(250, 100);
		txtUserName = new JTextField(12);
		txtUserPass = new JTextField(12);
		
		login = new JDialog(jFrame, "请登陆", true);
		login.setLayout(new BorderLayout());
		login.setSize(300, 170);
		
		//标签加入
		jpInput.add(jlServerIP);
		jpInput.add(txtSerIP);
		jpInput.add(jlUserName);
		jpInput.add(txtUserName);
		jpInput.add(jlUserPass);
		jpInput.add(txtUserPass);
	
		//登陆按钮初始化
		jbLogin = new JButton("注册");
		jbLogin.addActionListener(new jbuttonListener());
		jbLogon = new JButton("登陆");
		jbLogon.addActionListener(new jbuttonListener());
		jbExit = new JButton("退出");
		jbExit.addActionListener(new jbuttonListener());
		
		jpLogin = new JPanel();
		jpLogin.setBackground(Color.green);
		
		//登陆按钮加入
		jpLogin.add(jbLogin);
		jpLogin.add(jbLogon);
		jpLogin.add(jbExit);

		login.add(jpLogin, BorderLayout.SOUTH);
		login.add(jpInput, BorderLayout.CENTER);
		
		//通过toolkit工具包, 取得屏幕中央位置, 将窗口显示在中央
		Toolkit tool = Toolkit.getDefaultToolkit();
		int x = (int) ((tool.getScreenSize().getWidth() - 300) / 2);
		int y = (int) ((tool.getScreenSize().getHeight() - 170) / 2);
		login.setLocation(x, y);
		
		login.setVisible(true);
		
	}
	
	//登陆模块
	private void login() {
		
		login.setVisible(false);
		
		JDialog jdLogin = new JDialog();
		
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("驱动加载失败");
		}
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			System.out.println("数据库连接成功");
			
			String name = null;
			String password = null;
//			String 
//			sta = conn.prepareStatement(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
			System.exit(0);
		}
	}
	
	//按钮监听
	class jbuttonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jbLogin) {
				System.out.println("注册");
				login();
			} else if(e.getSource() == jbLogon) {
				System.out.println("登陆");
			} else if(e.getSource() == jbExit) {
//				System.out.println("exit");              
				System.exit(0);
			}
		}
	}
	
}
