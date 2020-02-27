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
	JTextField jtUserIn, jtPasswdIn; // ��¼�����ı���
	JTextField jtUserUp, jtPasswdUp, jtVerifyUp; // ע������ı���
	JTextField jtDelNo; // ���ݿ����ɾ����ť
	JTextField jtUpdateNo, jtUpdateName, jtUpdateSex, jtUpdateProfess;
	JTextField jtAddNo, jtAddName, jtAddSex, jtAddProfess;
	JTextArea jaShowDb; // ������ʾ���ݿ������
	// database_showҳ�����
	JTextField jtNoSearch;
	JTextField jtNameSearch, jtSexSearch, jtProfessSearch;
	JTextArea jaShow; // ������ʾ��
	JButton jbNoSearch; // ѧ�Ų�ѯ��ť
	JButton jbMultiSearch; // ������ѡ��ť
	JButton jbBack;

	JButton jbSubmitIn, jbBackIn; // ��¼���水ť
	JButton jbSubmitUp, jbBackUp; // ע����水ť
	JButton jbDelDb, jbUpdateDb, jbAddDb, jbShowDb, jbBackDb, jbSearch; // ���ݿ�������水ť
	JButton jbHomeUp, jbHomeIn; // home����ĵ�¼��ע�ᰴť
	Box baseBox, boxV1, boxV2, boxV3, boxV4, boxV5; // ��������
	
	JComboBox jcSexUpdate,jcSexAdd,jcSexSearch;
	JComboBox jcProfessUpdate,jcProfessAdd,jcProfessSearch;
	
	ConnectStudentDB dbS;
	ConnectAccountDB dbA;
	//���ڿ��Ƶ�ǰ����ͼƬ
	public static final int HOME = 0;
	public static final int SIGNIN = 2;
	public static final int SIGNUP = 1;
	public static final int DATABASE = -1;
	public static final int DBSEARCH = 3;
	//��ʼ��ʾhomeҳ
	public static int STATE = HOME;

	public MyPanel() {
		//��ʼ����home
		home();
		//��ʼ�����
		jcProfessUpdate = new JComboBox();
		jcProfessUpdate.addItem("");
		jcProfessUpdate.addItem("�����");
		jcProfessUpdate.addItem("��ѧ");
		jcProfessUpdate.addItem("����");
		jcProfessUpdate.addItem("��ѧ");
		jcProfessUpdate.addItem("ҽѧ");
		jcProfessUpdate.addItem("����");
		jcProfessUpdate.addItem("����");
		jcProfessUpdate.addItem("��ѧ");
		jcProfessUpdate.addItem("����");
		jcProfessUpdate.addItem("����");
		jcProfessAdd = new JComboBox();
		jcProfessAdd.addItem("");
		jcProfessAdd.addItem("�����");
		jcProfessAdd.addItem("��ѧ");
		jcProfessAdd.addItem("����");
		jcProfessAdd.addItem("��ѧ");
		jcProfessAdd.addItem("ҽѧ");
		jcProfessAdd.addItem("����");
		jcProfessAdd.addItem("����");
		jcProfessAdd.addItem("��ѧ");
		jcProfessAdd.addItem("����");
		jcProfessAdd.addItem("����");
		jcProfessSearch = new JComboBox();
		jcProfessSearch.addItem("");
		jcProfessSearch.addItem("�����");
		jcProfessSearch.addItem("��ѧ");
		jcProfessSearch.addItem("����");
		jcProfessSearch.addItem("��ѧ");
		jcProfessSearch.addItem("ҽѧ");
		jcProfessSearch.addItem("����");
		jcProfessSearch.addItem("����");
		jcProfessSearch.addItem("��ѧ");
		jcProfessSearch.addItem("����");
		jcProfessSearch.addItem("����");
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// ����ͼƬ����
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
		// ����panel����
		this.removeAll();
		STATE=DBSEARCH;
		updateUI();

		// ���Ӳ���
		this.setLayout(new FlowLayout());
		// ����boxV1�Ĳ���Ϊˮƽ
		boxV1 = Box.createHorizontalBox();
		boxV1.add(new JLabel("ѧ�Ų�ѯ"));

		boxV2 = Box.createHorizontalBox();
		jtNoSearch = new JTextField(10);
		jbNoSearch = new JButton("��ѯ");
		jbNoSearch.addActionListener(this);
		boxV2.add(new JLabel("ѧ�ţ�    "));
		boxV2.add(jtNoSearch);
		boxV2.add(jbNoSearch);
		boxV2.add(Box.createHorizontalStrut(500));

		boxV3 = Box.createHorizontalBox();
		boxV3.add(new JLabel("��������ѯ"));

		boxV4 = Box.createHorizontalBox();
		jtNameSearch = new JTextField(10);
		jcSexSearch = new JComboBox();
		jcSexSearch.addItem("");
		jcSexSearch.addItem("��");
		jcSexSearch.addItem("Ů");
		jbMultiSearch = new JButton("��ѯ");
		jbMultiSearch.addActionListener(this);
		boxV4.add(new JLabel("������    "));
		boxV4.add(jtNameSearch);
		boxV4.add(new JLabel("�Ա�    "));
	//	boxV4.add(jtSexSearch);
		boxV4.add(jcSexSearch);
		boxV4.add(new JLabel("רҵ��    "));
		boxV4.add(jcProfessSearch);

		boxV4.add(jbMultiSearch);
		boxV4.add(Box.createHorizontalStrut(200));
		boxV5 = Box.createHorizontalBox();
		jaShow = new JTextArea(10,60);
		boxV5.add(new JScrollPane(jaShow));

		baseBox = Box.createVerticalBox();
		jbBack = new JButton("����");
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

	// home������
	public void home() {
		// ����panel����
		this.removeAll();
		STATE = HOME;
		updateUI();
		// ���Ӳ���
		this.setLayout(new FlowLayout());
		// ����boxV1�Ĳ���Ϊˮƽ
		boxV1 = Box.createHorizontalBox();
		jbHomeIn = new JButton("��¼����");
		jbHomeIn.addActionListener(this);
		jbHomeUp = new JButton("ע�����");
		jbHomeUp.addActionListener(this);
		boxV1.add(jbHomeIn);
		// ���һ���Ŀո�
		boxV1.add(Box.createHorizontalStrut(100));
		boxV1.add(jbHomeUp);
		// �������Ӳ���Ϊ��ֱ
		baseBox = Box.createVerticalBox();
		JLabel title = new JLabel("java���ݿ����ҵ"); 
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

	// ���ݿ��������
	public void database() {
		this.removeAll();
		STATE = DATABASE;
		updateUI();

		this.setLayout(new FlowLayout());
		// ɾ��
		boxV1 = Box.createHorizontalBox();
		boxV1.add(new JLabel("ѧ��:     "));
		jtDelNo = new JTextField(10);
		jbDelDb = new JButton("ɾ��");
		jbDelDb.addActionListener(this);
		boxV1.add(jtDelNo);
		boxV1.add(jbDelDb);
		boxV1.add(Box.createHorizontalStrut(500));
		// ����
		boxV2 = Box.createHorizontalBox();

		boxV2.add(new JLabel("ѧ��:     "));
		jtUpdateNo = new JTextField(10);
		boxV2.add(jtUpdateNo);

		boxV2.add(new JLabel("����:     "));
		jtUpdateName = new JTextField(10);
		boxV2.add(jtUpdateName);

		boxV2.add(new JLabel("�Ա�:     "));

		jcSexUpdate = new JComboBox();
		jcSexUpdate.addItem("");
		jcSexUpdate.addItem("��");
		jcSexUpdate.addItem("Ů");
		boxV2.add(jcSexUpdate);

		boxV2.add(new JLabel("רҵ:     "));
		boxV2.add(jcProfessUpdate);

		jbUpdateDb = new JButton("����");
		jbUpdateDb.addActionListener(this);
		boxV2.add(jbUpdateDb);
		// ���
		boxV3 = Box.createHorizontalBox();

		boxV3.add(new JLabel("ѧ��:     "));
		jtAddNo = new JTextField(10);
		boxV3.add(jtAddNo);

		boxV3.add(new JLabel("����:     "));
		jtAddName = new JTextField(10);
		boxV3.add(jtAddName);

		boxV3.add(new JLabel("�Ա�:     "));
		jcSexAdd = new JComboBox();
		jcSexAdd.addItem("");
		jcSexAdd.addItem("��");
		jcSexAdd.addItem("Ů");
		boxV3.add(jcSexAdd);

		boxV3.add(new JLabel("רҵ:     "));
		boxV3.add(jcProfessAdd);

		jbAddDb = new JButton("���");
		jbAddDb.addActionListener(this);
		boxV3.add(jbAddDb);
		// ��ʾ
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

		jbBackDb = new JButton("�˳�");
		jbBackDb.addActionListener(this);
		add(jbBackDb);

		jbSearch = new JButton("��ѯ���ݿ�");
		jbSearch.addActionListener(this);
		add(jbSearch);

		add(baseBox);

	}

	// ע�����
	public void signUp() {
		this.removeAll();
		STATE = SIGNUP;
		updateUI();
		this.setLayout(new FlowLayout());

		boxV1 = Box.createHorizontalBox();
		boxV1.add(new JLabel("�û���:     "));
		jtUserUp = new JTextField(10);
		boxV1.add(jtUserUp);

		boxV2 = Box.createHorizontalBox();
		boxV2.add(new JLabel("��    ��:      "));
		jtPasswdUp = new JTextField(10);
		boxV2.add(jtPasswdUp);

		boxV3 = Box.createHorizontalBox();
		boxV3.add(new JLabel("��ʵ����: "));
		jtVerifyUp = new JTextField(10);
		boxV3.add(jtVerifyUp);

		boxV4 = Box.createHorizontalBox();
		jbSubmitUp = new JButton("ע��");
		jbSubmitUp.addActionListener(this);
		jbBackUp = new JButton("����");
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

	// ��¼����
	public void signIn() {
		this.removeAll();
		STATE = SIGNIN;
		updateUI();
		this.setLayout(new FlowLayout());

		boxV1 = Box.createHorizontalBox();
		boxV1.add(new JLabel("�û���:   "));
		jtUserIn = new JTextField(10);
		boxV1.add(jtUserIn);

		boxV2 = Box.createHorizontalBox();
		boxV2.add(new JLabel("��    ��:   "));
		jtPasswdIn = new JTextField(10);
		boxV2.add(jtPasswdIn);

		boxV3 = Box.createHorizontalBox();
		jbSubmitIn = new JButton("��¼");
		jbSubmitIn.addActionListener(this);
		jbBackIn = new JButton("����");
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

	// ʵ�ֵ����ť��Ӧ��Ӧ���¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		// ������home����ĵ�¼����ת����¼����
		if (e.getSource() == jbHomeIn) {
			signIn();
		}
		// ������home�����ע�ᣬ��ת��ע�����
		if (e.getSource() == jbHomeUp) {
			signUp();
		}
		if (e.getSource() == jbSearch) {
			database_search();
		}
		// ���ݿ�ɾ��
		if (e.getSource() == jbDelDb) {
			actionDbDel();
		}
		// ���ݿ����
		if (e.getSource() == jbUpdateDb) {
			actionDbUpdate();
		}
		// ���ݿ�����
		if (e.getSource() == jbAddDb) {
			actionDbInsert();
		}
		// ��ѧ�Ų�ѯ
		if (e.getSource() == jbNoSearch) {
			actionNoSearch();
		}
		// ��������ѯ
		if (e.getSource() == jbMultiSearch) {
			actionMultiSearch();
		}
		if (e.getSource() == jbBack) {
			database();
		}

		// ���������ݿ����ķ��أ���ת��home����
		if (e.getSource() == jbBackDb) {
			home();
		}

		// ��¼����ĵ�¼��ť�¼�
		if (e.getSource() == jbSubmitIn) {
			actionSignin();
		}
		// ����home����
		if (e.getSource() == jbBackIn) {
			home();
		}
		// ע�����
		if (e.getSource() == jbSubmitUp) {
			actionSignup();
		}
		// ����home����
		if (e.getSource() == jbBackUp) {
			home();
		}
	}
	//��ѧ�Ų�ѯ
	public void actionNoSearch() {
		dbS = new ConnectStudentDB();
		String str = dbS.getDataByNo(jtNoSearch.getText());
		jaShow.setText(str);
		dbS.close();
	}
	//��������ѯ�¼�
	public void actionMultiSearch() {
		dbS = new ConnectStudentDB();
		String str = dbS.getDataByMulti(jtNameSearch.getText(),
				jcSexSearch.getSelectedItem().toString(), jcProfessSearch.getSelectedItem().toString());
		jaShow.setText(str);
		dbS.close();
	}

	// �ж������ı����Ƿ�Ϊ��
	public boolean isNull(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	// ���ݿ���ɾ����ť�¼�
	public void actionDbDel() {
		// ��ȡɾ���ı����ѧ��
		String delNo = jtDelNo.getText();
		if (isNull(delNo)) {
			// ѧ��Ϊ������ʾ������ʾ
			JOptionPane.showMessageDialog(this, "��Ҫɾ����ѧ�Ų���Ϊ��", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
		} else {
			// ���������ݿ�����
			dbS = new ConnectStudentDB();
			// �ж�Ҫѧ���Ƿ�Ϊ��
			if (dbS.isExist(delNo)) {
				// ��Ϊ����ɾ��
				dbS.del(delNo);
				// ��ʾ����
				showDb();
				JOptionPane.showMessageDialog(this, "ɾ���ɹ�", "��Ϣ��ʾ",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "�����ڸ�ѧ��", "��Ϣ��ʾ",
						JOptionPane.WARNING_MESSAGE);
			}
			// �ر����ݿ�����
			dbS.close();
		}
	}

	// ���ݿ�ĸ��²���
	public void actionDbUpdate() {
		String updateNo, updateName, updateSex, updateProfess;
		updateNo = jtUpdateNo.getText();
		updateName = jtUpdateName.getText();
		updateSex = jcSexUpdate.getSelectedItem().toString();
		updateProfess = jcProfessUpdate.getSelectedItem().toString();
		if (isNull(updateNo)) {
			JOptionPane.showMessageDialog(this, "��Ҫ��ӵ�ѧ�Ų���Ϊ��", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
		} else {
			dbS = new ConnectStudentDB();
			if (dbS.isExist(updateNo)) {
				dbS.update(updateNo, updateName, updateSex, updateProfess);
				showDb();
			} else {
				JOptionPane.showMessageDialog(this, "��ѧ�Ų�����", "��Ϣ��ʾ",
						JOptionPane.WARNING_MESSAGE);
			}
			dbS.close();
		}

	}

	// ���ݿ�������Ӱ�ťʱ��
	public void actionDbInsert() {
		String insertNo, insertName, insertSex, insertProfess;
		insertNo = jtAddNo.getText();
		insertName = jtAddName.getText();
		insertSex = jcSexAdd.getSelectedItem().toString();
		insertProfess = jcProfessAdd.getSelectedItem().toString();
		if (isNull(insertNo)) {
			JOptionPane.showMessageDialog(this, "��Ҫ��ӵ�ѧ�Ų���Ϊ��", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (isNull(insertName)) {
			JOptionPane.showMessageDialog(this, "��Ҫ��ӵ���������Ϊ��", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (isNull(insertSex)) {
			JOptionPane.showMessageDialog(this, "��Ҫ��ӵ��Ա���Ϊ��", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (isNull(insertProfess)) {
			JOptionPane.showMessageDialog(this, "��Ҫ��ӵ�רҵ����Ϊ��", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		dbS = new ConnectStudentDB();
		if (dbS.isExist(insertNo)) {
			JOptionPane.showMessageDialog(this, "��ѧ���Ѿ����ڣ������ظ����", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
		} else {
			dbS.insert(insertNo, insertName, insertSex, insertProfess);
			showDb();
		}
		dbS.close();
	}

	// ��¼��ť�¼���Ϊ
	public void actionSignin() {
		String username = jtUserIn.getText();
		String password = jtPasswdIn.getText();
		// �ж����Ϊ��break
		if (username.equals("") || username == null) {
			JOptionPane.showMessageDialog(this, "�û���δ����", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (password.equals("") || password == null) {
			JOptionPane.showMessageDialog(this, "����δ����", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// �������ݿ�
		dbA = new ConnectAccountDB();
		if (dbA.isLogin(username, password)) {
			database();
		} else {
			JOptionPane.showMessageDialog(this, "�û������������", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
		}
		dbA.close();

	}

	// ע�ᰴť�¼���Ϊ
	public void actionSignup() {
		String username = jtUserUp.getText();
		String password = jtPasswdUp.getText();
		String verify = jtVerifyUp.getText();
		// �ж����Ϊ���˳�
		if (isNull(username)) {
			JOptionPane.showMessageDialog(this, "�û���δ����", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (isNull(password)) {
			JOptionPane.showMessageDialog(this, "����δ����", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (!password.equals(verify)) {
			JOptionPane.showMessageDialog(this, "�������벻ͬ", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// �������ݿ�
		dbA = new ConnectAccountDB();
		if (!dbA.isExist(username)) {
			dbA.insert(username, password);
			if (JOptionPane.showConfirmDialog(this, "ע��ɹ����Ƿ�������¼����", "ȷ�϶Ի���",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				home();
			}

		} else {
			JOptionPane.showMessageDialog(this, "�Ѵ��ڸ��û�", "��Ϣ��ʾ",
					JOptionPane.WARNING_MESSAGE);
		}
		dbA.close();
	}

	// �ı�����ʾ����
	public void showDb() {
		dbS = new ConnectStudentDB();
		String str = dbS.showDate();
		jaShowDb.setText(str);
		dbS.close();
	}
}
