package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.khoul.Pojo.Player;
import be.khoul.Pojo.VideoGame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
					//ConsultGameFrame frame = new ConsultGameFrame();
					//frame.setVisible(true);
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
		
		JLabel lbl_available = new JLabel("Exemplaire disponible : ");
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
		btn_back.setBounds(28, 324, 87, 29);
		contentPane.add(btn_back);
	}
}
