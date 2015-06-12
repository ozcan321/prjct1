import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EtchedBorder;

import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class EnterGraph extends JFrame{
	


	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tableRebound;
	Double[][] data = new Double[][] { { null, null }, { null, null },
			{ null, null }, { null, null }, { null, null }, { null, null },
			{ null, null }, { null, null }, { null, null } };
	String flag = "data";
	String[] columnNames = new String[] { "X deðerleri", "Y deðerleri" };
	private JTable tableCompression;
	
	public JButton btnVeritabannaAktar = new JButton("Veritaban\u0131na Aktar");
	
	JButton btnyki = new JButton("Yeni Kullan\u0131c\u0131 \u0130ste\u011Fi");
	
	 static ArrayList<Double> velocity = new ArrayList<Double>();
    
	
    String url = "jdbc:sqlserver://SATELLITEA30-PC;instanceName=SQLEXPRESS;databaseName=ValveCombination;user=sa;password=123"; 		// a JDBC url
    

     static ArrayList<ArrayList<Integer>> reb = new ArrayList<ArrayList<Integer>>();
	 static ArrayList<ArrayList<Integer>> comp = new ArrayList<ArrayList<Integer>>();
	 
	 static ArrayList<Integer> userR = new ArrayList<Integer>();                    //user rebound values
     static ArrayList<Integer> userC = new ArrayList<Integer>();                    //user comp. values
	
	 static int[] indexesR;
     static int[] indexesC;

    public static Graph g1 = new Graph();
    
    static EnterGraph frame = new EnterGraph();
	
	/**
	 * Launch the application.
	 */
    
	public static void main(String[] args) {
		
		
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame.setVisible(true);
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
	public EnterGraph() {
		
		userR.clear();
		userC.clear();
		reb.clear();
		comp.clear();
		velocity.clear();
		
		velocity.add(0.05);
	    velocity.add(0.1);
	    velocity.add(0.2);
	    velocity.add(0.3);
	    velocity.add(0.4);
	    velocity.add(0.55);
	    velocity.add(0.75);
	    velocity.add(0.95);
	    
		setTitle("En Yak\u0131n Grafi\u011Fi Ara\u015Ft\u0131r");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				EnterGraph.class.getResource("/images/frameIcon.JPG")));
		
		setBounds(100, 100, 614, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 45, 578, 417);
		contentPane.add(panel);
		panel.setLayout(null);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(252, 11, 1, 185);
		panel.add(verticalStrut);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel_1.setBounds(10, 11, 256, 313);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblXDeerleri = new JLabel("Velocity");
		lblXDeerleri.setBackground(Color.WHITE);
		lblXDeerleri.setBounds(64, 36, 92, 14);
		panel_1.add(lblXDeerleri);

		JLabel lblYDeerleri = new JLabel("Force");
		lblYDeerleri.setBounds(155, 36, 101, 14);
		panel_1.add(lblYDeerleri);

		JLabel lblRebound = new JLabel("Rebound");
		lblRebound.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		lblRebound.setHorizontalAlignment(SwingConstants.CENTER);
		lblRebound.setBounds(91, 11, 78, 14);
		panel_1.add(lblRebound);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel_2.setBounds(312, 11, 256, 313);
		panel.add(panel_2);
		panel_2.setLayout(null);

		tableCompression = new JTable();
		tableCompression.setRowHeight(20);
		tableCompression.setSurrendersFocusOnKeystroke(true);
		tableCompression.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCompression.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		tableCompression.setFillsViewportHeight(true);
		tableCompression.setColumnSelectionAllowed(true);
		tableCompression.setCellSelectionEnabled(true);
		tableCompression.setModel(new DefaultTableModel(
			new Object[][] {
				{velocity.get(0), null},
				{velocity.get(1), null},
				{velocity.get(2), null},
				{velocity.get(3), null},
				{velocity.get(4), null},
				{velocity.get(5), null},
				{velocity.get(6), null},
				{velocity.get(7), null},
			},
			new String[] {
				"Velocity", "Force"
			}
		) {
			Class[] columnTypes = new Class[] {
				Double.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableCompression.setBounds(37, 61, 182, 160);
		panel_2.add(tableCompression);

		JLabel lblVelocity = new JLabel("Velocity");
		lblVelocity.setBounds(53, 36, 92, 14);
		panel_2.add(lblVelocity);

		JLabel lblForce = new JLabel("Force");
		lblForce.setBounds(155, 36, 101, 14);
		panel_2.add(lblForce);

		JLabel lblstGrup = new JLabel("Compression");
		lblstGrup.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 11));
		lblstGrup.setLabelFor(tableCompression);
		lblstGrup.setHorizontalAlignment(SwingConstants.CENTER);
		lblstGrup.setBounds(85, 11, 86, 14);
		panel_2.add(lblstGrup);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 351, 558, 55);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnGrafikOlutur = new JButton("Grafik Olu\u015Ftur");
		
		
		
		btnGrafikOlutur.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent arg0) {
				
				frame.setVisible(false);
				
				
				reb.clear();
				comp.clear();
				
				    
				Connection connection = null;
	            Statement stmt = null;
	            ResultSet rs1 = null;
	            
		         
	            try {
	            
	             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	             connection = DriverManager.getConnection(url);
	             
	             
	             String  SQL = "SELECT F1, F2, F3, F4, F5, F6, F7, F8 FROM Rebound";

	             stmt = connection.createStatement();
	             rs1 = stmt.executeQuery(SQL);
	             
	             ResultSetMetaData rsmd1 = rs1.getMetaData();

	             int columnsNumber = rsmd1.getColumnCount();
	             
	            
	             System.out.println(columnsNumber+" \n");
	             
	             int cnt = 0;
	             
	             while(rs1.next()){

	            	 ArrayList<Integer> arr = new ArrayList<Integer>();
	            	 for (int i = 1; i < columnsNumber+1; i++)
	            		 arr.add(Integer.parseInt(rs1.getString(i)));
	            	 
	            	 reb.add(arr);
	        
	             }
	             
	             for(int i = 0; i < reb.size(); i++)
	            	 { 
	            		 for(int j = 0; j < 8; j++){
	            	 
	            			 System.out.println(reb.get(i).get(j));
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
	           
	            	//compression
	            
				try {
	            	
		            System.out.println("Comp a girdi");
		            
		            
		            
		             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		             connection = DriverManager.getConnection(url);
		             
		             
		             String  SQL = "SELECT F1, F2, F3, F4, F5, F6, F7, F8 FROM Compression";

		             stmt = connection.createStatement();
		             rs1 = stmt.executeQuery(SQL);
		             
		             ResultSetMetaData rsmd1 = rs1.getMetaData();

		             int columnsNumber = rsmd1.getColumnCount();
		             
		             
		             System.out.println(columnsNumber+" \n");
		             
		             int cnt = 0;
		             
		             while(rs1.next()){
		            	 int value;
		            	 
		            	 ArrayList<Integer> arr = new ArrayList<Integer>();
		            	 for (int i = 1; i < 9; i++){
		            		 if(rs1.getString(i) != null)
		            		 {
		            			 value = Integer.parseInt(rs1.getString(i));
			            		 arr.add(value);
		            		 }
		            		
		            	 }
		            	 
		            	 if(arr.size() > 0)
		            		 comp.add(arr);
		            	 else
		            		 System.out.println("arr.size() <= 0");
		        
		             }
		             
		             for(int i = 0; i < comp.size(); i++)
		            	 { 
		            		 for(int j = 0; j < 8; j++){
		            	 
		            			 System.out.println(comp.get(i).get(j));
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
		    
			
	        
	        for(int i = 0; i < tableRebound.getRowCount(); i++)						//userR is filled with tableRebound values
	        	userR.add((Integer) tableRebound.getModel().getValueAt(i, 1));
	        
	        System.out.print("---------------userR----------------\n");
	        
	        for (int i = 0; i < userR.size(); i++)
	        	System.out.print(userR.get(i) + " ");
	        
        	System.out.print("\n");
	        
        
	        for(int i = 0; i < tableCompression.getRowCount(); i++)						//userC is filled with tableRebound values
	        	userC.add((Integer) tableCompression.getModel().getValueAt(i, 1));
	        
	        System.out.print("---------------userC----------------\n");
	        
	        for (int i = 0; i < userC.size(); i++)
	        	System.out.print(userC.get(i) + " ");
	        
	        System.out.print("\n");
	        
	        for(int i = 0; i < tableCompression.getRowCount(); i++)						//userC is filled with tableCompression values
	        	tableCompression.getModel().getValueAt(i, 1);
	        
	     
	        ArrayList<Double> areaReb = new ArrayList<Double>();                    //rebound ile userR arasýndaki alan
	        ArrayList<Double> areaComp = new ArrayList<Double>();                   //compression ile userC arasýndaki alan
	        areaReb.clear();
	        areaComp.clear();
	        
	        System.out.println("reb.size(): " + reb.size());
	        
	        for(int i=0; i<reb.size(); i++){
	        	
	        	
	        	
	        	double area = 0;
	        	
	            for(int j = 0; j < velocity.size(); j++)
	            {
	            	if (j != 7)
	            	{	
	            		int a = Math.abs(userR.get(j) - reb.get(i).get(j));             //üst tavan
	            		int b = Math.abs(userR.get(j+1) - reb.get(i).get(j+1));         //alt taban
	            		double h = Math.abs(velocity.get(j) -  velocity.get(j+1));      //yükseklik
	            
	            		if( (reb.get(i).get(j)-userR.get(j)<0 && reb.get(i).get(j+1)-userR.get(j+1)<0) || (reb.get(i).get(j)-userR.get(j)>0 && reb.get(i).get(j+1)-userR.get(j+1)>0 )){
	                       
	            			double area2 = (a+b)* (h/2);                              //kesiþmiyorsa alan hesabý
	            			area = area + area2;
	            			//areaReb.add(area);  
	            		}
	            		else {
	                    
	            			double area2 = ((a*a + b*b)/2*(a+b))*h ;                  //kesiþiyorsa alan hesabý
	            			area = area + area2;
	            			//areaReb.add(area);
	            		}           
	            	}
	            	
	           }
	           
	            areaReb.add(area);
	        }
	       
	        System.out.println("comp.size(): " + comp.size());
	        
	        for(int i=0; i < comp.size(); i++){
	        	
	        	double area = 0;
	        	
	        	for(int j = 0; j < velocity.size(); j++)
	            {
	            	if (j != 7)
	            	{	
	            		int a = Math.abs(userC.get(j) - comp.get(i).get(j));             //üst tavan
	            		int b = Math.abs(userC.get(j+1) - comp.get(i).get(j+1));         //alt taban
	            		double h = Math.abs(velocity.get(j) -  velocity.get(j+1));      //yükseklik
	            
	            		if( (comp.get(i).get(j)-userC.get(j)<0 && comp.get(i).get(j+1)-userC.get(j+1)<0) || (comp.get(i).get(j)-userC.get(j)>0 && comp.get(i).get(j+1)-userC.get(j+1)>0 )){
	                       
	            			double area2 = (a+b)* (h/2);                              //kesiþmiyorsa alan hesabý
	            			area = area + area2;
	            			//areaReb.add(area);  
	            		}
	            		else {
	                    
	            			double area2 = ((a*a + b*b)/2*(a+b))*h ;                  //kesiþiyorsa alan hesabý
	            			area = area + area2;
	            			//areaReb.add(area);
	            		}           
	            	}
	            	
	           }
	        	areaComp.add(area);
	        }
	        

	        indexesR = new int[reb.size()];
	        indexesC = new int[comp.size()];
	        
	        double[] sortAreaR = new double[areaReb.size()];
	        
	        for (int i = 0; i < areaReb.size(); i++)
	            sortAreaR[i] = areaReb.get(i);
	        
	        Arrays.sort(sortAreaR);
	        
	        for(int i = 0; i < sortAreaR.length; i++)
	        	for(int j = 0; j < areaReb.size(); j++)
	        	{
	        		if(sortAreaR[i] == areaReb.get(j))
	        			indexesR[i] = j;
	           }

	        double[] sortAreaC = new double[areaComp.size()];
	        
	        for (int i = 0; i < areaComp.size(); i++)
	            sortAreaC[i] = areaComp.get(i);
	        
	        Arrays.sort(sortAreaC);
	        
	        
	        for(int i = 0; i < sortAreaC.length; i++)
	        	for(int j = 0; j < areaComp.size(); j++)
	        	{
	        		if(sortAreaC[i] == areaComp.get(j))
	        			indexesC[i] = j;
	           }
	        
	        int flag = 3;
	        int c2 = 1;
	        
	        for(int i = 0; i < flag; i++){
	        	
	        	
	        	if( i != 0  &&  indexesR[i] != indexesR[i-1] ){
	        		
	        		System.out.println("\nEn yakýn " + (c2) + ". rebound: ");
	        		
	        		for(int j = 0; j < 8; j++)
	        			System.out.print(reb.get(indexesR[i]).get(j) + " ");
	        		
	        		System.out.print("Index: " + (indexesR[i]+1) + "  Area: " + areaReb.get(indexesR[i]));
	        		c2++;
	        	}
	        	else
	        		flag++;
	        	
	        	
			}
	        
	        System.out.println("\n");
	        
	        int flag2 = 3;
	        int c = 1;
	        
	        for(int i = 0; i < flag2; i++){
	        	
	        	
	        	if( i != 0  &&  indexesC[i] != indexesC[i-1] )
	        	{
	        		System.out.println("\nEn yakýn " + (c) + ". compression: ");
	        		for(int j = 0; j < 8; j++)
	        			System.out.print(comp.get(indexesC[i]).get(j) + " ");
	        		
	        		System.out.print("Index: " + (indexesC[i]+1) + "  Area: " + areaComp.get(indexesC[i]));
	        		c++;
	        	}
	        	else
	        		flag2++;
	        	
	        	
			}
	        
	        g1 = new Graph();
	       
	        final XYDataset dataset = createDataset(reb, userR, indexesR);
	        final JFreeChart chart = createChart(dataset);
	        final ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new java.awt.Dimension(797, 548));
	        setContentPane(chartPanel);
	        
	        g1.panelReb.add(chartPanel);
	        
	        final XYDataset dataset2 = createDataset(comp, userC, indexesC);
	        final JFreeChart chart2 = createChart(dataset2);
	        final ChartPanel chartPanel2 = new ChartPanel(chart2);
	        chartPanel2.setPreferredSize(new java.awt.Dimension(797, 548));
	        setContentPane(chartPanel2);
	        
	        g1.panelComp.add(chartPanel2);
	        
	        g1.setVisible(true);
	        
	        JTable tableR = new JTable(); 
			tableR.setFont(new Font("Georgia", Font.PLAIN, 12));
			tableR.setFillsViewportHeight(true);
			tableR.setColumnSelectionAllowed(true);
			tableR.setCellSelectionEnabled(true);
			//table.setEnabled(false);
			tableR.setModel(new DefaultTableModel(
				new Object[][] {
					{userR.get(0), reb.get(indexesR[0]).get(0), reb.get(indexesR[1]).get(0), reb.get(indexesR[2]).get(0)},
					{userR.get(1), reb.get(indexesR[0]).get(1), reb.get(indexesR[1]).get(1), reb.get(indexesR[2]).get(1)},
					{userR.get(2), reb.get(indexesR[0]).get(2), reb.get(indexesR[1]).get(2), reb.get(indexesR[2]).get(2)},
					{userR.get(3), reb.get(indexesR[0]).get(3), reb.get(indexesR[1]).get(3), reb.get(indexesR[2]).get(3)},
					{userR.get(4), reb.get(indexesR[0]).get(4), reb.get(indexesR[1]).get(4), reb.get(indexesR[2]).get(4)},
					{userR.get(5), reb.get(indexesR[0]).get(5), reb.get(indexesR[1]).get(5), reb.get(indexesR[2]).get(5)},
					{userR.get(6), reb.get(indexesR[0]).get(6), reb.get(indexesR[1]).get(6), reb.get(indexesR[2]).get(6)},
					{userR.get(7), reb.get(indexesR[0]).get(7), reb.get(indexesR[1]).get(7), reb.get(indexesR[2]).get(7)},
				},
				new String[] {
					"Requested", "First", "Second", "Third"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			tableR.setRowHeight(28);
			g1.scrollPane.setViewportView(tableR);
			
			

	    }

	    /**
	     * Creates a sample dataset.
	     * 
	     * @return a sample dataset.
	     */
	    	
	    private XYDataset createDataset(ArrayList<ArrayList<Integer>> valve, ArrayList<Integer> user, int[] indexes) {
	        
	        final XYSeries series1 = new XYSeries("First Nearest");
	        for(int i = 0; i < velocity.size(); i++)
	        	series1.add(velocity.get(i), valve.get(indexes[0]).get(i));
	        
	        final XYSeries series2 = new XYSeries("Second Nearest");
	        for(int i = 0; i < velocity.size(); i++)
	        	series2.add(velocity.get(i), valve.get(indexes[1]).get(i));
	        
	        final XYSeries series3 = new XYSeries("Third Nearest");
	        for(int i = 0; i < velocity.size(); i++)
	        	series3.add(velocity.get(i), valve.get(indexes[2]).get(i));
	        
	        final XYSeries series4 = new XYSeries("Requested Graph");
	        for(int i = 0; i < velocity.size(); i++)
	        	series4.add(velocity.get(i), user.get(i));
	        

	        final XYSeriesCollection dataset = new XYSeriesCollection();
	        dataset.addSeries(series1);
	        dataset.addSeries(series2);
	        dataset.addSeries(series3);
	        dataset.addSeries(series4);
	                
	        return dataset;
	        
	    }

	    /**
	     * Creates a chart.
	     * 
	     * @param dataset  the data for the chart.
	     * 
	     * @return a chart.
	     */

	    private JFreeChart createChart(final XYDataset dataset) {
	        
	        // create the chart...
	        final JFreeChart chart = ChartFactory.createXYLineChart(
	            "Nearest 3 graph with requested",      			// chart title
	            "Velocity",                  	    // x axis label
	            "Force",                     		// y axis label
	            dataset,                  			// data
	            PlotOrientation.VERTICAL,
	            true,                     			// include legend
	            true,                     			// tooltips
	            false                     			// urls
	        );

	        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
	        chart.setBackgroundPaint(Color.white);

	        final XYPlot plot = chart.getXYPlot();
	        plot.setBackgroundPaint(Color.lightGray);
//	        plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);
	        
	        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	        renderer.setSeriesLinesVisible(4, false);
	        renderer.setSeriesShapesVisible(4, false);
	        plot.setRenderer(renderer);

	        // change the auto tick unit selection to integer units only...
	        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        // OPTIONAL CUSTOMISATION COMPLETED.
	                
	        return chart;
	        
	    }
	    
	    
		});
		
			
		
		btnGrafikOlutur.setBounds(370, 11, 119, 33);
		panel_3.add(btnGrafikOlutur);
		btnVeritabannaAktar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EnterGraph eg = new EnterGraph(); 
				
				for(int i = 0; i < tableRebound.getRowCount(); i++)						//userR is filled with tableRebound values
		        	userR.add((Integer) tableRebound.getModel().getValueAt(i, 1));
		        for(int i = 0; i < tableCompression.getRowCount(); i++)						//userC is filled with tableRebound values
		        	userC.add((Integer) tableCompression.getModel().getValueAt(i, 1));
				
				
				try { 
					
					int fr1 = userR.get(0);
					int fr2 = userR.get(1);
					int fr3 = userR.get(2);
					int fr4 = userR.get(3);
					int fr5 = userR.get(4);
					int fr6 = userR.get(5);
					int fr7 = userR.get(6);
					int fr8 = userR.get(7);
					
					int fc1 = userC.get(0);
					int fc2 = userC.get(1);
					int fc3 = userC.get(2);
					int fc4 = userC.get(3);
					int fc5 = userC.get(4);
					int fc6 = userC.get(5);
					int fc7 = userC.get(6);
					int fc8 = userC.get(7);
					
					PreparedStatement pstmt = null;
					
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            Connection conn = DriverManager.getConnection(url); 
		            String SQL = "INSERT INTO Rebound (F1, F2, F3, F4, F5, F6, F7, F8) " + 
			                "VALUES (?,?,?,?,?,?,?,?)";
		            
		            String SQL2 = "INSERT INTO Compression (F1, F2, F3, F4, F5, F6, F7, F8) " + 
			                "VALUES (?,?,?,?,?,?,?,?)";
		            
		            
		            pstmt = conn.prepareStatement(SQL); 	// create a statement		
		             pstmt.setInt(1, fr1); 					// set input parameter 1
		             pstmt.setInt(2, fr2); 					// set input parameter 2
		             pstmt.setInt(3, fr3); 
		             pstmt.setInt(4, fr4); 
		             pstmt.setInt(5, fr5); 
		             pstmt.setInt(6, fr6); 				// set input parameter 3						
		             pstmt.setInt(7, fr7);
		             pstmt.setInt(8, fr8);
		             pstmt.executeUpdate(); 
		             
		             
		             pstmt = conn.prepareStatement(SQL2); 	// create a statement		
		             pstmt.setInt(1, fc1); 					// set input parameter 1
		             pstmt.setInt(2, fc2); 					// set input parameter 2
		             pstmt.setInt(3, fc3); 
		             pstmt.setInt(4, fc4); 
		             pstmt.setInt(5, fc5); 
		             pstmt.setInt(6, fc6); 				// set input parameter 3						
		             pstmt.setInt(7, fc7);
		             pstmt.setInt(8, fc8);
		             pstmt.executeUpdate(); 
			            
		             JOptionPane.showMessageDialog(null,
								"Veritabanýna aktarýldý." );
		            
		            conn.close(); 
		            
			         
		            frame.setVisible(false); 
			         
			           frame = new EnterGraph();
					   frame.setVisible(true);
					   
					   if(FP.userType.equals("normal")){
						   frame.btnVeritabannaAktar.setVisible(false);
						   frame.btnyki.setVisible(false);
					   }
					   else
					   {
						   frame.btnVeritabannaAktar.setVisible(true);
						   frame.btnyki.setVisible(true);
					   }
					   	   
		            
		            
		        } catch (Exception e) { 
		            System.err.println("Got an exception! "); 
		            System.err.println(e.getMessage()); 
		            
		            JOptionPane.showMessageDialog(null,
							"Veritabanýna aktarýlamadý !" );
		            
		           frame.setVisible(false); 
		         
		           frame = new EnterGraph();
				   frame.setVisible(true);
		            
		        } 
				
			}
		});
		
		btnVeritabannaAktar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVeritabannaAktar.setBounds(60, 11, 144, 33);
		panel_3.add(btnVeritabannaAktar);
				
						tableRebound = new JTable();
						tableRebound.setRowHeight(20);
						tableRebound.setBounds(37, 61, 182, 160);
						panel_1.add(tableRebound);
						lblXDeerleri.setLabelFor(tableRebound);
						tableRebound.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
								null));
						tableRebound.setSurrendersFocusOnKeystroke(true);
						tableRebound.setFillsViewportHeight(true);
						tableRebound.setColumnSelectionAllowed(true);
						tableRebound.setCellSelectionEnabled(true);
						tableRebound.setToolTipText("");
						
						tableRebound.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						tableRebound.setModel(new DefaultTableModel(
							new Object[][] {
								{velocity.get(0), null},
								{velocity.get(1), null},
								{velocity.get(2), null},
								{velocity.get(3), null},
								{velocity.get(4), null},
								{velocity.get(5), null},
								{velocity.get(6), null},
								{velocity.get(7), null},
							},
							new String[] {
								"Velocity", "Force"
							}
						) {
							Class[] columnTypes = new Class[] {
								Double.class, Integer.class
							};
							
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
						lblRebound.setLabelFor(tableRebound);
		btnyki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Request r = new Request();
				r.setVisible(true);
			}
		});
		
		btnyki.setVisible(false);
		btnyki.setBounds(442, 11, 146, 23);
		contentPane.add(btnyki);

		}
	}
