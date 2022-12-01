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
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class EndLoanFrame extends JFrame {

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
	public EndLoanFrame(Player player, Copy copy) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_title = new JLabel("Cloture de l'emprunt");
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_title.setBounds(0, 26, 564, 35);
		designTitle(lbl_title);
		contentPane.add(lbl_title);
		
		JButton btn_confirm = new JButton("Confirmer");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_message.setVisible(false);
				if(copy.releaseCopy()) {
					//Go check if a booking can transform into a loan
					copy.getVideoGame().getBookingIntoLoan();
					CopiesFrame copiesFrame = new CopiesFrame(copy.getOwner());
					copiesFrame.setVisible(true);
					dispose();
				}
				else {
					lbl_message.setVisible(true);
					lbl_message.setForeground(Color.red);
					lbl_message.setText("Il semblerait que la fin de l'emprunt ne s'est pas bien déroulé.");
				}
			}
		});
		btn_confirm.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_confirm.setBounds(411, 290, 109, 30);
		designButton(btn_confirm);
		contentPane.add(btn_confirm);
		
		JButton btn_back = new JButton("Retour");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CopiesFrame copiesFrame = new CopiesFrame(player);
				copiesFrame.setVisible(true);
				dispose();
			}
		});
		btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_back.setBounds(40, 290, 109, 30);
		designButton(btn_back);
		contentPane.add(btn_back);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String start_date = copy.getLoan().getStartDate().format(formatter);
		String end_date = copy.getLoan().getEndDate().format(formatter);
		
		JLabel lbl_date_loan = new JLabel("Date de l'emprunt : " + start_date + " au " + end_date);
		lbl_date_loan.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_date_loan.setBounds(42, 80, 393, 22);
		contentPane.add(lbl_date_loan);
		
		JLabel lbl_game = new JLabel("Jeu : " + copy.getVideoGame().getName());
		lbl_game.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_game.setBounds(40, 121, 268, 22);
		contentPane.add(lbl_game);
		
		JLabel lbl_credit = new JLabel("Crédit : " + copy.getVideoGame().getCreditCost() + "/semaine");
		lbl_credit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_credit.setBounds(40, 161, 239, 22);
		contentPane.add(lbl_credit);
		
		JLabel lblNewLabel = new JLabel("Total : " + copy.getLoan().calculateBalance() + " crédits");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel.setBounds(328, 223, 178, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lbl_penality = new JLabel("Pénalité : " + copy.getLoan().calculatePenality());
		lbl_penality.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_penality.setBounds(40, 205, 239, 22);
		contentPane.add(lbl_penality);
		
	    lbl_message = new JLabel("");
		lbl_message.setBackground(new Color(225, 60, 84));
		lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_message.setBounds(40, 330, 480, 25);
		lbl_message.setVisible(false);
		contentPane.add(lbl_message);
		
		
	}
}
