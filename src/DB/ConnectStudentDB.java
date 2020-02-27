package DB;

import java.sql.*;

public class ConnectStudentDB {
	private String driver = "com.mysql.jdbc.Driver";
	private String name = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/ss?characterEncoding=utf-8";
	Connection con;
	public static final String SQLTITLE = "student";
	PreparedStatement ps;
	Statement st;
	ResultSet rs;
	//�������ݿ�
	public ConnectStudentDB() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//����
	public int insert(String insertNo, String insertName,String insertSex,String insertProfess) {
		//�������ɹ���i���̲���Ӱ�������
		int i = 0;
		String sql = "insert into " + SQLTITLE + " values (?,?,?,?)";
		try {
			//ִ��sql���
			ps = con.prepareStatement(sql);
			//һ������ÿ������ֵ��Ҳ����Ҫ�����ֵ
			ps.setString(1, insertNo);
			ps.setString(2, insertName);
			ps.setString(3, insertSex);
			ps.setString(4, insertProfess);
			//ִ��
			i = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	// �ж��Ƿ��Ѿ������û���
	public boolean isExist(String No) {
		String sql = "select * from " + SQLTITLE + " where No='"
				+ No + "'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if (rs.next()) {
				// ���ڷ�����
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �Ѿ�����
		return false;
	}
	//	ɾ������
	public int del(String delNo){ //����Ҫɾ���Ĳ���
		String sql = "delete from "+SQLTITLE+" where No='"+delNo+"'";
		int i=0;
		try {
			ps = con.prepareStatement(sql);
			i = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	//���²���
	public int update(String updateNo,String updateName,String updateSex,String updateProfess){
		int i=0;
		try {
			//��������ı���Ϊ�գ�����Ҫ����
			if(updateName!=null && !updateName.trim().equals("")){
				String sql="update "+SQLTITLE+" set Name=? where No=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, updateName);
				ps.setString(2,updateNo);
				i = ps.executeUpdate();
			}
			//����Ա��ı���Ϊ�գ�����Ҫ����
			if(updateSex!=null && !updateSex.trim().equals("")){
				String sql="update "+SQLTITLE+" set Sex=? where No=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, updateSex);
				ps.setString(2,updateNo);
				i = ps.executeUpdate();
			}
			//���רҵ�ı���Ϊ�գ�����Ҫ����
			if(updateProfess!=null && !updateProfess.trim().equals("")){
				String sql="update "+SQLTITLE+" set Profess=? where No=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, updateProfess);
				ps.setString(2,updateNo);
				i = ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
		
	}
//��string��ʽ�������ݿ�����
	public String showDate() {
		String date = "";
		date += "\tѧ��\t\t����\t\t�Ա�\t\tרҵ\t\n";
		String sql = "select * from " + SQLTITLE;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				date += "\t" + rs.getString(1);
				date += "\t\t" + rs.getString(2);
				date += "\t\t" + rs.getString(3);
				date += "\t\t" + rs.getString(4);
				date += "\t\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return date;
	}
	//��ѧ�Ų�ѯ
	public String getDataByNo(String no){
		String date = "";
		date += "\tѧ��\t\t����\t\t�Ա�\t\tרҵ\t\n";
		String sql = "select * from " + SQLTITLE;
		if(!isNull(no)){
			sql+= " where no='"+no+"'";
		}
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				date += "\t" + rs.getString(1);
				date += "\t\t" + rs.getString(2);
				date += "\t\t" + rs.getString(3);
				date += "\t\t" + rs.getString(4);
				date += "\t\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return date;
	}
	//��������ѯ����
	public String getDataByMulti(String name,String sex,String profess){
		String date = "";
		date += "\tѧ��\t\t����\t\t�Ա�\t\tרҵ\t\n";
		String sql = "select * from " + SQLTITLE +" where 1=1 ";
		//���������Ϊ�գ��������������
		if(!isNull(name)){
			sql+=" and name= '"+ name+"'";
		}
		//����Ա�Ϊ�գ�����Ա������
		if(!isNull(sex)){
			sql+=" and sex= '"+sex+"'";
		}
		//���רҵ��Ϊ�գ����רҵ������
		if(!isNull(profess)){
			sql+=" and profess= '"+profess+"'";
		}
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				date += "\t" + rs.getString(1);
				date += "\t\t" + rs.getString(2);
				date += "\t\t" + rs.getString(3);
				date += "\t\t" + rs.getString(4);
				date += "\t\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return date;
	}
//�ر����ݿ�
	public void close() {
		try {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ж��ַ����Ƿ�Ϊ�յķ���
	public boolean isNull(String str){
		if(str==null || str.trim().equals("")){
			return true;
		}else{
			return false;
		}
	}

}
