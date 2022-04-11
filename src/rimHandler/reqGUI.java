package rimHandler;

import java.awt.EventQueue;


import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import rimHandler.RIMHandler;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor; 
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Toolkit;
import java.awt.Color;

public class reqGUI {
	static String barcode1;
	static String altbarcode;
	static String busUnit;
	static String recCode;
	static String recType;
	static String recDesc;
	static String yearsOfRecords;
	static String employeeName;
	static String curLoc;
	static String assignedLoc;
	static String oldLoc;
	static String checkInDate;
	static String checkOutDate;
	static String legalHold;
	static String searchKeySel;
	static String searchKey;
	static JFrame frame;
	private JTable table_1;
	private static JTextField barcode;
	private static JTextField altBarcode;
	private static  JTextField rCode;
	private static  JTextField bUnit;
	private static  JTextField yearOfRec;
	private static  JTextField rType;
	private static  JTextField rDesc;
	private static  JTextField empName;
	private static  JTextField oLoc;
	private static  JTextField cLoc;
	private static  JTextField aLoc;
	private static  JTextField cInDate;
	private static  JTextField cOutDate;
	private static  JTextField searchKeyField;
	private static JTable table;
	private static JComboBox lHold;
	static DefaultTableModel tableModel;
	private static String ID;
	private static JButton returnInput, approveBtn, denyBtn;
	static String user, pass, level, value;
	static int x,y;
	static ArrayList<ArrayList> Original = new ArrayList<ArrayList>();
	static ArrayList<String> IDList = new ArrayList<String>();
	private static JButton clearAll;

	public static void reqMain(String user1, String pass1, String level1) {
		user = user1;
		pass = pass1;
		level = level1;
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reqGUI window = new reqGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		});
	}
	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */

	public static void reqGUI() {

		initialize(); 
		String searchKeySel = "ALL RECORDS";
		RIMHandler.reqGUI(searchKeySel, user,pass,level);
		System.out.println("RunsRecSearch");

	}
  static void initialize() {
		frame = new JFrame();
	//	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dwilson1\\Pictures\\DLogo.PNG"));
		frame.setBounds(searchGUI.x, searchGUI.y, 860, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		JButton update = new JButton("Update"); 
		
		

		JButton delete = new JButton("Delete");

		JScrollPane scrollPane = new JScrollPane();

		JButton refresh = new JButton("Refresh");
		refresh.setBounds(-369, 544, 86, 23);
		frame.getContentPane().add(refresh);
		JComboBox<String> LegalHold = new JComboBox<String>();
		LegalHold.addItem("Yes");
		LegalHold.addItem("No"); 
		 

		String col[] = {"Username","Barcode","Date Out","Date Return","RequestedLocation","CurrentLocation","Date Requested","Status","Approve","Deny"};
		tableModel = new DefaultTableModel(col, 0);
		
	
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 30, 834, 329);
		frame.getContentPane().add(scrollPane_1);
		
		 table = new JTable(tableModel) 	
		{
		    public String getToolTipText( MouseEvent e ) 
		    {
		    	int charCount = 0;
		        int row = rowAtPoint( e.getPoint() );
		        int column = columnAtPoint( e.getPoint() );
		        if (column == 2) {
		        Object value = getValueAt(row, 1);
				ArrayList<String> valueList = new ArrayList<String>();			
				for (char c :((String) value).toCharArray()) {					
					String v = Character.toString(c);
					
					charCount++;
					
					if (charCount == 80) {
						
						if (v.equals(" ")) {
						valueList.add("\n");
						charCount = 0; }
						else {
							valueList.add("-\n");					
							charCount = 0;
						}
						
						}		
					
					valueList.add(v);
				}
				
				StringBuffer sb = new StringBuffer();
				
				for (String s : valueList) {
				 	sb.append(s);
				}
				Object newValue = sb.toString();
				
				
				
		        return newValue == null ? null : newValue.toString(); }
		        else {
		        	return null;
		        }
		

		    }

		
		};
				
				scrollPane_1.setViewportView(table);
				table.setFillsViewportHeight(true);
				table.getTableHeader().setReorderingAllowed(false);
						table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
						table.setRowSelectionAllowed(true);
				
				
				returnInput = new JButton("Return ");
				returnInput.setBounds(694, 381, 133, 23);
				frame.getContentPane().add(returnInput);
				
				clearAll = new JButton("Clear All\r\n");
				clearAll.setBackground(Color.RED);
				clearAll.setBounds(384, 381, 102, 23);
				frame.getContentPane().add(clearAll);
				

				

		
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

		returnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				IDList.clear();
				frame.dispose();
				//System.exit(0);
				//RIMGui.guiMain(); 
			
			}
			});

		clearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				frame.dispose(); 
				RIMHandler.reqClear(user,pass,level);
				
			}
			});
	
		
		
		Action approve = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        
		        Object barcode = table.getModel().getValueAt(modelRow, 1);

		    	String status = "Approved";
		        RIMHandler.reqStat(barcode, status);
		    }
		};
		ButtonColumn buttonColumn = new ButtonColumn(table, approve, 8);

		
		
		
		
		
		
		
		
		
		
		
		Action deny = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        
		        Object barcode = table.getModel().getValueAt(modelRow, 1);

		    	String status = "Denied";
		        RIMHandler.reqStat(barcode, status);
		    }
		};
		ButtonColumn buttonColumndeny = new ButtonColumn(table, deny, 9);

  
	
		
} }