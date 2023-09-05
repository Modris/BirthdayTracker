package com.modris.Model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.LocalDate;

public class TrackerDao {
	private String url;
	private String username;
	private String password;
	private int credsId;
	private Connection con;

	public TrackerDao(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;

	}

	public void setCredsId(int id) {
		this.credsId = id;
	}

	private void connect() {

		try {
			if (con == null || con.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	private void disconnect() {

		try {
			if (con != null || !con.isClosed()) {
				con.close();
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public List<Tracker> listAll(String order) {

		updateAge();
		updateBirthdayDatesInDB();
		updateCountdownInDB();

		List<Tracker> list1 = new ArrayList<>();
		// impelemt JDBC steps. 1) import the package.
		String query = chooseQuery(order);
		connect();
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, credsId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				LocalDate birth_date = rs.getDate(4).toLocalDate();
				int countdown_days = rs.getInt(5);
				LocalDate next_bday = rs.getDate(6).toLocalDate();
				Tracker tacky = new Tracker(id, name, age, birth_date, countdown_days, next_bday);
				list1.add(tacky);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		disconnect();
		return list1;
	}

	private String chooseQuery(String order) {
		String query = "";
		switch (order) {
		case "sortByDefaultDesc":
		case "sortByCountDownDays":
			query = "SELECT * FROM tracker WHERE creds_id = ? ORDER BY countdown_days";
			break;
		case "sortByCountDownDaysDesc":
			query = "SELECT * FROM tracker WHERE creds_id = ? ORDER BY countdown_days DESC";
			break;
		case "sortByAge":
			query = "SELECT * FROM tracker WHERE creds_id = ?  ORDER BY age";
			break;
		case "sortByAgeDesc":
			query = "SELECT * FROM tracker WHERE creds_id = ?  ORDER BY age DESC";
			break;
		case "sortByName":
			query = "SELECT * FROM tracker WHERE creds_id = ?  ORDER BY name";
			break;
		case "sortByNameDesc":
			query = "SELECT * FROM tracker WHERE creds_id = ? ORDER BY name DESC";
			break;
		default: // "sortByDefault":
			query = "SELECT * FROM tracker WHERE creds_id = ?";
			break;
		}
		return query;

	}

	private void updateAge() {

		connect();
		String query = "UPDATE TRACKER\r\n" + "	SET age = TIMESTAMPDIFF(YEAR, birth_date, CURDATE())\r\n"
				+ "    WHERE id>0";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			int rows = pst.executeUpdate();
			pst.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		disconnect();

	}

	private void updateBirthdayDatesInDB() {
		connect();
		String query = "UPDATE TRACKER\r\n" + "	SET next_birthday_date = \r\n"
				+ "		CASE WHEN EXTRACT(MONTH FROM NOW()) < EXTRACT(MONTH FROM birth_date) \r\n"
				+ "				  THEN DATE(CONCAT(EXTRACT(YEAR FROM NOW()),'-',EXTRACT(MONTH FROM birth_date),'-', EXTRACT(DAY FROM birth_date)))\r\n"
				+ "            WHEN EXTRACT(MONTH FROM NOW()) = EXTRACT(MONTH FROM birth_date)\r\n"
				+ "				 AND EXTRACT(DAY FROM NOW()) <= EXTRACT(DAY FROM birth_date)\r\n"
				+ "					THEN DATE(CONCAT(EXTRACT(YEAR FROM NOW()),'-',EXTRACT(MONTH FROM birth_date),'-', EXTRACT(DAY FROM birth_date)))\r\n"
				+ "             ELSE DATE(CONCAT(EXTRACT(YEAR FROM NOW())+1,'-',EXTRACT(MONTH FROM birth_date),'-', EXTRACT(DAY FROM birth_date)))   \r\n"
				+ "             END\r\n" + "             WHERE id>0";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			int rows = pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		disconnect();
	}

	private void updateCountdownInDB() {
		connect();
		String query = "UPDATE TRACKER\r\n" + "	SET countdown_days = DATEDIFF(next_birthday_date, NOW())\r\n"
				+ "    WHERE id > 0";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			int rows = pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		disconnect();
	}

	// Create method/insert
	public void insertTracker(Tracker track) {
		String query = "INSERT INTO tracker VALUES (?,?,?,?,?,?,?)";
		try {
			connect();
			PreparedStatement pst = con.prepareStatement(query);

			pst.setNull(1, java.sql.Types.NULL);
			pst.setString(2, track.getName());
			if (track.getAge() < 0) {
				track.setAge(0);
			}
			pst.setInt(3, track.getAge());
			pst.setObject(4, track.getBirth_date());
			pst.setInt(5, 0);
			pst.setObject(6, track.getBirthday_date());
			pst.setInt(7, credsId);
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		disconnect();
	}

	public void delete(int id) {
		connect();
		String query = "DELETE FROM tracker WHERE id=? AND creds_id = ?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.setInt(2, credsId);
			int rows = pst.executeUpdate();
			System.out.println(rows + " deleted");
			pst.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		disconnect();
	}

	public void update(String name, int age, LocalDate birthYear, int id) {
		connect();
		String query = "UPDATE tracker SET name = ?, age = ?, birth_date = ? WHERE id = ? AND creds_id = ?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setObject(3, birthYear);
			pst.setInt(4, id);
			pst.setInt(5, credsId);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		disconnect();
	}

}
