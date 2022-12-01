package be.khoul.Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import be.khoul.Pojo.*;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CancelBookingFrame extends JFrame {

	private JPanel contentPane;
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
	public CancelBookingFrame(Player player, Booking booking) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_title = new JLabel("Annulation de réservation");
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_title.setBounds(0, 33, 581, 39);
		designTitle(lbl_title);
		contentPane.add(lbl_title);
		
		JButton btn_back = new JButton("Retour");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookingsFrame bookingsFrame = new BookingsFrame(player);
				bookingsFrame.setVisible(true);
				dispose();
			}
		});
		btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_back.setBounds(59, 330, 108, 33);
		designButton(btn_back);
		contentPane.add(btn_back);
		
		JButton btn_confirm = new JButton("Confirmer");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_message.setVisible(false);
				if(booking.delete()) {
					BookingsFrame bookingsFrame = new BookingsFrame(player);
					bookingsFrame.setVisible(true);
					dispose();
				}
				else {
					lbl_message.setText("Il semblerait que l'annulation s'est mal déroulée.");
					lbl_message.setVisible(true);
				}
				
			}
		});
		btn_confirm.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_confirm.setBounds(414, 330, 108, 33);
		designButton(btn_confirm);
		contentPane.add(btn_confirm);
		
		JLabel lbl_video_game = new JLabel("Jeu :" + booking.getVideoGame().getName());
		lbl_video_game.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_video_game.setBounds(59, 118, 170, 20);
		contentPane.add(lbl_video_game);
		
		JLabel lbl_console = new JLabel("Console : " + booking.getVideoGame().getConsole());
		lbl_console.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_console.setBounds(59, 167, 170, 20);
		contentPane.add(lbl_console);
		
		JLabel lbl_credit = new JLabel("Crédit : " + booking.getVideoGame().getCreditCost());
		lbl_credit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_credit.setBounds(59, 221, 170, 20);
		contentPane.add(lbl_credit);
		
		JLabel lbl_date = new JLabel("Date de réservation : " + booking.getBookingDate());
		lbl_date.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_date.setBounds(59, 273, 170, 20);
		contentPane.add(lbl_date);
		
		lbl_message = new JLabel("");
		lbl_message.setBackground(new Color(225, 60, 84));
		lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lbl_message.setBounds(59, 373, 463, 27);
		lbl_message.setVisible(false);
		contentPane.add(lbl_message);
	}
}
