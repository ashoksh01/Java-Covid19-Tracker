package CW_Task2;

import java.awt.EventQueue;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ContactTrace extends JFrame {
	File file = new File("TraceMatrix.txt");
	private JPanel contentPane;
	JComboBox<Combo> per_1;
	JComboBox<Combo> per_2;
	private ArrayList<String[]> array_string;
	int[][] adjacency_matrix = new int[61][61];
	private ArrayList<String[]> all_data;

//	=========================== Lunch Application =========================

	public static void main(String[] args) {
		ContactTrace frame = new ContactTrace();
		frame.setVisible(true);
	}

//	=========================== Create Frame =========================

	public ContactTrace() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Person one");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(64, 88, 148, 29);
		contentPane.add(lblNewLabel);

		JLabel lblPerson = new JLabel("Person two");
		lblPerson.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPerson.setBounds(64, 170, 148, 25);
		contentPane.add(lblPerson);

		per_1 = new JComboBox<>();
		per_1.setBounds(234, 92, 235, 25);
		contentPane.add(per_1);

		per_2 = new JComboBox<>();
		per_2.setBounds(234, 172, 235, 25);
		contentPane.add(per_2);

		JLabel lblNewLabel_1 = new JLabel("CONNECTED WITH");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(255, 132, 186, 29);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(38, 283, 148, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object p1 = per_1.getSelectedItem();
				assert p1 != null;
				int s1 = ((Combo) p1).getValue();

				Object p2 = per_2.getSelectedItem();
				int s2 = ((Combo) p2).getValue();

				trace(s1, s2);
				JOptionPane.showMessageDialog(btnNewButton, "Matrix written!");

			}
		});
		contentPane.add(btnNewButton);

		JButton btnGoToDashboard = new JButton("GO TO DASHBOARD");
		btnGoToDashboard.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGoToDashboard.setBounds(218, 338, 235, 29);
		contentPane.add(btnGoToDashboard);
		btnGoToDashboard.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminPanel().setVisible(true);
			}
		});

		JButton btnHighRisk = new JButton("HIGH RISK");
		btnHighRisk.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHighRisk.setBounds(196, 285, 174, 29);
		btnHighRisk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HighRisk(adjacency_matrix).setVisible(true);
			}
		});
		contentPane.add(btnHighRisk);

		JButton btnGoToDashboard_1_1 = new JButton("LOW RISK");
		btnGoToDashboard_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnGoToDashboard_1_1.setBounds(401, 286, 235, 29);
		btnGoToDashboard_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ModerateRisk(adjacency_matrix).setVisible(true);
			}
		});
		contentPane.add(btnGoToDashboard_1_1);

		JLabel lblNewLabel_2 = new JLabel("Connection Persons");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel_2.setBounds(203, 11, 317, 46);
		contentPane.add(lblNewLabel_2);
		read_MatrixFirst();
		fetch_data();
		fillCombo();
	}

	public void fillCombo() {
		for (String[] i : array_string) {
			per_1.addItem(new Combo(i[1], Integer.parseInt(i[0])));
			per_2.addItem(new Combo(i[1], Integer.parseInt(i[0])));
		}
	}

	public void fetch_data() {
		File f = new File("citizen.txt");
		Object[] tablelines;
		array_string = new ArrayList<String[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			tablelines = br.lines().toArray();
			for (int i = 0; i < tablelines.length; i++) {
				String taine = tablelines[i].toString().trim();
				String[] arr = taine.split(",");
//				System.out.println(Arrays.toString(arr));
				array_string.add(arr);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void trace(int source, int destination) {
		System.out.println("Source: " + source);
		System.out.println("Destiantion: " + destination);
		adjacency_matrix[source][destination] = 1;
		writeMatrix();

	}

	void writeMatrix() {
		StringBuilder builder = new StringBuilder();
		try {
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					builder.append(adjacency_matrix[i][j]);
					if (j < 19) {
						builder.append(",");
					}
				}

				builder.append("\n");
			}

			BufferedWriter wr = new BufferedWriter(new FileWriter("TraceMatrix.txt"));
			wr.write(builder.toString());
			wr.close();
		}

		catch (Exception e) {
			System.out.println();
		}
	}

	public void makeMatrix() {
		adjacency_matrix = new int[20][20];
		file = new File("TraceMatrix.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
				writeNextMatrix();
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void writeNextMatrix() {
		adjacency_matrix = new int[61][61];
		StringBuilder builder = new StringBuilder();
		try {
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					builder.append(adjacency_matrix[i][j] + "");
					if (j < 19) {
						builder.append(",");
					}
				}
				builder.append("\n");
			}

			BufferedWriter wr = new BufferedWriter(new FileWriter("TraceMatrix.txt"));
			wr.write(builder.toString());
			wr.close();
		}

		catch (Exception e) {
			System.out.println();
		}
	}

	public void read_MatrixFirst() {
		all_data = new ArrayList<String[]>();
		if (!file.exists()) {
			makeMatrix();
			writeNextMatrix();
		}

		try {
			Object[] currentLine;
			BufferedReader br = new BufferedReader(new FileReader(new File("TraceMatrix.txt")));
			currentLine = br.lines().toArray();
			for (Object s : currentLine) {
				String ln = s.toString().trim();
				String[] row = ln.split(",");
				all_data.add(row);

			}
			br.close();

			int len = all_data.size();
			if (len != 0) {
				for (int i = 0; i < len - 1; i++) {
					for (int j = 0; j < all_data.get(0).length; j++) {
						adjacency_matrix[i][j] = Integer.parseInt(all_data.get(i)[j]);
					}
				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
