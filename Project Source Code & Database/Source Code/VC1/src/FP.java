import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

import javax.swing.JOptionPane;


public class FP extends JFrame {

	EnterGraph eg = new EnterGraph();
	
	private JPanel contentPane;
	private JTextField userField;
	private JTextField adminPasswordField;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField tcField;
	private JTextField surnameField;
	private JPasswordField passwordField;
	private JTextField adminUserField;
	private JTextField usernameField;
	static String userType;
	
	 JLabel lblValidationGiris = new JLabel();
	 JLabel lblValidationAdmin = new JLabel();
	 private JTextField passField;
	 JLabel lblusername = new JLabel("    !");
	 JLabel lblpassword = new JLabel("    !");
	 JLabel lblNewLabel_1 = new JLabel("    !");
     JLabel lblNewLabel_2 = new JLabel("    !");
     private JTextField pass2Field;
     JLabel lblyk = new JLabel("Validation Label Text");
     
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		FP frame = new FP();

		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */

	public FP() {
		setTitle("Valf Kombinasyon Sistemi");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				FP.class.getResource("/images/frameIcon.JPG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 475);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.inactiveCaption);
		tabbedPane.setBounds(10, 121, 277, 307);
		contentPane.add(tabbedPane);

		JPanel kullaniciTab = new JPanel();
		kullaniciTab.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Kullan\u0131c\u0131", null, kullaniciTab, null);
		tabbedPane.setEnabledAt(0, true);
		kullaniciTab.setLayout(null);

		userField = new JTextField();
		userField.setBounds(99, 43, 118, 20);
		kullaniciTab.add(userField);
		userField.setColumns(10);

		JLabel lblKllncAdi = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblKllncAdi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKllncAdi.setBounds(10, 43, 79, 14);
		kullaniciTab.add(lblKllncAdi);

		JLabel lblKllncSifre = new JLabel("\u015Eifre:");
		lblKllncSifre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKllncSifre.setBounds(10, 75, 46, 14);
		kullaniciTab.add(lblKllncSifre);

		JButton btnGiris = new JButton("Giri\u015F Yap");
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				lblusername.setVisible(false);
				lblpassword.setVisible(false);
				
				if (userField.getText().equals("") || passwordField.getText().equals("")){
					if (userField.getText().equals(""))
						lblusername.setVisible(true);
					if (passwordField.getText().equals(""))
						lblpassword.setVisible(true);
				}
				else {
					
					lblusername.setVisible(false);
					lblpassword.setVisible(false);
					
					boolean flag = false;
				
					String pass = passwordField.getText();
					String uname = userField.getText();
				
					String url = "jdbc:sqlserver://SATELLITEA30-PC;instanceName=SQLEXPRESS;databaseName=ValveCombination;user=sa;password=123"; // a JDBC url
		            
		            Connection connection = null;
		            Statement stmt = null;
		            ResultSet rs1 = null;
		            ResultSetMetaData rsmd = null;
		            try {
		            
		             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		             connection = DriverManager.getConnection(url);
		             
		             String  SQL = "SELECT userName, password FROM Userr";
		             stmt = connection.createStatement();
		             rs1 = stmt.executeQuery(SQL);
			            		 
		             while(rs1.next()){

		            	 if(rs1.getString("userName").equals(uname) && rs1.getString("password").equals(pass)){
		            		 
		            		 eg.setVisible(true);
		            		 eg.btnVeritabannaAktar.setVisible(false);
		            		 eg.btnyki.setVisible(false);
		            		 userType = "normal";
		            		 
		            		 lblValidationGiris.setVisible(false);
		            		 passwordField.setText("");
		            		 
		            		 flag = true;
		            	 }
		            	 else{
		            		 
		            		 userField.setText("");
		            		 passwordField.setText("");
		            		 
		            	 }
		            	 
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
	                
	                if (flag == false) {
	                	lblValidationGiris.setText("Login failed");
           		 		lblValidationGiris.setVisible(true);
	                }
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }         
				
			}
		}
		});
		btnGiris.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGiris.setBounds(128, 126, 89, 23);
		kullaniciTab.add(btnGiris);

		
		lblValidationGiris.setFont(new Font("Georgia", Font.BOLD, 12));
		lblValidationGiris.setHorizontalAlignment(SwingConstants.CENTER);
		lblValidationGiris.setForeground(Color.RED);
		lblValidationGiris.setBounds(10, 190, 207, 14);
		lblValidationGiris.setVisible(false);
		kullaniciTab.add(lblValidationGiris);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 75, 118, 20);
		kullaniciTab.add(passwordField);
		
		
		lblusername.setVisible(false);
		lblusername.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 18));
		lblusername.setForeground(Color.RED);
		lblusername.setBounds(216, 46, 46, 14);
		kullaniciTab.add(lblusername);
		
		
		lblpassword.setVisible(false);
		lblpassword.setForeground(Color.RED);
		lblpassword.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 18));
		lblpassword.setBounds(216, 78, 46, 14);
		kullaniciTab.add(lblpassword);
		lblValidationGiris.hide();

		JPanel adminTab = new JPanel();
		adminTab.setBackground(SystemColor.controlHighlight);
		tabbedPane.addTab("Admin", null, adminTab, null);
		adminTab.setLayout(null);

		JLabel lblSifreAdmin = new JLabel("\u015Eifre:");
		lblSifreAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSifreAdmin.setBounds(10, 74, 46, 14);
		adminTab.add(lblSifreAdmin);

		adminPasswordField = new JPasswordField();
		adminPasswordField.setColumns(10);
		adminPasswordField.setBounds(99, 74, 118, 20);
		adminTab.add(adminPasswordField);

		JButton btnAdminGiris = new JButton("Giri\u015F Yap");
		btnAdminGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel_1.setVisible(false);
				lblNewLabel_2.setVisible(false);
				
				boolean flag = false;
				
				if (adminUserField.getText().equals("") || adminPasswordField.getText().equals("")){
					if (adminUserField.getText().equals(""))
						lblNewLabel_1.setVisible(true);
					if (adminPasswordField.getText().equals(""))
						lblNewLabel_2.setVisible(true);
				}
				else {
					
					String pass = adminPasswordField.getText();
					String uname = adminUserField.getText();
				
					String url = "jdbc:sqlserver://SATELLITEA30-PC;instanceName=SQLEXPRESS;databaseName=ValveCombination;user=sa;password=123"; // a JDBC url
		            
		            Connection connection = null;
		            Statement stmt = null;
		            ResultSet rs1 = null;
		            ResultSetMetaData rsmd = null;
		            try {
		            
		             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		             connection = DriverManager.getConnection(url);
		             
		             String  SQL = "SELECT userName, password, isAdmin FROM Userr";
		             stmt = connection.createStatement();
		             rs1 = stmt.executeQuery(SQL);
			            
		             while(rs1.next()){

		            	 if(rs1.getString("userName").equals(uname) &&  rs1.getString("password").equals(pass) && (rs1.getString("isAdmin").equals("1"))){
		            		 System.out.println(uname);
		            		 eg.setVisible(true);
		            		 eg.btnyki.setVisible(true);
		            		 eg.btnVeritabannaAktar.setVisible(true);;
		            		 eg.btnyki.setVisible(true);
		            		 userType = "admin";
		            		 
		            		 lblValidationAdmin.setVisible(false);
		            		 adminPasswordField.setText("");
		            		 
		            		 flag = true;
		            	 }
		            	 else{
		            		 
		            		 adminUserField.setText("");
		            		 adminPasswordField.setText("");
		            		 
		            	 }
		            	 
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
	                
	                if (flag == false)
	                {
	                	 lblValidationAdmin.setText("Login failed");
	            		 lblValidationAdmin.setVisible(true);
	                }
	                	
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }         
		          
			}
	
		}
		});
		btnAdminGiris.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdminGiris.setBounds(128, 126, 89, 23);
		adminTab.add(btnAdminGiris);

		
		lblValidationAdmin.setFont(new Font("Georgia", Font.BOLD, 12));
		lblValidationAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblValidationAdmin.setForeground(Color.RED);
		lblValidationAdmin.setBounds(10, 168, 207, 14);
		adminTab.add(lblValidationAdmin);
		
		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131: ");
		lblKullancAd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKullancAd.setBounds(10, 43, 76, 14);
		adminTab.add(lblKullancAd);
		
		adminUserField = new JTextField();
		adminUserField.setBounds(99, 43, 118, 20);
		adminTab.add(adminUserField);
		adminUserField.setColumns(10);
		
		
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(216, 46, 46, 14);
		adminTab.add(lblNewLabel_1);
		
		
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(216, 78, 46, 14);
		adminTab.add(lblNewLabel_2);

		JPanel panelYeniKayit = new JPanel();
		panelYeniKayit.setBackground(SystemColor.controlHighlight);
		panelYeniKayit.setBounds(311, 121, 277, 307);
		contentPane.add(panelYeniKayit);
		panelYeniKayit.setLayout(null);

		JLabel lblYeniKayit = new JLabel("Yeni Kay\u0131t Olu\u015Ftur");
		lblYeniKayit.setForeground(new Color(0, 0, 128));
		lblYeniKayit.setFont(new Font("Georgia", Font.BOLD, 11));
		lblYeniKayit.setHorizontalAlignment(SwingConstants.CENTER);
		lblYeniKayit.setBounds(10, 11, 257, 14);
		panelYeniKayit.add(lblYeniKayit);

		JLabel lblAd = new JLabel("Ad :");
		lblAd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAd.setBounds(23, 47, 97, 14);
		panelYeniKayit.add(lblAd);

		JLabel lblSoyad = new JLabel("Soyad :");
		lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSoyad.setBounds(23, 72, 97, 14);
		panelYeniKayit.add(lblSoyad);

		JLabel lblTcKimlikNo = new JLabel("TC Kimlik No :");
		lblTcKimlikNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTcKimlikNo.setBounds(23, 97, 97, 14);
		panelYeniKayit.add(lblTcKimlikNo);

		JLabel lblEmail = new JLabel("E-Mail :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmail.setBounds(23, 122, 97, 14);
		panelYeniKayit.add(lblEmail);

		JButton btnYeniKayit = new JButton("Kay\u0131t Ol");
		btnYeniKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				lblyk.setText("");
				lblyk.setVisible(false);
				
				if ( ! passField.getText().equals(pass2Field.getText()) ) {
					lblyk.setText("Þifreyi kontrol ediniz");
					lblyk.setVisible(true);
					return;
				}
				if ( nameField.getText().equals("") || surnameField.getText().equals("") || usernameField.getText().equals("") || passField.getText().equals("")
						|| tcField.getText().equals("") || emailField.getText().equals("")){
					lblyk.setText("Eksik bilgi girdiniz");
					lblyk.setVisible(true);
					return;
				}
				
				lblyk.setText("");
				lblyk.setVisible(false);
				
				String Name = nameField.getText();
				String Surname = surnameField.getText();
				String TC = tcField.getText();
				String Email = emailField.getText();
				String Username = usernameField.getText();
				String Password = passField.getText();
						
				 String url = "jdbc:sqlserver://SATELLITEA30-PC;instanceName=SQLEXPRESS;databaseName=ValveCombination;user=sa;password=123"; // a JDBC url
		            
		            Connection connection = null;
		            Statement stmt = null;
		            ResultSet rs1 = null;
		            ResultSetMetaData rsmd = null;
		            
		            PreparedStatement pstmt = null;
		            
		            try {
		            
		             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		             connection = DriverManager.getConnection(url);
		             
		             String  SQL = "INSERT INTO UserRequests (name, surname, tc, email, username, password, isAdmin) "
		             		+ "VALUES (?,?,?,?,?,?,?)";
		             
		             pstmt = connection.prepareStatement(SQL); 	// create a statement		
		             pstmt.setString(1, Name); 					// set input parameter 1
		             pstmt.setString(2, Surname); 					// set input parameter 2
		             pstmt.setString(3, TC); 
		             pstmt.setString(4, Email); 
		             pstmt.setString(5, Username); 
		             pstmt.setString(6, Password); 				// set input parameter 3		
		             pstmt.setInt(7, 0); 
		             pstmt.executeUpdate(); 						// execute insert statement
		             
		             
		            } catch (ClassNotFoundException e1) {
		                e1.printStackTrace();
		            // Could not find the database driver
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            // Could not connect to the database
		            }
					finally {
							try {
								JOptionPane.showMessageDialog(null,
										"Kullanýcý isteði göderildi." );
								
								pstmt.close();
								
								nameField.setText("");
								surnameField.setText("");
								tcField.setText("");
								emailField.setText("");
								usernameField.setText("");
								passField.setText("");
								pass2Field.setText("");
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
		});
		btnYeniKayit.setBounds(151, 248, 85, 23);
		panelYeniKayit.add(btnYeniKayit);

		nameField = new JTextField();
		nameField.setBounds(130, 47, 119, 17);
		panelYeniKayit.add(nameField);
		nameField.setColumns(10);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(130, 122, 119, 17);
		panelYeniKayit.add(emailField);

		tcField = new JTextField();
		tcField.setColumns(10);
		tcField.setBounds(130, 97, 119, 17);
		panelYeniKayit.add(tcField);

		surnameField = new JTextField();
		surnameField.setColumns(10);
		surnameField.setBounds(130, 72, 119, 17);
		panelYeniKayit.add(surnameField);

		
		lblyk.setVisible(false);
		lblyk.setFont(new Font("Georgia", Font.BOLD, 11));
		lblyk.setForeground(Color.RED);
		lblyk.setHorizontalAlignment(SwingConstants.CENTER);
		lblyk.setBounds(44, 282, 192, 14);
		panelYeniKayit.add(lblyk);
		
		JLabel lblKullancAd_1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131: ");
		lblKullancAd_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKullancAd_1.setBounds(23, 150, 75, 14);
		panelYeniKayit.add(lblKullancAd_1);
		
		usernameField = new JTextField();
		usernameField.setBounds(130, 150, 119, 17);
		panelYeniKayit.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblsifre = new JLabel("\u015Eifre: ");
		lblsifre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblsifre.setBounds(23, 180, 46, 14);
		panelYeniKayit.add(lblsifre);
		
		passField = new JPasswordField();
		passField.setBounds(130, 178, 119, 17);
		panelYeniKayit.add(passField);
		passField.setColumns(10);
		
		JLabel lblsifre2 = new JLabel("\u015Eifre (Tekrar):");
		lblsifre2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblsifre2.setBounds(23, 208, 75, 14);
		panelYeniKayit.add(lblsifre2);
		
		pass2Field = new JPasswordField();
		pass2Field.setBounds(130, 206, 119, 17);
		panelYeniKayit.add(pass2Field);
		pass2Field.setColumns(10);

		JPanel panelTITLE = new JPanel();
		panelTITLE.setBorder(UIManager.getBorder("TitledBorder.border"));
		panelTITLE.setBounds(10, 11, 578, 99);
		contentPane.add(panelTITLE);
		panelTITLE.setLayout(null);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(290, 11, 1, 77);
		panelTITLE.add(verticalStrut);

		JLabel lblTITLE = new JLabel("VALVE COMBINATION SYSTEM");
		lblTITLE.setHorizontalAlignment(SwingConstants.CENTER);
		lblTITLE.setForeground(new Color(128, 128, 128));
		lblTITLE.setFont(new Font("Eras Demi ITC", Font.PLAIN, 18));
		lblTITLE.setBounds(10, 11, 332, 77);
		panelTITLE.add(lblTITLE);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(375, 11, 193, 77);
		panelTITLE.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 193, 77);
		panel_2.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(FP.class.getResource("/images/maysanMandoIcon.JPG")));

	}
}
