package be.khoul.Frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import be.khoul.Pojo.Booking;
import be.khoul.Pojo.Player;
import be.khoul.Pojo.VideoGame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class BookingsFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Booking> listBookings;
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
	public BookingsFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_title = new JLabel("Mes réservations");
	    lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
	    lbl_title.setBounds(0, 20, 617, 38);
	    designTitle(lbl_title);
	    contentPane.add(lbl_title);
	    
	    JButton btn_back = new JButton("Retour");
	    btn_back.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		PlayerHomeFrame playerHomeFrame = new PlayerHomeFrame(player);
	    		playerHomeFrame.setVisible(true);
	    		dispose();
	    	}
	    });
	    btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
	    btn_back.setBounds(31, 409, 98, 31);
	    designButton(btn_back);
	    contentPane.add(btn_back);
	    
	    JLabel lbl_no_bookings = new JLabel("Vous n'avez fait aucune réservations pour l'instant.");
	    lbl_no_bookings.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_no_bookings.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
	    lbl_no_bookings.setBounds(98, 122, 428, 21);
	    lbl_no_bookings.setVisible(false);
	    contentPane.add(lbl_no_bookings);
	    
	    JButton btn_cancel = new JButton("Annuler réservation");
	    btn_cancel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ListSelectionModel selectModel= table.getSelectionModel();
				if(selectModel.getMinSelectionIndex() >= 0) {
					int selectedRow = selectModel.getMinSelectionIndex();
					Booking booking = listBookings.get(selectedRow);
					CancelBookingFrame cancelBookingFrame = new CancelBookingFrame(player,booking);
					cancelBookingFrame.setVisible(true);
					dispose();
				}
				else{
					lbl_message.setForeground(Color.RED);
					lbl_message.setText("Veuillez sélectionner une réservation");
				}
	    		
	    	}
	    });
	    btn_cancel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
	    btn_cancel.setBounds(426, 409, 161, 31);
	    designButton(btn_cancel);
	    contentPane.add(btn_cancel);
	    
	    lbl_message = new JLabel("");
	    lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
	    lbl_message.setBounds(170, 419, 246, 13);
	    contentPane.add(lbl_message);
		
	   
	    listBookings = player.getOwnBookings();
		if(listBookings.size() > 0) {
	
		    String[] nomCol = {"Date", "Nom du jeu", "Console", "Durée"};
			
		    JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setSize(556, 323);
		    scrollPane.setLocation(31, 68);
			contentPane.add(scrollPane);
		    
		    table = new JTable();
		    scrollPane.setViewportView(table);
		    table.setDefaultEditor(Object.class, null);
		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    
		    
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setColumnIdentifiers(nomCol);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			for(Booking b: listBookings) {
				Object[] data ={b.getBookingDate().format(formatter), b.getVideoGame().getName(), b.getVideoGame().getConsole(), b.getDuration()};
				model.addRow(data);
			}
		}
		else {
			lbl_no_bookings.setVisible(true);
			btn_cancel.setEnabled(false);
		}
		
		
	}
}
