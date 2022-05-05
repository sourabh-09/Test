import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;

public class Signupform {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signupform window = new Signupform();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Signupform() {
		initialize();
		connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pat;
	ResultSet rs;
	
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/java","root","");
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
	}
	
	void table_load() {
		try {
			pat=con.prepareStatement("select * from book");
			rs=pat.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1012, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOKSHOP");
		lblNewLabel.setBackground(new Color(255, 200, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(395, 23, 191, 49);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(31, 113, 395, 199);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 33, 119, 45);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Edition");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(10, 78, 119, 36);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setBounds(10, 145, 129, 45);
		panel.add(lblNewLabel_3);
		
		txtbname = new JTextField();
		txtbname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtbname.setBounds(139, 47, 206, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtedition.setColumns(10);
		txtedition.setBounds(139, 94, 206, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtprice.setColumns(10);
		txtprice.setBounds(139, 159, 206, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price;
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				
				try {
					pat=con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pat.setString(1, bname);
					pat.setString(2, edition);
					pat.setString(3, price);
					pat.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addeddd!!!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					
				}
				catch(SQLException el){
					el.printStackTrace();
				}
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(32, 348, 95, 49);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExite = new JButton("Exit");
		btnExite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExite.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExite.setBounds(178, 348, 95, 49);
		frame.getContentPane().add(btnExite);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
				
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(317, 348, 95, 49);
		frame.getContentPane().add(btnNewButton_1_1);
		
		table = new JTable();
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setBounds(473, 97, 459, 300);
		frame.getContentPane().add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(47, 430, 412, 86);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Book Id");
		lblNewLabel_2_1.setBounds(20, 22, 82, 36);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_1.add(lblNewLabel_2_1);
		
		txtbid = new JTextField();
		txtbid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtbid.addKeyListener(new KeyAdapter() {
		
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id=txtbid.getText();
					pat=con.prepareStatement("select name,edition,price from book where id=?");
					pat.setString(1, id);
					ResultSet rs=pat.executeQuery();
					
					if(rs.next()==true) {
						String name=rs.getString(1);
						String edition=rs.getString(2);
						String price=rs.getString(3);
						
						txtbname.setText(name);
						txtedition.setText(edition);
						txtprice.setText(price);
					}
					else {
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
					
					
			
					
				}
				catch(SQLException ex) {
					
				}
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(131, 24, 190, 36);
		panel_1.add(txtbid);
		
		JButton btnNewButton_1_1_1 = new JButton("Update");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price,bid;
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				bid=txtbid.getText();
				
				try {
					pat=con.prepareStatement("update book set name=?, edition=?, price=? where id=?");
					pat.setString(1, bname);
					pat.setString(2, edition);
					pat.setString(3, price);
					pat.setString(4, bid);
					pat.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updateddd!!!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					
				}
				catch(SQLException el){
					el.printStackTrace();
				}
				
				
				
				
			}
				
			
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1_1_1.setBounds(521, 467, 149, 49);
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Delete");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				bid=txtbid.getText();

				
				try {
					pat=con.prepareStatement("delete from book where id=?");
					pat.setString(1, bid);
			
					pat.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleteddd!!!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
					
				}
				catch(SQLException el){
					el.printStackTrace();
				}
				
				
				
				
			}
			
		});
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1_1_1_1.setBounds(730, 467, 143, 49);
		frame.getContentPane().add(btnNewButton_1_1_1_1);
	}
}
