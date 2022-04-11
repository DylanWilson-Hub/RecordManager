package rimHandler;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;

public class optionPage {

	static String value;
	static JFrame frame;
	static String user;
	static String pass;
	static String level,first,last;
	
	static int x, y;
	/**
	 * Launch the application.
	 * @param level 
	 * @param pass2 
	 */
	public static void optionMain(String user1, String pass1, String level1, String first1, String last1) {
		FlatDarkLaf.setup();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					optionPage window = new optionPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		});
		user = user1;
		pass = pass1;
		level = level1;
		first = first1;
		last = last1;
	}

	/**
	 * Create the application.
	 */
	public optionPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
	//	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dwilson1\\OneDrive - Diamondback Energy\\Pictures\\DLogo.PNG"));
		
		if (RIMGui.value !=null) {
			frame.setBounds(RIMGui.x, RIMGui.y, 250,322); 
			RIMGui.value = null;
			}
		else if (searchGUI.value !=null) {
			frame.setBounds(searchGUI.x, searchGUI.y, 250,322); 
			searchGUI.value = null;
		}
		
		else if (auditGUI.value !=null) {
			frame.setBounds(auditGUI.x, auditGUI.y, 250,322); 
			auditGUI.value = null;
		}
		else if (RimAdmin.value !=null) {
			frame.setBounds(RimAdmin.x, RimAdmin.y, 250,322); 
			RimAdmin.value = null;
		}
		
		else {
			frame.setBounds(RimLogin.x, RimLogin.y, 250, 322); }
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//frame.setLocationRelativeTo(RimLogin.frame);
	
		
		//frame.setVisible(true);
		
		JButton enter = new JButton("Enter new record\r\n");
		
		
		JButton search = new JButton("Search existing record(s)");

		
		JButton btnSearchAudits = new JButton("Search Audit(s)");
		
		
		JButton logout = new JButton("Logout");
		logout.setBounds(32, 250, 161, 22);
		frame.getContentPane().add(logout);
		
		JLabel userLabel = new JLabel("User: "+user); 
		userLabel.setBounds(12, 11, 108, 14);
		frame.getContentPane().add(userLabel);
		
		JButton adminPanel = new JButton("Admin Panel\r\n");
		
		JLabel levelLabel = new JLabel("Level: "+level);
		levelLabel.setBounds(12, 28, 106, 14);
		frame.getContentPane().add(levelLabel);

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



		
		if (level.equals("Viewer")) {			
			search.setBounds(32, 61, 161, 22);
			frame.getContentPane().add(search);			

		}
		
		
		
		//USER PERMISSIONS
		if (level.equals("User") || level.equals("Admin") || level.equals("SuperUser")) {
			enter.setBounds(32, 53, 161, 22);
			frame.getContentPane().add(enter);
			
			search.setBounds(32, 86, 161, 22);
			frame.getContentPane().add(search);			
		}
		
		
		
		//ADMIN PERMISSIONS
		if (level.equals("Admin") || level.equals("SuperUser")) {
			adminPanel.setBounds(32, 223, 161, 22);
			frame.getContentPane().add(adminPanel);
		
		
			btnSearchAudits.setBounds(32, 118, 161, 22);
			frame.getContentPane().add(btnSearchAudits);
		}
		

		
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//frame.dispose();
		         RIMGui.guiMain(user,pass,level); 
				frame.dispose();
				 
			}
			});
		adminPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//frame.dispose();
				
				RIMHandler.admin(user,pass);
				frame.dispose();
				 
			}
			});
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				frame.dispose();
				 value = "true";
				RimLogin.loginMain();		
				
			}
			});
		
		
		btnSearchAudits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				frame.dispose();
				auditGUI.auditMain(user,pass,level);				 
			}
			});
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				frame.dispose();
				searchGUI.searchMain(user,pass,level);				 
			}
			});
		
		
		
		
		
		
		
		
	}
}
