package rimHandler;

import java.awt.EventQueue;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import rimHandler.RIMHandler;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import rimHandler.batchSearch;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Toolkit;
 
public class searchGUI {
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
	private static JComboBox lHold;
	static String user, pass, level, value;
	static int x,y;
	/**
	 * Launch the application.
	 */
	public static void searchMain(String user1, String pass1, String level1) { 
		FlatDarkLaf.setup();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					searchGUI window = new searchGUI();
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
	public searchGUI() { 
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dwilson1\\Pictures\\DLogo.PNG"));
		
		if (batchSearch.value != null ) {
			frame.setBounds(batchSearch.x, batchSearch.y, 850, 473);

		batchSearch.value = null;
		}
		else {
			frame.setBounds(optionPage.x, optionPage.y, 850, 473);

		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Box Barcode*");
		lblNewLabel.setBounds(10, 157, 116, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel altBarcode1 = new JLabel("Alternative Box Barcode");
		altBarcode1.setBounds(383, 154, 137, 14);
		frame.getContentPane().add(altBarcode1);
		
		barcode = new JTextField();
		barcode.setBounds(105, 154, 194, 20);
		frame.getContentPane().add(barcode);
		barcode.setColumns(10);
		
		altBarcode = new JTextField();
		altBarcode.setColumns(10);
		altBarcode.setBounds(532, 154, 194, 20);
		frame.getContentPane().add(altBarcode);
		
		JButton update = new JButton("Update");
		
		
		JButton search = new JButton("Search");
		search.setBounds(309, 29, 89, 23);
		frame.getContentPane().add(search);
		

		JButton delete = new JButton("Delete");

		
		JLabel rCode1 = new JLabel("Record Code");
		rCode1.setBounds(383, 194, 73, 14);
		frame.getContentPane().add(rCode1);
		
		JLabel bUnit1 = new JLabel("Business Unit\r\n");
		bUnit1.setBounds(10, 197, 116, 14);
		frame.getContentPane().add(bUnit1);
		
		rCode = new JTextField();
		rCode.setColumns(10);
		rCode.setBounds(532, 191, 194, 20);
		frame.getContentPane().add(rCode);
		
		bUnit = new JTextField();
		bUnit.setColumns(10);
		bUnit.setBounds(105, 194, 194, 20);
		frame.getContentPane().add(bUnit);
		
		JLabel rType1 = new JLabel("Record Type");
		rType1.setBounds(10, 229, 116, 14);
		frame.getContentPane().add(rType1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Years of Records");
		lblNewLabel_1_1.setBounds(10, 255, 116, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		yearOfRec = new JTextField();
		yearOfRec.setColumns(10);
		yearOfRec.setBounds(105, 256, 194, 20);
		frame.getContentPane().add(yearOfRec); 
		
		rType = new JTextField();
		rType.setColumns(10);
		rType.setBounds(105, 224, 194, 20);
		frame.getContentPane().add(rType);
		
		JLabel lblBoxName_1_1 = new JLabel("Employee Name");
		lblBoxName_1_1.setBounds(383, 223, 137, 14);
		frame.getContentPane().add(lblBoxName_1_1);
		
		JLabel lblBoxName_2 = new JLabel("Records Description\r\n");
		lblBoxName_2.setBounds(383, 330, 137, 14);
		frame.getContentPane().add(lblBoxName_2);
		
		rDesc = new JTextField();
		rDesc.setColumns(10);
		rDesc.setBounds(532, 330, 194, 78);
		frame.getContentPane().add(rDesc);
		
		empName = new JTextField();
		empName.setColumns(10);
		empName.setBounds(532, 223, 194, 20);
		frame.getContentPane().add(empName);
		
		JLabel lblNewLabel_3 = new JLabel("Current Location");
		lblNewLabel_3.setBounds(10, 288, 116, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel oLoc1 = new JLabel("Old Location");
		oLoc1.setBounds(383, 288, 116, 14);
		frame.getContentPane().add(oLoc1);
		
		oLoc = new JTextField();
		oLoc.setColumns(10);
		oLoc.setBounds(532, 285, 194, 20);
		frame.getContentPane().add(oLoc);
		
		cLoc = new JTextField();
		cLoc.setColumns(10);
		cLoc.setBounds(105, 288, 194, 20);
		frame.getContentPane().add(cLoc);
		
		JLabel checkInDate1 = new JLabel("Check in date");
		checkInDate1.setBounds(10, 330, 137, 14);
		frame.getContentPane().add(checkInDate1);
		
		JLabel lblBoxName_3 = new JLabel("Assigned Location");
		lblBoxName_3.setBounds(383, 256, 137, 14);
		frame.getContentPane().add(lblBoxName_3);
		
		aLoc = new JTextField();
		aLoc.setColumns(10);
		aLoc.setBounds(532, 256, 194, 20);
		frame.getContentPane().add(aLoc);
		
		cInDate = new JTextField();
		cInDate.setColumns(10);
		cInDate.setBounds(105, 327, 194, 20);
		frame.getContentPane().add(cInDate);
		
		JLabel checkOutDate1 = new JLabel("Check out date");
		checkOutDate1.setBounds(10, 361, 116, 14);
		frame.getContentPane().add(checkOutDate1);
		
		cOutDate = new JTextField();
		cOutDate.setColumns(10);
		cOutDate.setBounds(105, 361, 194, 20);
		frame.getContentPane().add(cOutDate);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Legal Hold* ");
		lblNewLabel_1_2_1.setBounds(10, 394, 116, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		lHold = new JComboBox();
		lHold.setModel(new DefaultComboBoxModel(new String[] {"  (Select One)", "Yes", "No"}));
		lHold.setMaximumRowCount(2);
		lHold.setBounds(119, 390, 107, 22);
		frame.getContentPane().add(lHold);
		
		
		
		
		
		JComboBox searchKeySelector = new JComboBox();
		searchKeySelector.setModel(new DefaultComboBoxModel(new String[] {"  (Select one)", "Barcode", "AltBarcode", "BusinessUni", "RecordCode", "RecordType", "RecordDescription", "YearsOfRecords", "EmployeeName", "CurrentLocation", "AssignedLocation", "OldLocation", "CheckIN", "CheckOut", "legalHold", "ALL RECORDS"}));
		searchKeySelector.setBounds(179, 33, 116, 22);
		frame.getContentPane().add(searchKeySelector);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("How would you like to search?");
		lblNewLabel_1_2_1_1.setBounds(9, 33, 194, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1_1);
		
		searchKeyField = new JTextField();
		searchKeyField.setColumns(10);
		searchKeyField.setBounds(179, 67, 116, 20);
		frame.getContentPane().add(searchKeyField);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Please enter your selection");
		lblNewLabel_1_2_1_1_1.setBounds(9, 66, 194, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1_1_1);
		
		JButton refresh = new JButton("Refresh");
		refresh.setBounds(726, 6, 89, 23);
		frame.getContentPane().add(refresh);
		
		JButton batchSearch = new JButton("Batch Search\r\n");
		batchSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		batchSearch.setBounds(110, 98, 116, 23);
		frame.getContentPane().add(batchSearch);
		
		JButton returnLogin = new JButton("Return\r\n");
		returnLogin.setBounds(738, 401, 93, 22);
		frame.getContentPane().add(returnLogin);
		
		JButton batchSearchAll = new JButton("ALL RECORDS");
		batchSearchAll.setBounds(236, 98, 116, 23);
		frame.getContentPane().add(batchSearchAll);
		
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

		
		
		
		if (level.equals("Admin") || level.equals("User")) {
			update.setBounds(738, 333, 93, 23);
			frame.getContentPane().add(update);
			
			delete.setBounds(738, 367, 93, 23);
			frame.getContentPane().add(delete);
			

		}
		
		
		
		if (level.equals("Viewer")) {
			lHold.setEnabled(false);
			barcode.setEnabled(false);
			altBarcode.setEnabled(false);
			bUnit.setEnabled(false);
			rCode.setEnabled(false);
			rType.setEnabled(false);
			empName.setEnabled(false);
			yearOfRec.setEnabled(false);
			aLoc.setEnabled(false);
			cLoc.setEnabled(false);
			oLoc.setEnabled(false);
			cInDate.setEnabled(false);
			cOutDate.setEnabled(false);
			rDesc.setEnabled(false);
			
			
			
		}
		
		
		
		
		refresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { 
				barcode.setText("");
				altBarcode.setText("");
				bUnit.setText("");
				rCode.setText("");
				rType.setText("");
				rDesc.setText("");
				yearOfRec.setText("");
				empName.setText("");
				aLoc.setText("");
				cLoc.setText("");
				oLoc.setText("");
				cInDate.setText("");
				cOutDate.setText("");
				lHold.setSelectedIndex(0);

			}
			});
		
		
		
		returnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { 
				frame.dispose();
				value = "true";
				optionPage.optionMain(user,pass, level,optionPage.first,optionPage.last);
				
			}
			});
	
		
		
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 barcode1 = barcode.getText();
				 altbarcode = altBarcode.getText();
				 busUnit=bUnit.getText();
				 recCode=rCode.getText();
				 recType = rType.getText();
				 recDesc = rDesc.getText();
				 yearsOfRecords = yearOfRec.getText();
				 employeeName = empName.getText();
				 assignedLoc = aLoc.getText();
				 curLoc = cLoc.getText();
				 oldLoc = oLoc.getText();
				 checkInDate = cInDate.getText();
				 checkOutDate = cOutDate.getText();
				 legalHold = lHold.getSelectedItem().toString();
				
				 searchKeySel = searchKeySelector.getSelectedItem().toString();
				 searchKey = searchKeyField.getText();
				 
				try {
				
					RIMHandler.updated(barcode1,altbarcode,busUnit,recCode,recType,recDesc,yearsOfRecords,employeeName,assignedLoc, curLoc,oldLoc,checkInDate,checkOutDate,legalHold, searchKeySel, searchKey);
					barcode.setText("");
					altBarcode.setText("");
					bUnit.setText("");
					rType.setText("");
					rDesc.setText("");
					yearOfRec.setText("");
					empName.setText("");
					aLoc.setText("");
					cLoc.setText("");
					oLoc.setText("");
					cInDate.setText("");
					cOutDate.setText("");
					rCode.setText("");

					lHold.setSelectedIndex(0);				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchKeySel = searchKeySelector.getSelectedItem().toString();
				searchKey = searchKeyField.getText();
				RIMHandler.search(searchKeySel,searchKey);
				
			}
		});
		
		
		
		batchSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				searchKeySel = searchKeySelector.getSelectedItem().toString();
				searchKey = searchKeyField.getText();
				
				
				
				
			 try {
					

					rimHandler.batchSearch.batchSearch(searchKeySel, searchKey, user,pass,level); 
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		
				}
	
		}); 
		
		
		batchSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchKeySel = "ALL RECORDS";
				searchKey = searchKeyField.getText();
				
				
				
				
			 try {
					frame.dispose();

					rimHandler.batchSearch.batchSearch(searchKeySel, searchKey,user,pass,level); 

				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		
		
		
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					searchKeySel = searchKeySelector.getSelectedItem().toString();
					searchKey = searchKeyField.getText();
				 
				 
				 try {
					RIMHandler.delete(searchKeySel,searchKey);
					barcode.setText("");
					altBarcode.setText("");
					bUnit.setText("");
					rType.setText("");
					rDesc.setText("");
					yearOfRec.setText("");
					empName.setText("");
					aLoc.setText("");
					cLoc.setText("");
					oLoc.setText("");
					cInDate.setText("");
					rCode.setText("");

					cOutDate.setText("");
					lHold.setSelectedIndex(0);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}
		});
		
		
		
		

		
	
	} 
		
		
		
		
	
	
	// Setter Methods
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