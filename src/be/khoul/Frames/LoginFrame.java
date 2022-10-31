package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.khoul.Pojo.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JCheckBox;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField pf_password;
	private JLabel lbl_password_error;
	private JLabel lbl_username_error;
	private JLabel lbl_login_error;
	private boolean loginValid;

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
	
	
	public void verificationLogin() {
		String username = tf_username.getText();
		String password = new String(pf_password.getPassword());
		
		if(username.isEmpty() || username == null) {
			loginValid = false;
			lbl_username_error.setVisible(true);
			lbl_username_error.setText("Veuillez saisir un nom d'utilisateur");
		}
		
		if(password.isEmpty() || password == null) {
			loginValid = false;
			lbl_username_error.setVisible(true);
			lbl_username_error.setText("Veuillez saisir un mot de passe");
		}
		
			
		
		
		
		
	}
	
	public void maskErrorsMessage() {
		lbl_username_error.setVisible(false);
		lbl_username_error.setVisible(false);
		lbl_login_error.setVisible(false);
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
		lbl_welcome.setBounds(165, 23, 160, 39);
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
		btn_signup.setBounds(61, 249, 96, 33);
		contentPane.add(btn_signup);
		
		JLabel lbl_username = new JLabel("Username:");
		lbl_username.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_username.setBounds(108, 110, 69, 15);
		contentPane.add(lbl_username);
		
		JLabel lbl_password = new JLabel("Password:");
		lbl_password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_password.setBounds(108, 170, 69, 15);
		contentPane.add(lbl_password);
		
		tf_username = new JTextField();
		tf_username.setBounds(186, 109, 96, 19);
		contentPane.add(tf_username);
		tf_username.setColumns(10);
		
		pf_password = new JPasswordField();
		pf_password.setBounds(187, 169, 95, 19);
		contentPane.add(pf_password);
		
		JCheckBox chckbox_admin = new JCheckBox("Admin");
		chckbox_admin.setBounds(383, 168, 93, 21);
		contentPane.add(chckbox_admin);
		
		
		JButton btn_login = new JButton("Se connecter");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginValid = true;
				verificationLogin();
				if(loginValid) {
					maskErrorsMessage();
					User u = User.getUser(tf_username.getText(), new String(pf_password.getPassword()));
					if(u != null) {
						if(u instanceof Player) {
							//Send player to the Home frame	
							Player player = (Player)u;
							PlayerHomeFrame playerHomeFrame = new PlayerHomeFrame(player);
							playerHomeFrame.setVisible(true);
							dispose();
							
						}
						if(u instanceof Administrator) {
							//Send admin to the Home frame
							Administrator admin = (Administrator)u;
							AdminHomeFrame adminHomeFrame = new AdminHomeFrame(admin);
							adminHomeFrame.setVisible(true);
							dispose();
						}
					}
					else {
						lbl_login_error.setVisible(true);
						lbl_login_error.setText("Utilisateur non trouvé");
					}
					
			
				}
				
			}

			
		});
		btn_login.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_login.setBounds(333, 249, 108, 33);
		contentPane.add(btn_login);
		
		lbl_password_error = new JLabel("");
		lbl_password_error.setForeground(new Color(240, 60, 78));
		lbl_password_error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_password_error.setBounds(108, 200, 174, 25);
		lbl_password_error.setVisible(false);
		contentPane.add(lbl_password_error);
		
		lbl_username_error = new JLabel("");
		lbl_username_error.setForeground(new Color(240, 60, 78));
		lbl_username_error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_username_error.setBounds(108, 135, 174, 25);
		lbl_username_error.setVisible(false);
		contentPane.add(lbl_username_error);
		
		lbl_login_error = new JLabel("");
		lbl_login_error.setForeground(new Color(240, 60, 78));
		lbl_login_error.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_login_error.setBounds(108, 290, 264, 33);
		lbl_login_error.setVisible(false);
		contentPane.add(lbl_login_error);
		
		
	}
}
