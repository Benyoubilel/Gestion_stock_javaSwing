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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import config.Connexion;
import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Commandes extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField prd;
	private JTextField qte;
	private JTextField mtt;
	private JTable table;
	private JTextField statut;
	private JTextField idC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commandes frame = new Commandes();
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
	public Commandes() {
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(153, 28, 847, 577);
		contentPane.add(panel_3);
		
		JLabel lblGestionDesCommandes = new JLabel("GESTION DES COMMANDES");
		lblGestionDesCommandes.setEnabled(false);
		lblGestionDesCommandes.setForeground(new Color(0, 0, 0));
		lblGestionDesCommandes.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblGestionDesCommandes.setBounds(300, 22, 315, 28);
		panel_3.add(lblGestionDesCommandes);
		
		JLabel lblId = new JLabel("ID ");
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblId.setBounds(20, 80, 103, 24);
		panel_3.add(lblId);
		
		id = new JTextField();
		id.setEnabled(false);
		id.setEditable(false);
		id.setForeground(new Color(0, 0, 0));
		id.setFont(new Font("Century Gothic", Font.BOLD, 14));
		id.setColumns(10);
		id.setBackground(Color.WHITE);
		id.setBounds(148, 81, 190, 28);
		panel_3.add(id);
		
		JLabel lblProduits_1 = new JLabel("Produit");
		lblProduits_1.setForeground(new Color(0, 0, 0));
		lblProduits_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblProduits_1.setBounds(20, 126, 126, 24);
		panel_3.add(lblProduits_1);
		
		prd = new JTextField();
		prd.setForeground(new Color(0, 0, 0));
		prd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		prd.setColumns(10);
		prd.setBounds(148, 126, 190, 28);
		panel_3.add(prd);
		
		JLabel lblEmail = new JLabel("Quantitie");
		lblEmail.setForeground(new Color(0, 0, 0));
		lblEmail.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblEmail.setBounds(433, 80, 103, 24);
		panel_3.add(lblEmail);
		
		qte = new JTextField();
		qte.setForeground(new Color(0, 0, 0));
		qte.setFont(new Font("Century Gothic", Font.BOLD, 14));
		qte.setColumns(10);
		qte.setBackground(Color.WHITE);
		qte.setBounds(550, 81, 190, 28);
		panel_3.add(qte);
		
		JLabel lblTelephone = new JLabel("M Total");
		lblTelephone.setForeground(new Color(0, 0, 0));
		lblTelephone.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblTelephone.setBounds(433, 125, 112, 24);
		panel_3.add(lblTelephone);
		
		mtt = new JTextField();
		mtt.setForeground(new Color(0, 0, 0));
		mtt.setFont(new Font("Century Gothic", Font.BOLD, 14));
		mtt.setColumns(10);
		mtt.setBounds(550, 126, 190, 28);
		panel_3.add(mtt);
		
		JLabel lblIdClient = new JLabel("ID Client");
		lblIdClient.setForeground(new Color(0, 0, 0));
		lblIdClient.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblIdClient.setBounds(20, 179, 103, 24);
		panel_3.add(lblIdClient);
		
		JButton btnEditer = new JButton("EDITER");
		btnEditer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				if (id.getText().isEmpty() || statut.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");
				}
				try {
					String query = "UPDATE `commandes` SET `statut`='" + statut.getText() +"'" + " WHERE id=" + id.getText();
					st = cn.createStatement();
					st.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Commande modifié avec succées");
					getCommandes();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnEditer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnEditer.setBounds(174, 251, 120, 28);
		panel_3.add(btnEditer);
		
		JButton btnSupprimer = new JButton("SUPPRIMER");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				if (id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Saisir ID de commande a supprimé");
				}
				try {
					
					st = cn.createStatement();
					st.executeUpdate("delete from `commandes` WHERE id=" + id.getText());
					JOptionPane.showMessageDialog(null, "Commande supprimé aves succeés");
					getCommandes();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnSupprimer.setBounds(359, 251, 120, 28);
		panel_3.add(btnSupprimer);
		
		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText("");
				prd.setText("");
				idC.setText("");
				qte.setText("");
				mtt.setText("");
				statut.setText("");
				id.requestFocus();
			}
		});
		btnReset_1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnReset_1.setBounds(553, 251, 120, 28);
		panel_3.add(btnReset_1);
		
		JLabel lblListeDesCommandes = new JLabel("LISTE DES COMMANDES");
		lblListeDesCommandes.setEnabled(false);
		lblListeDesCommandes.setForeground(new Color(0, 0, 0));
		lblListeDesCommandes.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblListeDesCommandes.setBounds(293, 317, 279, 28);
		panel_3.add(lblListeDesCommandes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(96, 357, 644, 148);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();
				id.setText(model.getValueAt(index, 0).toString());
				prd.setText(model.getValueAt(index, 1).toString());
				idC.setText(model.getValueAt(index, 2).toString());
				qte.setText(model.getValueAt(index, 3).toString());
				mtt.setText(model.getValueAt(index, 4).toString());
				statut.setText(model.getValueAt(index, 5).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "PRODUITS", "ID CLIENT", "QUANTITIE", "M TOTAL", "STATUT"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setForeground(new Color(0, 0, 0));
		lblStatut.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblStatut.setBounds(433, 174, 112, 24);
		panel_3.add(lblStatut);
		
		statut = new JTextField();
		statut.setForeground(new Color(0, 0, 0));
		statut.setFont(new Font("Century Gothic", Font.BOLD, 14));
		statut.setColumns(10);
		statut.setBounds(550, 175, 190, 28);
		panel_3.add(statut);
		
		idC = new JTextField();
		idC.setForeground(new Color(0, 0, 0));
		idC.setFont(new Font("Century Gothic", Font.BOLD, 14));
		idC.setColumns(10);
		idC.setBounds(148, 179, 190, 28);
		panel_3.add(idC);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 69, 0));
		panel_2.setBounds(124, 604, 876, 29);
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
		getCommandes();
	}
	
	public void getCommandes() {
		Connection cn = Connexion.getConnexion();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = cn.createStatement();
			rs = st.executeQuery("select * from commandes");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
