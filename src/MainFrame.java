
import javax.swing.JFrame;

public class MainFrame{
	JFrame f = new JFrame();
	MyPanel p = new MyPanel();
	public MainFrame(){
		f.setTitle("java���ݿ����ҵ");
		f.setBounds(100,100,800,600);
		//������ϽǼ��ر�
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		//���ô��ڴ�С���ɱ�
		f.setResizable(false);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new MainFrame();

	}

}