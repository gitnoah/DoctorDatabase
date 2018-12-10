package doctordatabase;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DoctorGUI {
	
	public DoctorGUI() {
		JFrame frame = new JFrame();
		JTable table = new JTable();
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		Object[] rows = {"Username", "Password", "Enter"};
		
		JLabel user = new JLabel("Username");
		c.gridx = 0;
		c.gridy = 0;
		
		panel.add(user, c);
		
		
		JLabel pass = new JLabel("Password");
		c.gridx = 0;
		c.gridy = 1;
		
		panel.add(pass, c);
		
		JButton enter = new JButton("Enter");
		c.gridx = 0;
		c.gridy = 2;
		
		panel.add(enter, c);
		
		JTextField usert = new JTextField(20);
		c.gridx = 1;
		c.gridy = 0;
		
		panel.add(usert, c);
		
		JTextField passt = new JTextField(20);
		c.gridx = 1;
		c.gridy = 1;
		
		panel.add(passt, c);
		
		JLabel passwordcheck = new JLabel("Wrong Password.");
		c.gridx = 1;
		c.gridy = 2;
		
		panel.add(passwordcheck, c);
		passwordcheck.setVisible(false);
		
		//////////////////////////Button Action Listener START////////////////////////////////
		
		enter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sql = "SELECT Password FROM DoctorTable WHERE Username = ?";
				
				try {
					Connection conn = connect();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, usert.getText());
					ResultSet rs = pstmt.executeQuery();
					
					
					
					if(rs.getString("Password").equals(passt.getText())) {
						System.out.println("Hey");
					}else {
						passwordcheck.setVisible(true);
					}
				}catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
			}
			
		});
		
		//////////////////////////////Button Action Listener END////////////////////////////
		
		frame.add(panel);
		frame.setVisible(true);
		
	}
	
	private Connection connect() {
		Connection conn = null;
		
		try {
			String url = "jdbc:sqlite:H:\\git\\DoctorDatabase\\DoctorDatabase\\DoctorDatabase.db";
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

}
