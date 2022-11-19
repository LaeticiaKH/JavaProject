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
import javax.swing.table.DefaultTableModel;

import be.khoul.Pojo.Booking;
import be.khoul.Pojo.Player;
import be.khoul.Pojo.VideoGame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookingsFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<Booking> listBookings;
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
	public BookingsFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_bookings = new JLabel("Mes réservations");
	    lbl_bookings.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_bookings.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lbl_bookings.setBounds(223, 20, 161, 38);
	    contentPane.add(lbl_bookings);
	    
	    JButton btn_back = new JButton("Retour");
	    btn_back.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		PlayerHomeFrame playerHomeFrame = new PlayerHomeFrame(player);
	    		playerHomeFrame.setVisible(true);
	    		dispose();
	    	}
	    });
	    btn_back.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    btn_back.setBounds(31, 409, 98, 31);
	    contentPane.add(btn_back);
	    
	    JLabel lbl_no_bookings = new JLabel("Vous n'avez fait aucune réservations pour l'instant.");
	    lbl_no_bookings.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_no_bookings.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
	    btn_cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    btn_cancel.setBounds(426, 409, 141, 31);
	    contentPane.add(btn_cancel);
	    
	    lbl_message = new JLabel("");
	    lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
	    lbl_message.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lbl_message.setBounds(170, 419, 246, 13);
	    contentPane.add(lbl_message);
		
		//listBookings = Booking.getBookings(player);
	    player.getOwnBookings();
	    listBookings = player.getBookings();
		if(listBookings.size() > 0) {
			System.out.println(listBookings.size());
		    String[] nomCol = {"Date de la réservation", "Nom du jeu", "Console"};
			
		    JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setSize(527, 323);
		    scrollPane.setLocation(10, 78);
			contentPane.add(scrollPane);
		    
		    table = new JTable();
		    scrollPane.setViewportView(table);
		    table.setDefaultEditor(Object.class, null);
		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    
		    
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setColumnIdentifiers(nomCol);
			for(Booking b: listBookings) {
				Object[] data ={b.getBookingDate(), b.getVideoGame().getName(), b.getVideoGame().getConsole()};
				model.addRow(data);
			}
		}
		else {
			lbl_no_bookings.setVisible(true);
		}
		
		
	}
}
