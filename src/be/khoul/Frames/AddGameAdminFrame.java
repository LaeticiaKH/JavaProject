package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import be.khoul.Pojo.Administrator;
import be.khoul.Pojo.VideoGame;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AddGameAdminFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_name_game;
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
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
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
	public AddGameAdminFrame(Administrator admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_title = new JLabel("Ajout d'un jeu");
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lbl_title.setBounds(0, 36, 656, 40);
		designTitle(lbl_title);
		contentPane.add(lbl_title);
		
		JLabel lbl_name_game = new JLabel("Nom du jeu :");
		lbl_name_game.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_name_game.setBounds(76, 136, 85, 22);
		contentPane.add(lbl_name_game);
		
		tf_name_game = new JTextField();
		tf_name_game.setBounds(201, 139, 199, 19);
		contentPane.add(tf_name_game);
		tf_name_game.setColumns(10);
		
		JLabel lbl_console = new JLabel("Console : ");
		lbl_console.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_console.setBounds(76, 204, 85, 22);
		contentPane.add(lbl_console);
		
		Choice choice_console = new Choice();
		choice_console.setBounds(201, 208, 123, 18);
		contentPane.add(choice_console);
		
		String[] console = {"Nintendo Switch", "PS5" , "PS4", "Xbox Series X", "Wii U", "3DS"};
		
		for(String s: console) {
			choice_console.add(s);
		}
		
		JLabel lbl_credit = new JLabel("Credit");
		lbl_credit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_credit.setBounds(76, 276, 85, 22);
		contentPane.add(lbl_credit);
		
		Choice choice_credit = new Choice();
		choice_credit.setBounds(201, 280, 50, 18);
		contentPane.add(choice_credit);
		
		 for(int i=1; i <=10; i++) {
				choice_credit.add("" + i);
		 }
		

		lbl_message = new JLabel("");
		lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_message.setBounds(146, 405, 381, 19);
		contentPane.add(lbl_message);
		
		JButton btn_confirm = new JButton("Confirmer");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tf_name_game.getText();
				
				if(!name.isEmpty()) {
					String console = choice_console.getSelectedItem();
					int credit = Integer.parseInt(choice_credit.getSelectedItem());
					VideoGame videoGame = new VideoGame(tf_name_game.getText(), credit, console);
					
					//check if the game doesn't exist already
					ArrayList<VideoGame> games = VideoGame.getAllVideoGames();
					boolean exist = false;
					for(VideoGame v: games) {  
						if(v.getName().toLowerCase().equals(videoGame.getName().toLowerCase()) && v.getConsole().toLowerCase().equals(videoGame.getConsole().toLowerCase())) {
							exist = true;
						}
					}
					
					if(!exist) {
						//Create video Game in DB
						if(videoGame.create()) {
							//Back to the previous frame
							ConsultGamesAdminFrame consultGamesAdminFrame = new ConsultGamesAdminFrame(admin);
							consultGamesAdminFrame.setVisible(true);
							dispose();
						}
						else {
							lbl_message.setForeground(Color.RED);
							lbl_message.setText("Il semblerait que l'ajout du jeu se soit mal déroulé.");	
						}
						
					}
					else {
						lbl_message.setForeground(Color.RED);
						lbl_message.setText("Ce jeu est déjà enregistré.");	
					}
					
					
				}
				else {
					lbl_message.setForeground(Color.RED);
					lbl_message.setText("Veuillez saisir le nom du jeu.");		
				}
			}
		});
		btn_confirm.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_confirm.setBounds(457, 357, 107, 31);
		designButton(btn_confirm);
		contentPane.add(btn_confirm);
		
		JButton btn_back = new JButton("Retour");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultGamesAdminFrame consultGamesAdminFrame = new ConsultGamesAdminFrame(admin);
				consultGamesAdminFrame.setVisible(true);
				dispose();
			}
		});
		btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_back.setBounds(54, 356, 107, 32);
		designButton(btn_back);
		contentPane.add(btn_back);
		
		
		
	}
}
