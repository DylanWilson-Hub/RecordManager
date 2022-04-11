package rimHandler;

import java.awt.EventQueue;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor; 
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;

public class allAudit {
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
	static int x,y;
	private static JComboBox lHold;
	static DefaultTableModel tableModel;
	private static String ID;
	private static JButton returnInput;
	static JTable table;
	static String user, pass, level,value;
	public static void auditMain(String user1,String pass1,String level1) {
		FlatDarkLaf.setup();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					allAudit window = new allAudit();
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
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */

	
	
	/*int rowCount = table.getRowCount();
	
	for (int i = 0; i< rowCount; i++) {
		String status = table.getValueAt(i, 4).toString();
		if (status.equals("insert")) {
			
		} */
	
	
	public static void auditSearch(String searchKeySel, String searchKey, String user, String pass, String level) {
		ID = searchKey;
		initialize(); 
		RIMHandler.auditAll(searchKeySel, searchKey,user,pass,level);

	}
  static void initialize() {
		frame = new JFrame();
	//	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dwilson1\\Pictures\\DLogo.PNG"));
		
		
		
		frame.setBounds(RimAudit.x, RimAudit.y, 1436, 544);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		
		JButton refresh = new JButton("Refresh");
		refresh.setBounds(-369, 544, 86, 23);
		frame.getContentPane().add(refresh);
		JComboBox<String> LegalHold = new JComboBox<String>();
		LegalHold.addItem("Yes");
		LegalHold.addItem("No");
		 
		String col[] = {"Barcode","altBarcode","BusUnit", "RecCode", "RecType", "RecDesc", "YearOfRec", "EmpName", "CurrentLoc", "AssignedLoc","OldLoc","CheckInDate","CheckOutDate","LegalHold", "Changed By", "Date Changed", "Status"};
		tableModel = new DefaultTableModel(col, 0);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 28, 1407, 429);
		frame.getContentPane().add(scrollPane_1);
		 table = new JTable(tableModel) 
		 
		 
		{
			
			/* @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    } */
			 
			 public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			 {
			     Component c = super.prepareRenderer(renderer, row, column);

			     //  Color row based on a cell value

			    
			         c.setBackground(getBackground()); // set default background

			         int modelRow = convertRowIndexToModel(row);
			         String value = (String)getModel().getValueAt(modelRow, 16);			         
			         
			         if (value.equals("Insert")) c.setBackground(new Color(0,255,0,50));
			         else if(value.equals("Update")) c.setBackground(new Color(255,255,0,50)); 
			         else if(value.equals("Delete")) c.setBackground(new Color(255,0,0,50));
			         


			     

			     return c;
			 }
			 
			  
			 
			 
		    public String getToolTipText( MouseEvent e )
		    {    	
		    	int charCount = 0;
		        int row = rowAtPoint( e.getPoint() );
		        int column = columnAtPoint( e.getPoint() );
		        if (column == 5 || column == 15 ) {
		        Object value = getValueAt(row, column);
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
		        } }
				};
				
				
				
				table.addMouseListener(new MouseListener() {

			        public void mouseClicked (MouseEvent me) {
			            if (me.getClickCount() == 2) {
			            	JTable table = (JTable)me.getSource();
			            	int row = table.getSelectedRow();
			            	int column = table.getSelectedColumn();
			            	String o = (String)table.getValueAt(row, column);
			            }
			           
			        }

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
			    });
				
				
		 table.setColumnSelectionAllowed(false);
		 table.setFocusable(false);
				scrollPane_1.setViewportView(table);
				table.setFillsViewportHeight(true);
				table.getTableHeader().setReorderingAllowed(false);
						table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						table.setRowSelectionAllowed(false);

				returnInput = new JButton("Return to Input ");
				returnInput.setBounds(1284, 464, 133, 23);
				frame.getContentPane().add(returnInput);
				
				JLabel lblNewLabel = new JLabel("Double click a barcode to see history.");
				lblNewLabel.setBounds(670, 11, 263, 14);
				frame.getContentPane().add(lblNewLabel);
				
				
				
				
				//	frame.add(new JScrollPane(table));
				//	table.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(LegalHold));

		
		
				
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
				frame.dispose();
				//System.exit(0);
				auditMain(user,pass,level);
			
			}
			});
		
		returnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				frame.dispose();
				//System.exit(0);
				value = "true";
				RimAudit.auditMain(user, pass, level);
			
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