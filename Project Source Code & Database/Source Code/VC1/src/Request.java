import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


public class Request extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	public static JTable table;
	String[][] users;
	String url = "jdbc:sqlserver://SATELLITEA30-PC;instanceName=SQLEXPRESS;databaseName=ValveCombination;user=sa;password=123"; 		// a JDBC url
	static Request frame;
	Request rq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Request();
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter() {
					      public void windowClosing(WindowEvent e) {
					        System.exit(0);
					      }
					    });
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public Request() {
		
		String url = "jdbc:sqlserver://SATELLITEA30-PC;instanceName=SQLEXPRESS;databaseName=ValveCombination;user=sa;password=123"; 

		setBounds(100, 100, 488, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 452, 323);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 432, 200);
		panel.add(scrollPane);
		
		ArrayList<ArrayList<String>> newusers = new ArrayList<ArrayList<String>>();
		
		Connection connection = null;
        java.sql.Statement stmt = null;
        ResultSet rs1 = null;
        
		try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);
            
            
            String  SQL = "SELECT name, surname, tc FROM UserRequests";

            stmt = connection.createStatement();
            rs1 = stmt.executeQuery(SQL);
            
            int cnt = 0;
            
            while(rs1.next()){

           	 ArrayList<String> user = new ArrayList<String>();
           	 for (int i = 1; i < 4; i++)
           		 user.add((rs1.getString(i)));
           	 
           	 newusers.add(user);
       
            }
            
            for(int i = 0; i < newusers.size(); i++)
           	 { 
           		 for(int j = 0; j < 3; j++){
           	 
           			 System.out.println(newusers.get(i).get(j));
           		 }
   	 		 	 cnt ++;	            	 		 	 
   	 		 	System.out.println("cnt: " + cnt);
   	 		 	System.out.println("\n");
           	 }
            
           
           } catch (ClassNotFoundException e1) {
               e1.printStackTrace();
           // Could not find the database driver
           } catch (SQLException e1) {
               e1.printStackTrace();
           // Could not connect to the database
           }
			finally {
					try {
           if (rs1 != null) {
               rs1.close();
           }

           if (stmt != null) {
               stmt.close();
           }

           connection.close();
           
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
			} 
		
		users = new String[newusers.size()][4];
		
		int i, j;
		for(i = 0; i < newusers.size(); i++){
			for(j = 0; j < 3; j++)
				users[i][j] = newusers.get(i).get(j);
				users[i][3] = "Onayla";
			}
		
		table = new JTable();
		table.setRowHeight(20);
		table.setModel(new DefaultTableModel(
				users,
				new String[] {
					"Ad", "Soyad", "TC", ""
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
				};
				@SuppressWarnings("unchecked")
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		try
		{
			ButtonEditor bted=new ButtonEditor(new JCheckBox());
			bted.setReq(this);
			table.getColumn("").setCellRenderer(new ButtonRenderer());
			table.getColumn("").setCellEditor(bted);
		}
		 catch (Exception e) 
		{
			 System.err.println("Got an exception in request ! "); 
	         System.err.println(e.getMessage()); 
		}
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("                                     New User Requests");
		lblNewLabel.setForeground(new Color(0, 0, 153));
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 432, 14);
		panel.add(lblNewLabel);
		
	}
	public void getIndexOfCell(int rowIdx, int colIdx)
	{
		if(users!=null)
		{
			System.out.println("rowIdx: " + rowIdx + "columnIdx: " + colIdx);
            
            Connection connection = null;
            java.sql.Statement stmt = null;
            ResultSet rs1 = null;
            PreparedStatement ps = null;
            
    		try {
    			
    			String tc = users[rowIdx][colIdx-1];
    			
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(url);
                
                String SQL1 = "SELECT name, surname, tc, email, username, password, isAdmin FROM UserRequests WHERE tc = ? ";

                ps = connection.prepareStatement(SQL1);
                ps.setString(1, tc);
	            ps.execute(); 
	            
	            rs1 = ps.getResultSet();
	            
	            //stmt = connection.createStatement();
	            //rs1 = stmt.executeQuery(SQL);

              	ArrayList<String> userr = new ArrayList<String>();
                
                while(rs1.next()){

               	 for (int i = 1; i < 8; i++)
               		 userr.add((rs1.getString(i)));
               	 
               	System.out.println("userr: ");
               	for(int i = 0; i < 7; i++)
               		System.out.println(userr.get(i));
                }
                
                PreparedStatement pstmt = null;
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            Connection conn = DriverManager.getConnection(url); 
	            String SQL2 = "INSERT INTO Userr (name, surname, tc, email, username, password, isAdmin) " + 
		                "VALUES (?,?,?,?,?,?,?)";
	             
	            pstmt = conn.prepareStatement(SQL2); 	// create a statement		
	            for(int i = 1; i < 8; i++) 
	            	pstmt.setString(i, userr.get(i-1)); 					// set input parameter 1
	            pstmt.execute(); 
	            
	            conn.close(); 
	            
	            PreparedStatement pstmt1 = null;
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            Connection conn1 = DriverManager.getConnection(url); 
	            String SQL3 = "DELETE FROM UserRequests WHERE tc = ?";
	             
	            pstmt1 = conn1.prepareStatement(SQL3); 	// create a statement	
	            
	            pstmt1.setString(1, tc);
	            pstmt1.execute(); 
	            
	            conn1.close(); 
	            
	            rq = new Request();
	            rq.setVisible(false);
	            rq.setVisible(true);
	            
               } catch (ClassNotFoundException e1) {
                   e1.printStackTrace();
               // Could not find the database driver
               } catch (SQLException e1) {
                   e1.printStackTrace();
               // Could not connect to the database
               }
    			finally {
    					try {
               if (rs1 != null) {
                   rs1.close();
               }

               if (stmt != null) {
                   stmt.close();
               }

               connection.close();
               
    					} catch (SQLException e2) {
    						e2.printStackTrace();
    					}
    			} 
		}
			
	}
	
}
