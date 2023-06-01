package CW_Task2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class HighRisk extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	ArrayList<Integer> allNodes;
	int vertices;
	int id;
	static int[][] adjacency_matrix;

	public static void main(String[] args) {
		HighRisk frame = new HighRisk(adjacency_matrix);
		frame.setVisible(true);
	}

	public HighRisk(int[][] matrix) {
		adjacency_matrix = matrix;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		table_load();
	}

	public void table_load() {
		String filepath = "citizen.txt";
		File file = new File(filepath);

		try {
			ArrayList<String[]> arr = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String firstline = br.readLine().trim();
			String[] columsName = firstline.split(",");
			contentPane.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(26, 139, 615, 349);
			contentPane.add(scrollPane);

			table = new JTable();
			model = new DefaultTableModel();
			Object[] column = { "ID", "Firstname", "Lastname", "Age", "Gender", "Status", "Date" };
			Object[] row = new Object[0];
			model.setColumnIdentifiers(column);
			table.setModel(model);
			scrollPane.setViewportView(table);

			JLabel lblNewLabel = new JLabel("HIGH RISK PERSON");
			lblNewLabel.setFont(new Font("Stencil Std", Font.PLAIN, 30));
			lblNewLabel.setBounds(158, 34, 426, 55);
			contentPane.add(lblNewLabel);
			table.addMouseListener(this);

			Object[] tableLines = br.lines().toArray();

			for (int i = 0; i < tableLines.length; i++) {
				String line = tableLines[i].toString().trim();
				String[] dataRow = line.split(",");
				arr.add(dataRow);
//				System.out.println(Arrays.toString(dataRow));
				if (dataRow[5].equals("Positive")) {
					model.addRow(dataRow);
				}
			}

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<String[]> BFS(int source) {
		int vertices = 20;
		ArrayList<String[]> arr = new ArrayList<String[]>();
		allNodes = new ArrayList<Integer>();
		String[] data;
		int j = 0;
		boolean[] visited = new boolean[vertices];

//		=========================== Visited array to so that a vertex is not visited more than once =========================

		ArrayList<Integer> q = new ArrayList<>();
		q.add(source);
		// =========source vertex is visited at the beginning=============

		visited[source] = true;
		// ========= Set source as visited=================

		int vis;
		while (!q.isEmpty())

		{
			vis = q.get(0);

			// =================Get next element from queue and store it in array==========
			q.remove(q.get(0));

			for (int i = 0; i < vertices; i++)
			// ======== For every adjacent vertex to the current vertex================
			{
				if (adjacency_matrix[vis][i] == 1 && (!visited[i])) {
					data = new String[3];
					q.add(i);

					// ============= Push the adjacent node to the queue==============
					visited[i] = true;
					// =============== Set current node to visited==============
					allNodes.add(i);
					data[0] = Integer.toString(i);
					data[1] = Integer.toString(vis);
					data[2] = Integer.toString(i);
					arr.add(data);
				}

			}
			j++;
		}
		return arr;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.rowAtPoint(e.getPoint());
		DefaultTableModel recordtable = (DefaultTableModel) table.getModel();
		int selectedRow = table.getSelectedRow();
		id = Integer.parseInt(recordtable.getValueAt(selectedRow, 0).toString());

		ArrayList<String[]> arr = BFS(id);
		for (String[] i : arr) {
			System.out.println(Arrays.toString(i));
		}
		visual vs = new visual();
		vs.getVisual(arr);

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
