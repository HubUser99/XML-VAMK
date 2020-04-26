package package1;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

public class AddCustomerWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Container contentPane;

	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel roomComboBoxLabel;
	private JLabel durationOfStayLabel;

	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField durationOfStayTextField;

	private JComboBox<String> roomComboBox;

	private JButton addCustomerButton;

	private JScrollPane scrollPane;

	private JTextArea commentsTextArea;

	GridBagConstraints gbc = null;

	private String windowTitle = "Add Customer Window";
	private String firstNameLabelText = "First name";
	private String lastNameLabelText = "Last name";
	private String durationOfStayLabelText = "Duration of stay";
	private String roomComboBoxLabelText = "Room";
	private String addCustomerButtonText = "Add customer";

	private final String errorMessage = "Missing data!";

	private Document doc;

	private List<Customer> customers;
	private List<Room> rooms;

	public AddCustomerWindow() {
		contentPane = this.getContentPane();

		this.setLayout(new GridBagLayout());

		Dimension textFieldDimension = new Dimension(100, 30);
		Dimension buttonDimension = new Dimension(150, 30);

		gbc = setConstraints(0, 0, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		firstNameLabel = new JLabel(firstNameLabelText);
		contentPane.add(firstNameLabel, gbc);

		gbc = setConstraints(0, 1, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		lastNameLabel = new JLabel(lastNameLabelText);
		contentPane.add(lastNameLabel, gbc);

		gbc = setConstraints(0, 2, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		durationOfStayLabel = new JLabel(durationOfStayLabelText);
		contentPane.add(durationOfStayLabel, gbc);

		gbc = setConstraints(0, 3, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		roomComboBoxLabel = new JLabel(roomComboBoxLabelText);
		contentPane.add(roomComboBoxLabel, gbc);

		gbc = setConstraints(1, 0, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		firstNameTextField = new JTextField();
		firstNameTextField.setPreferredSize(textFieldDimension);
		contentPane.add(firstNameTextField, gbc);

		gbc = setConstraints(1, 1, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		lastNameTextField = new JTextField();
		lastNameTextField.setPreferredSize(textFieldDimension);
		contentPane.add(lastNameTextField, gbc);

		gbc = setConstraints(1, 2, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		durationOfStayTextField = new JTextField();
		durationOfStayTextField.setPreferredSize(textFieldDimension);
		contentPane.add(durationOfStayTextField, gbc);

		gbc = setConstraints(1, 3, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		roomComboBox = new JComboBox<String>();
		contentPane.add(roomComboBox, gbc);

		gbc = setConstraints(0, 4, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		addCustomerButton = new JButton(addCustomerButtonText);
		addCustomerButton.setPreferredSize(buttonDimension);
		addCustomerButton.setActionCommand(addCustomerButtonText);
		addCustomerButton.addActionListener(this);
		contentPane.add(addCustomerButton, gbc);

		gbc = setConstraints(0, 5, 2, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		commentsTextArea = new JTextArea(20, 40);
		scrollPane = new JScrollPane(commentsTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane, gbc);

		loadData();
		showData();

		this.pack();
		setSize(600, 800);
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
		if (ae.getActionCommand()
				.equals(addCustomerButtonText)) {
			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			String roomNumber = roomComboBox.getSelectedItem()
					.toString();
			String durationOfStay = durationOfStayTextField.getText();

			if (firstName.length() != 0 && lastName.length() != 0 && Integer.parseInt(durationOfStay) > 0
					&& roomNumber.length() != 0) {
				Utils.updateDocument(doc, new Customer(firstName, lastName, roomNumber, Integer.parseInt(durationOfStay)));
				loadData();
				showData();
			} else {
				JOptionPane.showConfirmDialog(null, errorMessage, "Error", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}

	private void loadData() {
		try {
			doc = Utils.readXMLFile("C:\\Users\\cliff\\Documents\\Work\\Studies\\xml\\Assignment 10\\hotel.xml");
			this.customers = Utils.getCustomersFromXMLDoc(doc, "customer");
			this.rooms = Utils.getRoomsFromXMLDoc(doc, "room");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showData() {
		roomComboBox.removeAllItems();
		commentsTextArea.setText(null);

		List<String> roomTypes = this.rooms.stream()
				.map(room -> room.getRoomNumber())
				.collect(Collectors.toList());
		for (String roomType : roomTypes) {
			roomComboBox.addItem(roomType);
		}

		for (Customer customer : this.customers) {
			commentsTextArea.append(customer.toString());
			commentsTextArea.append("------------------------------------\n");
		}
	}
}