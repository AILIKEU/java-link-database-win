
import javax.swing.JFrame;

public class MainFrame{
	JFrame f = new JFrame();
	MyPanel p = new MyPanel();
	public MainFrame(){
		f.setTitle("java数据库大作业");
		f.setBounds(100,100,800,600);
		//点击右上角即关闭
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(p);
		//设置窗口大小不可变
		f.setResizable(false);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new MainFrame();

	}

}