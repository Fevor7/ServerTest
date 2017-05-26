package by.htp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDaoImpl {

	public List<Equipment> fetchAllEquip() {
		
		String dbUrl = "jdbc:mysql://localhost/mysport?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String dbUser = "root";
		String dbPass = "root";
		String driverName = "com.mysql.cj.jdbc.Driver";
		List<Equipment> equipList = new ArrayList<Equipment>();
		Connection conn = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName(driverName);
     		conn = DriverManager.getConnection(dbUrl, dbUser,dbPass);
			 ps = conn.prepareStatement("select * from mysport.equipment");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Long equipId = rs.getLong(1);
				String name = rs.getString(2);
				String title = rs.getString(3);
				Double price = rs.getDouble(4);
				String type = rs.getString(5);
				String url = rs.getString(6);
				Equipment equip = new Equipment (equipId, name, title, price, type,url);
				equipList.add(equip);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            try { conn.close(); } catch(SQLException se) { se.printStackTrace();}
            try { ps.close(); } catch(SQLException se) {se.printStackTrace();}
            try { rs.close(); } catch(SQLException se) {se.printStackTrace();}
        }
		
		return equipList;
	}
}
