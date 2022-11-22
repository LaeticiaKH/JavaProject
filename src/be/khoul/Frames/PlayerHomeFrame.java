package be.khoul.Frames;

import java.awt.EventQueue;

import be.khoul.Pojo.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class PlayerHomeFrame extends JFrame {

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

	public void design_buttons(JButton btn) {
		btn.setBackground(Color.pink);
		btn.setForeground(Color.white);
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
		lbl_welcome.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_welcome.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lbl_welcome.setBounds(10, 28, 478, 44);
		lbl_welcome.setForeground(Color.white);
		lbl_welcome.setBorder(new LineBorder(Color.white));
		lbl_welcome.setOpaque(true);
		lbl_welcome.setBackground(Color.darkGray);
		
		contentPane.add(lbl_welcome);
		
		JLabel lbl_credit = new JLabel("Vous disposez de " + player.getCredit() + " crédits");
		lbl_credit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_credit.setBounds(41, 89, 248, 13);
		contentPane.add(lbl_credit);
		
		JButton btn_consultgames = new JButton("Voir Jeux Vidéos");
		btn_consultgames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultGamesFrame consultGamesFrame = new ConsultGamesFrame(player);
				consultGamesFrame.setVisible(true);
				dispose();
					
				
			}
		});
		btn_consultgames.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
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
		btn_consult_bookings.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_consult_bookings.setBounds(173, 132, 142, 30);
		contentPane.add(btn_consult_bookings);
		
		JButton btn_consult_copies = new JButton("Mes copies");
		btn_consult_copies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CopiesFrame copiesFrame = new CopiesFrame(player);
				copiesFrame.setVisible(true);
				dispose();
				
			}
		});
		btn_consult_copies.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_consult_copies.setBounds(346, 131, 142, 30);
		contentPane.add(btn_consult_copies);
		
		JButton btn_consult_loans = new JButton("Mes emprunts");
		btn_consult_loans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoansFrame loansFrame = new LoansFrame(player);
				loansFrame.setVisible(true);
				dispose();
			}
		});
		btn_consult_loans.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_consult_loans.setBounds(10, 201, 142, 30);
		contentPane.add(btn_consult_loans);
		
		JButton btn_log_out = new JButton("Déconnexion");
		btn_log_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose();
				
			}
		});
		btn_log_out.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_log_out.setBounds(346, 312, 142, 30);
		contentPane.add(btn_log_out);
		
		JLabel lbl_birthday = new JLabel("Bonne anniversaire ! Vous recevez 2 crédits bonus");
		lbl_birthday.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		lbl_birthday.setForeground(new Color(147, 47, 49));
		lbl_birthday.setBounds(14, 305, 322, 44);
		lbl_birthday.setVisible(false);
		contentPane.add(lbl_birthday);
		
		if(player.isBirthday()) {
			lbl_birthday.setVisible(true);
		}
	}
}
