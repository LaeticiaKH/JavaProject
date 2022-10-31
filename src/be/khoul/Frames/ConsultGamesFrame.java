package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import be.khoul.Pojo.Player;
import be.khoul.Pojo.VideoGame;

import javax.swing.*;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;

public class ConsultGamesFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<VideoGame> listGames;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ConsultGamesFrame frame = new ConsultGamesFrame();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void tableMouseClick(java.awt.event.MouseEvent evt) {                                     
	     JTable source = (JTable)evt.getSource();
	            int row = source.rowAtPoint( evt.getPoint() );
	            int column = source.columnAtPoint( evt.getPoint() );
	            String s=source.getModel().getValueAt(row, column)+"";

	            System.out.println(s);


	} 	
	/**
	 * Create the frame.
	 */
	public ConsultGamesFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_consultGames = new JLabel("Jeux Vidéos");
		lbl_consultGames.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_consultGames.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_consultGames.setBounds(193, 10, 150, 35);
		contentPane.add(lbl_consultGames);
		
		listGames = VideoGame.getAllVideoGames();
		System.out.println(listGames.size());
	    String[] nomCol = {"Nom", "Crédits", "Console"};
		
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setSize(527, 323);
	    scrollPane.setLocation(10, 44);
		contentPane.add(scrollPane);
	    
	    table = new JTable();
	    scrollPane.setViewportView(table);
	    table.setDefaultEditor(Object.class, null);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
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
