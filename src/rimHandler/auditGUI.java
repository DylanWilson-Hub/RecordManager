package rimHandler;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class auditGUI {

	static JFrame frame;
	private JButton returnLogin;
	private JButton auditSearch;
	private JTextField searchkey;
	private JButton auditSearchAll;
	static String user, pass, level, value;
	static int x,y;
	/**
	 * Launch the application.
	 */
	public static void auditMain(String user1, String pass1, String level1) {
		FlatDarkLaf.setup();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					auditGUI window = new auditGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		});
	user = user1;
	pass = pass1;
	level = level1;
	}
	

	/**
	 * Create the application.
	 */
	public auditGUI() {
		initialize(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		
		
		if(RimAudit.value != null ) {
			frame.setBounds(RimAudit.x, RimAudit.y, 357, 208);

			RimAudit.value = null;
		}
		else {
		frame.setBounds(optionPage.x, optionPage.y, 357, 208);
		}
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		returnLogin = new JButton("Return");
		returnLogin.setBounds(232, 117, 93, 22);
		frame.getContentPane().add(returnLogin);
		
		auditSearch = new JButton("Search");
		auditSearch.setBounds(38, 59, 93, 22);
		frame.getContentPane().add(auditSearch);
		
		JComboBox audSel = new JComboBox();
		audSel.setModel(new DefaultComboBoxModel(new String[] {"  (Select one)", "Barcode", "AltBarcode", "EmployeeName", "CurrentLocation", "AssignedLocation", "OldLocation"}));
		audSel.setMaximumRowCount(7);
		audSel.setBounds(28, 25, 107, 22);
		frame.getContentPane().add(audSel);
		
		searchkey = new JTextField();
		searchkey.setColumns(10);
		searchkey.setBounds(151, 25, 107, 22);
		frame.getContentPane().add(searchkey);
		
		auditSearchAll = new JButton("Search ALL");
		auditSearchAll.setBounds(151, 59, 93, 22);
		frame.getContentPane().add(auditSearchAll);
		frame.addComponentListener(new ComponentListener()
        {
            @Override
            public void componentMoved(ComponentEvent e)
            {
                 x = frame.getX();
                 y=  frame.getY();
                
                
            }

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
        });		

		
		auditSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String searchKeySel = audSel.getSelectedItem().toString();
				String searchKey = searchkey.getText();
				
				
				
			 try {
					rimHandler.RimAudit.auditSearch(searchKeySel, searchKey,user,pass,level);  

				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				 
		
				}
	
		}); 
		
	
		auditSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String searchKeySel = "ALL RECORDS";
				String searchKey = searchkey.getText();
				
				
				
			 try {
					rimHandler.RimAudit.auditSearch(searchKeySel, searchKey,user,pass,level); 
					frame.dispose();

				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				 
		
				}
	
		}); 
		
		
		
		returnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				frame.dispose();
				
				value = "true";
				optionPage.optionMain(user, pass, level,optionPage.first,optionPage.last);
				
				 
			}
			});

	}


}
