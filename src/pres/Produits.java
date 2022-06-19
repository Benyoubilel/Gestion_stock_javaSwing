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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import config.Connexion;
import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class Produits extends JFrame {

	private JPanel contentPane;
	private JTextField idProd;
	private JTextField desProd;
	private JTextField qteProd;
	private JTextField prixProd;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produits frame = new Produits();
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
	public Produits() {
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
		panel_1.setBounds(124, 0, 876, 28);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 69, 0));
		panel_2.setBounds(138, 598, 862, 35);
		contentPane.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(186, 26, 834, 572);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblGestionDesProduits = new JLabel("GESTION DES PRODUITS");
		lblGestionDesProduits.setEnabled(false);
		lblGestionDesProduits.setBounds(279, 23, 277, 28);
		lblGestionDesProduits.setForeground(new Color(0, 0, 0));
		lblGestionDesProduits.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel_3.add(lblGestionDesProduits);

		JLabel lblIdProd = new JLabel("ID Produit");
		lblIdProd.setBounds(31, 80, 103, 24);
		lblIdProd.setForeground(new Color(0, 0, 0));
		lblIdProd.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_3.add(lblIdProd);

		idProd = new JTextField();
		idProd.setEnabled(false);
		idProd.setEditable(false);
		idProd.setBounds(159, 81, 190, 28);
		idProd.setForeground(new Color(0, 0, 0));
		idProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		idProd.setColumns(10);
		idProd.setBackground(Color.WHITE);
		panel_3.add(idProd);

		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(31, 126, 158, 24);
		lblDesignation.setForeground(new Color(0, 0, 0));
		lblDesignation.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_3.add(lblDesignation);

		desProd = new JTextField();
		desProd.setBounds(159, 126, 190, 28);
		desProd.setForeground(new Color(0, 0, 0));
		desProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		desProd.setColumns(10);
		panel_3.add(desProd);

		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setBounds(444, 80, 103, 24);
		lblQuantite.setForeground(new Color(0, 0, 0));
		lblQuantite.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_3.add(lblQuantite);

		qteProd = new JTextField();
		qteProd.setBounds(561, 81, 190, 28);
		qteProd.setForeground(new Color(0, 0, 0));
		qteProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		qteProd.setColumns(10);
		qteProd.setBackground(Color.WHITE);
		panel_3.add(qteProd);

		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setBounds(444, 125, 111, 24);
		lblPrix.setForeground(new Color(0, 0, 0));
		lblPrix.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_3.add(lblPrix);

		prixProd = new JTextField();
		prixProd.setBounds(561, 126, 190, 28);
		prixProd.setForeground(new Color(0, 0, 0));
		prixProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		prixProd.setColumns(10);
		panel_3.add(prixProd);

		JLabel lblCategore = new JLabel("Categorie");
		lblCategore.setBounds(31, 179, 103, 24);
		lblCategore.setForeground(new Color(0, 0, 0));
		lblCategore.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_3.add(lblCategore);

		JComboBox catProd = new JComboBox();
		catProd.setBounds(159, 179, 190, 28);
		catProd.setModel(new DefaultComboBoxModel(new String[] {}));
		try {
			Connection cn = Connexion.getConnexion();
			Statement st = null;
			ResultSet rs = null;

			st = cn.createStatement();
			rs = st.executeQuery("select * from categories");
			while (rs.next()) {
				catProd.addItem(rs.getString(2));
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
		catProd.setForeground(new Color(0, 0, 0));
		catProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panel_3.add(catProd);

		JButton btnAjouter = new JButton("AJOUTER");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				PreparedStatement st = null;
				ResultSet rs = null;

				if ( desProd.getText().isEmpty()
						|| ((String) catProd.getSelectedItem()).isEmpty() || qteProd.getText().isEmpty()
						|| prixProd.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");
				}
				try {
					st = cn.prepareStatement("insert into produits(designation,categorie,quantite,prix) values(?,?,?,?)");
					st.setString(1, desProd.getText());
					st.setString(2, catProd.getSelectedItem().toString());
					st.setDouble(3, Double.valueOf(qteProd.getText()));
					st.setDouble(4, Double.valueOf(prixProd.getText()));
					int row = st.executeUpdate();
					if (row == 1) {
						JOptionPane.showMessageDialog(null, "Produits Ajouté aves succeé");
						idProd.setText("");
						desProd.setText("");
						qteProd.setText("");
						prixProd.setText("");
						idProd.requestFocus();
						cn.close();
						getProduits();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAjouter.setBounds(145, 250, 120, 28);
		btnAjouter.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panel_3.add(btnAjouter);

		JButton btnEditer = new JButton("EDITER");
		btnEditer.setBounds(291, 250, 120, 28);
		btnEditer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				if (idProd.getText().isEmpty() || desProd.getText().isEmpty()
						|| ((String) catProd.getSelectedItem()).isEmpty() || qteProd.getText().isEmpty()
						|| prixProd.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");
				}
				try {
					String query = "UPDATE `produits` SET `designation`='" + desProd.getText() + "'" + ",`categorie`='"
							+ catProd.getSelectedItem().toString() + "'" + ",`quantite`='"
							+ Double.valueOf(qteProd.getText()) + "'" + ",`prix`='" + Double.valueOf(prixProd.getText())
							+ "'" + " WHERE id=" + idProd.getText()+" and `categorie`='"+ catProd.getSelectedItem().toString() + "'";
							
					st = cn.createStatement();
					st.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Produits modifié avec succées");
					getProduits();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnEditer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panel_3.add(btnEditer);

		JButton btnSupprimer = new JButton("SUPPRIMER");
		btnSupprimer.setBounds(485, 250, 120, 28);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				if (idProd.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Saisir ID de produit a supprimé");
				}
				try {
					String idP = idProd.getText();
					st = cn.createStatement();
					st.executeUpdate("delete from produits where id=" + idP);
					JOptionPane.showMessageDialog(null, "Produit supprimé aves succeés");
					getProduits();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panel_3.add(btnSupprimer);

		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idProd.setText("");
				desProd.setText("");
				qteProd.setText("");
				prixProd.setText("");
				idProd.requestFocus();
			}
		});
		btnReset_1.setBounds(631, 250, 120, 28);
		btnReset_1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		panel_3.add(btnReset_1);

		JLabel lblListeDesProduits = new JLabel("LISTE DES PRODUITS");
		lblListeDesProduits.setEnabled(false);
		lblListeDesProduits.setBounds(301, 309, 239, 28);
		lblListeDesProduits.setForeground(new Color(0, 0, 0));
		lblListeDesProduits.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel_3.add(lblListeDesProduits);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(96, 349, 655, 148);
		panel_3.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();
				idProd.setText(model.getValueAt(index, 0).toString());
				desProd.setText(model.getValueAt(index, 1).toString());
				qteProd.setText(model.getValueAt(index, 3).toString());
				prixProd.setText(model.getValueAt(index, 4).toString());
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "ID", "DESIGNATION", "QUANTITIE", "PRIX", "GATEGORIE" }) {
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
		
		JLabel lblMesCommandes_1 = new JLabel("Gerer Commandes");
		lblMesCommandes_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Commandes frame = new Commandes();
				frame.setVisible(true);
			}
		});
		lblMesCommandes_1.setForeground(Color.WHITE);
		lblMesCommandes_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMesCommandes_1.setBounds(6, 213, 155, 46);
		panel.add(lblMesCommandes_1);
		
		JLabel lblMonProfile = new JLabel("Gerer Categories");
		lblMonProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Categorie frame = new Categorie();
				frame.setVisible(true);
			}
		});
		lblMonProfile.setForeground(Color.WHITE);
		lblMonProfile.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMonProfile.setBounds(6, 271, 145, 46);
		panel.add(lblMonProfile);
		
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
		getProduits();
	}

	public void getProduits() {
		Connection cn = Connexion.getConnexion();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = cn.createStatement();
			rs = st.executeQuery("select * from produits");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
