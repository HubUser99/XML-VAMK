package package1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class Utils {
	public static Document readXMLFile(String filePath) throws ParserConfigurationException, SAXException, IOException {
		File file = new File(filePath);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement()
				.normalize();
		return doc;
	}

	public static List<Customer> getCustomersFromXMLDoc(Document doc, String customerNodeName) {
		NodeList nodeList = doc.getElementsByTagName(customerNodeName);
		List<Customer> customers = new ArrayList<Customer>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				String firstName = eElement.getElementsByTagName("firstName")
						.item(0)
						.getTextContent();
				String lastName = eElement.getElementsByTagName("lastName")
						.item(0)
						.getTextContent();
				String roomNumber = eElement.getElementsByTagName("roomNumber")
						.item(0)
						.getTextContent();
				int durationOfStay = Integer.parseInt(eElement.getElementsByTagName("durationOfStay")
						.item(0)
						.getTextContent());
				customers.add(new Customer(firstName, lastName, roomNumber, durationOfStay));
			}
		}
		return customers;
	}

	public static List<Room> getRoomsFromXMLDoc(Document doc, String roomNodeName) {
		NodeList nodeList = doc.getElementsByTagName(roomNodeName);
		List<Room> rooms = new ArrayList<Room>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				String roomNumber = eElement.getElementsByTagName("roomNumber")
						.item(0)
						.getTextContent();
				RoomType type = RoomType.valueOf(eElement.getElementsByTagName("type")
						.item(0)
						.getTextContent());
				Double pricePerNight = Double.parseDouble(eElement.getElementsByTagName("pricePerNight")
						.item(0)
						.getTextContent());
				rooms.add(new Room(roomNumber, type, pricePerNight));
			}
		}
		return rooms;
	}
	
	public static void updateDocument(Document doc, Customer customer) {
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String roomNumber = customer.getRoomNumber();
		String durationOfStay = Integer.toString(customer.getDurationOfStay());
		
		Element customersRoot = (Element) doc.getElementsByTagName("customers").item(0);
		Element customerElement = doc.createElement("customer");
		
		Element firstNameNode = doc.createElement("firstName");
		Element lastNameNode = doc.createElement("lastName");
		Element roomNumberNode = doc.createElement("roomNumber");
		Element durationOfStayNode = doc.createElement("durationOfStay");
		
		Node firstNameTextNode = doc.createTextNode(firstName);
		Node lastNameTextNode = doc.createTextNode(lastName);
		Node roomNumberTextNode = doc.createTextNode(roomNumber);
		Node durationOfStayTextNode = doc.createTextNode(durationOfStay);
		
		firstNameNode.appendChild(firstNameTextNode);
		lastNameNode.appendChild(lastNameTextNode);
		roomNumberNode.appendChild(roomNumberTextNode);
		durationOfStayNode.appendChild(durationOfStayTextNode);
		
		customerElement.appendChild(firstNameNode);
		customerElement.appendChild(lastNameNode);
		customerElement.appendChild(roomNumberNode);
		customerElement.appendChild(durationOfStayNode);
		
		customersRoot.appendChild(customerElement);
		
		try {
			writeToXMLFile(doc, "C:\\Users\\cliff\\Documents\\Work\\Studies\\xml\\Assignment 10\\hotel.xml");
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateDocument(Document doc, Room room) {
		String roomNumber = room.getRoomNumber();
		String type = room.getType().toString();
		String pricePerNight = room.getPricePerNight().toString();
		
		Element roomsRoot = (Element) doc.getElementsByTagName("rooms").item(0);
		Element roomElement = doc.createElement("room");
		
		Element roomNumberNode = doc.createElement("roomNumber");
		Element typeNode = doc.createElement("type");
		Element pricePerNightNode = doc.createElement("pricePerNight");
		
		Node roomNumberTextNode = doc.createTextNode(roomNumber);
		Node typeTextNode = doc.createTextNode(type);
		Node pricePerNightTextNode = doc.createTextNode(pricePerNight);
		
		roomNumberNode.appendChild(roomNumberTextNode);
		typeNode.appendChild(typeTextNode);
		pricePerNightNode.appendChild(pricePerNightTextNode);
		
		roomElement.appendChild(roomNumberNode);
		roomElement.appendChild(typeNode);
		roomElement.appendChild(pricePerNightNode);
		
		roomsRoot.appendChild(roomElement);
		
		try {
			writeToXMLFile(doc, "C:\\Users\\cliff\\Documents\\Work\\Studies\\xml\\Assignment 10\\hotel.xml");
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeToXMLFile(Document doc, String filePath) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(doc);
		StreamResult streamResult = new StreamResult(new File(filePath));
		transformer.transform(domSource, streamResult);
	}
}
