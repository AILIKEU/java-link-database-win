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
	//连接数据库
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
	//插入
	public int insert(String insertNo, String insertName,String insertSex,String insertProfess) {
		//如果插入成功，i会编程产生影响的行数
		int i = 0;
		String sql = "insert into " + SQLTITLE + " values (?,?,?,?)";
		try {
			//执行sql语句
			ps = con.prepareStatement(sql);
			//一次设置每个？的值，也就是要插入的值
			ps.setString(1, insertNo);
			ps.setString(2, insertName);
			ps.setString(3, insertSex);
			ps.setString(4, insertProfess);
			//执行
			i = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	// 判断是否已经存在用户名
	public boolean isExist(String No) {
		String sql = "select * from " + SQLTITLE + " where No='"
				+ No + "'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if (rs.next()) {
				// 存在返回真
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 已经存在
		return false;
	}
	//	删除方法
	public int del(String delNo){ //传入要删除的参数
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
	//更新操作
	public int update(String updateNo,String updateName,String updateSex,String updateProfess){
		int i=0;
		try {
			//如果姓名文本框为空，则不需要更新
			if(updateName!=null && !updateName.trim().equals("")){
				String sql="update "+SQLTITLE+" set Name=? where No=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, updateName);
				ps.setString(2,updateNo);
				i = ps.executeUpdate();
			}
			//如果性别文本框为空，则不需要更新
			if(updateSex!=null && !updateSex.trim().equals("")){
				String sql="update "+SQLTITLE+" set Sex=? where No=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, updateSex);
				ps.setString(2,updateNo);
				i = ps.executeUpdate();
			}
			//如果专业文本框为空，则不需要更新
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
//以string方式返回数据库内容
	public String showDate() {
		String date = "";
		date += "\t学号\t\t姓名\t\t性别\t\t专业\t\n";
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
	//按学号查询
	public String getDataByNo(String no){
		String date = "";
		date += "\t学号\t\t姓名\t\t性别\t\t专业\t\n";
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
	//多条件查询方法
	public String getDataByMulti(String name,String sex,String profess){
		String date = "";
		date += "\t学号\t\t姓名\t\t性别\t\t专业\t\n";
		String sql = "select * from " + SQLTITLE +" where 1=1 ";
		//如果姓名不为空，添加姓名的条件
		if(!isNull(name)){
			sql+=" and name= '"+ name+"'";
		}
		//如果性别不为空，添加性别的条件
		if(!isNull(sex)){
			sql+=" and sex= '"+sex+"'";
		}
		//如果专业不为空，添加专业的条件
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
//关闭数据库
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
	//判断字符串是否为空的方法
	public boolean isNull(String str){
		if(str==null || str.trim().equals("")){
			return true;
		}else{
			return false;
		}
	}

}
