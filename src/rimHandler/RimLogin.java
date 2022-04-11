package rimHandler;

import java.awt.EventQueue;
import java.awt.Rectangle;

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
import javax.swing.event.MouseInputAdapter;

public class RimLogin {

	static JFrame frame;
	static String user;
	static String pass;
	private JTextField password;
	private JTextField username;
	private JLabel lblPassword;
	static int x;
	static int y;
	/**
	 * Launch the application.
	 */
	public static void loginMain() {
		FlatDarkLaf.setup();

		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					RimLogin window = new RimLogin();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RimLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		if (optionPage.value != null) {
		frame.setBounds(optionPage.x, optionPage.y, 250, 294);
		}
		else {
			
		frame.setBounds(100, 100, 250, 294); 
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.pack();
		

		
		
		
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(65, 168, 106, 22);
		frame.getContentPane().add(loginButton);
		
		password = new JPasswordField();
		password.setBounds(59, 111, 124, 22);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(59, 71, 124, 22);
		frame.getContentPane().add(username);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(59, 51, 124, 16);
		frame.getContentPane().add(lblNewLabel);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(59, 93, 124, 16);
		frame.getContentPane().add(lblPassword);
		
		
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
		
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//frame.dispose(); 
				 user = username.getText();
				 pass = password.getText();
				RIMHandler.login(user,pass);
				 
			}
			});
		
		
	}
}
