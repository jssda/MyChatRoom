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
	Container conClit = null; 	//��������
	
	JPanel jpBack = null; 	//�����¼���
	JPanel jpOnLine = null; 	//������Ա���
	JPanel jpTalk = null; 	//����������뷢����Ա���
	
	JTextArea txtBack = null; 	//�����¼��ʾ����
	JScrollPane jsBack = null; 	//�����¼���������
	
	JTextArea txtAction = null; 	//������������
	JButton jbSend = null; 	//���Ͱ�ť
	JLabel jlTo = null;
	JComboBox<String> jcbPersons = null;
	
	public ClientFrame() {
		super("Happy chatting 0.0");
		conClit = this.getContentPane();
		init();
		
		//ͨ��toolkit���߰�, ȡ����Ļ����λ��, ��������ʾ������
		int x = (int) ((tool.getScreenSize().getWidth() - 800) / 2);
		int y = (int) ((tool.getScreenSize().getHeight() - 800) / 2);
		
		//����λ��, ������ʾ
		this.setSize(800, 800);
		this.setLocation(x, y);
		this.setVisible(true);
	}
	
	public void init() {
		
		//��ʼ�����ڲ���
		BorderLayout layout = new BorderLayout();
		conClit.setLayout(layout);
		
		//�����¼���
		jpBack = new JPanel(new BorderLayout());
		jpBack.setBackground(Color.BLUE);
		
		//�����¼������
		jsBack = new JScrollPane();
		jsBack.add(jpBack);
		
		//������Ա���
		jpOnLine = new JPanel(new BorderLayout());
		jpOnLine.setBackground(Color.RED);
		
		//��ʼ������������뷢����Ա���
		jpTalk = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 12));
		jpTalk.setBackground(Color.yellow);
		
		//��ʼ�������¼����, �����������¼���
		txtBack = new JTextArea(40, 30);
		jpBack.add(txtBack, BorderLayout.CENTER);
		
		//��ʼ�����췢�ʹ���, ��������������
		txtAction = new JTextArea(3, 40);
		txtAction.setText("��������");
		jpTalk.add(txtAction);
		
		//��ʼ�����Ͱ�ť, ������������Ա���
		jbSend = new JButton("send"); 	
		jbSend.setSize(50, 50);
		jpTalk.add(jbSend); 	//���뷢�͵�������Ա���	
		
		jlTo = new JLabel("TO");
		jpTalk.add(jlTo);
		
		jcbPersons = new JComboBox<String>();
		jcbPersons.addItem("ALL");
		jpTalk.add(jcbPersons);
		
		//���������뵽������
		conClit.add(jsBack, BorderLayout.CENTER);
		conClit.add(jpOnLine, BorderLayout.EAST);
		conClit.add(jpTalk, BorderLayout.SOUTH);
		
		//���ڼ���
		this.addWindowListener(new WindowAdapter() {

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
		new ClientFrame();
	}
}
