package CW_Task2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.Color;

public class AdminPanel extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField text_fname;
	private JTextField text_lname;
	private JTextField text_age;
	private JTextField text_date;
	DefaultTableModel model;
	private JTextField text_id;
	JComboBox text_gen;
	JComboBox text_status;

//	=========================== Lunch Application =========================

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
					frame.setVisible(true);
				}

				catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

//	=========================== Create Frame =========================

	public AdminPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome To Admin Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(10, 10, 489, 51);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Add person details");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Stencil Std", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(509, 22, 369, 41);
		contentPane.add(lblNewLabel_1);

		JLabel lblFirstname = new JLabel("FIRSTNAME");
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFirstname.setBounds(10, 115, 87, 17);
		contentPane.add(lblFirstname);

		JLabel lblLastname = new JLabel("LASTNAME");
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastname.setBounds(10, 153, 87, 31);
		contentPane.add(lblLastname);

		JLabel lblAge = new JLabel("AGE");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAge.setBounds(10, 208, 87, 16);
		contentPane.add(lblAge);

		text_fname = new JTextField();
		text_fname.setBounds(98, 113, 180, 19);
		contentPane.add(text_fname);
		text_fname.setColumns(10);

		text_lname = new JTextField();
		text_lname.setColumns(10);
		text_lname.setBounds(101, 158, 177, 19);
		contentPane.add(text_lname);

		text_age = new JTextField();
		text_age.setColumns(10);
		text_age.setBounds(101, 207, 177, 19);
		contentPane.add(text_age);

		JLabel lblGender = new JLabel("GENDER");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGender.setBounds(10, 254, 87, 17);
		contentPane.add(lblGender);

		String[] gen = { "Male", "Female", "Others" };
		text_gen = new JComboBox(gen);
		text_gen.setBounds(101, 253, 177, 21);
		contentPane.add(text_gen);

		JLabel lblNewLabel_2 = new JLabel(" COVID STATUS");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(64, 282, 155, 21);
		contentPane.add(lblNewLabel_2);

		String[] stat = { "Positive(+ve)", "Negative(-ve)" };
		text_status = new JComboBox(stat);
		text_status.setBounds(35, 306, 180, 36);
		contentPane.add(text_status);

		JButton btnNewButton_1 = new JButton("Add Patient To Contact");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ContactTrace().setVisible(true);
			}
		});

		btnNewButton_1.setBounds(28, 473, 262, 51);
		contentPane.add(btnNewButton_1);

		text_date = new JTextField();
		text_date.setColumns(10);
		text_date.setBounds(35, 378, 177, 31);
		contentPane.add(text_date);

		JButton btnNewButton_2 = new JButton("SAVE");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String id = getPersonId();
				String fname = text_fname.getText();
				String lname = text_lname.getText();
				String age = text_age.getText();
				String gender = String.valueOf(text_gen.getSelectedItem());
				String status = String.valueOf(text_status.getSelectedItem());
				String date = text_date.getText();

				if (fname.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Firstname is required.");
				}

				else if (gender.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Gender is required.");
				}

				else if (age.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Age is required.");
				}

				else {
					String[] citizenDetail = { id, fname, lname, age, gender, status, date };
					String message = null;
					message = addCitizen(citizenDetail);
					JOptionPane.showMessageDialog(btnNewButton_2, "New citizen added!");
					get_table();
				}
			}
		});

		btnNewButton_2.setBounds(24, 435, 114, 27);
		contentPane.add(btnNewButton_2);

		JLabel lblCovidDateifAny = new JLabel("DATE OF COVID");
		lblCovidDateifAny.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCovidDateifAny.setBounds(64, 353, 127, 26);
		contentPane.add(lblCovidDateifAny);
		get_table();
	}

//	=========================== Increment Auto=========================

	String getPersonId() {
		FileHand fh = new FileHand();
		ArrayList<String> data = fh.ReadOrFetchAll(Constants.citizenDetail);
		if (data.size() > 0) {

			int prevId = Integer.parseInt(data.get(data.size() - 1).split(Constants.spliter)[Constants.citizenID]);
			return Integer.toString(prevId + 1);
		}

		else {
			return "0";
		}
	}

	public void get_table() {
		String filepath = "citizen.txt";
		File file = new File(filepath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String firstline = br.readLine().trim();
			String[] columsName = firstline.split(",");

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(300, 77, 634, 447);
			contentPane.add(scrollPane);

			table = new JTable();
			model = new DefaultTableModel();
			Object[] column = { "ID", "Firstname", "Lastname", "Age", "Gender", "Status", "Date" };
			Object[] row = new Object[0];
			model.setColumnIdentifiers(column);
			table.setModel(model);
			scrollPane.setViewportView(table);

			JButton btnNewButton_2 = new JButton("UPDATE");
			btnNewButton_2.setForeground(Color.BLACK);
			btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
			btnNewButton_2.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String id = text_id.getText();
					String fname = text_fname.getText();
					String lname = text_lname.getText();
					String age = text_age.getText();
					String gender = String.valueOf(text_gen.getSelectedItem());
					String status = String.valueOf(text_status.getSelectedItem());
					String date = text_date.getText();

					if (fname.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane, "Firstname is required.");
					}

					else if (gender.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane, "Gender is required.");
					}

					else if (status.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane, "Status is required.");
					}

					else {
						String[] citizenDetail = { id, fname, lname, age, gender, status, date };
						String message = null;
						message = updatePerson(citizenDetail);
						JOptionPane.showMessageDialog(btnNewButton_2, "Citizen Updated!!");
						get_table();

					}
				}
			});

			btnNewButton_2.setBounds(143, 435, 122, 27);
			contentPane.add(btnNewButton_2);

			JLabel lblSelectedId = new JLabel("SELECTED ID");
			lblSelectedId.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblSelectedId.setBounds(10, 72, 74, 16);
			contentPane.add(lblSelectedId);

			text_id = new JTextField();
			text_id.setColumns(10);
			text_id.setBounds(97, 72, 180, 19);
			contentPane.add(text_id);
			table.addMouseListener(this);

			Object[] tableLines = br.lines().toArray();

			for (int i = 0; i < tableLines.length; i++) {
				String line = tableLines[i].toString().trim();
				String[] dataRow = line.split(",");
				model.addRow(dataRow);
			}

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String addCitizen(String[] addPerson) {
		FileHand file = new FileHand();
		return file.CreateOrAdd(Constants.citizenDetail, addPerson);
	}

	public String updatePerson(String[] changeData) {
		FileHand fh = new FileHand();
		return fh.UpdateFile(Constants.citizenDetail, changeData);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.rowAtPoint(e.getPoint());

		text_id.setText((String) table.getValueAt(row, 0));
		text_fname.setText((String) table.getValueAt(row, 1));
		text_lname.setText((String) table.getValueAt(row, 2));
		text_age.setText((String) table.getValueAt(row, 3));
		text_date.setText((String) table.getValueAt(row, 6));

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
