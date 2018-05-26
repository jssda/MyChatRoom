package ChatRoom.javaTest7;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class ClientFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Toolkit tool = Toolkit.getDefaultToolkit();
	JFrame jClint = null;
	JPanel jClintPan = null;
	
	public void init() {
		
		//初始化窗口
		jClint = new JFrame("ChatHappy");
		jClint.setLayout(new GridBagLayout());
		
		//初始化窗口面板
		jClintPan = new JPanel(new GridBagLayout());
		jClintPan.setSize(600, 600);
		
		jClint.add(jClintPan);
		jClint.setSize(600, 600);
		
		//通过toolkit工具包, 取得屏幕中央位置, 将窗口显示在中央
		int x = (int) ((tool.getScreenSize().getWidth() - 600) / 2);
		int y = (int) ((tool.getScreenSize().getHeight() - 600) / 2);
		
		jClint.setLocation(x, y);
		jClint.setVisible(true);
		
		jClint.addWindowListener(new WindowAdapter() {

			@Override
			//窗口关闭监听器
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(1);
			}
			
		});
	}
	
	public static void main(String[] args) {
		new ClientFrame().init();;
	}
}
