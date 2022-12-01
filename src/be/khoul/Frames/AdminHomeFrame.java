package be.khoul.Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import be.khoul.Pojo.Administrator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AdminHomeFrame extends JFrame {

	private JPanel contentPane;
	private static Color color_background_label = Color.darkGray;
	private static Color color_background_btn= Color.darkGray;
	private static Color color_text = Color.white;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void designTitle(JLabel lbl_title) {
		lbl_title.setForeground(color_text);
		lbl_title.setBorder(new LineBorder(Color.white));
		lbl_title.setOpaque(true);
		lbl_title.setBackground(color_background_label);
	}
	
	public void designButton(JButton btn) {
		btn.setBackground(color_background_btn);
		btn.setForeground(color_text);
	}
	/**
	 * Create the frame.
	 */
	public AdminHomeFrame(Administrator admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_welcome = new JLabel("Bienvenue admin " + admin.getUsername() + "!");
		lbl_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_welcome.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_welcome.setBounds(0, 42, 588, 38);
		designTitle(lbl_welcome);
		getContentPane().add(lbl_welcome);
		
		JButton btn_change_credit = new JButton("Voir jeux vidéo");
		btn_change_credit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultGamesAdminFrame consultGamesAdminFrame = new ConsultGamesAdminFrame(admin);
				consultGamesAdminFrame.setVisible(true);
				dispose();
				
			}
		});
		btn_change_credit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_change_credit.setBounds(187, 149, 202, 45);
		designButton(btn_change_credit);
		contentPane.add(btn_change_credit);
		
		
		JButton btn_log_out = new JButton("Déconnexion");
		btn_log_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose();
			}
		});
		btn_log_out.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_log_out.setBounds(187, 244, 202, 45);
		designButton(btn_log_out);
		contentPane.add(btn_log_out);
		
	}
}
