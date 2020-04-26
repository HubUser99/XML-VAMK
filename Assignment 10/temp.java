import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class CopyXMLElemnt {

    private static String getFailureMessage(String elementID) {

        return "employee with id " + elementID

                + " was not found!";

    }

    public static void main(String[] args) {

        String xmlFile = "employees.xml";

        boolean employeeFound = false;

        String elementID = "1000";

        String elementAttribute = "id";

        String elementName = "employee";

        String searchResultFileName = elementName + "_search_results.xml";

        Document resultDocument = null;

        String commentText = "This is the information of " + elementName + " with " + elementAttribute + " ";

        String resultDocumentRootName = elementName + "_info";

        try {

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory

                    .newInstance();

            DocumentBuilder documentBuilder = builderFactory

                    .newDocumentBuilder();

            // Here we initialize the XML document for reading

            Document document = documentBuilder.parse(xmlFile);

            document.getDocumentElement().normalize();

            // In the following lines we initialize resultDocument (the XML

            // document) of employee information

            // to be built and returned

            resultDocument = documentBuilder.newDocument();

            // Here we create the root element

            Element resultDocumentRoot = resultDocument.createElement(resultDocumentRootName);

            // Here we add the root element to the XML tree

            resultDocument.appendChild(resultDocumentRoot);

            // Here we create a comment

            Comment comment = resultDocument

                    .createComment(commentText + elementID);

            // add in the root element

            resultDocumentRoot.appendChild(comment);

            // Here we get a list of employee elements in the XML database file

            NodeList nodeList = document.getElementsByTagName(elementName);

            Node node;

            Element element;

            Node importedNode;

            Element failureElement;

            Text failureMessage;

            for (int i = 0; i < nodeList.getLength(); i++) {

                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    element = (Element) node;

                    if (element.getAttribute(elementAttribute)

                            .equals(elementID)) {

                        employeeFound = true;

                        // Here we copy the element from the source document to

                        // the

                        // result document. The value true for the second

                        // argument means

                        // that we make a deep copy, which makes a copy of all

                        // child nodes.

                        importedNode = resultDocument.importNode(element,

                                true);

                        if (importedNode != null)

                            resultDocumentRoot.appendChild(importedNode);

                    } else if (employeeFound == false

                            && i == nodeList.getLength() - 1) {

                        // Here we create 'failure' child element

                        failureElement = resultDocument

                                .createElement("failure");

                        failureMessage = resultDocument

                                .createTextNode(getFailureMessage(elementID));

                        failureElement.appendChild(failureMessage);

                        // add in the root element

                        resultDocumentRoot.appendChild(failureElement);

                    } // The end of else statement

                } // The end of checking for the element node

            } // This is the end of loop for the node list

            TransformerFactory transformerFactory = TransformerFactory

                    .newInstance();

            try {

                Transformer transformer = transformerFactory.newTransformer();

                Source src = new DOMSource(resultDocument);

                // Result output = new StreamResult(System.out);

                Result output = new StreamResult(new FileOutputStream(

                        searchResultFileName));

                transformer.transform(src, output);

                // Here we re-initialize the output to write to the standard output device

                output = new StreamResult(System.out);

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                transformer.transform(src, output);

            } catch (TransformerConfigurationException e) {

                e.printStackTrace();

            }

        } catch (TransformerException e) {

            e.printStackTrace();

        } catch (ParserConfigurationException e) {

        } catch (SAXException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}