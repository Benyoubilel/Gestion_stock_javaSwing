package pres;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
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

public class MesCommandes extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtFilter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MesCommandes frame = new MesCommandes();
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
	public MesCommandes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 650);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 69, 0));
		panel_1.setBounds(104, 0, 847, 28);
		contentPane.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(167, 25, 784, 572);
		contentPane.add(panel_3);
		
		JLabel lblMesCommandes = new JLabel("MES COMMANDES");
		lblMesCommandes.setEnabled(false);
		lblMesCommandes.setForeground(new Color(0, 0, 0));
		lblMesCommandes.setFont(new Font("Century Gothic", Font.BOLD, 22));
		lblMesCommandes.setBounds(333, 23, 277, 28);
		panel_3.add(lblMesCommandes);
		
		JLabel welcom = new JLabel("");
		welcom.setForeground(new Color(255, 69, 0));
		welcom.setText("Bienvenu "+Login.userName);
		welcom.setFont(new Font("Century Gothic", Font.BOLD, 18));
		welcom.setBounds(27, 84, 277, 34);
		panel_3.add(welcom);
		
		JLabel lblCategore = new JLabel("Trier Par statut");
		lblCategore.setForeground(new Color(0, 0, 0));
		lblCategore.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblCategore.setBounds(86, 158, 118, 24);
		panel_3.add(lblCategore);
		
		JButton btnSupprimer = new JButton("TRIER");
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection cn = Connexion.getConnexion();
				Statement st = null;
				ResultSet rs = null;
				try {
					st = cn.createStatement();
					rs = st.executeQuery("select * from commandes WHERE userid="+Login.userId+" and statut LIKE '%" + txtFilter.getText() + "%'");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnSupprimer.setBounds(476, 156, 111, 28);
		panel_3.add(btnSupprimer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 222, 676, 182);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "PRODUIT", "QUANTITIE", "MONTANT", "STATUT"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel totale = new JLabel("");
		totale.setForeground(new Color(255, 127, 80));
		totale.setFont(new Font("Century Gothic", Font.BOLD, 22));
		totale.setBounds(272, 487, 338, 28);
		panel_3.add(totale);
		
		txtFilter = new JTextField();
		txtFilter.setBounds(226, 156, 219, 24);
		panel_3.add(txtFilter);
		txtFilter.setColumns(10);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getCommandesByUser();
			}
		});
		btnReset.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnReset.setBounds(601, 156, 111, 28);
		panel_3.add(btnReset);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 69, 0));
		panel_2.setBounds(104, 597, 847, 29);
		contentPane.add(panel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(255, 127, 80));
		panel_4.setBounds(949, 0, 27, 633);
		contentPane.add(panel_4);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(0, 0, 167, 633);
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
		lblMesCommandes_1.setBounds(6, 213, 155, 46);
		panel.add(lblMesCommandes_1);
		
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
		lblNewLabel.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-place-du-marché-50.png"));
		lblNewLabel.setBounds(52, 37, 86, 89);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 574, 66, 12);
		panel.add(separator);
		setResizable(false);
		getCommandesByUser();
	}
	public void getCommandesByUser() {
		Connection cn = Connexion.getConnexion();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = cn.createStatement();
			rs = st.executeQuery("select * from commandes where userid="+Login.userId);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
