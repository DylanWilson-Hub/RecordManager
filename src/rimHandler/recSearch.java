package rimHandler;

import java.awt.EventQueue;

import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import rimHandler.RIMHandler;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor; 
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Toolkit;
import java.awt.Color;

public class recSearch {
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
	private static JButton returnInput;
	static String user, pass, level, value;
	static int x,y;
	static ArrayList<ArrayList> Original = new ArrayList<ArrayList>();
	static ArrayList<String> IDList = new ArrayList<String>();
	private static JTextField emailField;
	private static JTextField dateOut;
	private static JTextField location;
	private static JLabel lblDateOut;
	private static JLabel lblNewLabel_2;
	private static JLabel lblReturnDate;
	private static JTextField returnDate;

	public static void recMain() {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					recSearch window = new recSearch();
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

	public static void recSearch(ArrayList<String> IDList) {

		initialize(); 
		RIMHandler.recSearch(IDList);
		System.out.println("RunsRecSearch");

	}
  static void initialize() {
		frame = new JFrame();
	//	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dwilson1\\Pictures\\DLogo.PNG"));
		frame.setBounds(searchGUI.x, searchGUI.y, 860, 449);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		 

		String col[] = {"Barcode","RecDesc","CurrentLoc", "AssignedLoc"};
		tableModel = new DefaultTableModel(col, 0);
		
	
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 143, 823, 216);
		frame.getContentPane().add(scrollPane_1);
		 table = new JTable(tableModel) 
		
		{
		    public String getToolTipText( MouseEvent e ) 
		    {
		    	int charCount = 0;
		        int row = rowAtPoint( e.getPoint() );
		        int column = columnAtPoint( e.getPoint() );
		        if (column == 1) {
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

				emailField = new JTextField();
				emailField.setBounds(10, 25, 265, 20);
				frame.getContentPane().add(emailField);
				emailField.setColumns(10);
				
				if (RIMHandler.email != null) {
					emailField.setText(RIMHandler.email);
				}
				
				JButton requestManager = new JButton("Send Request\r\n");
				requestManager.setBounds(696, 110, 137, 23);
				frame.getContentPane().add(requestManager);
				
				JLabel lblNewLabel = new JLabel("Enter/Confirm your email");
				lblNewLabel.setBounds(10, 11, 213, 14);
				frame.getContentPane().add(lblNewLabel);
				
				dateOut = new JTextField();
				dateOut.setColumns(10);
				dateOut.setBounds(10, 69, 94, 20);
				frame.getContentPane().add(dateOut);
				
				location = new JTextField();
				location.setColumns(10);
				location.setBounds(10, 111, 198, 20);
				frame.getContentPane().add(location);
				
				lblDateOut = new JLabel("Date Out");
				lblDateOut.setBounds(10, 56, 94, 14);
				frame.getContentPane().add(lblDateOut);
				
				lblNewLabel_2 = new JLabel("Location");
				lblNewLabel_2.setBounds(10, 97, 94, 14);
				frame.getContentPane().add(lblNewLabel_2);
				
				lblReturnDate = new JLabel("Return Date");
				lblReturnDate.setBounds(114, 56, 94, 14);
				frame.getContentPane().add(lblReturnDate);
				
				returnDate = new JTextField();
				returnDate.setColumns(10);
				returnDate.setBounds(114, 69, 94, 20);
				frame.getContentPane().add(returnDate);
				
				
				//	frame.add(new JScrollPane(table));
					//table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(LegalHold));
					
					

			/*
						System.out.println("Viewer runs");
						table.setDefaultEditor(Object.class, null);
						LegalHold.setEnabled(false);
						//LegalHold.setEditable(false);
						 table.setColumnSelectionAllowed(false);
						 //LegalHold.setEditor(null);
						 table.setFocusable(false);
								scrollPane_1.setViewportView(table);
								table.setFillsViewportHeight(true);
								table.getTableHeader().setReorderingAllowed(false);
										table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
										table.setRowSelectionAllowed(false);
					*/
					
		
		
		
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


		requestManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String email = emailField.getText();
				String dOut = dateOut.getText();
				String rDate = returnDate.getText();
				String loc = location.getText();


				try {
					RIMHandler.emailManager(email,dOut,rDate,loc);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
			});
  }
	

	
		public static void setBarcode(String barcodeField) {
			
			barcode.setText(barcodeField);
		}
		
		
		public static void setAltBarcode(String altBarcodeField) {
			altBarcode.setText(altBarcodeField);
		}
		public static void setrCode(String rCodeField) {
			rCode.setText(rCodeField);
		}
		public static void setbUnit(String bUnitField) {
			bUnit.setText(bUnitField);
		}
		public static void setyearOfRec(String yearOfRecField) {
			yearOfRec.setText(yearOfRecField);
		}
		public static void setrType(String rTypeField) {
			rType.setText(rTypeField);
		}
		public static void setrDesc(String rDescField) {
			rDesc.setText(rDescField);
		}
		public static void setempName(String empNameField) {
			empName.setText(empNameField);
		}
		public static void setoLoc(String oLocField) {
			oLoc.setText(oLocField);
		}
		public static void setcLoc(String cLocField) {
			cLoc.setText(cLocField);
		}
		public static void setaLoc(String aLocField) {
			aLoc.setText(aLocField);
		}
		public static void setcIn(String cInField) {
			cInDate.setText(cInField);
		}
		public static void setcOut(String cOutField) {
			cOutDate.setText(cOutField);
		}
		public static void setlHold(int lValue) {
			lHold.setSelectedIndex(lValue);
} 
}