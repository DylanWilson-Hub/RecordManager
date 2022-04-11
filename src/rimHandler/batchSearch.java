package rimHandler;

import java.awt.EventQueue;

import java.awt.event.MouseEvent;
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

public class batchSearch {
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
	static String user, pass, level,value;
	static ArrayList<ArrayList> Original = new ArrayList<ArrayList>();
	static ArrayList<String> IDList = new ArrayList<String>();
	static int x,y;
	public static void batchMain() {
		FlatDarkLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					batchSearch window = new batchSearch();
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

	public static void batchSearch(String searchKeySel, String searchKey, String user1, String pass1, String level1) {
		ID = searchKey;
		user = user1;
		pass = pass1;
		level = level1;
		initialize(); 
		RIMHandler.btchSearch(searchKeySel, searchKey);


	}
  static void initialize() {
		frame = new JFrame();
		frame.setBounds(searchGUI.x, searchGUI.y, 1448, 611);
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
		 

		String col[] = {"Barcode","altBarcode","BusUnit", "RecCode", "RecType", "RecDesc", "YearOfRec", "EmpName", "CurrentLoc", "AssignedLoc","OldLoc","CheckInDate","CheckOutDate","LegalHold"};
		tableModel = new DefaultTableModel(col, 0);
		
	
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 1427, 410);
		frame.getContentPane().add(scrollPane_1);
		 table = new JTable(tableModel) 
		
		{
		    public String getToolTipText( MouseEvent e ) 
		    {
		    	int charCount = 0;
		        int row = rowAtPoint( e.getPoint() );
		        int column = columnAtPoint( e.getPoint() );
		        if (column == 5) {
		        Object value = getValueAt(row, 5);
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
				
				scrollPane_1.setViewportView(table);
				table.setFillsViewportHeight(true);
				table.getTableHeader().setReorderingAllowed(false);
						table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
						table.setRowSelectionAllowed(true);
				
				
				returnInput = new JButton("Return to Input ");
				returnInput.setBounds(1293, 544, 133, 23);
				frame.getContentPane().add(returnInput);
				
				JLabel lblNewLabel = new JLabel("After editing a cell, always click outside of it");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
				lblNewLabel.setBounds(29, 31, 281, 16);
				frame.getContentPane().add(lblNewLabel);
				
				JLabel lblYouCannotUpdate = new JLabel("You cannot update a barcode and alt barcode simultaneously");
				lblYouCannotUpdate.setVerticalAlignment(SwingConstants.BOTTOM);
				lblYouCannotUpdate.setHorizontalAlignment(SwingConstants.CENTER);
				lblYouCannotUpdate.setBounds(-12, -15, 421, 34);
				frame.getContentPane().add(lblYouCannotUpdate);
				
				JLabel lblDeletesAreBased = new JLabel("Deletes are based off barcodes so guarantee the barcode field is correct with no duplicates.");
				lblDeletesAreBased.setVerticalAlignment(SwingConstants.BOTTOM);
				lblDeletesAreBased.setHorizontalAlignment(SwingConstants.LEFT);
				lblDeletesAreBased.setBounds(461, 24, 561, 23);
				frame.getContentPane().add(lblDeletesAreBased);
				
				JButton requestBtn = new JButton("Select Record(s) to request\r\n");
				requestBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				requestBtn.setBounds(10, 467, 215, 22);
				frame.getContentPane().add(requestBtn);
				
				JLabel recordsSelCount = new JLabel("Records Selected: "+IDList.size());
				recordsSelCount.setVerticalAlignment(SwingConstants.BOTTOM);
				recordsSelCount.setHorizontalAlignment(SwingConstants.LEFT);
				recordsSelCount.setBounds(227, 467, 130, 22);
				frame.getContentPane().add(recordsSelCount);
				
				JButton clearReq = new JButton("Clear requested");
				
				clearReq.setBounds(10, 544, 215, 22);
				frame.getContentPane().add(clearReq);
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
				JButton confirmReq = new JButton("Confirm requested Records");
				 confirmReq.setBounds(10, 501, 215, 22);
	  				frame.getContentPane().add(confirmReq);
	  				confirmReq.setEnabled(false);
				//	frame.add(new JScrollPane(table));
					table.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(LegalHold));
					
					if (level.equals("Admin") || level.equals("User")) {
						update.setBounds(1229, 11, 89, 23);
						frame.getContentPane().add(update);
						
						delete.setBounds(1328, 11, 89, 23);
						frame.getContentPane().add(delete);
						

					}

					if (level.equals("Viewer")) { 
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
										table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
										table.setRowSelectionAllowed(true);
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
	
			
			}
			});
		
		
		
		clearReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				IDList.clear();
				recordsSelCount.setText("Records Selected: "+IDList.size());
  				confirmReq.setEnabled(false);

						}
			});
		
		
		
		
		returnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				IDList.clear();
				frame.dispose();
				value = "true";
				searchGUI.searchMain(user, pass, level);
				//System.exit(0);
				//RIMGui.guiMain(); 
			
			}
			});

		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 
				try {
					
					ArrayList<ArrayList> row = new ArrayList<ArrayList>();
					int rowCount = table.getSelectedRow();
					 if (rowCount > -1) {

		                // int[] selectedrows = table.getSelectedRows();  
		                 int selectedrows = table.getRowCount(); 
		                 System.out.println(selectedrows);

		                 
		                 for (int i = 0; i <selectedrows; i++) 
		                {
		 					ArrayList<String> data = new ArrayList<String>();

		                	 for (int i1 = 0; i1 < 14; i1++)
				                {
		     
		                		 data.add((String) table.getValueAt(i, i1));
		                		 
				                }
		                	 row.add(data);

		                	 
		                	if (row.get(i).equals(Original.get(i))) { 
			                	 System.out.println(" Equals");

		                		}
		                	
		                	 else {
		                		 System.out.println("RUNS UPDATE");
			                		RIMHandler.btchUpdate(data); 
			                	 System.out.println("Doesnt Equals");
			                 }
		           
		                
		                
		                
		                }
			        	 getOriginal();

	                	 System.out.println(row.get(0));
	                	 System.out.println(Original.get(0));
		                 
		                 JFrame f = new JFrame();
				   		 JOptionPane.showMessageDialog(f, "Updated!");
		            }
					 else {
						 JFrame f = new JFrame();
					     JOptionPane.showMessageDialog(f, "Change something to update");	
					 }
					 
					 
					 
					  
					 
				} catch (Exception e1) {
					 JFrame f = new JFrame();
				     JOptionPane.showMessageDialog(f, "Select something to update" +e1);	
				}

				

				
			}	}); 

	delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			
				 try {

					 ArrayList<ArrayList> row = new ArrayList<ArrayList>();
						int rowCount = table.getSelectedRow();
						 if (rowCount > -1) {

			                 int[] selectedrows = table.getSelectedRows(); 
			                 

			               //  int selectedrows = table.getRowCount(); 

			                 for (int i = 0; i < selectedrows.length; i++)
			                { 
			                     String user = (table.getValueAt(selectedrows[i], 0).toString()); 

							//	Vector data = tableModel.getDataVector().elementAt(table.getSelectedRow());
			                	// System.out.println(user);
			                    //System.out.println(table.getValueAt(selectedrows[i], 2).toString());
			 					RIMHandler.btchDelete(user, rowCount);

			                }
			                 JFrame f = new JFrame();
					   		 JOptionPane.showMessageDialog(f, "Deleted");
	 

					
					
					DefaultTableModel model = (DefaultTableModel)table.getModel();
	                int row1 = table.getSelectedRow();
	                while (row1 != -1)
	                {
	                    int modelRow = table.convertRowIndexToModel( row1 );
	                    model.removeRow( modelRow );
	                    row1 = table.getSelectedRow();

	                } 
	                
		        	 batchSearch.getOriginal();

						 }
						 else {
							 JFrame f = new JFrame();
						     JOptionPane.showMessageDialog(f, "Select something to delete");		
						 }
						 
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				      JFrame f = new JFrame();
					     JOptionPane.showMessageDialog(f, "Select something to delete");					}
					batchMain();   
					
					frame.dispose();
			}
		}); 
	requestBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

		
			 try {
				 //IDList.clear();
				 ArrayList<ArrayList> row = new ArrayList<ArrayList>();
					int rowCount = table.getSelectedRow();
					 if (rowCount > -1) {

		                 int[] selectedrows = table.getSelectedRows(); 
		                 

		               //  int selectedrows = table.getRowCount(); 

		                 for (int i = 0; i < selectedrows.length; i++)
		                { 
		                     String user = (table.getValueAt(selectedrows[i], 0).toString()); 

						//	Vector data = tableModel.getDataVector().elementAt(table.getSelectedRow());
		                	// System.out.println(user);
		                    //System.out.println(table.getValueAt(selectedrows[i], 2).toString());
		 					RIMHandler.reqSave(user, rowCount);

		                }
		                 System.out.println(IDList);
		                 recordsSelCount.setText("Records Selected: "+IDList.size());
			  				confirmReq.setEnabled(true);

		                 
					 }
					 else {
						 JFrame f = new JFrame();
					     JOptionPane.showMessageDialog(f, "Select something to delete");		
					 }	 
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			      JFrame f = new JFrame();
				     JOptionPane.showMessageDialog(f, "Select something to delete");					}

		}
	}); 
	
	
	
	
	confirmReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				
			 try {
					rimHandler.recSearch.recSearch(IDList); 
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		
				}
	
		}); 
	}
		
	
	
	// Setter Methods
	public static void getOriginal() {
		
		try {
			Original.clear();
			int rowCount = 1;
			 if (rowCount > -1) {

                // int[] selectedrows = table.getSelectedRows();  
                 int selectedrows = table.getRowCount(); 
                // System.out.println("Total Rows"+selectedrows);
                 for (int i = 0; i < selectedrows; i++) 
                {
                	// System.out.println("i"+i);
 					ArrayList<String> data = new ArrayList<String>();

                	 for (int i1 = 0; i1 < 14; i1++)
		                {

                		 data.add((String) table.getValueAt(i, i1));
                		 
		                }
                	//System.out.println(data);
                	 Original.add(data);
                	// System.out.println("This"+data);
				//	Vector data = tableModel.getDataVector().elementAt(table.getSelectedRow());
                    //System.out.println(table.getValueAt(selectedrows[i], 2).toString());
                	 
 				//	RIMHandler.btchUpdate(data);
                }
                 System.out.println("OG Runs");

                 
            }
			 else {
				 JFrame f = new JFrame();
			     JOptionPane.showMessageDialog(f, "Change something to update");	
			 }
			 

		} catch (Exception e1) {
			 JFrame f = new JFrame();
		     JOptionPane.showMessageDialog(f, "Select something to update" +e1);	
		}

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
