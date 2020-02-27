package DB;
import java.sql.*;


public class ConnectAccountDB {
	//���ݿ����ӵ�һ������
	private String driver = "com.mysql.jdbc.Driver";
	private String name = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/ss";
	Connection con;
	public static final String SQLTITLE = "account";
	PreparedStatement ps;
	Statement st;
	ResultSet rs;
	//�������ݿ�
	public ConnectAccountDB() {
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
		System.out.println("���ӳɹ�");
	}
	//�ж��Ƿ��Ѿ������û���
	public boolean isExist(String username){
		String sql = "select * from "+SQLTITLE+" where username='"+username+"'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if(rs.next()){
				//���ڷ�����
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//�Ѿ�����
		return false;
	}
//ע���û�
	public int insert(String username,String password) {
		int i=0;
		String sql = "insert into "+SQLTITLE+" values (?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();  
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}
//��֤�Ƿ������¼
	public boolean isLogin(String username,String password){
		String sql = "select * from "+SQLTITLE+" where username='"+username+"'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if(rs.next()){
				//���������������ϣ�������
				if(rs.getString(2).equals(password)){			
					return true;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	//���ݿ�ر�
	public void close(){
		try {
			if(ps != null){
				ps.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
