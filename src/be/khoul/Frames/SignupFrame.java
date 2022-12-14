package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
	private JDateChooser dateChooser;
	private JLabel lbl_date_error;
	private JLabel lbl_username_error;
	private JLabel lbl_password_error;
	private JTextField tf_pseudo;
	private JLabel lbl_pseudo_error;
	private boolean signupValid;
	private JLabel lbl_message;
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
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void verificationDate(JDateChooser dateChooser) {
		
		try {
			LocalDate date = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			if(date.toString().isEmpty()) {
				signupValid = false;
		    	lbl_date_error.setText("Une date de naissance est nécessaire pour vous inscrire.");
		    	lbl_date_error.setVisible(true);
		    }
		    
		    if(date.isAfter(LocalDate.now())) {
		    	signupValid = false;
		    	lbl_date_error.setText("La date de naissance ne peut pas être une date du future.");
		    	lbl_date_error.setVisible(true);
		    }
			
		} catch(NullPointerException e) {
			signupValid = false;
	    	lbl_date_error.setText("Une date de naissance est nécessaire pour vous inscrire.");
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
		if(Player.usernameExist(username)) {
			signupValid = false;
			lbl_username_error.setText("Ce nom d'utilisateur est déjà utilisé.");
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
		String pseudo = tf_pseudo.getText();
		if(pseudo.isEmpty() || tf_pseudo.getText() == null) {
			signupValid = false;
			lbl_pseudo_error.setText("Veuillez saisir un pseudo");
			lbl_pseudo_error.setVisible(true);
		}
		if(Player.pseudoExist(pseudo)) {
			signupValid = false;
			lbl_pseudo_error.setText("Ce pseudo est déjà utilisé.");
			lbl_pseudo_error.setVisible(true);
		}
		
		
		
	}
	
	public void maskErrorMessage() {
		lbl_date_error.setVisible(false);
		lbl_username_error.setVisible(false);
		lbl_password_error.setVisible(false);
		lbl_pseudo_error.setVisible(false);
	}
	
	public void emptyTextField() {
		tf_username.setText("");
		pf_password.setText("");
		dateChooser.setDate(null);
		tf_pseudo.setText("");
		
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
		
		JLabel lbl_title = new JLabel("Inscription");
		lbl_title.setBounds(0, 10, 604, 41);
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lbl_title.setForeground(color_text);
		lbl_title.setBorder(new LineBorder(Color.white));
		lbl_title.setOpaque(true);
		lbl_title.setBackground(color_background_label);
		contentPane.add(lbl_title);
		
		JLabel lbl_username = new JLabel("Username:");
		lbl_username.setBounds(37, 88, 101, 13);
		lbl_username.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lbl_username.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lbl_username);
		
		tf_username = new JTextField();
		tf_username.setBounds(153, 86, 144, 19);
		contentPane.add(tf_username);
		tf_username.setColumns(10);
		
		JLabel lbl_password = new JLabel("Mot de passe:");
		lbl_password.setBounds(37, 152, 101, 13);
		lbl_password.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_password.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		contentPane.add(lbl_password);
		
		pf_password = new JPasswordField();
		pf_password.setBounds(155, 150, 144, 19);
		contentPane.add(pf_password);
		pf_password.setColumns(10);
		
		JLabel lbl_dateofbirth = new JLabel("Date de naissance:");
		lbl_dateofbirth.setBounds(39, 214, 121, 20);
		lbl_dateofbirth.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_dateofbirth.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		contentPane.add(lbl_dateofbirth);
		
	    dateChooser = new JDateChooser();
		dateChooser.setBounds(161, 214, 138, 20);
	    getContentPane().add(dateChooser);
	    
	    JLabel lbl_pseudo = new JLabel("Pseudo:");
	    lbl_pseudo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
	    lbl_pseudo.setBounds(37, 283, 86, 13);
	    contentPane.add(lbl_pseudo);
	    
	    
	    
	    lbl_date_error = new JLabel("");
	    lbl_date_error.setForeground(new Color(234, 58, 21));
	    lbl_date_error.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
	    lbl_date_error.setBounds(39, 244, 410, 29);
	    lbl_date_error.setVisible(false);
	    contentPane.add(lbl_date_error);
	    
	    lbl_username_error = new JLabel("");
	    lbl_username_error.setForeground(new Color(234, 58, 21));
	    lbl_username_error.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
	    lbl_username_error.setBounds(56, 115, 431, 27);
	    lbl_username_error.setVisible(false);
	    contentPane.add(lbl_username_error);
	    
	    lbl_password_error = new JLabel("");
	    lbl_password_error.setForeground(new Color(234, 58, 21));
	    lbl_password_error.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
	    lbl_password_error.setBounds(37, 175, 450, 27);
	    lbl_password_error.setVisible(false);
	    contentPane.add(lbl_password_error);
	    
	    
	    tf_pseudo = new JTextField();
	    tf_pseudo.setBounds(155, 281, 142, 19);
	    contentPane.add(tf_pseudo);
	    tf_pseudo.setColumns(10);
	    
	    lbl_pseudo_error = new JLabel("");
	    lbl_pseudo_error.setForeground(new Color(234, 58, 21));
	    lbl_pseudo_error.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
	    lbl_pseudo_error.setBounds(37, 298, 431, 27);
	    lbl_pseudo_error.setVisible(false);
	    contentPane.add(lbl_pseudo_error);
	    
	    lbl_message = new JLabel("");
	    lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
	    lbl_message.setBounds(32, 396, 521, 29);
	    lbl_message.setVisible(false);
	    contentPane.add(lbl_message);
	    
	    JButton btn_confirm = new JButton("Confirmer");
	    btn_confirm.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		maskErrorMessage();
	    		signupValid = true;
	    		verificationDate(dateChooser);
	    		verificationUsernamePassword(tf_username, pf_password);
	    		verificationPseudo(tf_pseudo);
	    		if(signupValid) {
	    			maskErrorMessage();
	    			LocalDate birth_date = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    			Player player = new Player(tf_username.getText(), new String(pf_password.getPassword()), 10, LocalDate.now(), birth_date, tf_pseudo.getText(), false);
	    			if(player.signUp()) {
	    				lbl_message.setText("Inscription réussite");
	    				lbl_message.setVisible(true);
	    				lbl_message.setForeground(new Color(55, 175, 95));
	    				emptyTextField();
	    				
	    			}
	    			else {
	    				lbl_message.setVisible(true);
	    				lbl_message.setText("Il semble qu'une erreur s'est produite lors de l'inscription.");
	    				lbl_message.setForeground(Color.RED);
	    			}
	    		}
	    	}
	    });
	    btn_confirm.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
	    btn_confirm.setBounds(447, 366, 106, 29);
	    btn_confirm.setBackground(color_background_btn);
		btn_confirm.setForeground(color_text);
	    contentPane.add(btn_confirm);
	    
	    JButton btn_back = new JButton("Retour");
	    btn_back.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    		LoginFrame loginFrame = new LoginFrame();
	    		loginFrame.setVisible(true);
	    	}
	    });
	    btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
	    btn_back.setBounds(32, 366, 106, 29);
	    btn_back.setBackground(color_background_btn);
		btn_back.setForeground(color_text);
	    contentPane.add(btn_back);
	    
	    
		
		
	}
}
