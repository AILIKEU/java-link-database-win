package DB;
import java.sql.*;


public class ConnectAccountDB {
	//数据库连接的一般流程
	private String driver = "com.mysql.jdbc.Driver";
	private String name = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/ss";
	Connection con;
	public static final String SQLTITLE = "account";
	PreparedStatement ps;
	Statement st;
	ResultSet rs;
	//连接数据库
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
		System.out.println("连接成功");
	}
	//判断是否已经存在用户名
	public boolean isExist(String username){
		String sql = "select * from "+SQLTITLE+" where username='"+username+"'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if(rs.next()){
				//存在返回真
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//已经存在
		return false;
	}
//注册用户
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
//验证是否允许登录
	public boolean isLogin(String username,String password){
		String sql = "select * from "+SQLTITLE+" where username='"+username+"'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if(rs.next()){
				//如果姓名和密码符合，返回真
				if(rs.getString(2).equals(password)){			
					return true;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	//数据库关闭
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
