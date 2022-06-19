package pres;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Loader extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Loader myLoader = new Loader();
		myLoader.setVisible(true);
		try {
			for (int i = 0; i <= 100; i++) {
				Thread.sleep(40);
				myLoader.progressBar.setValue(i);
				myLoader.lblNewLabel_1.setText(Integer.toString(i) + "%");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		new Login().setVisible(true);
		myLoader.dispose();

	}

	/**
	 * Create the frame.
	 */
	public Loader() {
	//	setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/icons8-boutique-50.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(240, 255, 255));
		contentPane.setBackground(new Color(255, 69, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(30, 144, 255));
		progressBar.setBounds(0, 361, 650, 11);
		contentPane.add(progressBar);

		lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setForeground(new Color(240, 255, 255));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_1.setBounds(222, 317, 58, 55);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2_1 = new JLabel("Gestion ");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_2_1.setBounds(130, 0, 144, 48);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_3 = new JLabel("De");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Snell Roundhand", Font.ITALIC, 15));
		lblNewLabel_3.setBounds(233, 36, 61, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Stock");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 31));
		lblNewLabel_4.setBounds(256, 52, 144, 35);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"/Users/bilelbenyoussef/eclipse-workspace/Gestion_Stock/image/in_store_interactive_marketing_banner_blog.jpeg"));
		lblNewLabel.setBounds(0, 115, 460, 190);
		contentPane.add(lblNewLabel);
	}
}