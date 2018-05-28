package ChatRoom.javaTest7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class ClientFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Toolkit tool = Toolkit.getDefaultToolkit();
	Container conClit = null; 	//窗口容器
	
	JPanel jpBack = null; 	//聊天记录面板
	JPanel jpOnLine = null; 	//在线人员面板
	JPanel jpTalk = null; 	//聊天输入框与发送人员面板
	
	JTextArea txtBack = null; 	//聊天记录显示区域
	JScrollPane jsBack = null; 	//聊天记录滚动条面板
	
	JTextArea txtAction = null; 	//聊天输入区域
	JButton jbSend = null; 	//发送按钮
	JLabel jlTo = null;
	JComboBox<String> jcbPersons = null;
	
	
	public ClientFrame() {
		super("Happy chatting 0.0");
		conClit = this.getContentPane();
		init();
		
		//通过toolkit工具包, 取得屏幕中央位置, 将窗口显示在中央
		int x = (int) ((tool.getScreenSize().getWidth() - 800) / 2);
		int y = (int) ((tool.getScreenSize().getHeight() - 800) / 2);
		
		//窗口位置, 令其显示
		this.setSize(800, 800);
		this.setLocation(x, y);
		
		//窗口监听
		this.addWindowListener(new WindowAdapter() {

			@Override
			//窗口关闭监听器
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(1);
			}
			
		});
		
		jbSend.addActionListener(new jbSendListener());
		
		this.setVisible(true);
	}
	
	
	public void init() {
		
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
		txtBack.setText("刘浩是傻逼");
		txtBack.setLineWrap(true);
		jpBack.add(txtBack, BorderLayout.CENTER);
		
		//聊天记录滚动条
		jsBack = new JScrollPane(jpBack, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
									JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//初始化聊天发送窗口, 并加入输入框面板
		txtAction = new JTextArea(3, 40);
		txtAction.setText("我是刘浩");
		txtAction.setLineWrap(true);
		jpTalk.add(txtAction);
		
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
//		pack();
	}
	
	
	private class jbSendListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jbSend) {
				String s = txtAction.getText().trim();
				txtBack.setText(s);			}
		}
	}
	
	
	public static void main(String[] args) {
		new ClientFrame();
	}
}
