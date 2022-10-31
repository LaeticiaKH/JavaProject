package be.khoul.Frames;

import java.awt.EventQueue;

import be.khoul.Pojo.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class PlayerHomeFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerHomeFrame frame = new PlayerHomeFrame();
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
	public PlayerHomeFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
	}
	
	public PlayerHomeFrame() {}
	
		
}
