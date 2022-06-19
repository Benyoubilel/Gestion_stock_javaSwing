package pres;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class Menu extends JFrame {

	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setResizable(false);
		setTitle("MenuAdmin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 69, 0));
		panel.setBounds(6, 6, 438, 260);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(182, 6, 250, 248);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(97, 29, 61, 16);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Gerer Produits");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Produits frame = new Produits();
				frame.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(6, 81, 238, 29);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gerer Categories");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Categorie frame = new Categorie();
				frame.setVisible(true);
				
				
			}
		});
		btnNewButton_1.setBounds(6, 122, 238, 29);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Gerer Clients");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Clients frame = new Clients();
				frame.setVisible(true);
			}
		});
		btnNewButton_2.setBackground(new Color(0, 0, 128));
		btnNewButton_2.setBounds(6, 204, 238, 29);
		panel_1.add(btnNewButton_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 46, 213, 12);
		panel_1.add(separator);
		
		JButton btnNewButton_1_1 = new JButton("Gerer Commandes");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Commandes frame = new Commandes();
				frame.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(6, 163, 238, 29);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_3 = new JButton("Logout");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(6, 225, 164, 29);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome");
		lblNewLabel_1.setFont(new Font("Brush Script MT", Font.BOLD | Font.ITALIC, 37));
		lblNewLabel_1.setBounds(25, 6, 145, 57);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-boutique-50.png"));
		lblNewLabel_2.setBounds(59, 99, 85, 66);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Admin");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_3.setBounds(109, 47, 61, 16);
		panel.add(lblNewLabel_3);
	}
}
