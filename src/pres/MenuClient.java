package pres;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class MenuClient extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuClient frame = new MenuClient();
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
	public MenuClient() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 452, 266);
		contentPane.add(panel);
		panel.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 69, 0));
		panel_1.setBounds(0, 0, 167, 266);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(6, 231, 155, 29);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Brush Script MT", Font.BOLD | Font.ITALIC, 37));
		lblNewLabel.setBounds(30, 28, 131, 41);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-boutique-50.png"));
		lblNewLabel_2.setBounds(48, 101, 100, 75);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Menu");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel_1.setBounds(266, 21, 125, 46);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Mes Commandes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(194, 202, 232, 29);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Passer Commande");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Commander frame = new Commander();
				frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(194, 102, 232, 29);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Gerer Profile");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EditProfile frame = new EditProfile();
				frame.setVisible(true);
			
			}
		});
		btnNewButton_3.setBounds(194, 151, 232, 29);
		panel.add(btnNewButton_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(194, 57, 232, 12);
		panel.add(separator);
	}
}
