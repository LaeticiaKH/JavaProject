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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;

public class LoanFrame extends JFrame {

	private JPanel contentPane;
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
		lbl_loan.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_loan.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_loan.setBounds(181, 32, 161, 25);
		contentPane.add(lbl_loan);
		
		JLabel lbl_video_game = new JLabel("Vous souhaitez emprunter une copie de " + videoGame.getName());
		lbl_video_game.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_video_game.setBounds(25, 89, 480, 13);
		contentPane.add(lbl_video_game);
		
		JLabel lbl_cost = new JLabel("Crédits par semaine : " + videoGame.getCreditCost());
		lbl_cost.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_cost.setBounds(41, 116, 273, 13);
		contentPane.add(lbl_cost);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		JLabel lbl_start_date = new JLabel("Date de début: " + LocalDate.now().format(formatter));
		lbl_start_date.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_start_date.setBounds(25, 169, 190, 13);
		contentPane.add(lbl_start_date);
		
		JLabel lbl_duration = new JLabel("Durée en semaines :");
		lbl_duration.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_duration.setBounds(25, 216, 116, 18);
		contentPane.add(lbl_duration);
		
		 Choice choice = new Choice();
		 choice.setBounds(147, 216, 46, 18);
		 contentPane.add(choice);
		 
		 for(int i=1; i <=12; i++) {
				choice.add("" + i);
		 }
		 
	    
	    JTextArea ta_warning = new JTextArea("Attention : Si vous ne rendez pas le jeu à la date convenue,\n une pénalité de 5 crédits/jour vous sera demandé.");
	    ta_warning.setBounds(25, 289, 491, 53);
	    contentPane.add(ta_warning);
	    
	    JButton btn_confirm = new JButton("Confirmer");
	    btn_confirm.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		lbl_message.setVisible(false);
	    		
	  
	    		Copy copy  = videoGame.copyAvailable();
	    		if(copy != null) {
	    			LocalDate end_date = LocalDate.now().plusWeeks(Integer.parseInt(choice.getSelectedItem()));
	    			Loan l = new Loan(LocalDate.now(), end_date, true, copy, copy.getOwner(), player);
	    			if(copy.borrow(l)) {
	    				//go back to the game frame
	    				ConsultGameFrame consultGameFrame = new ConsultGameFrame(player, videoGame);
	    				consultGameFrame.setVisible(true);
	    				dispose();
	    				
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
	    });
	    btn_confirm.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
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
	    btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
	    btn_back.setBounds(48, 370, 93, 25);
	    contentPane.add(btn_back);
	    
	    lbl_message = new JLabel("");
	    lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
	    lbl_message.setBounds(48, 416, 500, 20);
	    lbl_message.setVisible(false);
	    contentPane.add(lbl_message);
	    
	   
	    
	  
		
		
	}
}
