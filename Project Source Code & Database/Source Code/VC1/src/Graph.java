import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Graph extends JFrame{

	private JPanel contentPane;
	JScrollPane scrollPane;
	static Graph g = new Graph();

	/**
	 * Launch the application.
	 */
	JPanel panel_1 = new JPanel();
	JPanel panelReb = new JPanel();
	JPanel panelComp = new JPanel();
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	JButton btnFirst = new JButton("First Nearest Graph");
	JButton btnSecond = new JButton("Second Nearest Graph");
	JButton btnThird = new JButton("Third Nearest Graph");
	private final JPanel panel_3 = new JPanel();
	private JTable table;
	
	int flagR = 0;
	int flagC = 0;
	
	JTable tableR, table1, table2, table3;
	
	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graph frame = new Graph();
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
	public Graph() {
		
		setBounds(100, 100, 1246, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 1220, 598);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		panel_1.setBounds(10, 11, 876, 576);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		tabbedPane.setBounds(10, 11, 856, 554);
		panel_1.add(tabbedPane);
		
		
		panelReb.setToolTipText("");
		tabbedPane.addTab("Rebound", null, panelReb, null);
		tabbedPane.setEnabledAt(0, true);
		panelReb.setLayout(new BorderLayout(0, 0));
		
		tabbedPane.addTab("Compression", null, panelComp, null);
		tabbedPane.setEnabledAt(1, true);
		panelComp.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(896, 11, 314, 576);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		
		btnFirst.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnFirst.setBounds(76, 11, 167, 48);
		panel_2.add(btnFirst);
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				flagR = 1;
				flagC = 1;
				
				//tableR = new JTable(); 
				table.setFont(new Font("Georgia", Font.PLAIN, 12));
				table.setFillsViewportHeight(true);
				table.setColumnSelectionAllowed(true);
				table.setCellSelectionEnabled(true);
				//table.setEnabled(false);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{EnterGraph.userR.get(0), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(0)},
						{EnterGraph.userR.get(1), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(1)},
						{EnterGraph.userR.get(2), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(2)},
						{EnterGraph.userR.get(3), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(3)},
						{EnterGraph.userR.get(4), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(4)},
						{EnterGraph.userR.get(5), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(5)},
						{EnterGraph.userR.get(6), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(6)},
						{EnterGraph.userR.get(7), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(7)},
					},
					new String[] {
						"Requested", "First", "Second", "Third"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.setRowHeight(28);
				
				g.btnFirst.disable();
				g.btnSecond.enable();
				g.btnThird.enable();
			
				g.setVisible(false);
				g = new Graph();
				
			    final XYDataset dataset = createDataset(EnterGraph.userR, EnterGraph.reb, EnterGraph.indexesR);
			    final JFreeChart chart = createChart(dataset);
			    final ChartPanel chartPanel = new ChartPanel(chart);
			    chartPanel.setPreferredSize(new java.awt.Dimension(797, 548));
			    setContentPane(chartPanel);
			    
			    g.panelReb.add(chartPanel);
			  
			    final XYDataset dataset2 = createDataset(EnterGraph.userC, EnterGraph.comp, EnterGraph.indexesC);
			    final JFreeChart chart2 = createChart(dataset2);
			    final ChartPanel chartPanel2 = new ChartPanel(chart2);
			    chartPanel2.setPreferredSize(new java.awt.Dimension(797, 548));
			    setContentPane(chartPanel2);
			    
			    g.panelComp.add(chartPanel2);
			    g.setVisible(true);
			    

				g.scrollPane.setViewportView(table);
			    
			    	
			   
			
			}


			/**
			 * Creates a sample dataset.
			 * 
			 * @return a sample dataset.
			 */
				
			private XYDataset createDataset(ArrayList<Integer> user, ArrayList<ArrayList<Integer>> valve, int[] indexes) {
			    
				
			    final XYSeries series1 = new XYSeries("First Nearest");
			    for(int i = 0; i < EnterGraph.velocity.size(); i++)
			    	series1.add(EnterGraph.velocity.get(i), valve.get(indexes[0]).get(i));
			    
			    final XYSeries series2 = new XYSeries("Requested Graph");
			    for(int i = 0; i < EnterGraph.velocity.size(); i++)
			    	series2.add(EnterGraph.velocity.get(i), user.get(i));
			    
			    final XYSeriesCollection dataset = new XYSeriesCollection();
			    dataset.addSeries(series1);
			    dataset.addSeries(series2);

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
			        "First Nearest with Requested",      			// chart title
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
//			    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
			    plot.setDomainGridlinePaint(Color.white);
			    plot.setRangeGridlinePaint(Color.white);
			    
			    final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			    renderer.setSeriesLinesVisible(2, false);
			    renderer.setSeriesShapesVisible(2, false);
			    plot.setRenderer(renderer);

			    // change the auto tick unit selection to integer units only...
			    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			    // OPTIONAL CUSTOMISATION COMPLETED.
			            
			    return chart;
			    
			}
		});
		btnFirst.setIcon(null);
		
		//Nearest Second Graph Button Action Listener
		btnSecond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				flagR = 2;
				flagC = 2;
				
				table = new JTable(); 
				table.setFont(new Font("Georgia", Font.PLAIN, 12));
				table.setFillsViewportHeight(true);
				table.setColumnSelectionAllowed(true);
				table.setCellSelectionEnabled(true);
				//table.setEnabled(false);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{EnterGraph.userR.get(0), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(0)},
						{EnterGraph.userR.get(1), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(1)},
						{EnterGraph.userR.get(2), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(2)},
						{EnterGraph.userR.get(3), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(3)},
						{EnterGraph.userR.get(4), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(4)},
						{EnterGraph.userR.get(5), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(5)},
						{EnterGraph.userR.get(6), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(6)},
						{EnterGraph.userR.get(7), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(7)},
					},
					new String[] {
						"Requested", "First", "Second", "Third"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.setRowHeight(28);
				
				
				g.btnFirst.enable();
				g.btnSecond.disable();
				g.btnThird.enable();
				
				g.setVisible(false);
				g = new Graph();
				
			    
			    final XYDataset dataset = createDataset(EnterGraph.userR, EnterGraph.reb, EnterGraph.indexesR);
			    final JFreeChart chart = createChart(dataset);
			    final ChartPanel chartPanel = new ChartPanel(chart);
			    chartPanel.setPreferredSize(new java.awt.Dimension(797, 548));
			    setContentPane(chartPanel);
			    
			    g.panelReb.add(chartPanel);
			    
			    final XYDataset dataset2 = createDataset(EnterGraph.userC, EnterGraph.comp, EnterGraph.indexesC);
			    final JFreeChart chart2 = createChart(dataset2);
			    final ChartPanel chartPanel2 = new ChartPanel(chart2);
			    chartPanel2.setPreferredSize(new java.awt.Dimension(797, 548));
			    setContentPane(chartPanel2);
			    
			    g.panelComp.add(chartPanel2);

				g.scrollPane.setViewportView(table);
			    
			    g.setVisible(true);
			   
			}

			/**
			 * Creates a sample dataset.
			 * 
			 * @return a sample dataset.
			 */
				
			private XYDataset createDataset(ArrayList<Integer> user, ArrayList<ArrayList<Integer>> valve, int[] indexes) {
			    
				
			    final XYSeries series1 = new XYSeries("Second Nearest");
			    for(int i = 0; i < EnterGraph.velocity.size(); i++)
			    	series1.add(EnterGraph.velocity.get(i), valve.get(indexes[1]).get(i));
			    
			    final XYSeries series2 = new XYSeries("Requested Graph");
			    for(int i = 0; i < EnterGraph.velocity.size(); i++)
			    	series2.add(EnterGraph.velocity.get(i), user.get(i));
			    
			    final XYSeriesCollection dataset = new XYSeriesCollection();
			    dataset.addSeries(series1);
			    dataset.addSeries(series2);

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
			        "Second Nearest with Requested",      			// chart title
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
//			    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
			    plot.setDomainGridlinePaint(Color.white);
			    plot.setRangeGridlinePaint(Color.white);
			    
			    final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			    renderer.setSeriesLinesVisible(2, false);
			    renderer.setSeriesShapesVisible(2, false);
			    plot.setRenderer(renderer);

			    // change the auto tick unit selection to integer units only...
			    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			    // OPTIONAL CUSTOMISATION COMPLETED.
			            
			    return chart;
			    
			}
			
		});
		btnSecond.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnSecond.setBounds(76, 70, 167, 48);
		panel_2.add(btnSecond);
		
		
		btnThird.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				flagR = 3;
				flagC = 3;
				
				table = new JTable(); 
				table.setFont(new Font("Georgia", Font.PLAIN, 12));
				table.setFillsViewportHeight(true);
				table.setColumnSelectionAllowed(true);
				table.setCellSelectionEnabled(true);
				//table.setEnabled(false);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{EnterGraph.userR.get(0), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(0)},
						{EnterGraph.userR.get(1), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(1)},
						{EnterGraph.userR.get(2), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(2)},
						{EnterGraph.userR.get(3), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(3)},
						{EnterGraph.userR.get(4), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(4)},
						{EnterGraph.userR.get(5), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(5)},
						{EnterGraph.userR.get(6), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(6)},
						{EnterGraph.userR.get(7), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(7)},
					},
					new String[] {
						"Requested", "First", "Second", "Third"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.setRowHeight(28);
				
				
				g.setVisible(false);
				g = new Graph();
			    
			    final XYDataset dataset = createDataset(EnterGraph.userR, EnterGraph.reb, EnterGraph.indexesR);
			    final JFreeChart chart = createChart(dataset);
			    final ChartPanel chartPanel = new ChartPanel(chart);
			    chartPanel.setPreferredSize(new java.awt.Dimension(797, 548));
			    setContentPane(chartPanel);
			    
			 
			    g.panelReb.add(chartPanel);
			    
			    final XYDataset dataset2 = createDataset(EnterGraph.userC, EnterGraph.comp, EnterGraph.indexesC);
			    final JFreeChart chart2 = createChart(dataset2);
			    final ChartPanel chartPanel2 = new ChartPanel(chart2);
			    chartPanel2.setPreferredSize(new java.awt.Dimension(797, 548));
			    setContentPane(chartPanel2);

			    g.panelComp.add(chartPanel2);

			    g.setVisible(true);
			    

				g.scrollPane.setViewportView(table);
			    
			    g.btnFirst.enable();
				g.btnSecond.enable();
				g.btnThird.disable();
				
			}

			/**
			 * Creates a sample dataset.
			 * 
			 * @return a sample dataset.
			 */
				
			private XYDataset createDataset(ArrayList<Integer> user, ArrayList<ArrayList<Integer>> valve, int[] indexes) {
			    
				
			    final XYSeries series1 = new XYSeries("Third Nearest");
			    for(int i = 0; i < EnterGraph.velocity.size(); i++)
			    	series1.add(EnterGraph.velocity.get(i), valve.get(indexes[2]).get(i));
			    
			    final XYSeries series2 = new XYSeries("Requested Graph");
			    for(int i = 0; i < EnterGraph.velocity.size(); i++)
			    	series2.add(EnterGraph.velocity.get(i), user.get(i));
			    
			    final XYSeriesCollection dataset = new XYSeriesCollection();
			    dataset.addSeries(series1);
			    dataset.addSeries(series2);

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
			        "Third Nearest with Requested",      			// chart title
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
//			    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
			    plot.setDomainGridlinePaint(Color.white);
			    plot.setRangeGridlinePaint(Color.white);
			    
			    final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			    renderer.setSeriesLinesVisible(2, false);
			    renderer.setSeriesShapesVisible(2, false);
			    plot.setRenderer(renderer);

			    // change the auto tick unit selection to integer units only...
			    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			    // OPTIONAL CUSTOMISATION COMPLETED.
			            
			    return chart;
			    
			}
			
		});
		btnThird.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnThird.setBounds(76, 129, 167, 48);
		panel_2.add(btnThird);
		panel_3.setBounds(0, 244, 314, 332);
		
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 294, 270);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Georgia", Font.PLAIN, 12));
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Requested", "First", "Second", "Third"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setRowHeight(28);
		scrollPane.setViewportView(table);
		
		JButton btnReboundValues = new JButton("Rebound Values");
		btnReboundValues.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReboundValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				tableR = new JTable(); 
				tableR.setFont(new Font("Georgia", Font.PLAIN, 12));
				tableR.setFillsViewportHeight(true);
				tableR.setColumnSelectionAllowed(true);
				tableR.setCellSelectionEnabled(true);
				//table.setEnabled(false);
				tableR.setModel(new DefaultTableModel(
					new Object[][] {
						{EnterGraph.userR.get(0), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(0)},
						{EnterGraph.userR.get(1), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(1)},
						{EnterGraph.userR.get(2), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(2)},
						{EnterGraph.userR.get(3), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(3)},
						{EnterGraph.userR.get(4), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(4)},
						{EnterGraph.userR.get(5), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(5)},
						{EnterGraph.userR.get(6), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(6)},
						{EnterGraph.userR.get(7), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(7)},
					},
					new String[] {
							"Requested", "First", "Second", "Third"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				tableR.setRowHeight(28);
				g.scrollPane.setViewportView(tableR);
				if (! g.isVisible())
					EnterGraph.g1.scrollPane.setViewportView(tableR);
				
			}});
		btnReboundValues.setBounds(10, 11, 142, 23);
		panel_3.add(btnReboundValues);
		
		JButton btnCompressionValues = new JButton("Compression Values");
		btnCompressionValues.setToolTipText("");
		btnCompressionValues.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCompressionValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				tableR = new JTable(); 
				tableR.setFont(new Font("Georgia", Font.PLAIN, 12));
				tableR.setFillsViewportHeight(true);
				tableR.setColumnSelectionAllowed(true);
				tableR.setCellSelectionEnabled(true);
				//table.setEnabled(false);
				tableR.setModel(new DefaultTableModel(
					new Object[][] {
						{EnterGraph.userC.get(0), EnterGraph.comp.get(EnterGraph.indexesR[0]).get(0), EnterGraph.comp.get(EnterGraph.indexesR[1]).get(0), EnterGraph.comp.get(EnterGraph.indexesR[2]).get(0)},
						{EnterGraph.userC.get(1), EnterGraph.comp.get(EnterGraph.indexesR[0]).get(1), EnterGraph.comp.get(EnterGraph.indexesR[1]).get(1), EnterGraph.comp.get(EnterGraph.indexesR[2]).get(1)},
						{EnterGraph.userC.get(2), EnterGraph.comp.get(EnterGraph.indexesR[0]).get(2), EnterGraph.comp.get(EnterGraph.indexesR[1]).get(2), EnterGraph.comp.get(EnterGraph.indexesR[2]).get(2)},
						{EnterGraph.userC.get(3), EnterGraph.comp.get(EnterGraph.indexesR[0]).get(3), EnterGraph.comp.get(EnterGraph.indexesR[1]).get(3), EnterGraph.comp.get(EnterGraph.indexesR[2]).get(3)},
						{EnterGraph.userC.get(4), EnterGraph.comp.get(EnterGraph.indexesR[0]).get(4), EnterGraph.comp.get(EnterGraph.indexesR[1]).get(4), EnterGraph.comp.get(EnterGraph.indexesR[2]).get(4)},
						{EnterGraph.userC.get(5), EnterGraph.comp.get(EnterGraph.indexesR[0]).get(5), EnterGraph.comp.get(EnterGraph.indexesR[1]).get(5), EnterGraph.comp.get(EnterGraph.indexesR[2]).get(5)},
						{EnterGraph.userC.get(6), EnterGraph.comp.get(EnterGraph.indexesR[0]).get(6), EnterGraph.comp.get(EnterGraph.indexesR[1]).get(6), EnterGraph.comp.get(EnterGraph.indexesR[2]).get(6)},
						{EnterGraph.userC.get(7), EnterGraph.comp.get(EnterGraph.indexesR[0]).get(7), EnterGraph.comp.get(EnterGraph.indexesR[1]).get(7), EnterGraph.comp.get(EnterGraph.indexesR[2]).get(7)},
					},
					new String[] {
						"Requested", "First", "Second", "Third"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				tableR.setRowHeight(28);
				g.scrollPane.setViewportView(tableR);
				if(! g.isVisible())
					EnterGraph.g1.scrollPane.setViewportView(tableR);
			}
		});
		btnCompressionValues.setBounds(162, 11, 142, 23);
		panel_3.add(btnCompressionValues);
		
		JButton btnNearestGraph = new JButton("All Together");
		btnNearestGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table = new JTable(); 
				table.setFont(new Font("Georgia", Font.PLAIN, 12));
				table.setFillsViewportHeight(true);
				table.setColumnSelectionAllowed(true);
				table.setCellSelectionEnabled(true);
				//table.setEnabled(false);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{EnterGraph.userR.get(0), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(0), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(0)},
						{EnterGraph.userR.get(1), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(1), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(1)},
						{EnterGraph.userR.get(2), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(2), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(2)},
						{EnterGraph.userR.get(3), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(3), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(3)},
						{EnterGraph.userR.get(4), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(4), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(4)},
						{EnterGraph.userR.get(5), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(5), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(5)},
						{EnterGraph.userR.get(6), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(6), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(6)},
						{EnterGraph.userR.get(7), EnterGraph.reb.get(EnterGraph.indexesR[0]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[1]).get(7), EnterGraph.reb.get(EnterGraph.indexesR[2]).get(7)},
					},
					new String[] {
						"Requested", "First", "Second", "Third"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.setRowHeight(28);
				
				g.setVisible(false);
				g = new Graph();
				
				final XYDataset dataset = createDataset(EnterGraph.reb, EnterGraph.userR, EnterGraph.indexesR);
		        final JFreeChart chart = createChart(dataset);
		        final ChartPanel chartPanel = new ChartPanel(chart);
		        chartPanel.setPreferredSize(new java.awt.Dimension(797, 548));
		        setContentPane(chartPanel);
		        
		        g.panelReb.add(chartPanel);
		        
		        final XYDataset dataset2 = createDataset(EnterGraph.comp, EnterGraph.userC, EnterGraph.indexesC);
		        final JFreeChart chart2 = createChart(dataset2);
		        final ChartPanel chartPanel2 = new ChartPanel(chart2);
		        chartPanel2.setPreferredSize(new java.awt.Dimension(797, 548));
		        setContentPane(chartPanel2);
		        
		        g.panelComp.add(chartPanel2);
		        

		        
				g.scrollPane.setViewportView(table);
		        g.setVisible(true);
				
			}
			private XYDataset createDataset(ArrayList<ArrayList<Integer>> valve, ArrayList<Integer> user, int[] indexes) {
		        
		        final XYSeries series1 = new XYSeries("First Nearest");
		        for(int i = 0; i < EnterGraph.velocity.size(); i++)
		        	series1.add(EnterGraph.velocity.get(i), valve.get(indexes[0]).get(i));
		        
		        final XYSeries series2 = new XYSeries("Second Nearest");
		        for(int i = 0; i < EnterGraph.velocity.size(); i++)
		        	series2.add(EnterGraph.velocity.get(i), valve.get(indexes[1]).get(i));
		        
		        final XYSeries series3 = new XYSeries("Third Nearest");
		        for(int i = 0; i < EnterGraph.velocity.size(); i++)
		        	series3.add(EnterGraph.velocity.get(i), valve.get(indexes[2]).get(i));
		        
		        final XYSeries series4 = new XYSeries("Requested Graph");
		        for(int i = 0; i < EnterGraph.velocity.size(); i++)
		        	series4.add( EnterGraph.velocity.get(i), user.get(i));
		        

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
//		        plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
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
		btnNearestGraph.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnNearestGraph.setBounds(76, 188, 167, 48);
		panel_2.add(btnNearestGraph);
		
		JButton btnEnterGraph = new JButton("Enter Graph");
		btnEnterGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EnterGraph eg = new EnterGraph();
				if (FP.userType.equals("admin"))
				{
					eg.btnyki.setVisible(true);
					eg.btnVeritabannaAktar.setVisible(true);
				}
				else
				{
					eg.btnyki.setVisible(false);
					eg.btnVeritabannaAktar.setVisible(false);
				}
				eg.setVisible(true);
				
				g.setVisible(false);
				eg.g1.setVisible(false);
				
			}
		});
		btnEnterGraph.setBounds(445, 609, 118, 23);
		contentPane.add(btnEnterGraph);
		
	}
}
