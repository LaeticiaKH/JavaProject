package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.khoul.Pojo.Administrator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AdminHomeFrame extends JFrame {

	private JPanel contentPane;

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
		lbl_welcome.setBounds(47, 42, 483, 38);
		getContentPane().add(lbl_welcome);
		
		JButton btn_change_credit = new JButton("Voir jeux");
		btn_change_credit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultGamesAdminFrame consultGamesAdminFrame = new ConsultGamesAdminFrame(admin);
				consultGamesAdminFrame.setVisible(true);
				dispose();
				
			}
		});
		btn_change_credit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_change_credit.setBounds(187, 149, 202, 45);
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
		contentPane.add(btn_log_out);
		
	}
}
