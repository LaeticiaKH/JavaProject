package be.khoul.Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import be.khoul.Pojo.*;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultGamesAdminFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<VideoGame> listGames;
	private DefaultTableModel model;
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
	public ConsultGamesAdminFrame(Administrator admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_title = new JLabel("Jeux Vidéo");
		lbl_title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBounds(0, 10, 634, 35);
		designTitle(lbl_title);
		contentPane.add(lbl_title);
		
		
		listGames = VideoGame.getAllVideoGames();
		for(VideoGame v :listGames) {
			v.getVideoGameCopies();
		}
		
		
	    String[] nomCol = {"Nom", "Crédits", "Console"};
		
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setSize(593, 245);
	    scrollPane.setLocation(25, 55);
		contentPane.add(scrollPane);
	    
	    table = new JTable();
	    scrollPane.setViewportView(table);
	    table.setDefaultEditor(Object.class, null);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
		
		JButton btn_retour = new JButton("Retour");
		btn_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHomeFrame adminHomeFrame = new AdminHomeFrame(admin);
				adminHomeFrame.setVisible(true);
				dispose();
			}
		});
		btn_retour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_retour.setBounds(34, 397, 112, 29);
		designButton(btn_retour);
		contentPane.add(btn_retour);
		
		JButton btn_change_credit = new JButton("Changer crédit");
		btn_change_credit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_message.setVisible(false);
				ListSelectionModel selectModel= table.getSelectionModel();
				if(selectModel.getMinSelectionIndex() >= 0) {
					int selectedRow = selectModel.getMinSelectionIndex();
	                VideoGame videoGame = listGames.get(selectedRow);
	                ChangeCreditFrame changeCreditFrame = new ChangeCreditFrame(admin, videoGame);
	                changeCreditFrame.setVisible(true);
	                dispose();
	                
				}else {
					lbl_message.setVisible(true);
					lbl_message.setForeground(Color.RED);
					lbl_message.setText("Veuillez sélectionner un jeu.");
				}
			}
		});
		btn_change_credit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_change_credit.setBounds(426, 332, 134, 29);
		designButton(btn_change_credit);
		contentPane.add(btn_change_credit);
		
		JButton btn_add_game = new JButton("Ajouter un jeu");
		btn_add_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGameAdminFrame addGameAdminFrame = new AddGameAdminFrame(admin);
				addGameAdminFrame.setVisible(true);			
				dispose();
			}
		});
		btn_add_game.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_add_game.setBounds(248, 332, 128, 29);
		designButton(btn_add_game);
		contentPane.add(btn_add_game);
		
		lbl_message = new JLabel("");
		lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_message.setBounds(34, 371, 526, 24);
		contentPane.add(lbl_message);
		
		JButton btn_delete_game = new JButton("Supprimer un jeu");
		btn_delete_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_message.setVisible(false);
				ListSelectionModel selectModel= table.getSelectionModel();
				if(selectModel.getMinSelectionIndex() >= 0) {
					int selectedRow = selectModel.getMinSelectionIndex();
	                VideoGame videoGame = listGames.get(selectedRow);
	                if(videoGame.getVideoGameCopies().size() == 0 && videoGame.getBookings().size() == 0) {
	                	int dialogResult = JOptionPane.showConfirmDialog (null ,"Etes-vous sur de vouloir supprimer ce jeu vidéo ?"," Attention" , JOptionPane.YES_NO_OPTION);
	                	if(dialogResult == JOptionPane.YES_OPTION){
	                		if(videoGame.delete()) {
	                			 model.removeRow(selectedRow);
	 	                	    lbl_message.setVisible(true);
	 						    lbl_message.setForeground(Color.GREEN);
	 							lbl_message.setText("Jeu supprimé.");
	                		}
	                		else {
	                			lbl_message.setVisible(true);
	    						lbl_message.setForeground(Color.RED);
	    						lbl_message.setText("Il semblerait que la suppression du jeu se soit mal déroulée");
	                		}
	                	   
	                	}
	                }
	                else {
	                	lbl_message.setVisible(true);
						lbl_message.setForeground(Color.RED);
						lbl_message.setText("Ce jeu ne peux pas être supprimé car il possède encore des copies ou des résevations.");
	                }
	                
				}else {
					lbl_message.setVisible(true);
					lbl_message.setForeground(Color.RED);
					lbl_message.setText("Veuillez sélectionner un jeu à supprimer.");
				}
			}
			
		});
		btn_delete_game.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_delete_game.setBounds(30, 332, 158, 29);
		designButton(btn_delete_game);
		contentPane.add(btn_delete_game);
		
	    model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(nomCol);
		for(VideoGame v: listGames) {
			Object[] data = {v.getName(), v.getCreditCost(), v.getConsole()};
			model.addRow(data);
		}
		
		if(listGames.size() == 0) {
			btn_delete_game.setEnabled(false);
			btn_change_credit.setEnabled(false);
		}
		
	}

}
