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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DoctorGUI {
	Object[] columns;
	DefaultTableModel model;
	Object[] row;
	
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
		
	//////////////////////New Frame///////////////////////////////////
		
		JFrame frame2 = new JFrame();
		JTable table2 = new JTable();
		frame2.setSize(800, 800);
		frame2.setLocationRelativeTo(null);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel2 = new JPanel(new GridBagLayout());
		
		GridBagConstraints c2 = new GridBagConstraints();
		
		/////////////////////
		//Object[] columns = {"Name","Phone"};
		//model = new DefaultTableModel();
		//model.setColumnIdentifiers(columns);
		//model.addRow(new Object[] {"Column 1", "Column 2"});
		//table2.setModel(model);
		
		//table2.setRowHeight(30);
		
		JScrollPane pane1 = new JScrollPane(table2);
		//pane1.setVisible(false);
		c2.gridx = 0;
		c2.gridy = 2;
		pane1.setSize(600, 600);
		
		panel2.add(pane1, c2);
		frame2.setLayout(null);
		////////////////////
		
		JButton display = new JButton("Display Patients.");
		c.gridx = 0;
		c.gridy = 0;
		
		panel2.add(display, c2);
		
		display.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//pane1.setVisible(true);
				Object[] columns = {"Name","Phone"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(columns);
				table2.setModel(model);
				table2.setRowHeight(30);
				
				refresh();
				
			}
			
		});
		
		JButton newp = new JButton("New Patients.");
		c.gridx = 1;
		c.gridy = 0;
		
		panel2.add(newp, c);
		
		newp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JButton visits = new JButton("Display Patient Visits.");
		c.gridx = 2;
		c.gridy = 0;
		
		panel2.add(visits, c);
		
		visits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JButton enterv = new JButton("Enter new Visit.");
		c.gridx = 3;
		c.gridy = 0;
		
		panel2.add(enterv, c);
		
		enterv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		frame2.add(panel2);
		frame2.setContentPane(panel2);
		
		//////////////////////New Frame End///////////////////////////////
		
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
						frame.dispose();
						frame2.setVisible(true);
					}else {
						passwordcheck.setVisible(true);
					}
				}catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
			}
			
		});
		
		//////////////////////////////Button Action Listener END////////////////////////////
		
		/////////////////////////////Select DoctorID start//////////////////////////
		
		
		
		/////////////////////////////Select DoctorID end//////////////////////////
		
		frame.add(panel);
		frame.setVisible(true);
		
	
		
	}
	
	private static void getDoctorID() {
		String sql = "SELECT DoctorID FROM DoctorTable WHERE Username = ?";
		
		try {
			
		}
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
	
	public void refresh() {
		row = new Object[3];
		model.setRowCount(0);
		String sql = "SELECT Name, Phone number FROM Patient Table WHERE DoctorID = ?";
		
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, arg1);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while (rs.next()) {
				row[0] = rs.getString("Name");
				row[1] = rs.getString("Phone number");
				
				model.addRow(row);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}


}
