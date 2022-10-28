package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_welcome = new JLabel("Bienvenue");
		lbl_welcome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_welcome.setBounds(169, 39, 160, 39);
		contentPane.add(lbl_welcome);
		
		JButton btn_signup = new JButton("S'inscrire");
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupFrame signupFrame = new SignupFrame();
				signupFrame.setVisible(true);		
				dispose();
			}
		});
		btn_signup.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_signup.setBounds(193, 167, 93, 33);
		contentPane.add(btn_signup);
	}
}
