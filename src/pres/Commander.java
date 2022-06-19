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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import config.Connexion;
import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class Commander extends JFrame {

	private JPanel contentPane;
	private JTextField idProd;
	private JTextField desProd;
	private JTextField qteProd;
	JLabel totale;
	private JTable table;
	JTextArea facTxt;
	Double Uprix, prixT = 0.0, qteDispo, mntT = 0.0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commander frame = new Commander();
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
	public Commander() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(0, 0, 151, 633);
		contentPane.add(panel);

		JLabel lblDeconnexion = new JLabel("Logout");
		lblDeconnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		lblDeconnexion.setForeground(Color.WHITE);
		lblDeconnexion.setFont(new Font("Times", Font.PLAIN, 19));
		lblDeconnexion.setBounds(33, 551, 99, 28);
		panel.add(lblDeconnexion);
		
		JLabel lblMesCommandes = new JLabel("Mes Commandes");
		lblMesCommandes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MesCommandes().setVisible(true);
				dispose();
			}
		});
		lblMesCommandes.setForeground(Color.WHITE);
		lblMesCommandes.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMesCommandes.setBounds(6, 213, 145, 46);
		panel.add(lblMesCommandes);
		
		JLabel lblMonProfile = new JLabel("Mon Profile");
		lblMonProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				EditProfile frame = new EditProfile();
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
				MenuClient frame = new MenuClient();
				frame.setVisible(true);
			}
		});
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMenu.setBounds(6, 166, 145, 46);
		panel.add(lblMenu);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-vendre-les-stock-50.png"));
		lblNewLabel.setBounds(33, 33, 86, 89);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 574, 66, 12);
		panel.add(separator);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(150, 25, 814, 574);
		contentPane.add(panel_3);

		JLabel lblPasserUneCommande = new JLabel("PASSER UNE COMMANDE");
		lblPasserUneCommande.setEnabled(false);
		lblPasserUneCommande.setForeground(new Color(0, 0, 0));
		lblPasserUneCommande.setFont(new Font("Century Gothic", Font.BOLD, 22));
		lblPasserUneCommande.setBounds(272, 25, 290, 28);
		panel_3.add(lblPasserUneCommande);

		JLabel lblIdProd = new JLabel("ID PROD");
		lblIdProd.setForeground(new Color(0, 0, 0));
		lblIdProd.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblIdProd.setBounds(20, 80, 103, 24);
		panel_3.add(lblIdProd);

		idProd = new JTextField();
		idProd.setEnabled(false);
		idProd.setEditable(false);
		idProd.setForeground(new Color(0, 0, 0));
		idProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		idProd.setColumns(10);
		idProd.setBackground(Color.WHITE);
		idProd.setBounds(148, 81, 190, 28);
		panel_3.add(idProd);

		JLabel lblDesignation = new JLabel("DESIGNATION ");
		lblDesignation.setForeground(new Color(0, 0, 0));
		lblDesignation.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblDesignation.setBounds(20, 126, 158, 24);
		panel_3.add(lblDesignation);

		desProd = new JTextField();
		desProd.setEditable(false);
		desProd.setForeground(new Color(0, 0, 0));
		desProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		desProd.setColumns(10);
		desProd.setBounds(148, 126, 190, 28);
		panel_3.add(desProd);

		JLabel lblQuantite = new JLabel("QUANTITE");
		lblQuantite.setForeground(new Color(0, 0, 0));
		lblQuantite.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblQuantite.setBounds(20, 164, 103, 24);
		panel_3.add(lblQuantite);

		qteProd = new JTextField();
		qteProd.setForeground(new Color(0, 0, 0));
		qteProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		qteProd.setColumns(10);
		qteProd.setBackground(Color.WHITE);
		qteProd.setBounds(148, 165, 190, 28);
		panel_3.add(qteProd);

		JLabel lblCategore = new JLabel("Trier Par");
		lblCategore.setForeground(new Color(0, 0, 0));
		lblCategore.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCategore.setBounds(372, 82, 103, 24);
		panel_3.add(lblCategore);

		JComboBox catProd = new JComboBox();
		catProd.setForeground(new Color(0, 0, 0));
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
		catProd.setFont(new Font("Century Gothic", Font.BOLD, 14));
		catProd.setBounds(457, 80, 190, 28);
		panel_3.add(catProd);

		JButton btnAjouter = new JButton("AJOUTER");
		btnAjouter.addMouseListener(new MouseAdapter() {
			int i = 0;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (qteProd.getText().isEmpty() || desProd.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Remplir tous les champs svp");

				} else if (qteDispo <= Double.valueOf(qteProd.getText())) {

					JOptionPane.showMessageDialog(null, "Quantitie indisponible dans le stock !");
				} else {
					i++;
					prixT = Uprix * Double.valueOf(qteProd.getText());
					mntT = mntT + prixT;
					if (i == 1) {
						facTxt.setText(facTxt.getText()
								+ "   ========================   Votre Facture   =========================\n"
								+ "  N°PROD	PRODUIT	PRIX	QUANTITIE	TOTAL\n" + " " + i + "	" + desProd.getText()
								+ "	" + Uprix + "	" + qteProd.getText() + "	" + prixT + "DT\n");
						passerCmd();
					} else {
						facTxt.setText(facTxt.getText() + " " + i + "	" + desProd.getText() + "	" + Uprix + "	"
								+ qteProd.getText() + "	" + prixT + "DT\n");
						passerCmd();

					}
					totale.setText("MONTANT TOTALE : " + mntT + " DT");
					updateQte();
				}
			}
		});
		btnAjouter.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnAjouter.setBounds(63, 246, 120, 28);
		panel_3.add(btnAjouter);

		JButton btnSupprimer = new JButton("TRIER");
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				ResultSet rs = null;
				try {
					st = cn.createStatement();
					rs = st.executeQuery(
							"select * from produits where categorie='" + catProd.getSelectedItem().toString() + "'");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnSupprimer.setBounds(685, 80, 111, 28);
		panel_3.add(btnSupprimer);

		JButton btnReset_1 = new JButton("RESET");
		btnReset_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idProd.setText("");
				desProd.setText("");
				qteProd.setText("");
				
			}
		});
		btnReset_1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnReset_1.setBounds(211, 246, 120, 28);
		panel_3.add(btnReset_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(372, 129, 424, 145);
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
				qteProd.requestFocus();
				Uprix = Double.valueOf(model.getValueAt(index, 4).toString());
				prixT = Uprix * Double.valueOf(qteProd.getText());
				qteDispo = Double.valueOf(model.getValueAt(index, 3).toString());
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, "", null, null }, },
				new String[] { "ID", "DESIGNATION", "QUANTITIE", "PRIX", "CATEGORIE" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(115, 324, 587, 153);
		panel_3.add(scrollPane_1);

		facTxt = new JTextArea();
		facTxt.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		scrollPane_1.setViewportView(facTxt);

		totale = new JLabel("");
		totale.setForeground(new Color(255, 127, 80));
		totale.setFont(new Font("Century Gothic", Font.BOLD, 22));
		totale.setBounds(272, 487, 338, 28);
		panel_3.add(totale);

		JButton btnNewButton = new JButton("Commander");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Votre commande est ajouter avec succee");
				new Commander().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(282, 525, 111, 24);
		panel_3.add(btnNewButton);

		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnImprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					facTxt.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnImprimer.setBounds(445, 525, 111, 24);
		panel_3.add(btnImprimer);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 69, 0));
		panel_2.setBounds(104, 584, 860, 49);
		contentPane.add(panel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(104, 0, 860, 28);
		contentPane.add(panel_1);
		panel_1.setBackground(new Color(255, 69, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(255, 69, 0));
		panel_4.setBounds(963, 0, 37, 633);
		contentPane.add(panel_4);
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

	public void updateQte() {
		Connection cn = Connexion.getConnexion();
		Statement st = null;
		double qte = qteDispo - Double.valueOf(qteProd.getText());
		try {
			String query = "UPDATE `produits` SET `quantite`='" + qte + "'" + " WHERE id=" + idProd.getText();
			st = cn.createStatement();
			st.executeUpdate(query);
			getProduits();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public void passerCmd() {
		Connection cn = Connexion.getConnexion();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String query = "insert into commandes(produits,userid,quantitie,mtt) values(?,?,?,?)";
			st = cn.prepareStatement(query);
			st.setString(1, desProd.getText());
			st.setInt(2, Login.userId);
			st.setDouble(3, Double.valueOf(qteProd.getText()));
			st.setDouble(4, Uprix);
			int row = st.executeUpdate();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}
