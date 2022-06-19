package pres;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import config.Connexion;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField email;
	public static int userId;
	public static String userName="";
	public static String usermdp="";
	public static String useremail="";
	public static String usertel="";
	private JPasswordField pwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(0, 0, 157, 372);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblConnexion = new JLabel("CONNEXION");
		lblConnexion.setBounds(28, 55, 100, 28);
		panel.add(lblConnexion);
		lblConnexion.setForeground(new Color(255, 255, 255));
		lblConnexion.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-aliexpress-50.png"));
		lblNewLabel_2.setBounds(51, 185, 59, 67);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(157, 0, 393, 334);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		email = new JTextField();
		email.setForeground(new Color(255, 127, 80));
		email.setFont(new Font("Century Gothic", Font.BOLD, 14));
		email.setBackground(new Color(255, 255, 255));
		email.setBounds(145, 160, 190, 28);
		panel_1.add(email);
		email.setColumns(10);
		
		JComboBox role = new JComboBox();
		role.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Client"}));
		role.setForeground(new Color(255, 127, 80));
		role.setFont(new Font("Century Gothic", Font.BOLD, 14));
		role.setBounds(145, 111, 190, 28);
		panel_1.add(role);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setEnabled(false);
		lblEmail.setForeground(new Color(0, 0, 0));
		lblEmail.setFont(new Font("Times", Font.BOLD, 17));
		lblEmail.setBounds(28, 159, 103, 24);
		panel_1.add(lblEmail);
		
		JLabel lblMotDePasse = new JLabel("Password");
		lblMotDePasse.setEnabled(false);
		lblMotDePasse.setForeground(new Color(0, 0, 0));
		lblMotDePasse.setFont(new Font("Times", Font.BOLD, 17));
		lblMotDePasse.setBounds(28, 204, 120, 24);
		panel_1.add(lblMotDePasse);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setEnabled(false);
		lblRole.setForeground(new Color(0, 0, 0));
		lblRole.setFont(new Font("Times", Font.BOLD, 17));
		lblRole.setBounds(28, 111, 103, 24);
		panel_1.add(lblRole);
		
		JButton btnNewButton = new JButton("Connexion");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				ResultSet rs = null;
				String query="";
				if(role.getSelectedItem().toString().equals("Admin")) {
				 query = "select * from admin where email='"+email.getText()+"'and mdp='"+pwd.getText()+"'";
				}
				if(role.getSelectedItem().toString().equals("Client")) {
					 query = "select * from clients where email='"+email.getText()+"'and mdp='"+pwd.getText()+"'";
					}
				 try {
					
					st = cn.createStatement();
					rs = st.executeQuery(query);
					if (rs.next()) {
						if(role.getSelectedItem().toString().equals("Admin")) {
							new Menu().setVisible(true);
							dispose();
							return;
							
						}
						new MenuClient().setVisible(true);
						dispose();

					} else {
						JOptionPane.showMessageDialog(null,
								"Erreur d�authentification ! Veuillez re-saisir l'email et le mot de passe");
						email.setText("");
						pwd.setText("");
						email.requestFocus();
					}
					userId=rs.getInt("id");
					userName=rs.getString("nom");
					useremail=rs.getString("email");
					usermdp=rs.getString("mdp");
					usertel=rs.getString("tel");
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnNewButton.setBounds(83, 255, 120, 28);
		panel_1.add(btnNewButton);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email.setText("");
				pwd.setText("");
			}
		});
		btnReset.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnReset.setBounds(215, 255, 120, 28);
		panel_1.add(btnReset);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 127, 80));
		separator.setBounds(41, 70, 294, 12);
		panel_1.add(separator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-login-64.png"));
		lblNewLabel.setBounds(160, 6, 64, 63);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Inscription");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Inscription().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_1.setBounds(265, 295, 68, 16);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblVousNavezPas = new JLabel("vous n'avez pas de compte?");
		lblVousNavezPas.setBounds(88, 295, 179, 16);
		panel_1.add(lblVousNavezPas);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(255, 127, 80));
		separator_1.setBounds(83, 310, 254, 12);
		panel_1.add(separator_1);
		
		pwd = new JPasswordField();
		pwd.setBounds(145, 202, 190, 26);
		panel_1.add(pwd);
	}
}
