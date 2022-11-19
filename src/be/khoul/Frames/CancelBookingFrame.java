package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.khoul.Pojo.*;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CancelBookingFrame extends JFrame {

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
	public CancelBookingFrame(Player player, Booking booking) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_title = new JLabel("Annulation de réservation");
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_title.setBounds(154, 39, 257, 33);
		contentPane.add(lbl_title);
		
		JButton btn_back = new JButton("Retour");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookingsFrame bookingsFrame = new BookingsFrame(player);
				bookingsFrame.setVisible(true);
				dispose();
			}
		});
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_back.setBounds(59, 330, 108, 33);
		contentPane.add(btn_back);
		
		JButton btn_confirm = new JButton("Confirmer");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				booking.delete();
				BookingsFrame bookingsFrame = new BookingsFrame(player);
				bookingsFrame.setVisible(true);
				dispose();
				
			}
		});
		btn_confirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_confirm.setBounds(414, 330, 108, 33);
		contentPane.add(btn_confirm);
		
		JLabel lbl_video_game = new JLabel("Jeu :" + booking.getVideoGame().getName());
		lbl_video_game.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_video_game.setBounds(59, 118, 170, 20);
		contentPane.add(lbl_video_game);
		
		JLabel lbl_console = new JLabel("Console : " + booking.getVideoGame().getConsole());
		lbl_console.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_console.setBounds(59, 167, 170, 20);
		contentPane.add(lbl_console);
		
		JLabel lbl_credit = new JLabel("Crédit : " + booking.getVideoGame().getCreditCost());
		lbl_credit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_credit.setBounds(59, 221, 170, 20);
		contentPane.add(lbl_credit);
		
		JLabel lbl_date = new JLabel("Date de réservation : " + booking.getBookingDate());
		lbl_date.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_date.setBounds(59, 273, 170, 20);
		contentPane.add(lbl_date);
	}

}
