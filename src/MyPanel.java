import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DB.ConnectAccountDB;
import DB.ConnectStudentDB;

public class MyPanel extends JPanel implements ActionListener {
	JTextField jtUserIn, jtPasswdIn; // 登录界面文本框
	JTextField jtUserUp, jtPasswdUp, jtVerifyUp; // 注册界面文本框
	JTextField jtDelNo; // 数据库界面删除按钮
	JTextField jtUpdateNo, jtUpdateName, jtUpdateSex, jtUpdateProfess;
	JTextField jtAddNo, jtAddName, jtAddSex, jtAddProfess;
	JTextArea jaShowDb; // 用于显示数据库的内容
	// database_show页面组件
	JTextField jtNoSearch;
	JTextField jtNameSearch, jtSexSearch, jtProfessSearch;
	JTextArea jaShow; // 数据显示栏
	JButton jbNoSearch; // 学号查询按钮
	JButton jbMultiSearch; // 多条件选择按钮
	JButton jbBack;

	JButton jbSubmitIn, jbBackIn; // 登录界面按钮
	JButton jbSubmitUp, jbBackUp; // 注册界面按钮
	JButton jbDelDb, jbUpdateDb, jbAddDb, jbShowDb, jbBackDb, jbSearch; // 数据库操作界面按钮
	JButton jbHomeUp, jbHomeIn; // home界面的登录和注册按钮
	Box baseBox, boxV1, boxV2, boxV3, boxV4, boxV5; // 盒子容器
	
	JComboBox jcSexUpdate,jcSexAdd,jcSexSearch;
	JComboBox jcProfessUpdate,jcProfessAdd,jcProfessSearch;
	
	ConnectStudentDB dbS;
	ConnectAccountDB dbA;
	//用于控制当前窗口图片
	public static final int HOME = 0;
	public static final int SIGNIN = 2;
	public static final int SIGNUP = 1;
	public static final int DATABASE = -1;
	public static final int DBSEARCH = 3;
	//初始显示home页
	public static int STATE = HOME;

	public MyPanel() {
		//初始界面home
		home();
		//初始化组件
		jcProfessUpdate = new JComboBox();
		jcProfessUpdate.addItem("");
		jcProfessUpdate.addItem("计算机");
		jcProfessUpdate.addItem("数学");
		jcProfessUpdate.addItem("外语");
		jcProfessUpdate.addItem("法学");
		jcProfessUpdate.addItem("医学");
		jcProfessUpdate.addItem("艺术");
		jcProfessUpdate.addItem("体育");
		jcProfessUpdate.addItem("化学");
		jcProfessUpdate.addItem("物理");
		jcProfessUpdate.addItem("其他");
		jcProfessAdd = new JComboBox();
		jcProfessAdd.addItem("");
		jcProfessAdd.addItem("计算机");
		jcProfessAdd.addItem("数学");
		jcProfessAdd.addItem("外语");
		jcProfessAdd.addItem("法学");
		jcProfessAdd.addItem("医学");
		jcProfessAdd.addItem("艺术");
		jcProfessAdd.addItem("体育");
		jcProfessAdd.addItem("化学");
		jcProfessAdd.addItem("物理");
		jcProfessAdd.addItem("其他");
		jcProfessSearch = new JComboBox();
		jcProfessSearch.addItem("");
		jcProfessSearch.addItem("计算机");
		jcProfessSearch.addItem("数学");
		jcProfessSearch.addItem("外语");
		jcProfessSearch.addItem("法学");
		jcProfessSearch.addItem("医学");
		jcProfessSearch.addItem("艺术");
		jcProfessSearch.addItem("体育");
		jcProfessSearch.addItem("化学");
		jcProfessSearch.addItem("物理");
		jcProfessSearch.addItem("其他");
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// 背景图片设置
		super.paintComponent(g);
		if (STATE == HOME) {
			Image img = new ImageIcon("img/home.png").getImage();
			g.drawImage(img, 0, 0, 800, 600, null);
		} else if (STATE == SIGNIN) {
			Image img = new ImageIcon("img/signin.jpg").getImage();
			g.drawImage(img, 0, 0, 800, 600, null);
		} else if (STATE == SIGNUP) {
			Image img = new ImageIcon("img/signup.jpg").getImage();
			g.drawImage(img, 0, 0, 800, 600, null);
		} else if (STATE == DATABASE) {
			Image img = new ImageIcon("img/database.jpg").getImage();
			g.drawImage(img, 0, 0, 800, 600, null);
		} else {
			Image img = new ImageIcon("img/dbsearch.jpg").getImage();
			g.drawImage(img, 0, 0, 800, 600, null);
		}
	}

