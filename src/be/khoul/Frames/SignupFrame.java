package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import be.khoul.Pojo.Player;

import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignupFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField pf_password;
	private JLabel lbl_date_error;
	private JLabel lbl_username_error;
	private JLabel lbl_password_error;
	private JTextField tf_pseudo;
	private JLabel lbl_pseudo_error;
	private boolean signupValid;
	private JLabel lbl_message;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupFrame frame = new SignupFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void verificationDate(JDateChooser dateChooser) {
		if(dateChooser.getDate().toString().isEmpty() || dateChooser.getDate() == null) {
			signupValid = false;
	    	lbl_date_error.setText("Une date de naissance est nécessaire pour vous inscrire.");
	    	lbl_date_error.setVisible(true);
	    }
	    
	    Date today = Date.valueOf(LocalDate.now());
	    if(dateChooser.getDate().after(today)) {
	    	signupValid = false;
	    	lbl_date_error.setText("La date de naissance ne peut pas être une date du future.");
	    	lbl_date_error.setVisible(true);
	    }
	}
	
	public void verificationUsernamePassword(JTextField tf_username, JTextField tf_password) {
		String username = tf_username.getText();
		String password = tf_password.getText();
		if(username.isEmpty() || username == null) {
			signupValid = false;
			lbl_username_error.setText("Veuillez saisir un nom d'utilisateur");
			lbl_username_error.setVisible(true);
			
		}
		if(username.length() > 20 || username.length() < 3) {
			signupValid = false;
			lbl_username_error.setText("La taille du nom d'utilisateur doit être entre 3 et 20 caractères");
			lbl_username_error.setVisible(true);
			
		}
		if(password.isEmpty() || password == null) {
			signupValid = false;
			lbl_password_error.setText("Veuillez saisir un mot de passe");
			lbl_password_error.setVisible(true);
		}
		if(password.length() < 8 || password.length() > 30) {
			signupValid = false;
			lbl_password_error.setText("La taille du mot de passe doit être entre 8 et 30 caractères");
			lbl_password_error.setVisible(true);
		}
	}
	
	public void verificationPseudo(JTextField tf_pseudo) {
		if(tf_pseudo.getText().isEmpty() || tf_pseudo.getText() == null) {
			signupValid = false;
			lbl_pseudo_error.setText("Veuillez saisir un pseudo");
			lbl_pseudo_error.setVisible(true);
		}
		
	}
	
	public void maskErrorMessage() {
		lbl_date_error.setVisible(false);
		lbl_username_error.setVisible(false);
		lbl_password_error.setVisible(false);
		lbl_pseudo_error.setVisible(false);
	}
	
	
	/**
	 * Create the frame.
	 */
	public SignupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_inscription = new JLabel("Inscription");
		lbl_inscription.setBounds(183, 26, 137, 29);
		lbl_inscription.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inscription.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lbl_inscription);
		
		JLabel lbl_username = new JLabel("Username:");
		lbl_username.setBounds(37, 88, 64, 13);
		lbl_username.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_username.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lbl_username);
		
		tf_username = new JTextField();
		tf_username.setBounds(111, 86, 96, 19);
		contentPane.add(tf_username);
		tf_username.setColumns(10);
		
		JLabel lbl_password = new JLabel("Mot de passe:");
		lbl_password.setBounds(37, 152, 79, 13);
		lbl_password.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lbl_password);
		
		pf_password = new JPasswordField();
		pf_password.setBounds(133, 150, 96, 19);
		contentPane.add(pf_password);
		pf_password.setColumns(10);
		
		JLabel lbl_dateofbirth = new JLabel("Date de naissance:");
		lbl_dateofbirth.setBounds(40, 221, 101, 13);
		lbl_dateofbirth.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_dateofbirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lbl_dateofbirth);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(161, 214, 200, 20);
	    getContentPane().add(dateChooser);
	    
	    JLabel lbl_pseudo = new JLabel("Pseudo:");
	    lbl_pseudo.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbl_pseudo.setBounds(37, 275, 64, 13);
	    contentPane.add(lbl_pseudo);
	    
	    
	    
	    lbl_date_error = new JLabel("");
	    lbl_date_error.setForeground(new Color(234, 58, 21));
	    lbl_date_error.setFont(new Font("Tahoma", Font.BOLD, 12));
	    lbl_date_error.setBounds(39, 244, 410, 19);
	    lbl_date_error.setVisible(false);
	    contentPane.add(lbl_date_error);
	    
	    lbl_username_error = new JLabel("");
	    lbl_username_error.setForeground(new Color(234, 58, 21));
	    lbl_username_error.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbl_username_error.setBounds(37, 115, 431, 27);
	    lbl_username_error.setVisible(false);
	    contentPane.add(lbl_username_error);
	    
	    lbl_password_error = new JLabel("");
	    lbl_password_error.setForeground(new Color(234, 58, 21));
	    lbl_password_error.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbl_password_error.setBounds(37, 177, 431, 27);
	    lbl_password_error.setVisible(false);
	    contentPane.add(lbl_password_error);
	    
	    
	    tf_pseudo = new JTextField();
	    tf_pseudo.setBounds(98, 273, 96, 19);
	    contentPane.add(tf_pseudo);
	    tf_pseudo.setColumns(10);
	    
	    lbl_pseudo_error = new JLabel("");
	    lbl_pseudo_error.setForeground(new Color(234, 58, 21));
	    lbl_pseudo_error.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbl_pseudo_error.setBounds(37, 298, 431, 27);
	    lbl_pseudo_error.setVisible(false);
	    contentPane.add(lbl_pseudo_error);
	    
	    lbl_message = new JLabel("");
	    lbl_message.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbl_message.setBounds(133, 396, 283, 29);
	    lbl_message.setVisible(false);
	    contentPane.add(lbl_message);
	    
	    JButton btn_confirm = new JButton("Confirmer");
	    btn_confirm.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		signupValid = true;
	    		verificationDate(dateChooser);
	    		verificationUsernamePassword(tf_username, pf_password);
	    		verificationPseudo(tf_pseudo);
	    		if(signupValid) {
	    			maskErrorMessage();
	    			LocalDate birth_date = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    			//System.out.println(birth_date);
	    			Player player = new Player(tf_username.getText(), new String(pf_password.getPassword()), 10, LocalDate.now(), birth_date, tf_pseudo.getText());
	    			//System.out.println(player.getDateOfBirthToDate());
	    			if(player.addPlayer()) {
	    				lbl_message.setText("Inscription réussite");
	    				lbl_message.setVisible(true);
	    				lbl_message.setForeground(Color.GREEN);
	    			}
	    			else {
	    				lbl_message.setVisible(true);
	    				lbl_message.setText("Il semble qu'une erreur s'est produite lors de l'inscription.");
	    				lbl_message.setForeground(Color.RED);
	    			}
	    		}
	    	}
	    });
	    btn_confirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    btn_confirm.setBounds(439, 366, 100, 29);
	    contentPane.add(btn_confirm);
	    
	    JButton btn_back = new JButton("Retour");
	    btn_back.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    		LoginFrame loginFrame = new LoginFrame();
	    		loginFrame.setVisible(true);
	    	}
	    });
	    btn_back.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    btn_back.setBounds(10, 366, 106, 29);
	    contentPane.add(btn_back);
	    
	    
		
		
	}
}
