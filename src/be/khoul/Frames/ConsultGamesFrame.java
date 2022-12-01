package be.khoul.Frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import be.khoul.Pojo.Player;
import be.khoul.Pojo.VideoGame;

import javax.swing.*;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultGamesFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<VideoGame> listGames;
	private JButton btn_back;
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
	public ConsultGamesFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_title = new JLabel("Jeux Vidéos");
		lbl_title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBounds(0, 10, 562, 35);
		designTitle(lbl_title);
		contentPane.add(lbl_title);
		
		
		//Get video games
		listGames = VideoGame.getAllVideoGames();
		
	    String[] nomCol = {"Nom", "Crédits", "Console"};
		
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setSize(527, 252);
	    scrollPane.setLocation(25, 91);
		contentPane.add(scrollPane);
	    
	    table = new JTable();
	    scrollPane.setViewportView(table);
	    table.setDefaultEditor(Object.class, null);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    btn_back = new JButton("Retour");
	    btn_back.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		PlayerHomeFrame playerHomeFrame = new PlayerHomeFrame(player);
	    		playerHomeFrame.setVisible(true);
	    		dispose();
	    	}
	    });
	    btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
	    btn_back.setBounds(25, 380, 87, 33);
	    designButton(btn_back);
	    contentPane.add(btn_back);
	    
	    JLabel lbl_title2 = new JLabel("Veuillez sélectionner le jeu vidéo qui vous intéresse.");
	    lbl_title2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
	    lbl_title2.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_title2.setBounds(25, 68, 527, 13);
	    contentPane.add(lbl_title2);
        
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(nomCol);
		for(VideoGame v: listGames) {
			Object[] data = {v.getName(), v.getCreditCost(), v.getConsole()};
			model.addRow(data);
		}
		
		ListSelectionModel selectModel= table.getSelectionModel();
        selectModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;
                ListSelectionModel selectModel = (ListSelectionModel)e.getSource();
                //Get selected row position
                int selectedRow = selectModel.getMinSelectionIndex();
                VideoGame v = listGames.get(selectedRow);
                ConsultGameFrame consultGameFrame = new ConsultGameFrame(player, v);
                consultGameFrame.setVisible(true);
                dispose();
                
            }
        });
        
       
			
	}
}
