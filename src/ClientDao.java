import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDao {
	private MysqlConnect myconn;

	public ClientDao() {
		myconn = MysqlConnect.getInstance();
	}

	public void insert(Client c) {
		Connection conn = myconn.getConn();
		String sql = "insert into client(id,pw,age,gender,phone) values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getPw());
			pstmt.setInt(3, c.getAge());
			pstmt.setString(4, c.getGender());
			pstmt.setString(5, c.getPhone());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Client select(String id) {
		ResultSet rs;
		Client c = null;
		Connection conn = myconn.getConn();
		String sql = "select * from client where id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int numm = rs.getInt(1);
				String id2 = rs.getString(2);
				String pw = rs.getString(3);
				int age = rs.getInt(4);
				String gender = rs.getString(5);
				String phone = rs.getString(6);
				c = new Client(numm, id2, pw, age, gender, phone);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return c;
	}

	public void update(Client c) {
		Connection conn = myconn.getConn();
		String sql = "update client set pw=?, age=?,gender=?, phone=? where id =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, c.getPw());
			pstmt.setInt(2, c.getAge());
			pstmt.setString(3, c.getGender());
			pstmt.setString(4, c.getPhone());
			pstmt.setString(5,c.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void delete(String id) {
		Connection conn = myconn.getConn();
		String sql = "delete from client where id =? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
