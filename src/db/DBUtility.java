package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtility {

	public static ResultSet executeQuery(Connection con, String query) {
		ResultSet rs = null;
		try {
			Statement state = con.createStatement();
			rs = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static String printImagesAsTable(ResultSet rs) {
		String str = "";
		ResultSetMetaData rsmd;

		try {
			rsmd = rs.getMetaData();
			str = str + "<table border=\"1\" width=\"100%\" cellspacing=\"1\" cellpadding=\"1\">\r\n" + "<thead>\r\n"
					+ "<tr>";
			str = str + "</tr>\r\n" + "</thead>\r\n" + "<tbody>";
			str = str + "<tr>";
			int count=0;
			while (rs.next()) {
				str = str + ("<td width=\"30%\"><img style=\"display:block;\" width=\"100%\" src=\"" + rs.getString(2) + "\"></td>");
				count++;
				if(count==3) {
					count=0;
					str = str + "</tr><tr>";
				}
			}
			str = str + "</tr>";
			str = str + "\r\n" + "</tbody>\r\n" + "</table>";
			return str;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Nothing here!";
	}

	public static String printEntireRSAsTable(ResultSet rs) {
		String str = "";
		ResultSetMetaData rsmd;

		try {
			rsmd = rs.getMetaData();
			str = str + "<table border=\"1\" width=\"100%\" cellspacing=\"1\" cellpadding=\"1\">\r\n" + "<thead>\r\n"
					+ "<tr>";
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				str = str + ("<th>" + rsmd.getColumnName(i) + "</th>");
			}
			str = str + "</tr>\r\n" + "</thead>\r\n" + "<tbody>";
			while (rs.next()) {
				str = str + "<tr>";
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					str = str + ("<td>" + rs.getString(i) + "</td>");
				}
				str = str + "</tr>";
			}
			str = str + "\r\n" + "</tbody>\r\n" + "</table>";
			return str;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Nothing here!";
	}

	public static boolean executeUpdate(Connection con, String query) {
		Statement state;
		try {
			state = con.createStatement();
			System.out.println(state.executeUpdate(query) + " Rows  affected.");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void useDB(Connection con, String database) {
		try {
			con.setCatalog(database);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
