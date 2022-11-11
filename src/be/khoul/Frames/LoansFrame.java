package be.khoul.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.khoul.Pojo.*;

import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoansFrame extends JFrame {

	private JPanel contentPane;
	private ArrayList<Loan> listLoans;
	private JTable table;

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
	public LoansFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_loans = new JLabel("Mes emprunts");
		lbl_loans.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_loans.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_loans.setBounds(247, 10, 135, 44);
		contentPane.add(lbl_loans);
		
		JLabel lbl_no_loan= new JLabel("Vous n'avez pas encore loué de jeu");
		lbl_no_loan.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_no_loan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_no_loan.setBounds(173, 105, 291, 36);
		lbl_no_loan.setVisible(false);
		contentPane.add(lbl_no_loan);
		
		JButton btn_back = new JButton("Retour");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerHomeFrame playerHomeFrame = new PlayerHomeFrame(player);
				playerHomeFrame.setVisible(true);
				dispose();
			}
		});
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_back.setBounds(34, 345, 92, 23);
		contentPane.add(btn_back);
		
		player.getOwnLoans();
		listLoans = player.getLoans();
		if(listLoans.size() > 0) {
			String[] nomCol = {"Jeu","Console","Date de début", "Date de fin", "Prêteur", "En cours"};
			
			JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setSize(620, 280);
		    scrollPane.setLocation(10, 78);
			contentPane.add(scrollPane);
		    
		    table = new JTable();
		    scrollPane.setViewportView(table);
		    table.setDefaultEditor(Object.class, null);
		    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setColumnIdentifiers(nomCol);
			for(Loan loan: listLoans) {
				loan.calculateBalance();
				VideoGame videoGame = loan.getCopy().getVideoGame();
				Player lender = loan.getCopy().getOwner();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				Object[] data ={videoGame.getName(), videoGame.getConsole(), loan.getStartDate().format(formatter), loan.getEndDate().format(formatter), lender.getPseudo(), loan.isOngoing()};
				model.addRow(data);
			}
		} 
		else {
			lbl_no_loan.setVisible(true);
		}
		
	}
}
