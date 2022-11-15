package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.khoul.Pojo.*;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeCreditFrame extends JFrame {

	private JPanel contentPane;
	private Choice choice;
	private JLabel lbl_message;
	

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
	public ChangeCreditFrame(Administrator admin, VideoGame videoGame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		videoGame.getVideoGameCopies();
		
		JButton btn_back = new JButton("Retour");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultGamesAdminFrame consultGamesAdminFrame = new ConsultGamesAdminFrame(admin);
				consultGamesAdminFrame.setVisible(true);
				dispose();
			}
		});
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_back.setBounds(51, 366, 107, 29);
		contentPane.add(btn_back);
		
		JButton btn_confirm= new JButton("Confirmer");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choiceValue = Integer.parseInt(choice.getSelectedItem());
				if(choiceValue != videoGame.getCreditCost()) {
					admin.changeCredit(videoGame, choiceValue);
				}
				else {
					lbl_message.setForeground(Color.RED);
					lbl_message.setText("Le prix du jeu a deja cette valeur.");
				}
			
			}
		});
		btn_confirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_confirm.setBounds(472, 366, 115, 29);
		contentPane.add(btn_confirm);
		
		JLabel lbl_title = new JLabel(videoGame.getName());
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_title.setBounds(82, 43, 483, 29);
		contentPane.add(lbl_title);
		
		JLabel lbl_console = new JLabel("Console : " + videoGame.getConsole());
		lbl_console.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_console.setBounds(64, 116, 384, 20);
		contentPane.add(lbl_console);
		
		JLabel lbl_current_credit = new JLabel("Crédit actuel : " + videoGame.getCreditCost());
		lbl_current_credit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_current_credit.setBounds(64, 172, 251, 20);
		contentPane.add(lbl_current_credit);
		
		JLabel lbl_new_credit = new JLabel("Nouveau crédit : ");
		lbl_new_credit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_new_credit.setBounds(64, 226, 107, 20);
		contentPane.add(lbl_new_credit);
		
		choice = new Choice();
		choice.setBounds(197, 228, 52, 18);
		contentPane.add(choice);
		
		 for(int i=1; i <=10; i++) {
				choice.add("" + i);
		 }
		
		lbl_message = new JLabel("");
		lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_message.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_message.setBounds(108, 416, 433, 29);
		contentPane.add(lbl_message);
		
		
	}
}
