package package1;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Menu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Container contentPane;
	private JButton addCustomersButton;
	private JButton addRoomsButton;
	private JButton searchButton;
	GridBagConstraints gbc = null;
	private String addCustomersButtonText = "Add Customers";
	private String addRoomsButtonText = "Add Rooms";
	private String searchButtonText = "Search";
	
	private String windowTitle = "Hotel manager";
	
	public Menu() {
		contentPane = this.getContentPane();
		
		this.setLayout(new GridBagLayout());
		
		Dimension buttonDimension = new Dimension(150, 30);
		
		gbc = setConstraints(0, 0, 1, 1, 0.01, 0.01, 0, 0, new Insets(5, 200, 5, 5), GridBagConstraints.LINE_START,
				GridBagConstraints.NONE);
		
		addCustomersButton = new JButton(addCustomersButtonText);
		addCustomersButton.setPreferredSize(buttonDimension);
		addCustomersButton.setActionCommand(addCustomersButtonText);
		addCustomersButton.addActionListener(this);
		
		contentPane.add(addCustomersButton, gbc);
		
		gbc = setConstraints(0, 1, 1, 1, 0.01, 0.01, 0, 0, new Insets(5, 200, 5, 5), GridBagConstraints.LINE_START,
				GridBagConstraints.NONE);
		
		addRoomsButton = new JButton(addRoomsButtonText);
		addRoomsButton.setPreferredSize(buttonDimension);
		addRoomsButton.setActionCommand(addRoomsButtonText);
		addRoomsButton.addActionListener(this);
		
		contentPane.add(addRoomsButton, gbc);
		
		gbc = setConstraints(0, 2, 1, 1, 0.01, 0.01, 0, 0, new Insets(5, 200, 5, 5), GridBagConstraints.LINE_START,
				GridBagConstraints.NONE);
		
		searchButton = new JButton(searchButtonText);
		searchButton.setPreferredSize(buttonDimension);
		searchButton.setActionCommand(searchButtonText);
		searchButton.addActionListener(this);
		
		contentPane.add(searchButton, gbc);
		
		this.pack();
		setSize(600, 400);
		setTitle(windowTitle);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private GridBagConstraints setConstraints(int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty, int ipadx, int ipady, Insets insets, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.ipadx = ipadx;
		gbc.ipady = ipady;
		gbc.insets = insets;
		gbc.anchor = anchor;
		gbc.fill = fill;
		return gbc;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		showFrame(ae.getActionCommand());
	}
	
	public void showFrame(String frameName) {
		EventQueue.invokeLater(new Runnable() {
			@Override
            public void run() {
				if (frameName.equals(addCustomersButtonText)) {
					AddCustomerWindow customerWindow = new AddCustomerWindow();
					customerWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} else if (frameName.equals(addRoomsButtonText)) {
					AddRoomWindow roomWindow = new AddRoomWindow();
					roomWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
			}
		});
	}

	public static void main(String[] args) {
		Menu app = new Menu();
		app.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}