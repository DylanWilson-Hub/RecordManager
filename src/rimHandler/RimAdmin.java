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

public class RimAdmin {

	static JFrame frame;
	static JTextField username;
	static JTextField password;
	private JLabel lblNewLabel_2;
	private JButton create;
	private JButton search;
	private JButton update;
	private JButton delete;
	static JComboBox levelSel;
	private JButton returnLogin;
	static String user, pass, level, value;
	static JTextField email;
	static int x,y;
	static JTextField first;
	static JTextField last;
	private JLabel lblNewLabel_2_1_2;
	/**
	 * Launch the application.
	 */
	public static void adminMain(String user1, String pass1, String level1) {
		FlatDarkLaf.setup();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RimAdmin window = new RimAdmin();
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
	public RimAdmin() {
		initialize(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
	//	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dwilson1\\Pictures\\DLogo.PNG"));
		frame.setBounds(optionPage.x, optionPage.y, 540, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		username = new JTextField();
		username.setBounds(83, 44, 304, 22);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(83, 78, 304, 22);
		frame.getContentPane().add(password);
		
		JLabel lblNewLabel = new JLabel("Username\r\n");
		lblNewLabel.setBounds(6, 47, 71, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password\r\n");
		lblNewLabel_1.setBounds(6, 81, 71, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		levelSel = new JComboBox();
		levelSel.setBounds(83, 244, 124, 22);
		frame.getContentPane().add(levelSel);
		levelSel.setModel(new DefaultComboBoxModel(new String[] {"  (Select One)", "Viewer", "User", "Admin"}));
		levelSel.setMaximumRowCount(3);
		
		lblNewLabel_2 = new JLabel("Level");
		lblNewLabel_2.setBounds(6, 250, 71, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		create = new JButton("Create User\r\n");
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		create.setBounds(83, 274, 93, 22);
		frame.getContentPane().add(create);
		
		search = new JButton("Search ");
		search.setBounds(399, 43, 101, 22);
		frame.getContentPane().add(search);
		
		update = new JButton("Update");
		update.setBounds(399, 77, 101, 22);
		frame.getContentPane().add(update);
		
		delete = new JButton("Delete");
		delete.setBounds(399, 108, 101, 22);
		frame.getContentPane().add(delete);
		
		returnLogin = new JButton("Return");
		returnLogin.setBounds(425, 350, 93, 22);
		frame.getContentPane().add(returnLogin);
		
		JButton refresh = new JButton("Refresh");
		refresh.setBounds(425, 6, 93, 22);
		frame.getContentPane().add(refresh);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(83, 112, 304, 22);
		frame.getContentPane().add(email);
		
		JLabel lblNewLabel_2_1 = new JLabel("Email\r\n");
		lblNewLabel_2_1.setBounds(6, 109, 71, 16);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JButton requests = new JButton("Requests");
		requests.setBounds(83, 343, 124, 22);
		frame.getContentPane().add(requests);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("First Name");
		lblNewLabel_2_1_1.setBounds(6, 146, 71, 16);
		frame.getContentPane().add(lblNewLabel_2_1_1);
		
		first = new JTextField();
		first.setColumns(10);
		first.setBounds(83, 149, 304, 22);
		frame.getContentPane().add(first);
		
		last = new JTextField();
		last.setColumns(10);
		last.setBounds(83, 186, 304, 22);
		frame.getContentPane().add(last);
		
		lblNewLabel_2_1_2 = new JLabel("Last Name");
		lblNewLabel_2_1_2.setBounds(6, 183, 71, 16);
		frame.getContentPane().add(lblNewLabel_2_1_2);
	
		
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

		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//frame.dispose();
				String user = username.getText();
				String pass = password.getText();
				String level = levelSel.getSelectedItem().toString();
				String Email = email.getText();
				String First = first.getText();
				String Last = last.getText();
				
				try {
					RIMHandler.adminInsert(user,pass,level,Email, First, Last);
					//frame.dispose();
					//adminMain(user,pass,level);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 
			}
			});
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//frame.dispose();
				String user = username.getText();
				RIMHandler.adminSearch(user);
				
				 
			}
			});
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//frame.dispose();
				username.setText("");
				password.setText("");
				levelSel.setSelectedIndex(0);
				email.setText("");
				first.setText("");
				last.setText("");
			}
			});
		
		
		returnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				frame.dispose();
				
				value = "true";
				optionPage.optionMain(user, pass, level,optionPage.first,optionPage.last);
				
				 
			}
			});
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//frame.dispose();
				String user = username.getText();
				String pass = password.getText();
				String level = levelSel.getSelectedItem().toString();
				String Email = email.getText();
				String First = first.getText();
				String Last = last.getText();
				try {
					RIMHandler.adminUpdate(user,pass,level,Email,First,Last);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				 
			}
			});
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//frame.dispose();
				String user = username.getText();
				RIMHandler.adminDelete(user);
				
				 
			}
			});
		requests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				
			 try {
					rimHandler.reqGUI.reqGUI(); 
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		
				}
	
		}); 
	}
	







	public static void setUser(String user) {
		username.setText(user);
	}
	public static void setPass(String pass) {
		password.setText(pass);
	}
	public static void setEmail(String email1) {
		email.setText(email1);
	}
	public static void setFirst(String first1) {
		first.setText(first1);
	}	
	public static void setLast(String last1) {
		last.setText(last1);
	}
	public static void setlHold(int lValue) {
		levelSel.setSelectedIndex(lValue);
}
}
