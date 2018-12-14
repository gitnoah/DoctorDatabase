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
	int DoctorID;
	
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
		frame2.setSize(1200, 1200);
		frame2.setLocationRelativeTo(null);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel2 = new JPanel(new GridBagLayout());
		
		GridBagConstraints c2 = new GridBagConstraints();
		
		/////////////////////
		Object[] columns = {"Name","Phone"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		model.addRow(new Object[] {"Column 1", "Column 2"});
		table2.setModel(model);
		
		table2.setRowHeight(30);
		
		JScrollPane pane1 = new JScrollPane(table2);
		pane1.setVisible(false);
		c2.gridx = 0;
		c2.gridy = 5;
		c2.gridwidth = 4;
		pane1.setSize(600, 600);
		
		panel2.add(pane1, c2);
		frame2.setLayout(null);
		////////////////////
		
		/////////////////////add patient options////////////////////////////
		
		JLabel pname = new JLabel("Enter name of Patient");
		c.gridy = 1;
		c.gridx = 0;
		
		pname.setVisible(false);
		
		panel2.add(pname, c);
		
		JLabel pnum = new JLabel("Enter phone number");
		c.gridy = 2;
		c.gridx = 0;
		
		pnum.setVisible(false);
		
		panel2.add(pnum, c);
		
		JTextField pnamt = new JTextField(30);
		c.gridy = 1;
		c.gridx = 1;
		
		pnamt.setVisible(false);
		
		panel2.add(pnamt, c);
		
		JTextField pphont = new JTextField(30);
		c.gridy = 2;
		c.gridx = 1;
		
		pphont.setVisible(false);
		
		panel2.add(pphont);
		
		JButton entpat = new JButton("Enter");
		c.gridy = 3;
		c.gridx = 0;
		
		entpat.setVisible(false);
		
		panel2.add(entpat);
		
		entpat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sql = "INSERT INTO PatientTable (Name, Phone number, DoctorID) VALUES (?, ?, ?)";
				
				try {
					
				}
				
			}
			
		});
		
		
		
		
		
		
		//////////////////////////////
		
		JButton display = new JButton("Display Patients.");
		c2.gridx = 0;
		c2.gridy = 0;
		c2.gridwidth = 1;
		
		panel2.add(display, c2);
		
		display.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Display Patients Button");
				pane1.setVisible(true);
				Object[] columns = {"Name","Phone"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(columns);
				table2.setModel(model);
				table2.setRowHeight(30);
				
				refresh(DoctorID);
				
			}
			
		});
		
		JButton newp = new JButton("New Patients.");
		c2.gridx = 1;
		c2.gridy = 0;
		c2.gridwidth = 1;
		
		panel2.add(newp, c2);
		
		newp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("New Patients");
				pane1.setVisible(true);
				Object[] columns = {"Name","Phone"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(columns);
				table2.setModel(model);
				table2.setRowHeight(30);
				
				refresh(DoctorID);
				
				
			}
			
		});
		
		JButton visits = new JButton("Display Patient Visits.");
		c2.gridx = 2;
		c2.gridy = 0;
		c2.gridwidth = 1;
		
		panel2.add(visits, c2);
		
		visits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JButton enterv = new JButton("Enter new Visit.");
		c2.gridx = 3;
		c2.gridy = 0;
		c2.gridwidth = 1;
		
		panel2.add(enterv, c2);
		
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
					System.out.println("Enter Button");
					Connection conn = connect();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, usert.getText());
					ResultSet rs = pstmt.executeQuery();
					DoctorID = getDoctorID(usert.getText());
					
					
					
					if(rs.getString("Password").equals(passt.getText())) {
						frame.dispose();
						frame2.setVisible(true);
					}else {
						passwordcheck.setVisible(true);
					}
					
					rs.close();
					pstmt.close();
					conn.close();
				}catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
			}
			
		});
		
		//////////////////////////////Button Action Listener END////////////////////////////
		
		
		
		
		frame.add(panel);
		frame.setVisible(true);
		
	
		
	}
	
	private int getDoctorID(String usert) {
		System.out.println("getDoctorID");
		String sql = "SELECT DoctorID FROM DoctorTable WHERE Username = ?";
		
		int Doctorid = 0;
		
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usert);
			ResultSet rs = pstmt.executeQuery();
			
			Doctorid = rs.getInt("DoctorID");
			System.out.println("Doctor ID = " + Doctorid);
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return Doctorid;
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
//////Issue	
	public void refresh(int DoctorID) {
		System.out.println("Refresh method");
		row = new Object[2];
		model.setRowCount(0);
		String sql = "SELECT Name, Phonenumber FROM PatientTable WHERE DoctorID = ?";
		
		
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, DoctorID);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				row[0] = rs.getString("Name");
				row[1] = rs.getString("Phonenumber");
				
				model.addRow(row);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}


}
