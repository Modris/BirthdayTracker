package com.modris.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	private String url;
	private String username;
	private String password;
	private int credsId;
	private Connection con;

	public LoginDao(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
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

	public String getPassword(String username) {

		String query = "SELECT * FROM creds WHERE username = ?";
		connect();
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				credsId = rs.getInt(1);
				String hashedPassword = rs.getString(3);
				rs.close();
				pst.close();
				return hashedPassword;
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return "";
	}

	public boolean checkCredentials(String username, String password) {
		String query = "SELECT * FROM creds WHERE username = ? AND password = ?";
		connect();
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				credsId = rs.getInt(1);
				rs.close();
				pst.close();
				return true;
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return false;
	}

	public int getCredsId() {
		return credsId;
	}

	public void register(String uName, String passW) {
		connect();
		String query = "INSERT INTO creds VALUES(?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setNull(1, java.sql.Types.NULL);
			pst.setString(2, uName);
			pst.setString(3, passW);
			pst.executeUpdate();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();

	}

	public boolean checkNicknameAvailability(String name) {
		connect();
		String query = "SELECT * FROM creds WHERE username = ?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				pst.close();
				disconnect();
				return true;
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return false;
	}
}
