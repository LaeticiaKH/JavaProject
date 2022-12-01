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
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import java.awt.Choice;

public class BookingFrame extends JFrame {

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
	public BookingFrame(Player player, VideoGame videoGame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_title = new JLabel("Réservation");
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBounds(0, 27, 586, 34);
		lbl_title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		designTitle(lbl_title);
		contentPane.add(lbl_title);
		
		JLabel lbl_game = new JLabel("Jeu : " + videoGame.getName());
		lbl_game.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_game.setBounds(45, 112, 487, 17);
		contentPane.add(lbl_game);
		
		JLabel lbl_duration = new JLabel("Durée en semaines: ");
		lbl_duration.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_duration.setBounds(45, 161, 157, 17);
		contentPane.add(lbl_duration);
		
		Choice choice = new Choice();
		choice.setBounds(208, 160, 46, 18);
		contentPane.add(choice);
	    
		for(int i=1; i <=12; i++) {
			choice.add("" + i);
		}
		
		JTextArea ta_warning = new JTextArea("Attention : Si vous ne rendez pas le jeu à la date convenue, une pénalité de 5 crédits \n par jour vous sera demandé.");
		ta_warning.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		ta_warning.setBounds(27, 223, 519, 50);
		ta_warning.setEditable(false);
		contentPane.add(ta_warning);
		
		JLabel lbl_message = new JLabel("");
		lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_message.setBounds(133, 344, 311, 29);
		contentPane.add(lbl_message);
		
		JButton btn_confirm = new JButton("Confirmer");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Booking booking = new Booking(LocalDate.now(), Integer.parseInt(choice.getSelectedItem()) , player, videoGame);
				if(booking.createBooking()) {
					//Go back to game frame
					ConsultGameFrame consultGameFrame = new ConsultGameFrame(player, videoGame);
    				consultGameFrame.setVisible(true);
    				dispose();
				}
				else {
					lbl_message.setText("Un problème est survenu lors de la réservation");
					lbl_message.setForeground(Color.RED);
				}
				
				
			}
		});
		btn_confirm.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_confirm.setBounds(404, 302, 115, 34);
		designButton(btn_confirm);
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
		btn_back.setBounds(27, 302, 86, 34);
		designButton(btn_back);
		contentPane.add(btn_back);
		
		
		
		
	}
}
