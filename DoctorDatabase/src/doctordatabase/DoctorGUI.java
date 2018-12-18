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
	Object[] columns2;
	DefaultTableModel model, model2;
	Object[] row;
	Object[] row2;
	int DoctorID;
	int PatientID;

	public DoctorGUI() {
		JFrame frame = new JFrame();
		JTable table = new JTable();
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		Object[] rows = { "Username", "Password", "Enter" };

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

		////////////////////// New Frame///////////////////////////////////

		JFrame frame2 = new JFrame();
		JTable table2 = new JTable();
		JTable table3 = new JTable();
		frame2.setSize(1200, 1200);
		frame2.setLocationRelativeTo(null);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel2 = new JPanel(new GridBagLayout());

		GridBagConstraints c2 = new GridBagConstraints();

		///////////// Patient Table Start////////
		Object[] columns = { "Name", "Phone" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		model.addRow(new Object[] { "Column 1", "Column 2" });
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
		//////////// Patient Table End////////

		///////////////////// add patient options start////////////////////////////

		JLabel pname = new JLabel("Enter name of Patient");
		c2.gridy = 1;
		c2.gridx = 0;
		c2.gridwidth = 1;

		pname.setVisible(false);

		panel2.add(pname, c2);

		JLabel pnum = new JLabel("Enter phone number");
		c2.gridy = 2;
		c2.gridx = 0;
		c2.gridwidth = 1;

		pnum.setVisible(false);

		panel2.add(pnum, c2);

		JTextField pnamt = new JTextField(25);
		c2.gridy = 1;
		c2.gridx = 2;
		c2.gridwidth = 2;

		pnamt.setVisible(false);

		panel2.add(pnamt, c2);

		JTextField pphont = new JTextField(25);
		c2.gridy = 2;
		c2.gridx = 2;
		c2.gridwidth = 2;

		pphont.setVisible(false);

		panel2.add(pphont, c2);

		JButton entpat = new JButton("Enter");
		c2.gridy = 3;
		c2.gridx = 0;
		c2.gridwidth = 1;

		entpat.setVisible(false);

		panel2.add(entpat, c2);

		entpat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Enter patient button.");
				String sql = "INSERT INTO PatientTable (Name, Phonenumber, DoctorID) VALUES (?, ?, ?)";

				try {

					Connection conn = connect();
					PreparedStatement ps = conn.prepareStatement(sql);

					ps.setString(1, pnamt.getText());
					ps.setString(2, pphont.getText());
					ps.setInt(3, DoctorID);

					ps.executeUpdate();
					ps.close();
					conn.close();

				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

				refresh(DoctorID);

			}

		});

		///////////////////// add patient options end/////////

		////////////////////// display visits start/////////////////

		Object[] columns2 = { "Date", "Diagnosis", "Medicine" };
		model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(columns2);
		model2.addRow(new Object[] { "Column 1", "Column 2", "Column 3" });
		table3.setModel(model2);

		table3.setRowHeight(30);

		JLabel vname = new JLabel("Enter name of patient.");
		c2.gridx = 0;
		c2.gridy = 1;
		c2.gridwidth = 1;

		panel2.add(vname, c2);
		vname.setVisible(false);

		JTextField vnamet = new JTextField(20);
		c2.gridx = 2;
		c2.gridy = 1;
		c2.gridwidth = 2;

		panel2.add(vnamet, c2);
		vnamet.setVisible(false);

		JScrollPane pane2 = new JScrollPane(table3);
		pane2.setVisible(false);
		c2.gridx = 0;
		c2.gridy = 3;
		c2.gridwidth = 4;
		pane2.setSize(600, 600);

		panel2.add(pane2, c2);
		frame2.setLayout(null);

		JButton entervi = new JButton("Enter");
		c2.gridwidth = 1;
		c2.gridx = 0;
		c2.gridy = 2;
		entervi.setVisible(false);

		entervi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PatientID = getPatientID(vnamet.getText());
				refresh2(PatientID);

			}

		});

		panel2.add(entervi, c2);

		/////////////////////// display visits end///////////////////

		/////////////////// enter new visit start/////////////////////

		JLabel date = new JLabel("Enter Date");
		c2.gridx = 0;
		c2.gridy = 2;
		c2.gridwidth = 1;

		panel2.add(date, c2);
		date.setVisible(false);

		JLabel diagnosis = new JLabel("Enter Diagnosis");
		c2.gridx = 0;
		c2.gridy = 3;
		c2.gridwidth = 1;

		panel2.add(diagnosis, c2);
		diagnosis.setVisible(false);

		JLabel medicine = new JLabel("Enter Medicine");
		c2.gridx = 0;
		c2.gridy = 4;
		c2.gridwidth = 1;

		panel2.add(medicine, c2);
		medicine.setVisible(false);

		JButton entervisit = new JButton("Enter");
		c2.gridx = 0;
		c2.gridy = 5;
		c2.gridwidth = 1;

		panel2.add(entervisit, c2);
		entervisit.setVisible(false);

		JTextField enterdate = new JTextField();
		c2.gridx = 1;
		c2.gridy = 2;
		c2.gridwidth = 2;

		panel2.add(enterdate, c2);
		enterdate.setVisible(false);

		JTextField enterdiagnosis = new JTextField();
		c2.gridx = 1;
		c2.gridy = 3;
		c2.gridwidth = 2;

		panel2.add(enterdiagnosis, c2);
		enterdiagnosis.setVisible(false);

		JTextField entermedicine = new JTextField();
		c2.gridx = 1;
		c2.gridy = 4;
		c2.gridwidth = 2;

		panel2.add(entermedicine, c2);
		entermedicine.setVisible(false);
		
		entervisit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PatientID = getPatientID(vnamet.getText());
				
				String sql = "INSERT INTO PatientVisit (Date, PatientID, Diagnosis, Medicine) VALUES (?, ?, ?, ?)";
				
				try {
					Connection conn = connect();
					PreparedStatement ps = conn.prepareStatement(sql);
					
					ps.setString(1, enterdate.getText());
					ps.setInt(2, PatientID);
					ps.setString(3, enterdiagnosis.getText());
					ps.setString(4, entermedicine.getText());
					
					ps.executeUpdate();
					
					ps.close();
					conn.close();
					
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				
				refresh2(PatientID);
			}
			
				
		});

		/////////////// enter new visit end//////////////////////

		JButton display = new JButton("Display Patients.");
		c2.gridx = 0;
		c2.gridy = 0;
		c2.gridwidth = 1;

		panel2.add(display, c2);

		display.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Display Patients Button");
				pname.setVisible(false);
				pnum.setVisible(false);
				pnamt.setVisible(false);
				pphont.setVisible(false);
				entpat.setVisible(false);
				pane2.setVisible(false);
				entervi.setVisible(false);
				vname.setVisible(false);
				vnamet.setVisible(false);
				date.setVisible(false);
				diagnosis.setVisible(false);
				medicine.setVisible(false);
				entervisit.setVisible(false);
				enterdate.setVisible(false);
				enterdiagnosis.setVisible(false);
				entermedicine.setVisible(false);


				pane1.setVisible(true);
				Object[] columns = { "Name", "Phone" };
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

				pane2.setVisible(false);
				entervi.setVisible(false);
				vname.setVisible(false);
				vnamet.setVisible(false);
				date.setVisible(false);
				diagnosis.setVisible(false);
				medicine.setVisible(false);
				entervisit.setVisible(false);
				enterdate.setVisible(false);
				enterdiagnosis.setVisible(false);
				entermedicine.setVisible(false);


				pane1.setVisible(true);
				Object[] columns = { "Name", "Phone" };
				model = new DefaultTableModel();
				model.setColumnIdentifiers(columns);
				table2.setModel(model);
				table2.setRowHeight(30);

				pname.setVisible(true);
				pnum.setVisible(true);
				pnamt.setVisible(true);
				pphont.setVisible(true);
				entpat.setVisible(true);

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
				System.out.println("Display Patients Button");
				pname.setVisible(false);
				pnum.setVisible(false);
				pnamt.setVisible(false);
				pphont.setVisible(false);
				entpat.setVisible(false);
				pane1.setVisible(false);
				vname.setVisible(false);
				vnamet.setVisible(false);
				date.setVisible(false);
				diagnosis.setVisible(false);
				medicine.setVisible(false);
				entervisit.setVisible(false);
				enterdate.setVisible(false);
				enterdiagnosis.setVisible(false);
				entermedicine.setVisible(false);

				pane2.setVisible(true);
				entervi.setVisible(true);
				vname.setVisible(true);
				vnamet.setVisible(true);
				Object[] columns2 = { "Date", "Diagnosis", "Medicine" };
				model2 = new DefaultTableModel();
				model2.setColumnIdentifiers(columns2);
				table3.setModel(model2);
				table3.setRowHeight(30);

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
				
				
				System.out.println("Display Patients Button");
				pname.setVisible(false);
				pnum.setVisible(false);
				pnamt.setVisible(false);
				pphont.setVisible(false);
				entpat.setVisible(false);
				pane1.setVisible(false);
				entervi.setVisible(false);

				
				vname.setVisible(true);
				vnamet.setVisible(true);
				date.setVisible(true);
				diagnosis.setVisible(true);
				medicine.setVisible(true);
				entervisit.setVisible(true);
				enterdate.setVisible(true);
				enterdiagnosis.setVisible(true);
				entermedicine.setVisible(true);
				
				
				refresh2(PatientID);
				
			}

		});

		frame2.add(panel2);
		frame2.setContentPane(panel2);

		////////////////////// New Frame End///////////////////////////////

		////////////////////////// Button Action Listener START////////////////////////////////

		enter.addActionListener(new ActionListener() {

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

					if (rs.getString("Password").equals(passt.getText())) {
						frame.dispose();
						frame2.setVisible(true);
					} else {
						passwordcheck.setVisible(true);
					}

					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

			}

		});

		////////////////////////////// Button Action Listener END////////////////////////////

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

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void refresh2(int PatientID) {
		System.out.println("Refresh2 method");
		row2 = new Object[3];
		model2.setRowCount(0);
		String sql = "SELECT Date, Diagnosis, Medicine FROM PatientVisit WHERE PatientID = ?";

		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, PatientID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				row2[0] = rs.getString("Date");
				row2[1] = rs.getString("Diagnosis");
				row2[2] = rs.getString("Medicine");

				model2.addRow(row2);
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private int getPatientID(String bob) {
		System.out.println("getPatientID");
		String sql = "SELECT PatientID FROM PatientTable WHERE Name = ?";

		int Patientid = 0;

		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bob);
			ResultSet rs = pstmt.executeQuery();

			Patientid = rs.getInt("PatientID");

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return Patientid;
	}

}
