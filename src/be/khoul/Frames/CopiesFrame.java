package be.khoul.Frames;

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

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CopiesFrame extends JFrame {

	private JPanel contentPane;
	private ArrayList<Copy> listCopies;
	private JTable table;
	private DefaultTableModel model;
	private JLabel lbl_no_copy;
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
	
	public void showCopies(Player player) {
		listCopies = player.getOwnCopies();
		
		if(listCopies.size() > 0) {
			String[] nomCol = { "Jeu", "Console", "Emprunteur", "Crédits", "Date de début", "Date de fin", "En cours"};
			
			 JScrollPane scrollPane = new JScrollPane();
			    scrollPane.setSize(625, 280);
			    scrollPane.setLocation(10, 78);
				contentPane.add(scrollPane);
			    
			    table = new JTable();
			    scrollPane.setViewportView(table);
			    table.setDefaultEditor(Object.class, null);
			    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    
			    model = (DefaultTableModel) table.getModel();
			
				model.setColumnIdentifiers(nomCol);
				for(Copy c: listCopies) {
					VideoGame videoGame = c.getVideoGame();
					Loan loan = c.getLoan();
					
					if(loan != null) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						Object[] data ={ videoGame.getName(), videoGame.getConsole(), loan.getBorrower().getPseudo(), videoGame.getCreditCost(), loan.getStartDate().format(formatter), loan.getEndDate().format(formatter), loan.isOngoing()};
						model.addRow(data);
					}
					else {
						Object[] data ={videoGame.getName(), videoGame.getConsole(), "", videoGame.getCreditCost(), "", "", "",  ""};
						model.addRow(data);
					}		
					
				}
			    
		} else {
			lbl_no_copy.setVisible(true);
		}
		
	}
	/**
	 * Create the frame.
	 */
	public CopiesFrame(Player player) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lbl_title = new JLabel("Mes copies");
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_title.setBounds(0, 23, 649, 36);
		designTitle(lbl_title);
		contentPane.add(lbl_title);
		
		lbl_no_copy = new JLabel("Vous n'avez pas encore prêté une copie");
		lbl_no_copy.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_no_copy.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_no_copy.setBounds(175, 130, 291, 36);
		lbl_no_copy.setVisible(false);
		contentPane.add(lbl_no_copy);
		
		lbl_message = new JLabel("Veuillez sélectionner une copie.");
		lbl_message.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_message.setForeground(new Color(239, 37, 68));
		lbl_message.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_message.setBounds(39, 431, 554, 22);
		lbl_message.setVisible(false);
		contentPane.add(lbl_message);
		
		showCopies(player);

		
		JButton btn_end_loan = new JButton("Finir emprunt");
		btn_end_loan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_message.setVisible(false);
				//Get selected copy
				Copy copy;
				ListSelectionModel selectModel= table.getSelectionModel();
				if(selectModel.getMinSelectionIndex() >= 0) {
					int selectedRow = selectModel.getMinSelectionIndex();
	                copy = listCopies.get(selectedRow);
	                if(copy.getLoan() == null) {
	                	lbl_message.setVisible(true);
	                	lbl_message.setText("Cette copie n'est pas emprunté");
	                }
	                else {
	                	//If today is before the end of the location
	                	if(LocalDate.now().isBefore(copy.getLoan().getEndDate())) {
	                		lbl_message.setVisible(true);
		                	lbl_message.setText("La location n'est pas encore finie");
	                	}
	                	else {
	                		EndLoanFrame endLoanFrame = new EndLoanFrame(player, copy);
			                endLoanFrame.setVisible(true);
			                dispose();
	                	}
	                	
	                }
	                
				}
				else {
					lbl_message.setVisible(true);
					lbl_message.setText("Veuillez sélectionner une copie.");
				}
				
			}
		});
		btn_end_loan.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_end_loan.setBounds(479, 381, 114, 28);
		designButton(btn_end_loan);
		contentPane.add(btn_end_loan);
		
		JButton btn_back = new JButton("Retour");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerHomeFrame playerHomeFrame = new PlayerHomeFrame(player);
				playerHomeFrame.setVisible(true);
				dispose();
			}
		});
		btn_back.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_back.setBounds(39, 381, 114, 28);
		designButton(btn_back);
		contentPane.add(btn_back);
		
		JButton btn_remove_copy = new JButton("Retirer copie");
		btn_remove_copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_message.setVisible(false);
				//Get selected copy
				Copy copy;
				ListSelectionModel selectModel= table.getSelectionModel();
				if(selectModel.getMinSelectionIndex() >= 0) {
					int selectedRow = selectModel.getMinSelectionIndex();
	                copy = listCopies.get(selectedRow);
	              //If the copy doesn't have a ongoing loan it can be removed
	                if(copy.isAvailable()) {
	                	if(copy.delete()) {
	                		listCopies.remove(copy);
	                		model.removeRow(selectedRow);
	                		lbl_message.setVisible(true);
		                	lbl_message.setText("Copie retiré");
		                	
	                	}
	                	else {
	                		lbl_message.setVisible(true);
		                	lbl_message.setText("Il semblerait que le retrait de la copie ne se soit pas bien déroulé.");
	                	}
	                
	                }
	                else {
	                	lbl_message.setVisible(true);
	                	lbl_message.setText("La copie est emprunté. Vous ne pouvez pas la reprendre.");
	                }
				
			    }
				else {
					lbl_message.setVisible(true);
					lbl_message.setText("Veuillez sélectionner une copie.");
				}
		   }
		});
		btn_remove_copy.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_remove_copy.setBounds(257, 381, 114, 28);
		designButton(btn_remove_copy);
		contentPane.add(btn_remove_copy);
		
		if(listCopies.size() == 0) {
			btn_end_loan.setEnabled(false);
			btn_remove_copy.setEnabled(false);
			lbl_no_copy.setVisible(true);
		}
		
		
	}
}
