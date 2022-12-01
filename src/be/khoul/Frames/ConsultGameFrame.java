package be.khoul.Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import be.khoul.Pojo.*;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultGameFrame extends JFrame {

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
	public ConsultGameFrame(Player player, VideoGame v) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		v.getVideoGameCopies();
		v.getVideoGameBookings();
		
		JLabel lbl_videogame = new JLabel(v.getName());
		lbl_videogame.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_videogame.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_videogame.setBounds(0, 20, 540, 45);
		designTitle(lbl_videogame);
		contentPane.add(lbl_videogame);
		
		JLabel lbl_console = new JLabel("Console: " + v.getConsole());
		lbl_console.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_console.setBounds(28, 98, 186, 21);
		contentPane.add(lbl_console);
		
		JLabel lbl_credits = new JLabel("Crédits: " + v.getCreditCost());
		lbl_credits.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_credits.setBounds(28, 141, 126, 21);
		contentPane.add(lbl_credits);
		
		JLabel lbl_available = new JLabel("Exemplaire(s) disponible(s) : " + v.getAvailableCopiesForPlayer(player).size());
		lbl_available.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_available.setBounds(28, 187, 270, 13);
		contentPane.add(lbl_available);
		
		JButton btn_back = new JButton("Retour");
		btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultGamesFrame consultGamesFrame = new ConsultGamesFrame(player);
				consultGamesFrame.setVisible(true);	
				dispose();
			}
		});
		btn_back.setBounds(31, 348, 87, 29);
		designButton(btn_back);
		contentPane.add(btn_back);
		
		JLabel lbl_message = new JLabel("");
		lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_message.setBounds(28, 304, 472, 29);
		contentPane.add(lbl_message);
		
		
		JButton btn_book = new JButton("Réserver");
		btn_book.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Booking> bookings = player.getBookings();
				boolean canBook = true;
				for(Booking b: bookings) {
					if(b.getVideoGame().getId() == v.getId()) {
						canBook = false;
					}
					
				}
				if(canBook) {
					BookingFrame bookingFrame = new BookingFrame(player, v);
					bookingFrame.setVisible(true);
					dispose();
				}
				else {
					lbl_message.setText("Vous avez déjà une réservation en cours.");
				   	lbl_message.setForeground(Color.RED);
				}
				
				
				
			}
		});
		btn_book.setBounds(202, 252, 96, 29);
		designButton(btn_book);
		contentPane.add(btn_book);
		
		JButton btn_lend = new JButton("Prêter");
		btn_lend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Copy copy = new Copy(v, player);
				if(copy.create()) {
					lbl_message.setText("Prêt réussi.");
				   	lbl_message.setForeground(new Color(55, 175, 95));
				   	v.getBookingIntoLoan();
				}
				else {
					lbl_message.setText("Le prêt de votre jeu à échoué.");
				   	lbl_message.setForeground(Color.RED);
				}
				
			}
		});
		btn_lend.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_lend.setBounds(393, 252, 96, 29);
		designButton(btn_lend);
		contentPane.add(btn_lend);
		
		JButton btn_rent = new JButton("Louer");
		btn_rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Loan> loans  = player.getLoans();
				Boolean canRent = true;
				for(Loan l: loans) {
					if(l.getCopy().getVideoGame().getId() == v.getId() && l.isOngoing()) {
						//If a ongoing loan is found for this game
						canRent = false;
					}
				}
				if(canRent) {
					LoanFrame loanFrame = new LoanFrame(player, v);
					loanFrame.setVisible(true);
					dispose();
				}
				else {
					lbl_message.setText("Vous avez déjà une location en cours.");
				   	lbl_message.setForeground(Color.RED);
				}
				
			}
		});
		btn_rent.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_rent.setBounds(28, 252, 90, 29);
		designButton(btn_rent);
		contentPane.add(btn_rent);
		
		
		if(!player.loanAllowed()) {
			btn_book.setEnabled(false);
			btn_rent.setEnabled(false);
			lbl_message.setText("Vous n'avez pas assez de crédit pour louer ou réserver un jeu");
		   	lbl_message.setForeground(Color.RED);
		}
		
		if(v.getAvailableCopiesForPlayer(player).size() > 0) {
			//Loan can happen directly
			btn_book.setEnabled(false);
			btn_rent.setEnabled(true);
			
		}
		else {
			//The player has to book the game
			btn_book.setEnabled(true);
			btn_rent.setEnabled(false);
		}
		
		
		
		

	    
		
		
		
	}
}
