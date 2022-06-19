package pres;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import config.Connexion;

import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Inscription extends JFrame {
	Statement st = null;
	ResultSet rs = null;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JComboBox comboBox;
	Connection cn = Connexion.getConnexion();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscription frame = new Inscription();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inscription() {
		setType(Type.POPUP);
		setTitle("Registre\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(0, 0, 701, 392);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(195, 0, 506, 392);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 69, 0));
		separator.setBounds(69, 90, 391, 12);
		panel_1.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Connexion");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		lblNewLabel_1.setBounds(274, 342, 73, 16);
		panel_1.add(lblNewLabel_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(255, 127, 80));
		separator_1.setBounds(196, 357, 156, 12);
		panel_1.add(separator_1);
		
		JLabel lblNewLabel_5 = new JLabel("Nom");
		lblNewLabel_5.setBounds(35, 157, 61, 16);
		panel_1.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(111, 152, 130, 26);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Telephone");
		lblNewLabel_6.setBounds(35, 215, 79, 16);
		panel_1.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setBounds(111, 210, 130, 26);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setBounds(253, 157, 61, 16);
		panel_1.add(lblNewLabel_7);
		
		textField_2 = new JTextField();
		textField_2.setBounds(340, 152, 130, 26);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Mot de passe");
		lblNewLabel_8.setBounds(253, 215, 90, 16);
		panel_1.add(lblNewLabel_8);
		JLabel lblNewLabel_6_1 = new JLabel("Sexe");
		lblNewLabel_6_1.setBounds(35, 264, 79, 16);
		panel_1.add(lblNewLabel_6_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Homme", "Femme"}));
		comboBox.setBounds(111, 260, 130, 27);
		panel_1.add(comboBox);
		
		JButton btnNewButton = new JButton("Creer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				PreparedStatement st = null;
				ResultSet rs = null;
				String name= textField.getText();
				String tel = textField_1.getText();
				String pass = passwordField.getText();
				String email = textField_2.getText();
			//	String sexe = comboBox.getSelectedItem().toString();

				if ( name.isEmpty() || ((String) comboBox.getSelectedItem()).isEmpty()
						|| email.isEmpty() || tel.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");
				}
				try {
					st = cn.prepareStatement("insert into clients(email,mdp,nom,tel,sexe) values(?,?,?,?,?)");
					
					st.setString(1, email);
					st.setString(2, pass);
					st.setString(3, name);
					st.setString(4, tel);
					st.setString(5, ((String) comboBox.getSelectedItem()));

					int row = st.executeUpdate();
					if (row == 1) {
						JOptionPane.showMessageDialog(null, "compte creé avec succeé");
						new Login().setVisible(true);
						dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(171, 304, 192, 29);
		panel_1.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(339, 210, 131, 26);
		panel_1.add(passwordField);
		
		
		
		JLabel lblNewLabel = new JLabel("Déja client?");
		lblNewLabel.setBounds(201, 342, 79, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setEnabled(false);
		lblNewLabel_9.setBackground(new Color(0, 0, 0));
		lblNewLabel_9.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-ajouter-un-utilisateur-homme-64.png"));
		lblNewLabel_9.setBounds(231, 6, 116, 84);
		panel_1.add(lblNewLabel_9);

		JLabel lblNewLabel_2 = new JLabel("Gestion ");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_2.setBounds(36, 43, 144, 48);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("De");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Snell Roundhand", Font.ITALIC, 15));
		lblNewLabel_3.setBounds(79, 90, 61, 16);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Stock");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 31));
		lblNewLabel_4.setBounds(56, 103, 144, 35);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-boutique-50.png"));
		lblNewLabel_10.setBounds(67, 210, 61, 90);
		panel.add(lblNewLabel_10);
	}
}





/*
Connection cn = Connexion.getConnexion();
PreparedStatement st = null;
ResultSet rs = null;
String name= textField.getText();
String tel = textField_1.getText();
String pass = passwordField.getText();
String email = textField_2.getText();
String sexe = comboBox.getSelectedItem().toString();

if ( name.isEmpty() || sexe.isEmpty()
		|| email.isEmpty() || tel.isEmpty()) {
	JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");
}
try {
	st = cn.prepareStatement("insert into clients values(?,?,?,?,?)");
	
	st.setString(2, email);
	st.setString(3, pass);
	st.setString(4, name);
	st.setString(5, tel);
	st.setString(6, sexe);

	int row = st.executeUpdate();
	if (row == 1) {
		JOptionPane.showMessageDialog(null, "compte creé aves succeé");
		new Login().setVisible(true);
		dispose();
	}
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
*/