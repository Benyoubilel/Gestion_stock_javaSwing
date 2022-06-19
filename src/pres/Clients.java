package pres;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import config.Connexion;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class Clients extends JFrame {

	private JPanel contentPane;
	private JTextField idC;
	private JTextField nomC;
	private JTextField emailC;
	private JTextField telC;
	private JTable table;
	private JTextField mdp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clients frame = new Clients();
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
	public Clients() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 69, 0));
		panel_1.setBounds(138, 0, 862, 28);
		contentPane.add(panel_1);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(171, 28, 829, 572);
		contentPane.add(panel_3);

		JLabel lblGestionDesClients = new JLabel("GESTION DES CLIENTS");
		lblGestionDesClients.setEnabled(false);
		lblGestionDesClients.setForeground(new Color(0, 0, 0));
		lblGestionDesClients.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblGestionDesClients.setBounds(279, 23, 277, 28);
		panel_3.add(lblGestionDesClients);

		JLabel lblIdClient = new JLabel("ID Client");
		lblIdClient.setForeground(new Color(0, 0, 0));
		lblIdClient.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIdClient.setBounds(20, 80, 103, 24);
		panel_3.add(lblIdClient);

		idC = new JTextField();
		idC.setEnabled(false);
		idC.setEditable(false);
		idC.setForeground(new Color(0, 0, 0));
		idC.setFont(new Font("Century Gothic", Font.BOLD, 14));
		idC.setColumns(10);
		idC.setBackground(Color.WHITE);
		idC.setBounds(148, 81, 190, 28);
		panel_3.add(idC);

		JLabel lblNomPrenom = new JLabel("Nom");
		lblNomPrenom.setForeground(new Color(0, 0, 0));
		lblNomPrenom.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNomPrenom.setBounds(20, 126, 126, 24);
		panel_3.add(lblNomPrenom);

		nomC = new JTextField();
		nomC.setForeground(new Color(0, 0, 0));
		nomC.setFont(new Font("Century Gothic", Font.BOLD, 14));
		nomC.setColumns(10);
		nomC.setBounds(148, 126, 190, 28);
		panel_3.add(nomC);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(0, 0, 0));
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEmail.setBounds(433, 80, 103, 24);
		panel_3.add(lblEmail);

		emailC = new JTextField();
		emailC.setForeground(new Color(0, 0, 0));
		emailC.setFont(new Font("Century Gothic", Font.BOLD, 14));
		emailC.setColumns(10);
		emailC.setBackground(Color.WHITE);
		emailC.setBounds(550, 81, 190, 28);
		panel_3.add(emailC);

		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setForeground(new Color(0, 0, 0));
		lblTelephone.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTelephone.setBounds(433, 125, 112, 24);
		panel_3.add(lblTelephone);

		telC = new JTextField();
		telC.setForeground(new Color(0, 0, 0));
		telC.setFont(new Font("Century Gothic", Font.BOLD, 14));
		telC.setColumns(10);
		telC.setBounds(550, 126, 190, 28);
		panel_3.add(telC);

		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setForeground(new Color(0, 0, 0));
		lblSexe.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSexe.setBounds(20, 179, 103, 24);
		panel_3.add(lblSexe);

		JComboBox sexeC = new JComboBox();
		sexeC.setModel(new DefaultComboBoxModel(new String[] { "Homme", "Femme" }));
		sexeC.setForeground(new Color(0, 0, 0));
		sexeC.setFont(new Font("Century Gothic", Font.BOLD, 14));
		sexeC.setBounds(148, 179, 190, 28);
		panel_3.add(sexeC);

		JButton btnAjouter = new JButton("AJOUTER");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				PreparedStatement st = null;
				ResultSet rs = null;

				if ( nomC.getText().isEmpty() || ((String) sexeC.getSelectedItem()).isEmpty()
						|| emailC.getText().isEmpty() || telC.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");
				}
				try {
					st = cn.prepareStatement("insert into clients(email,mdp,nom,tel,sexe) values(?,?,?,?,?)");
					
					st.setString(1, emailC.getText());
					st.setString(2, mdp.getText());
					st.setString(3, nomC.getText());
					st.setString(4, telC.getText());
					st.setString(5, sexeC.getSelectedItem().toString());

					int row = st.executeUpdate();
					if (row == 1) {
						JOptionPane.showMessageDialog(null, "Clients Ajouté aves succeé");
						idC.setText("");
						emailC.setText("");
						telC.setText("");
						nomC.setText("");
						idC.requestFocus();
						cn.close();
						getClients();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAjouter.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnAjouter.setBounds(134, 251, 120, 28);
		panel_3.add(btnAjouter);

		JButton btnEditer = new JButton("EDITER");
		btnEditer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnEditer.setBounds(280, 251, 120, 28);
		btnEditer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				if (idC.getText().isEmpty() || nomC.getText().isEmpty() || ((String) sexeC.getSelectedItem()).isEmpty()
						|| emailC.getText().isEmpty() || telC.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");
				}
				try {
					String query = "UPDATE `clients` SET `email`='" + emailC.getText() + "'" + ",`nom`='"
							+ nomC.getText() + "'" + ",`tel`='" + telC.getText() + "'" + ",`sexe`='"
							+ sexeC.getSelectedItem().toString() + "'" + ",`mdp`='" + mdp.getText() + "'" + " WHERE id=" + idC.getText();
					st = cn.createStatement();
					st.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Clients modifié avec succées");
					getClients();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		panel_3.add(btnEditer);

		JButton btnSupprimer = new JButton("SUPPRIMER");
		btnSupprimer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnSupprimer.setBounds(474, 251, 120, 28);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				if (idC.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Saisir ID de client a supprimé");
				}
				try {
					String id = idC.getText();
					st = cn.createStatement();
					st.executeUpdate("delete from clients where id=" + id);
					JOptionPane.showMessageDialog(null, "client supprimé aves succeés");
					getClients();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		panel_3.add(btnSupprimer);

		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnReset_1.setBounds(620, 251, 120, 28);
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idC.setText("");
				nomC.setText("");
				emailC.setText("");
				telC.setText("");
				mdp.setText("");
				idC.requestFocus();
			}
		});
		panel_3.add(btnReset_1);

		JLabel lblListeDesClients = new JLabel("LISTE DES CLIENTS");
		lblListeDesClients.setEnabled(false);
		lblListeDesClients.setForeground(new Color(0, 0, 0));
		lblListeDesClients.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblListeDesClients.setBounds(317, 315, 219, 28);
		panel_3.add(lblListeDesClients);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 355, 617, 148);
		panel_3.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();
				idC.setText(model.getValueAt(index, 0).toString());
				emailC.setText(model.getValueAt(index, 1).toString());
				mdp.setText(model.getValueAt(index, 2).toString());
				nomC.setText(model.getValueAt(index, 3).toString());
				telC.setText(model.getValueAt(index, 4).toString());
				
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "ID", "NOM", "SEXE", "EMAIL", "TELEPHONE" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblTelephone_1 = new JLabel("Mot de passe");
		lblTelephone_1.setForeground(Color.BLACK);
		lblTelephone_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTelephone_1.setBounds(433, 179, 112, 24);
		panel_3.add(lblTelephone_1);
		
		mdp = new JTextField();
		mdp.setForeground(Color.BLACK);
		mdp.setFont(new Font("Dialog", Font.BOLD, 14));
		mdp.setColumns(10);
		mdp.setBounds(550, 178, 190, 28);
		panel_3.add(mdp);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 69, 0));
		panel_2.setBounds(138, 600, 862, 29);
		contentPane.add(panel_2);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(0, 0, 187, 633);
		contentPane.add(panel);
		
		JLabel lblDeconnexion = new JLabel("Logout");
		lblDeconnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		lblDeconnexion.setForeground(Color.WHITE);
		lblDeconnexion.setFont(new Font("Times", Font.PLAIN, 19));
		lblDeconnexion.setBounds(33, 551, 99, 28);
		panel.add(lblDeconnexion);
		
		JLabel lblMesCommandes_1 = new JLabel("Gerer Produits");
		lblMesCommandes_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Produits frame = new Produits();
				frame.setVisible(true);
			}
		});
		lblMesCommandes_1.setForeground(Color.WHITE);
		lblMesCommandes_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMesCommandes_1.setBounds(6, 213, 155, 46);
		panel.add(lblMesCommandes_1);
		
		JLabel lblGererCommandes = new JLabel("Gerer Commandes");
		lblGererCommandes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Commandes frame = new Commandes();
				frame.setVisible(true);
			}
		});
		lblGererCommandes.setForeground(Color.WHITE);
		lblGererCommandes.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGererCommandes.setBounds(6, 271, 145, 46);
		panel.add(lblGererCommandes);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Menu frame = new Menu();
				frame.setVisible(true);
			}
		});
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMenu.setBounds(6, 166, 145, 46);
		panel.add(lblMenu);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-boutique-50.png"));
		lblNewLabel.setBounds(52, 37, 86, 89);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 574, 66, 12);
		panel.add(separator);
		
		JLabel lblGererClients = new JLabel("Gerer Categories");
		lblGererClients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Categorie frame = new Categorie();
				frame.setVisible(true);
			}
		});
		lblGererClients.setForeground(Color.WHITE);
		lblGererClients.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGererClients.setBounds(6, 329, 145, 46);
		panel.add(lblGererClients);
		getClients();
	}

	public void getClients() {
		Connection cn = Connexion.getConnexion();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = cn.createStatement();
			rs = st.executeQuery("select * from clients");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
