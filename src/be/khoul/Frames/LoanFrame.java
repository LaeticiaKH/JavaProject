package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import be.khoul.Pojo.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoanFrame extends JFrame {

	private JPanel contentPane;
	private boolean dateValid;
	private JLabel lbl_date_error;
	private JLabel lbl_message;

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
	
	public void validationDate(JDateChooser dateChooser) {
		dateValid = true;

		LocalDate date = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if(date.toString().isEmpty() || date == null) {
			dateValid = false;
	    	lbl_date_error.setText("Une date de naissance est nécessaire pour vous inscrire.");
	    	lbl_date_error.setVisible(true);
	    }
	    
	    
	    if(date.isBefore(LocalDate.now())) {
	    	dateValid = false;
	    	lbl_date_error.setText("La date de fin de location ne peut pas être une date du passé.");
	    	lbl_date_error.setVisible(true);
	    }
	    
	    if(date.isAfter(LocalDate.now().plusMonths(6))) {
	    	dateValid = false;
	    	lbl_date_error.setText("La date de fin de location ne peut pas dépassé plus de 6 mois");
	    	lbl_date_error.setVisible(true);
	    }
	    
	    
	}
		
		
	

	/**
	 * Create the frame.
	 */
	public LoanFrame(Player player, VideoGame videoGame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_loan = new JLabel("Emprunt");
		lbl_loan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_loan.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_loan.setBounds(181, 32, 161, 25);
		contentPane.add(lbl_loan);
		
		JLabel lbl_video_game = new JLabel("Vous souhaitez emprunter une copie de " + videoGame.getName());
		lbl_video_game.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_video_game.setBounds(25, 89, 480, 13);
		contentPane.add(lbl_video_game);
		
		JLabel lbl_cost = new JLabel("Crédits par semaine : " + videoGame.getCreditCost());
		lbl_cost.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_cost.setBounds(41, 116, 273, 13);
		contentPane.add(lbl_cost);
		
		JLabel lbl_start_date = new JLabel("Date de début: " + LocalDate.now());
		lbl_start_date.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_start_date.setBounds(25, 169, 190, 13);
		contentPane.add(lbl_start_date);
		
		JLabel lbl_end_date = new JLabel("Date de fin :");
		lbl_end_date.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_end_date.setBounds(25, 216, 93, 13);
		contentPane.add(lbl_end_date);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(123, 209, 121, 20);
	    getContentPane().add(dateChooser);
	    
	    lbl_date_error = new JLabel("");
	    lbl_date_error.setForeground(new Color(232, 49, 81));
	    lbl_date_error.setBounds(110, 254, 395, 13);
	    lbl_date_error.setVisible(false);
	    contentPane.add(lbl_date_error);
	    
	    JTextArea ta_warning = new JTextArea("Attention : Si vous ne rendez pas le jeu à la date convenue,\n une pénalité de 5 crédits/jour vous sera demandé.");
	    ta_warning.setBounds(25, 289, 491, 53);
	    contentPane.add(ta_warning);
	    
	    JButton btn_confirm = new JButton("Confirmer");
	    btn_confirm.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		lbl_date_error.setVisible(false);
	    		lbl_message.setVisible(false);
	    		validationDate(dateChooser);
	    		if(dateValid) {
	    
	    			 Copy copy  = videoGame.copyAvailable();
	    			 if(copy != null) {
	    				 LocalDate end_date = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    				 copy.setLoan(new Loan(LocalDate.now(), end_date, dateValid, copy, copy.getOwner(), player));
	    				 if(copy.borrow()) {
	    					 //Show confirmation message
	    					 lbl_message.setVisible(true);
	    					 lbl_message.setText("Location réussite");
	    					 lbl_message.setForeground(Color.GREEN);
	    				 }
	    				 else {
	    					 //Show error message :
	    					 lbl_message.setVisible(true);
	 	    				 lbl_message.setText("Il semble qu'une erreur s'est produite lors de la location.");
	 	    				 lbl_message.setForeground(Color.RED);
	    				 }
	    			 }
	    			 else {
	    				 //Show error message : no more available copies
	    				 lbl_message.setVisible(true);
 	    				 lbl_message.setText("La location n'a pas pu se dérouler car il n'y a plus de copies disponbles.");
 	    				 lbl_message.setForeground(Color.RED);
	    			 }
	    			 	    			
	    		}
	    	}
	    });
	    btn_confirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    btn_confirm.setBounds(472, 370, 98, 25);
	    contentPane.add(btn_confirm);
	    
	    JButton btn_back = new JButton("Retour");
	    btn_back.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ConsultGameFrame consultGameFrame = new ConsultGameFrame(player, videoGame);
	    		consultGameFrame.setVisible(true);
	    		dispose();
	    	}
	    });
	    btn_back.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    btn_back.setBounds(48, 370, 93, 25);
	    contentPane.add(btn_back);
	    
	    lbl_message = new JLabel("");
	    lbl_message.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbl_message.setBounds(48, 416, 500, 20);
	    lbl_message.setVisible(false);
	    contentPane.add(lbl_message);
	    
	  
		
		
	}
}
