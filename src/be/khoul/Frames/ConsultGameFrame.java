package be.khoul.Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	public ConsultGameFrame(Player player, VideoGame v) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		v.getVideoGameCopies();
		v.getVideoGameBookings();
		v.getBookingIntoLoan();
		
		JLabel lbl_videogame = new JLabel(v.getName());
		lbl_videogame.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_videogame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_videogame.setBounds(113, 29, 281, 59);
		contentPane.add(lbl_videogame);
		
		JLabel lbl_console = new JLabel("Console: " + v.getConsole());
		lbl_console.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_console.setBounds(28, 132, 186, 21);
		contentPane.add(lbl_console);
		
		JLabel lbl_credits = new JLabel("Crédits: " + v.getCreditCost());
		lbl_credits.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_credits.setBounds(28, 173, 126, 21);
		contentPane.add(lbl_credits);
		
		JLabel lbl_available = new JLabel("Exemplaire disponible : " + v.getAvailableCopies().size());
		lbl_available.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_available.setBounds(28, 220, 270, 13);
		contentPane.add(lbl_available);
		
		JButton btn_back = new JButton("Retour");
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultGamesFrame consultGamesFrame = new ConsultGamesFrame(player);
				consultGamesFrame.setVisible(true);	
				dispose();
			}
		});
		btn_back.setBounds(31, 348, 87, 29);
		contentPane.add(btn_back);
		
		JLabel lbl_message = new JLabel("");
		lbl_message.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_message.setBounds(189, 313, 311, 29);
		contentPane.add(lbl_message);
		
		
		JButton btn_book = new JButton("Réserver");
		btn_book.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookingFrame bookingFrame = new BookingFrame(player, v);
				bookingFrame.setVisible(true);
				dispose();
				
			}
		});
		btn_book.setBounds(202, 255, 96, 26);
		contentPane.add(btn_book);
		
		JButton btn_lend = new JButton("Prêter");
		btn_lend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Copy copy = new Copy(v, player);
				if(copy.create()) {
					player.addCopy(copy);
					lbl_message.setText("Prêt réussi");
				   	lbl_message.setForeground(Color.GREEN);
				   	v.getBookingIntoLoan();
				}
				else {
					lbl_message.setText("Le prêt de votre jeu à échoué");
				   	lbl_message.setForeground(Color.RED);
				}
				
			}
		});
		btn_lend.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_lend.setBounds(393, 255, 96, 26);
		contentPane.add(btn_lend);
		
		JButton btn_rent = new JButton("Louer");
		btn_rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoanFrame loanFrame = new LoanFrame(player, v);
				loanFrame.setVisible(true);
				dispose();
			}
		});
		btn_rent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_rent.setBounds(28, 255, 90, 26);
		contentPane.add(btn_rent);
		
		
		if(!player.loanAllowed()) {
			btn_book.setEnabled(false);
			btn_rent.setEnabled(false);
			lbl_message.setText("Vous n'avez pas assez de crédit pour louer un jeu");
		   	lbl_message.setForeground(Color.RED);
		}
		
		if(v.getAvailableCopies().size() > 0) {
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
