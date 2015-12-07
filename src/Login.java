import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class Login {
	
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	JFrame frame = new JFrame("Chai Application");
	JLabel name = new JLabel("Username:");
	JLabel pwd = new JLabel("Password:");
	JTextField t = new JTextField(10);
	JTextField t1 = new JPasswordField(10);
	JButton b = new JButton("Login");
	JButton r = new JButton("Register");

	
	public Login()
	{
		connect();
		frame();
	}
	
	public void connect()
	{
	    String url = "jdbc:mysql://localhost:3306/chai";
	    String user = "root";
	    String password = "password";
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void frame()
	{
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel p = new JPanel();
		p.add(name);
		p.add(t);
		p.add(pwd);
		p.add(t1);
		p.add(b);
		p.add(r);
		frame.add(p);
		
		
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{
					connect();
					String user = t.getText().trim();
					String pass = t1.getText().trim();
					
					String sql = "select username, password from users where username='"+user+"'and password='"+pass+"'";
					rs = stmt.executeQuery(sql);
					int count = 0;
					while(rs.next())
					{
						count++;
					}
					
					if(count == 1){
						newframe regFace = new newframe(user);
						System.out.println(user);
						regFace.setVisible(true);
						frame.dispose();				
					}
					else if(count > 1){
						JOptionPane.showMessageDialog(null, "Multiple user found");						
					}
					else{
						JOptionPane.showMessageDialog(null, "User not found.");
					}
				}
				catch(Exception ex){
			        ex.printStackTrace();
				}
			}
		});
		
		r.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{
					connect();
					String user = t.getText().trim();
					String pass = t1.getText().trim();
					
					if(user == "" || pass == "")
					{
						JOptionPane.showMessageDialog(null, "Please enter a username and/or password.");	
					}
						
					String sql = "select username, password from users where username='"+user+"'and password='"+pass+"'";
					rs = stmt.executeQuery(sql);
					int count = 0;
					while(rs.next())
					{
						count++;
					}
					
					if(count == 0){
						
						sql = "insert into users values ('"+user+"','"+pass+"');";
						int i = stmt.executeUpdate(sql);
						if(i == 1)
							JOptionPane.showMessageDialog(null, "User "+user+" has been successfully created.");	
					}
					else{
						JOptionPane.showMessageDialog(null, "User already exists.");						
					}
				}
				catch(Exception ex){
			        ex.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args){
		new Login();
		
	}
}