	public void database_search() {
		// 更新panel容器
		this.removeAll();
		STATE=DBSEARCH;
		updateUI();

		// 盒子布局
		this.setLayout(new FlowLayout());
		// 设置boxV1的布局为水平
		boxV1 = Box.createHorizontalBox();
		boxV1.add(new JLabel("学号查询"));

		boxV2 = Box.createHorizontalBox();
		jtNoSearch = new JTextField(10);
		jbNoSearch = new JButton("查询");
		jbNoSearch.addActionListener(this);
		boxV2.add(new JLabel("学号：    "));
		boxV2.add(jtNoSearch);
		boxV2.add(jbNoSearch);
		boxV2.add(Box.createHorizontalStrut(500));

		boxV3 = Box.createHorizontalBox();
		boxV3.add(new JLabel("多条件查询"));

		boxV4 = Box.createHorizontalBox();
		jtNameSearch = new JTextField(10);
		jcSexSearch = new JComboBox();
		jcSexSearch.addItem("");
		jcSexSearch.addItem("男");
		jcSexSearch.addItem("女");
		jbMultiSearch = new JButton("查询");
		jbMultiSearch.addActionListener(this);
		boxV4.add(new JLabel("姓名：    "));
		boxV4.add(jtNameSearch);
		boxV4.add(new JLabel("性别：    "));
	//	boxV4.add(jtSexSearch);
		boxV4.add(jcSexSearch);
		boxV4.add(new JLabel("专业：    "));
		boxV4.add(jcProfessSearch);

		boxV4.add(jbMultiSearch);
		boxV4.add(Box.createHorizontalStrut(200));
		boxV5 = Box.createHorizontalBox();
		jaShow = new JTextArea(10,60);
		boxV5.add(new JScrollPane(jaShow));

		baseBox = Box.createVerticalBox();
		jbBack = new JButton("返回");
		jbBack.addActionListener(this);
		baseBox.add(jbBack);
		baseBox.add(Box.createVerticalStrut(50));
		baseBox.add(boxV1);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV2);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV3);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV4);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV5);

		add(baseBox);
	}

	// home主界面
	public void home() {
		// 更新panel容器
		this.removeAll();
		STATE = HOME;
		updateUI();
		// 盒子布局
		this.setLayout(new FlowLayout());
		// 设置boxV1的布局为水平
		boxV1 = Box.createHorizontalBox();
		jbHomeIn = new JButton("登录界面");
		jbHomeIn.addActionListener(this);
		jbHomeUp = new JButton("注册界面");
		jbHomeUp.addActionListener(this);
		boxV1.add(jbHomeIn);
		// 添加一定的空格
		boxV1.add(Box.createHorizontalStrut(100));
		boxV1.add(jbHomeUp);
		// 基础盒子布局为垂直
		baseBox = Box.createVerticalBox();
		JLabel title = new JLabel("java数据库大作业"); 
		title.setFont(new   java.awt.Font("Dialog",   10,   26));
		title.setForeground(Color.orange);
		boxV2 = Box.createHorizontalBox();
		baseBox.add(Box.createVerticalStrut(30));
		boxV2.add(title);
		baseBox.add(boxV2);
		baseBox.add(Box.createVerticalStrut(100));
		
		baseBox.add(boxV1);
		add(baseBox);
	}

	// 数据库操作界面
	public void database() {
		this.removeAll();
		STATE = DATABASE;
		updateUI();

		this.setLayout(new FlowLayout());
		// 删除
		boxV1 = Box.createHorizontalBox();
		boxV1.add(new JLabel("学号:     "));
		jtDelNo = new JTextField(10);
		jbDelDb = new JButton("删除");
		jbDelDb.addActionListener(this);
		boxV1.add(jtDelNo);
		boxV1.add(jbDelDb);
		boxV1.add(Box.createHorizontalStrut(500));
		// 更改
		boxV2 = Box.createHorizontalBox();

		boxV2.add(new JLabel("学号:     "));
		jtUpdateNo = new JTextField(10);
		boxV2.add(jtUpdateNo);

		boxV2.add(new JLabel("姓名:     "));
		jtUpdateName = new JTextField(10);
		boxV2.add(jtUpdateName);

		boxV2.add(new JLabel("性别:     "));

		jcSexUpdate = new JComboBox();
		jcSexUpdate.addItem("");
		jcSexUpdate.addItem("男");
		jcSexUpdate.addItem("女");
		boxV2.add(jcSexUpdate);

		boxV2.add(new JLabel("专业:     "));
		boxV2.add(jcProfessUpdate);

		jbUpdateDb = new JButton("更新");
		jbUpdateDb.addActionListener(this);
		boxV2.add(jbUpdateDb);
		// 添加
		boxV3 = Box.createHorizontalBox();

		boxV3.add(new JLabel("学号:     "));
		jtAddNo = new JTextField(10);
		boxV3.add(jtAddNo);

		boxV3.add(new JLabel("姓名:     "));
		jtAddName = new JTextField(10);
		boxV3.add(jtAddName);

		boxV3.add(new JLabel("性别:     "));
		jcSexAdd = new JComboBox();
		jcSexAdd.addItem("");
		jcSexAdd.addItem("男");
		jcSexAdd.addItem("女");
		boxV3.add(jcSexAdd);

		boxV3.add(new JLabel("专业:     "));
		boxV3.add(jcProfessAdd);

		jbAddDb = new JButton("添加");
		jbAddDb.addActionListener(this);
		boxV3.add(jbAddDb);
		// 显示
		boxV4 = Box.createHorizontalBox();
		jaShowDb = new JTextArea(20,60);
		showDb();
		boxV4.add(new JScrollPane(jaShowDb));

		baseBox = Box.createVerticalBox();
		baseBox.add(Box.createVerticalStrut(30));
		baseBox.add(boxV1);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV2);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV3);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV4);

		jbBackDb = new JButton("退出");
		jbBackDb.addActionListener(this);
		add(jbBackDb);

		jbSearch = new JButton("查询数据库");
		jbSearch.addActionListener(this);
		add(jbSearch);

		add(baseBox);

	}

	// 注册界面
	public void signUp() {
		this.removeAll();
		STATE = SIGNUP;
		updateUI();
		this.setLayout(new FlowLayout());

		boxV1 = Box.createHorizontalBox();
		boxV1.add(new JLabel("用户名:     "));
		jtUserUp = new JTextField(10);
		boxV1.add(jtUserUp);

		boxV2 = Box.createHorizontalBox();
		boxV2.add(new JLabel("密    码:      "));
		jtPasswdUp = new JTextField(10);
		boxV2.add(jtPasswdUp);

		boxV3 = Box.createHorizontalBox();
		boxV3.add(new JLabel("核实密码: "));
		jtVerifyUp = new JTextField(10);
		boxV3.add(jtVerifyUp);

		boxV4 = Box.createHorizontalBox();
		jbSubmitUp = new JButton("注册");
		jbSubmitUp.addActionListener(this);
		jbBackUp = new JButton("返回");
		jbBackUp.addActionListener(this);

		boxV4.add(jbSubmitUp);
		boxV4.add(Box.createHorizontalStrut(100));
		boxV4.add(jbBackUp);

		baseBox = Box.createVerticalBox();
		baseBox.add(Box.createVerticalStrut(100));
		baseBox.add(boxV1);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV2);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV3);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV4);
		add(baseBox);
	}

	// 登录界面
	public void signIn() {
		this.removeAll();
		STATE = SIGNIN;
		updateUI();
		this.setLayout(new FlowLayout());

		boxV1 = Box.createHorizontalBox();
		boxV1.add(new JLabel("用户名:   "));
		jtUserIn = new JTextField(10);
		boxV1.add(jtUserIn);

		boxV2 = Box.createHorizontalBox();
		boxV2.add(new JLabel("密    码:   "));
		jtPasswdIn = new JTextField(10);
		boxV2.add(jtPasswdIn);

		boxV3 = Box.createHorizontalBox();
		jbSubmitIn = new JButton("登录");
		jbSubmitIn.addActionListener(this);
		jbBackIn = new JButton("返回");
		jbBackIn.addActionListener(this);

		boxV3.add(jbSubmitIn);
		boxV3.add(Box.createHorizontalStrut(100));
		boxV3.add(jbBackIn);

		baseBox = Box.createVerticalBox();
		baseBox.add(Box.createVerticalStrut(100));
		baseBox.add(boxV1);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV2);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(boxV3);
		add(baseBox);

	}

	// 实现点击按钮响应相应的事件
	@Override
	public void actionPerformed(ActionEvent e) {
		// 如果点击home界面的登录，跳转到登录界面
		if (e.getSource() == jbHomeIn) {
			signIn();
		}
		// 如果点击home界面的注册，跳转到注册界面
		if (e.getSource() == jbHomeUp) {
			signUp();
		}
		if (e.getSource() == jbSearch) {
			database_search();
		}
		// 数据库删除
		if (e.getSource() == jbDelDb) {
			actionDbDel();
		}
		// 数据库更改
		if (e.getSource() == jbUpdateDb) {
			actionDbUpdate();
		}
		// 数据库增加
		if (e.getSource() == jbAddDb) {
			actionDbInsert();
		}
		// 按学号查询
		if (e.getSource() == jbNoSearch) {
			actionNoSearch();
		}
		// 多条件查询
		if (e.getSource() == jbMultiSearch) {
			actionMultiSearch();
		}
		if (e.getSource() == jbBack) {
			database();
		}

		// 如果点击数据库界面的返回，跳转到home界面
		if (e.getSource() == jbBackDb) {
			home();
		}

		// 登录界面的登录按钮事件
		if (e.getSource() == jbSubmitIn) {
			actionSignin();
		}
		// 返回home界面
		if (e.getSource() == jbBackIn) {
			home();
		}
		// 注册操作
		if (e.getSource() == jbSubmitUp) {
			actionSignup();
		}
		// 返回home界面
		if (e.getSource() == jbBackUp) {
			home();
		}
	}
	//按学号查询
	public void actionNoSearch() {
		dbS = new ConnectStudentDB();
		String str = dbS.getDataByNo(jtNoSearch.getText());
		jaShow.setText(str);
		dbS.close();
	}
	//多条件查询事件
	public void actionMultiSearch() {
		dbS = new ConnectStudentDB();
		String str = dbS.getDataByMulti(jtNameSearch.getText(),
				jcSexSearch.getSelectedItem().toString(), jcProfessSearch.getSelectedItem().toString());
		jaShow.setText(str);
		dbS.close();
	}

	// 判断输入文本框是否为空
	public boolean isNull(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	// 数据库中删除按钮事件
	public void actionDbDel() {
		// 获取删除文本框的学号
		String delNo = jtDelNo.getText();
		if (isNull(delNo)) {
			// 学号为空则显示错误提示
			JOptionPane.showMessageDialog(this, "你要删除的学号不能为空", "消息提示",
					JOptionPane.WARNING_MESSAGE);
		} else {
			// 否则建立数据库连接
			dbS = new ConnectStudentDB();
			// 判断要学号是否为空
			if (dbS.isExist(delNo)) {
				// 不为空则删除
				dbS.del(delNo);
				// 显示数据
				showDb();
				JOptionPane.showMessageDialog(this, "删除成功", "消息提示",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "不存在该学号", "消息提示",
						JOptionPane.WARNING_MESSAGE);
			}
			// 关闭数据库连接
			dbS.close();
		}
	}

	// 数据库的更新操作
	public void actionDbUpdate() {
		String updateNo, updateName, updateSex, updateProfess;
		updateNo = jtUpdateNo.getText();
		updateName = jtUpdateName.getText();
		updateSex = jcSexUpdate.getSelectedItem().toString();
		updateProfess = jcProfessUpdate.getSelectedItem().toString();
		if (isNull(updateNo)) {
			JOptionPane.showMessageDialog(this, "你要添加的学号不能为空", "消息提示",
					JOptionPane.WARNING_MESSAGE);
		} else {
			dbS = new ConnectStudentDB();
			if (dbS.isExist(updateNo)) {
				dbS.update(updateNo, updateName, updateSex, updateProfess);
				showDb();
			} else {
				JOptionPane.showMessageDialog(this, "该学号不存在", "消息提示",
						JOptionPane.WARNING_MESSAGE);
			}
			dbS.close();
		}

	}

	// 数据库界面的添加按钮时间
	public void actionDbInsert() {
		String insertNo, insertName, insertSex, insertProfess;
		insertNo = jtAddNo.getText();
		insertName = jtAddName.getText();
		insertSex = jcSexAdd.getSelectedItem().toString();
		insertProfess = jcProfessAdd.getSelectedItem().toString();
		if (isNull(insertNo)) {
			JOptionPane.showMessageDialog(this, "你要添加的学号不能为空", "消息提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (isNull(insertName)) {
			JOptionPane.showMessageDialog(this, "你要添加的姓名不能为空", "消息提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (isNull(insertSex)) {
			JOptionPane.showMessageDialog(this, "你要添加的性别不能为空", "消息提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (isNull(insertProfess)) {
			JOptionPane.showMessageDialog(this, "你要添加的专业不能为空", "消息提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		dbS = new ConnectStudentDB();
		if (dbS.isExist(insertNo)) {
			JOptionPane.showMessageDialog(this, "该学号已经存在，不能重复添加", "消息提示",
					JOptionPane.WARNING_MESSAGE);
		} else {
			dbS.insert(insertNo, insertName, insertSex, insertProfess);
			showDb();
		}
		dbS.close();
	}

	// 登录按钮事件行为
	public void actionSignin() {
		String username = jtUserIn.getText();
		String password = jtPasswdIn.getText();
		// 判断如果为空break
		if (username.equals("") || username == null) {
			JOptionPane.showMessageDialog(this, "用户名未输入", "消息提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (password.equals("") || password == null) {
			JOptionPane.showMessageDialog(this, "密码未输入", "消息提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// 连接数据库
		dbA = new ConnectAccountDB();
		if (dbA.isLogin(username, password)) {
			database();
		} else {
			JOptionPane.showMessageDialog(this, "用户名或密码错误", "消息提示",
					JOptionPane.WARNING_MESSAGE);
		}
		dbA.close();

	}

	// 注册按钮事件行为
	public void actionSignup() {
		String username = jtUserUp.getText();
		String password = jtPasswdUp.getText();
		String verify = jtVerifyUp.getText();
		// 判断如果为空退出
		if (isNull(username)) {
			JOptionPane.showMessageDialog(this, "用户名未输入", "消息提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (isNull(password)) {
			JOptionPane.showMessageDialog(this, "密码未输入", "消息提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (!password.equals(verify)) {
			JOptionPane.showMessageDialog(this, "两次密码不同", "消息提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// 连接数据库
		dbA = new ConnectAccountDB();
		if (!dbA.isExist(username)) {
			dbA.insert(username, password);
			if (JOptionPane.showConfirmDialog(this, "注册成功，是否跳到登录界面", "确认对话框",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				home();
			}

		} else {
			JOptionPane.showMessageDialog(this, "已存在该用户", "消息提示",
					JOptionPane.WARNING_MESSAGE);
		}
		dbA.close();
	}

	// 文本框显示数据
	public void showDb() {
		dbS = new ConnectStudentDB();
		String str = dbS.showDate();
		jaShowDb.setText(str);
		dbS.close();
	}
}
