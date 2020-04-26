package package1;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
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

public class AddRoomWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Container contentPane;

	private JLabel roomNumberLabel;
	private JLabel typeLabel;
	private JLabel pricePerNightLabel;
	
	private JTextField roomNumberTextField;
	private JTextField pricePerNightTextField;

	private JComboBox<String> typeComboBox;

	private JButton addRoomButton;

	private JScrollPane scrollPane;

	private JTextArea commentsTextArea;

	GridBagConstraints gbc = null;

	private String windowTitle = "Add Room Window";
	private String roomNumberLabelText = "Room number";
	private String typeLabelText = "Type";
	private String pricePerNightLabelText = "Price per night";
	private String addRoomButtonText = "Add room";

	private final String errorMessage = "Missing data!";
	
	private String[] roomTypes = new String[RoomType.values().length];

	private Document doc;

	private List<Room> rooms;

	public AddRoomWindow() {
		contentPane = this.getContentPane();

		this.setLayout(new GridBagLayout());

		Dimension textFieldDimension = new Dimension(100, 30);
		Dimension buttonDimension = new Dimension(150, 30);
		
		Arrays.asList(RoomType.values()).stream().map(type -> type.toString()).collect(Collectors.toList()).toArray(roomTypes);

		gbc = setConstraints(0, 0, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		roomNumberLabel = new JLabel(roomNumberLabelText);
		contentPane.add(roomNumberLabel, gbc);

		gbc = setConstraints(0, 1, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		typeLabel = new JLabel(typeLabelText);
		contentPane.add(typeLabel, gbc);

		gbc = setConstraints(0, 2, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		pricePerNightLabel = new JLabel(pricePerNightLabelText);
		contentPane.add(pricePerNightLabel, gbc);

		gbc = setConstraints(1, 0, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		roomNumberTextField = new JTextField();
		roomNumberTextField.setPreferredSize(textFieldDimension);
		contentPane.add(roomNumberTextField, gbc);

		gbc = setConstraints(1, 1, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		typeComboBox = new JComboBox<String>(roomTypes);
		contentPane.add(typeComboBox, gbc);
		
		gbc = setConstraints(1, 2, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		pricePerNightTextField = new JTextField();
		pricePerNightTextField.setPreferredSize(textFieldDimension);
		contentPane.add(pricePerNightTextField, gbc);

		gbc = setConstraints(0, 3, 1, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
				GridBagConstraints.BASELINE_LEADING, 1);

		addRoomButton = new JButton(addRoomButtonText);
		addRoomButton.setPreferredSize(buttonDimension);
		addRoomButton.setActionCommand(addRoomButtonText);
		addRoomButton.addActionListener(this);
		contentPane.add(addRoomButton, gbc);

		gbc = setConstraints(0, 4, 2, 1, 0.01, 0.01, 0, 0, new Insets(10, 10, 10, 10),
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
				.equals(addRoomButtonText)) {
			String roomNumber = roomNumberTextField.getText();
			String type = typeComboBox.getSelectedItem()
					.toString();
			String pricePerNight = pricePerNightTextField.getText();
			
			if (roomNumber.length() != 0 && pricePerNight.length() != 0 && type.length() != 0) {
				Utils.updateDocument(doc, new Room(roomNumber, RoomType.valueOf(type) , Double.parseDouble(pricePerNight)));
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
		commentsTextArea.setText(null);

		for (Room room : this.rooms) {
			commentsTextArea.append(room.toString());
			commentsTextArea.append("------------------------------------\n");
		}
	}
}