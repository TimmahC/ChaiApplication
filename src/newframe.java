
import javax.swing.*;
import java.util.concurrent.*;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import java.awt.*;
import java.awt.event.*;


public class newframe extends JFrame {
	final Browser browser = new Browser();
	BrowserView browserView = new BrowserView(browser);
	String name;
	
	public void load()
	{
		browser.loadURL("C:\\xampp\\htdocs\\Chai\\map.html");
		browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
            		browser.executeJavaScript("setName('"+name+"');");
                    System.out.println("HTML is loaded.");
                }
            }
        });
	}
	
	newframe(String input){
		
		super("Chai Application");
		load();

		setSize(800,800);
		name = input;
		
		JButton s = new JButton("Search");
		JButton r = new JButton("Reset");
		
		
		JLabel welcome = new JLabel("Welcome "+name +"!");
		JLabel space = new JLabel("		");
		JLabel search = new JLabel("Search");
		JTextField restaurant = new JTextField(10);
		
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel ratingPanel = new JPanel();
		JPanel superPanel = new JPanel();
		
		superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.LINE_AXIS));
		panel.setLayout(new FlowLayout());
		panel1.setLayout(new FlowLayout());
		ratingPanel.setLayout(new FlowLayout());
		
		panel.add(welcome);
		panel.add(space);
		
		panel1.add(search);
		panel1.add(restaurant);		
		panel1.add(s);
		panel1.add(r);
		
		ButtonGroup ratings = new ButtonGroup();
		JRadioButton option1 = new JRadioButton("1");
        JRadioButton option2 = new JRadioButton("2");
        JRadioButton option3 = new JRadioButton("3");
        JRadioButton option4 = new JRadioButton("4");
        JRadioButton option5 = new JRadioButton("5");
        ratings.add(option1);
        ratings.add(option2);
        ratings.add(option3);
        ratings.add(option4);
        ratings.add(option5);
		/*JButton zoomInButton = new JButton("Zoom In");
        zoomInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	browser.executeJavaScript("setName('"+name+"');");
            }
        });
        panel.add(zoomInButton)*/
		
		superPanel.add(panel);
		superPanel.add(panel1);
		superPanel.add(ratingPanel);
		
		getContentPane().add(browserView, BorderLayout.CENTER);
		getContentPane().add(superPanel, BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		s.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				browser.executeJavaScript("filterName('"+restaurant.getText().trim()+"');");
			}
		});
		r.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				browser.executeJavaScript("clearFilter()");
			}
		});
	}

}