package pres;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import config.Connexion;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditProfile extends JFrame {
	Statement st = null;
	int rs = 0;
	Connection cn = Connexion.getConnexion();
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfile frame = new EditProfile();
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
	public EditProfile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 441);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(0, 0, 162, 413);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-modifier-l'utilisateur-femme-48.png"));
		lblNewLabel.setBounds(50, 6, 61, 72);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Editer");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(16, 63, 95, 29);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Profile");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
		lblNewLabel_2.setBounds(60, 90, 61, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				MenuClient frame = new MenuClient();
				frame.setVisible(true);
			}
		});
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMenu.setBounds(6, 141, 145, 46);
		panel.add(lblMenu);
		
		JLabel lblMesCommandes_1 = new JLabel("Ajouter Commande");
		lblMesCommandes_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Commander frame = new Commander();
				frame.setVisible(true);
			}
		});
		lblMesCommandes_1.setForeground(Color.WHITE);
		lblMesCommandes_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMesCommandes_1.setBounds(6, 188, 155, 46);
		panel.add(lblMesCommandes_1);
		
		JLabel lblMesCommandes = new JLabel("Mes Commandes");
		lblMesCommandes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				MesCommandes frame = new MesCommandes();
				frame.setVisible(true);
			}
		});
		lblMesCommandes.setForeground(Color.WHITE);
		lblMesCommandes.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMesCommandes.setBounds(6, 246, 145, 46);
		panel.add(lblMesCommandes);
		
		JLabel lblDeconnexion = new JLabel("Logout");
		lblDeconnexion.setForeground(Color.WHITE);
		lblDeconnexion.setFont(new Font("Times", Font.PLAIN, 19));
		lblDeconnexion.setBounds(33, 503, 99, 28);
		panel.add(lblDeconnexion);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 526, 66, 12);
		panel.add(separator);
		
		JLabel lblDeconnexion_1 = new JLabel("Logout");
		lblDeconnexion_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		lblDeconnexion_1.setForeground(Color.WHITE);
		lblDeconnexion_1.setFont(new Font("Times", Font.PLAIN, 19));
		lblDeconnexion_1.setBounds(22, 372, 99, 28);
		panel.add(lblDeconnexion_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 395, 66, 12);
		panel.add(separator_1);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setFont(new Font("Times", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(290, 117, 61, 22);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(375, 114, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Mot de passe");
		lblNewLabel_4.setFont(new Font("Times", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(545, 119, 98, 19);
		contentPane.add(lblNewLabel_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(649, 116, 130, 22);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_5 = new JLabel("Nom");
		lblNewLabel_5.setFont(new Font("Times", Font.PLAIN, 17));
		lblNewLabel_5.setBounds(290, 184, 73, 16);
		contentPane.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(375, 178, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Telephone");
		lblNewLabel_6.setFont(new Font("Times", Font.PLAIN, 17));
		lblNewLabel_6.setBounds(545, 183, 79, 16);
		contentPane.add(lblNewLabel_6);
		
		textField_2 = new JTextField();
		textField_2.setBounds(649, 178, 130, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setFont(new Font("Times", Font.PLAIN, 17));
		lblNewLabel_7.setBounds(290, 246, 61, 16);
		contentPane.add(lblNewLabel_7);
		
		textField_3 = new JTextField();
		textField_3.setBounds(375, 240, 130, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Save");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name= textField_1.getText();
					String tel= textField_2.getText();
					String email= textField_3.getText();
					int id = Integer.parseInt(textField.getText());
					String pwd=passwordField.getText();
					st =  cn.createStatement();
					rs=st.executeUpdate("UPDATE `clients` SET `nom`='" + name + "',`email`='"
							+ email + "',`tel`='" + tel + "' ,`mdp`='" + pwd + "'WHERE ID = '"+id+"'");
					JOptionPane.showMessageDialog(null, "compte modifier");
					setVisible(false);
					MenuClient frame = new MenuClient();
					frame.setVisible(true);
					
				} catch (SQLException ev) {
					ev.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(448, 324, 195, 29);
		contentPane.add(btnNewButton_4);
		
		
		
		textField.setText(""+Login.userId);
		textField_1.setText(Login.userName);
		textField_2.setText(Login.usertel);
		textField_3.setText(Login.useremail);
		passwordField.setText(Login.usermdp);
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Home", "Femme"}));
		comboBox.setBounds(649, 241, 130, 27);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_7_1 = new JLabel("Sexe");
		lblNewLabel_7_1.setFont(new Font("Times", Font.PLAIN, 17));
		lblNewLabel_7_1.setBounds(545, 245, 61, 16);
		contentPane.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-modifier-l'utilisateur-femme-48.png"));
		lblNewLabel_8.setBounds(524, 6, 61, 61);
		contentPane.add(lblNewLabel_8);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(255, 127, 80));
		separator_2.setBounds(305, 60, 474, 19);
		contentPane.add(separator_2);
		//getClientInfo();
	}
	/*public void getClientInfo() {
		Connection cn = Connexion.getConnexion();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = cn.createStatement();
			rs = st.executeQuery("select * from clients WHERE id="+ cop.Login.userId);
			
			//textField.setText(""+rs.getInt("id"));
			textField_1.setText(rs.getString("nom"));
			textField_2.setText(rs.getString("tel"));
			textField_3.setText(rs.getString("email"));
			passwordField.setText(rs.getString("mdp"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}
