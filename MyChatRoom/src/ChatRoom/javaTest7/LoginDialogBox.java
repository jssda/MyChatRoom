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
			"jdbc:mysql://www.lovelybaobao.cn:3306/chat_user?useSSL=false&encoding=utf8&serverTimezone=UTC";
	public static final String DBUSER = "jssd";
	public static final String DBPASS = "142536";

	private JFrame parentPane = null;
	
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
	private JDialog Init = null;
	private JButton jbLogin = null;	//声明按钮
	private JButton jbLogon = null;
	private JButton jbExit = null;
	
	public LoginDialogBox(JFrame jFrame) {
		
		this.parentPane = jFrame;
		
		//输入初始化
		jpInput = new JPanel();
		jpInput.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpInput.setBackground(Color.green);
		
		jlServerIP = new JLabel("Server IP:      ");
		jlUserName = new JLabel("Your Name:   ");
		jlUserPass = new JLabel("PASSWORD: ");
		
		txtSerIP = new JTextField("172.0.0.1", 12);
//		txtSerIP.setSize(250, 100);
		txtUserName = new JTextField(12);
		txtUserPass = new JTextField(12);
		
		Init = new JDialog(jFrame, "请登陆", true);
		Init.setLayout(new BorderLayout());
		Init.setSize(300, 170);
		
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

		Init.add(jpLogin, BorderLayout.SOUTH);
		Init.add(jpInput, BorderLayout.CENTER);
		
		//通过toolkit工具包, 取得屏幕中央位置, 将窗口显示在中央
		Toolkit tool = Toolkit.getDefaultToolkit();
		int x = (int) ((tool.getScreenSize().getWidth() - 300) / 2);
		int y = (int) ((tool.getScreenSize().getHeight() - 170) / 2);
		Init.setLocation(x, y);
		
		Init.setVisible(true);
		
	}
	
	private JDialog jdLogin = null;
	private JButton jbOK = null;
	private JTextField jtName = null;
	private JTextField jtNickName = null;
	private JTextField jtSex = null;
	private JTextField jtAge = null;
	private JTextField jtEmail = null;
	private JTextField jtPassWord = null;
	private JTextField jtConfirm = null;
	
	//登陆模块
	private void login() {
		
		Init.setVisible(false);

		//注册对话框
		jdLogin = new JDialog(this.parentPane, "欢迎注册😘", true);
		jdLogin.setSize(280, 410);
		jdLogin.setBackground(Color.gray);
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		jdLogin.setLayout(layout);
		
		JLabel jlName = new JLabel("name:  ");
		JLabel jlNickName = new JLabel("nickName:  ");
		JLabel jlSex = new JLabel("sex:  ");
		JLabel jlAge = new JLabel("age:  ");
		JLabel jlEmail = new JLabel("email:  ");
		JLabel jlPassWord = new JLabel("PassWord:  ");
		JLabel jlConfirm = new JLabel("Confirm:  ");
		
		jtName = new JTextField(20);
		jtNickName = new JTextField(20);
		jtSex = new JTextField(20);
		jtAge = new JTextField(20);
		jtEmail = new JTextField(20);
		jtPassWord = new JTextField(20);
		jtConfirm = new JTextField(20);
		
		jbOK = new JButton("OK");
		jbOK.addActionListener(new jbuttonListener());
		
		jdLogin.add(jlName);
		jdLogin.add(jtName);
		jdLogin.add(jlNickName);
		jdLogin.add(jtNickName);
		jdLogin.add(jlSex);
		jdLogin.add(jtSex);
		jdLogin.add(jlAge);
		jdLogin.add(jtAge);
		jdLogin.add(jlEmail);
		jdLogin.add(jtEmail);
		jdLogin.add(jlPassWord);
		jdLogin.add(jtPassWord);
		jdLogin.add(jlConfirm);
		jdLogin.add(jtConfirm);
		jdLogin.add(jbOK);
		
		Toolkit tool = Toolkit.getDefaultToolkit();
		int x = (int) ((tool.getScreenSize().getWidth() - 400) / 2);
		int y = (int) ((tool.getScreenSize().getHeight() - 800) / 2);
		jdLogin.setLocation(x, y);

		jdLogin.setVisible(true);
		
	}
	
	private void connectionDB() {
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("驱动加载失败");
		}
		
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败!");
			e.printStackTrace();
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
				System.out.println("程序退出");
				System.exit(0);
			} else if(e.getSource() == jbOK) {
				clickOK();
			}
		}
		
		private void clickOK() {
			//连接数据库
			connectionDB();
			
			try {
				String name = jtName.getText();
				String nickName = jtNickName.getText();
				String password = jtPassWord.getText();
				String sex = jtSex.getText();
				int age = 0;
			
				try {
					age = Integer.parseInt(jtAge.getText());
				} catch (Exception e){
					JOptionPane.showMessageDialog(jdLogin, "年龄格式不对, 请输入一个整数", "Error", JOptionPane.ERROR_MESSAGE);
					jtAge.setText("");
					e.printStackTrace();
					return;
				}
				String email = null;
				email = jtEmail.getText();
				if( !email.matches("\\w+@\\w+.\\w*")) {
					JOptionPane.showMessageDialog(jdLogin, "邮箱格式不对", "Error", JOptionPane.ERROR_MESSAGE);
					jtEmail.setText("");
					return;
				}
				String passWord = jtPassWord.getText();
				String confirm = jtConfirm.getText();
				if( !passWord.equals(confirm)) {
					jtConfirm.setText("");
					return;
				}
				
				if(name == null) {
					JOptionPane.showMessageDialog(jdLogin, "Please input the userName!", 
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if(password == null) {
					JOptionPane.showMessageDialog(jdLogin, "Please input the passWord!", 
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if(nickName == null){
					JOptionPane.showMessageDialog(jdLogin, "Please input the nickName!", 
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if(sex == null) {
					JOptionPane.showMessageDialog(jdLogin, "Please input the sex!", 
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String sql = "INSERT (name, nickName, sex, age, email, passWord) values + (?, ?, ?, ? , ? , ?)" ;
					sta = conn.prepareStatement(sql);
					sta.execute();
//					sta.setString(1, name);
//					sta.setString(2, nickName);
//					sta.setString(3, sex);
//					sta.setInt(4, age);
//					sta.setString(5, email);
//					sta.setString(6, passWord);
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
				System.out.println("数据库操作失败");
				System.exit(0);
			} finally {
				try {
					if(sta != null)
						sta.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}

		}
	}
	
}
