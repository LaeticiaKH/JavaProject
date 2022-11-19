package be.khoul.Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import be.khoul.Pojo.*;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultGamesAdminFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<VideoGame> listGames;
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
	public ConsultGamesAdminFrame(Administrator admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listGames = VideoGame.getAllVideoGames();
		for(VideoGame v :listGames) {
			v.getVideoGameCopies();
		}
		
		
	    String[] nomCol = {"Nom", "Crédits", "Console"};
		
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setSize(550, 252);
	    scrollPane.setLocation(25, 48);
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
		btn_retour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_retour.setBounds(37, 332, 93, 29);
		contentPane.add(btn_retour);
		
		JButton btn_changeCredit = new JButton("Changer crédit");
		btn_changeCredit.addActionListener(new ActionListener() {
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
		btn_changeCredit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_changeCredit.setBounds(426, 332, 113, 29);
		contentPane.add(btn_changeCredit);
		
		JButton btn_add_game = new JButton("Ajouter un jeu");
		btn_add_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGameAdminFrame addGameAdminFrame = new AddGameAdminFrame(admin);
				addGameAdminFrame.setVisible(true);			
				dispose();
			}
		});
		btn_add_game.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_add_game.setBounds(220, 332, 113, 29);
		contentPane.add(btn_add_game);
		
		lbl_message = new JLabel("");
		lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_message.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_message.setBounds(37, 388, 502, 24);
		contentPane.add(lbl_message);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(nomCol);
		for(VideoGame v: listGames) {
			Object[] data = {v.getName(), v.getCreditCost(), v.getConsole()};
			model.addRow(data);
		}
		
		
	}

}
