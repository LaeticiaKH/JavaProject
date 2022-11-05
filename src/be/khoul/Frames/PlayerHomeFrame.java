package be.khoul.Frames;

import java.awt.EventQueue;

import be.khoul.Pojo.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerHomeFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//PlayerHomeFrame frame = new PlayerHomeFrame();
					//frame.setVisible(true);
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
	public PlayerHomeFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		player.getOwnCopies();
		player.getOwnBookings();
		player.getOwnLoans();
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_welcome = new JLabel("Bonjour " + player.getPseudo() + "!");
		lbl_welcome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_welcome.setBounds(35, 36, 244, 22);
		contentPane.add(lbl_welcome);
		
		JLabel lbl_credit = new JLabel("Vous disposez de " + player.getCredit() + " crédits");
		lbl_credit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_credit.setBounds(31, 71, 248, 13);
		contentPane.add(lbl_credit);
		
		JButton btn_consultgames = new JButton("Voir Jeux Vidéos");
		btn_consultgames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultGamesFrame consultGamesFrame = new ConsultGamesFrame(player);
				consultGamesFrame.setVisible(true);
				dispose();
					
				
			}
		});
		btn_consultgames.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_consultgames.setBounds(10, 131, 142, 30);
		contentPane.add(btn_consultgames);
		
		JButton btn_consult_bookings = new JButton("Mes réservations");
		btn_consult_bookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookingsFrame bookingsFrame = new BookingsFrame(player);
				bookingsFrame.setVisible(true);
				dispose();
			}
		});
		btn_consult_bookings.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_consult_bookings.setBounds(173, 132, 142, 30);
		contentPane.add(btn_consult_bookings);
		
		JButton btn_consult_copies = new JButton("Mes copies");
		btn_consult_copies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_consult_copies.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_consult_copies.setBounds(346, 131, 142, 30);
		contentPane.add(btn_consult_copies);
	}
}
