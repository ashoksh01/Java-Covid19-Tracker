package CW_Task2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.Color;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField text_username;
	private JTextField text_password;

//	=========================== Lunch Application===================

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

//	==================== Create Frame ===================

	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("COVID Contact Tracing");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Stencil Std", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(46, 11, 516, 71);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Login Form");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(161, 93, 193, 58);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(83, 162, 110, 33);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(83, 236, 110, 28);
		contentPane.add(lblNewLabel_2_1);

		text_username = new JTextField();
		text_username.setBounds(217, 162, 193, 40);
		contentPane.add(text_username);
		text_username.setColumns(10);

		text_password = new JTextField();
		text_password.setColumns(10);
		text_password.setBounds(217, 233, 193, 40);
		contentPane.add(text_password);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = text_username.getText();
				String password_v = text_password.getText();
				if (username.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Username is required!");
				} else if (password_v.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Password field is left blank!");
				} else {
					String userName = text_username.getText();
					String password = text_password.getText();

					boolean validate = false;
					try {
						validate = checkLogin(userName, password);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					if (validate) {
						JOptionPane.showMessageDialog(contentPane, "Welcome Here!");
						dispose();
						new AdminPanel().setVisible(true);

					} else {
						JOptionPane.showMessageDialog(contentPane, "Login Failed");
					}
				}
			}
		});

		btnNewButton.setBounds(68, 311, 136, 33);
		contentPane.add(btnNewButton);

		JButton btnRegistration = new JButton("Registration");
		btnRegistration.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Register().setVisible(true);

			}
		});

		btnRegistration.setBounds(239, 311, 206, 33);
		contentPane.add(btnRegistration);
	}

	public boolean validateStaff(String typedPassword, String savedPassword) {
		return typedPassword.equals(savedPassword);
	}

	public boolean checkLogin(String userName, String password) throws IOException {
		FileHand fh = new FileHand();
		boolean validStaff = false;

		String[] data = fh.ReadOrFetch(Constants.staffFile, userName, 0);
		if (data != null) {
			validStaff = validateStaff(password, data[5]);
		}
		return validStaff;
	}

}
