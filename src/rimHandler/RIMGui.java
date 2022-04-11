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
 
public class RIMGui {
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
	private static  JTextField oLoc;
	private static  JTextField cLoc;
	private static  JTextField aLoc;
	private static  JTextField cInDate;
	private static  JTextField cOutDate;
	private static JComboBox lHold;
	static String user, pass, level, value;
	static int x,y;
	private JTextField empName;
	/**
	 * Launch the application.
	 */
	public static void guiMain(String user1, String pass1, String level1) { 
		FlatDarkLaf.setup();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					RIMGui window = new RIMGui();
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
	public RIMGui() { 
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(optionPage.x, optionPage.y, 833, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Box Barcode*");
		lblNewLabel.setBounds(9, 39, 116, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel altBarcode1 = new JLabel("Alternative Box Barcode");
		altBarcode1.setBounds(382, 36, 137, 14);
		frame.getContentPane().add(altBarcode1);
		
		barcode = new JTextField();
		barcode.setBounds(104, 36, 194, 20);
		frame.getContentPane().add(barcode);
		barcode.setColumns(10);
		
		altBarcode = new JTextField();
		altBarcode.setColumns(10);
		altBarcode.setBounds(531, 36, 194, 20);
		frame.getContentPane().add(altBarcode);
		
		JButton submit = new JButton("Enter");
		submit.setBounds(636, 314, 89, 20);
		frame.getContentPane().add(submit);
		
		JLabel rCode1 = new JLabel("Record Code*");
		rCode1.setBounds(382, 76, 73, 14);
		frame.getContentPane().add(rCode1);
		
		JLabel bUnit1 = new JLabel("Business Unit*\r\n");
		bUnit1.setBounds(9, 79, 116, 14);
		frame.getContentPane().add(bUnit1);
		
		rCode = new JTextField();
		rCode.setColumns(10);
		rCode.setBounds(531, 73, 194, 20);
		frame.getContentPane().add(rCode);
		
		bUnit = new JTextField();
		bUnit.setColumns(10);
		bUnit.setBounds(104, 76, 194, 20);
		frame.getContentPane().add(bUnit);
		
		JLabel rType1 = new JLabel("Record Type*");
		rType1.setBounds(9, 111, 116, 14);
		frame.getContentPane().add(rType1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Years of Records*");
		lblNewLabel_1_1.setBounds(9, 137, 116, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		yearOfRec = new JTextField();
		yearOfRec.setColumns(10);
		yearOfRec.setBounds(104, 138, 194, 20);
		frame.getContentPane().add(yearOfRec);
		
		rType = new JTextField();
		rType.setColumns(10);
		rType.setBounds(104, 106, 194, 20);
		frame.getContentPane().add(rType);
		
		JLabel lblBoxName_2 = new JLabel("Records Description\r\n");
		lblBoxName_2.setBounds(382, 212, 137, 14);
		frame.getContentPane().add(lblBoxName_2);
		
		rDesc = new JTextField();
		rDesc.setColumns(10);
		rDesc.setBounds(531, 212, 194, 88);
		frame.getContentPane().add(rDesc);
		
		JLabel lblNewLabel_3 = new JLabel("Current Location");
		lblNewLabel_3.setBounds(9, 170, 116, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel oLoc1 = new JLabel("Old Location");
		oLoc1.setBounds(9, 206, 116, 14);
		frame.getContentPane().add(oLoc1);
		
		oLoc = new JTextField();
		oLoc.setColumns(10);
		oLoc.setBounds(104, 206, 194, 20);
		frame.getContentPane().add(oLoc);
		
		cLoc = new JTextField();
		cLoc.setColumns(10);
		cLoc.setBounds(104, 170, 194, 20);
		frame.getContentPane().add(cLoc);
		
		JLabel checkInDate1 = new JLabel("Check in date");
		checkInDate1.setBounds(382, 161, 137, 14);
		frame.getContentPane().add(checkInDate1);
		
		JLabel lblBoxName_3 = new JLabel("Assigned Location");
		lblBoxName_3.setBounds(382, 131, 137, 14);
		frame.getContentPane().add(lblBoxName_3);
		
		aLoc = new JTextField();
		aLoc.setColumns(10);
		aLoc.setBounds(531, 131, 194, 20);
		frame.getContentPane().add(aLoc);
		
		cInDate = new JTextField();
		cInDate.setColumns(10);
		cInDate.setBounds(531, 161, 194, 20);
		frame.getContentPane().add(cInDate);
		
		JLabel checkOutDate1 = new JLabel("Check out date");
		checkOutDate1.setBounds(9, 243, 116, 14);
		frame.getContentPane().add(checkOutDate1);
		
		cOutDate = new JTextField();
		cOutDate.setColumns(10);
		cOutDate.setBounds(104, 243, 194, 20);
		frame.getContentPane().add(cOutDate);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Legal Hold* ");
		lblNewLabel_1_2_1.setBounds(9, 276, 116, 14);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		lHold = new JComboBox();
		lHold.setModel(new DefaultComboBoxModel(new String[] {"  (Select One)", "Yes", "No"}));
		lHold.setMaximumRowCount(2);
		lHold.setBounds(118, 272, 107, 22);
		frame.getContentPane().add(lHold);
		
		JButton refresh = new JButton("Refresh");
		refresh.setBounds(726, 6, 89, 23);
		frame.getContentPane().add(refresh);
		
		JButton returnLogin = new JButton("Return\r\n");
		returnLogin.setBounds(722, 346, 93, 22);
		frame.getContentPane().add(returnLogin);
		
		JLabel lblBoxName_3_1 = new JLabel("Employee Name");
		lblBoxName_3_1.setBounds(382, 105, 137, 14);
		frame.getContentPane().add(lblBoxName_3_1);
		
		empName = new JTextField();
		empName.setColumns(10);
		empName.setBounds(531, 105, 194, 20);
		frame.getContentPane().add(empName);
		
		if (optionPage.first !=null && optionPage.last != null) {
		empName.setText(optionPage.first+" "+optionPage.last);
		}
		
		
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

		
		
		refresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { 
				barcode.setText("");
				altBarcode.setText("");
				bUnit.setText("");
				rType.setText("");
				rDesc.setText("");
				yearOfRec.setText("");
				aLoc.setText("");
				rCode.setText("");
				
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
				optionPage.optionMain(user,pass, level, optionPage.first,optionPage.last);
				
			}
			});
		
		
		
		submit.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				 barcode1 = barcode.getText();
				 altbarcode = altBarcode.getText();
				 busUnit=bUnit.getText();
				 recCode=rCode.getText();
				 recType = rType.getText();
				 recDesc = rDesc.getText(); 
				 yearsOfRecords = yearOfRec.getText();
				 employeeName= empName.getText();
				 assignedLoc = aLoc.getText();
				 curLoc = cLoc.getText();
				 oldLoc = oLoc.getText();
				 checkInDate = cInDate.getText();
				 checkOutDate = cOutDate.getText();
				 legalHold = lHold.getSelectedItem().toString();
				 
				 
				 try {
					RIMHandler.insert(barcode1,altbarcode,busUnit,recCode,recType,recDesc,yearsOfRecords,employeeName,assignedLoc, curLoc,oldLoc,checkInDate,checkOutDate,legalHold,user,pass,level);
					barcode.setText("");
					altBarcode.setText("");
					bUnit.setText("");
					rType.setText("");
					rDesc.setText("");
					yearOfRec.setText("");
					aLoc.setText("");
					rCode.setText("");

					cLoc.setText("");
					oLoc.setText("");
					cInDate.setText("");
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
