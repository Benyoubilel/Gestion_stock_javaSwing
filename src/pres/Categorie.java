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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import config.Connexion;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class Categorie extends JFrame {

	private JPanel contentPane;
	private JTextField idCat;
	private JTextField nomCat;
	private JTable table;
	private JTextField desCat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Categorie frame = new Categorie();
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
	public Categorie() {
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
		panel_1.setBounds(132, 0, 868, 28);
		contentPane.add(panel_1);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(172, 26, 828, 578);
		contentPane.add(panel_3);

		JLabel lblGestionDesCategorie = new JLabel("GESTION DES CATEGORIE");
		lblGestionDesCategorie.setEnabled(false);
		lblGestionDesCategorie.setForeground(new Color(0, 0, 0));
		lblGestionDesCategorie.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblGestionDesCategorie.setBounds(279, 23, 295, 28);
		panel_3.add(lblGestionDesCategorie);

		JLabel lblIdCat = new JLabel("ID Categorie");
		lblIdCat.setForeground(new Color(0, 0, 0));
		lblIdCat.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIdCat.setBounds(20, 80, 103, 24);
		panel_3.add(lblIdCat);

		idCat = new JTextField();
		idCat.setEnabled(false);
		idCat.setEditable(false);
		idCat.setForeground(new Color(0, 0, 0));
		idCat.setFont(new Font("Century Gothic", Font.BOLD, 14));
		idCat.setColumns(10);
		idCat.setBackground(Color.WHITE);
		idCat.setBounds(148, 81, 190, 28);
		panel_3.add(idCat);

		JLabel lblNomPrenom = new JLabel("Nom");
		lblNomPrenom.setForeground(new Color(0, 0, 0));
		lblNomPrenom.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNomPrenom.setBounds(436, 80, 71, 24);
		panel_3.add(lblNomPrenom);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(new Color(0, 0, 0));
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDescription.setBounds(20, 126, 133, 24);
		panel_3.add(lblDescription);

		nomCat = new JTextField();
		nomCat.setForeground(new Color(0, 0, 0));
		nomCat.setFont(new Font("Century Gothic", Font.BOLD, 14));
		nomCat.setColumns(10);
		nomCat.setBackground(Color.WHITE);
		nomCat.setBounds(496, 81, 190, 28);
		panel_3.add(nomCat);

		JButton btnAjouter = new JButton("AJOUTER");
		btnAjouter.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnAjouter.setBounds(134, 219, 120, 28);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				PreparedStatement st = null;
				ResultSet rs = null;

				if (nomCat.getText().isEmpty() || desCat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");
				}
				try {
					st = cn.prepareStatement("insert into categories(nom,description) values(?,?)");
					
					st.setString(1, nomCat.getText());
					st.setString(2, desCat.getText());
					int row = st.executeUpdate();
					if (row == 1) {
						JOptionPane.showMessageDialog(null, "Categorie Ajouté aves succeé");
						idCat.setText("");
						nomCat.setText("");
						desCat.setText("");
						idCat.requestFocus();
						cn.close();
						getCategories();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_3.add(btnAjouter);

		JButton btnEditer = new JButton("EDITER");
		btnEditer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnEditer.setBounds(280, 219, 120, 28);
		btnEditer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				if (idCat.getText().isEmpty() || nomCat.getText().isEmpty() || desCat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");
				}
				try {
					String query = "UPDATE `categories` SET `nom`='" + nomCat.getText() + "'" + ",`description`='"
							+ desCat.getText() + "'" + " WHERE id=" + idCat.getText();
					st = cn.createStatement();
					st.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Categorie modifié avec succées");
					getCategories();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		panel_3.add(btnEditer);

		JButton btnSupprimer = new JButton("SUPPRIMER");
		btnSupprimer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnSupprimer.setBounds(474, 219, 120, 28);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				if (idCat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Saisir ID de categories a supprimé");
				}
				try {
					String id = idCat.getText();
					st = cn.createStatement();
					st.executeUpdate("delete from categories where id=" + id);
					JOptionPane.showMessageDialog(null, "Categorie supprimé aves succeés");
					getCategories();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		panel_3.add(btnSupprimer);

		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnReset_1.setBounds(620, 219, 120, 28);
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idCat.setText("");
				nomCat.setText("");
				desCat.setText("");
				idCat.requestFocus();
			}
		});
		panel_3.add(btnReset_1);

		JLabel lblListeDesCategorie = new JLabel("LISTE DES CATEGORIE");
		lblListeDesCategorie.setEnabled(false);
		lblListeDesCategorie.setForeground(new Color(0, 0, 0));
		lblListeDesCategorie.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblListeDesCategorie.setBounds(317, 277, 257, 28);
		panel_3.add(lblListeDesCategorie);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(111, 326, 641, 148);
		panel_3.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();
				idCat.setText(model.getValueAt(index, 0).toString());
				nomCat.setText(model.getValueAt(index, 1).toString());
				desCat.setText(model.getValueAt(index, 2).toString());
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, },
				new String[] { "ID", "NOM", "DESCRIPTION" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);

		desCat = new JTextField();
		desCat.setFont(new Font("Century Gothic", Font.BOLD, 14));
		desCat.setForeground(new Color(0, 0, 0));
		desCat.setBounds(148, 119, 190, 48);
		panel_3.add(desCat);
		desCat.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 69, 0));
		panel_2.setBounds(132, 604, 868, 29);
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
		lblNewLabel.setBounds(73, 38, 86, 89);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 574, 66, 12);
		panel.add(separator);
		
		JLabel lblGererClients = new JLabel("Gerer Clients");
		lblGererClients.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Clients frame = new Clients();
				frame.setVisible(true);
			}
		});
		lblGererClients.setForeground(Color.WHITE);
		lblGererClients.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGererClients.setBounds(6, 329, 145, 46);
		panel.add(lblGererClients);
		getCategories();
	}

	public void getCategories() {
		Connection cn = Connexion.getConnexion();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = cn.createStatement();
			rs = st.executeQuery("select * from categories");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
