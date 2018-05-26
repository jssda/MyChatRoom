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
		
		//��ʼ������
		jClint = new JFrame("ChatHappy");
		jClint.setLayout(new GridBagLayout());
		
		//��ʼ���������
		jClintPan = new JPanel(new GridBagLayout());
		jClintPan.setSize(600, 600);
		
		jClint.add(jClintPan);
		jClint.setSize(600, 600);
		
		//ͨ��toolkit���߰�, ȡ����Ļ����λ��, ��������ʾ������
		int x = (int) ((tool.getScreenSize().getWidth() - 600) / 2);
		int y = (int) ((tool.getScreenSize().getHeight() - 600) / 2);
		
		jClint.setLocation(x, y);
		jClint.setVisible(true);
		
		jClint.addWindowListener(new WindowAdapter() {

			@Override
			//���ڹرռ�����
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
