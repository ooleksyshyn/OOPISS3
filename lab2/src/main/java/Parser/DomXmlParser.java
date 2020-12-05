package Parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.logging.Logger;

public class DomXmlParser implements XMLParser {
    private DeviceBuilder deviceBuilder;
    private Logger log = Logger.getLogger(DomXmlParser.class.getName());

    DomXmlParser(DeviceBuilder deviceBuilder){
        this.deviceBuilder = deviceBuilder;
    }

    @Override
    public void parseXmlDocument(String xmlPath) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(xmlPath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.info(e.getMessage());
        }

        Element root = doc.getDocumentElement();

        NodeList planeNodes = root.getElementsByTagName(deviceBuilder.getRootName());

        for (int i = 0; i < planeNodes.getLength(); i++) {

            Element paperElement = (Element) planeNodes.item(i);
            NodeList childNodes = paperElement.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {

                    Element child = (org.w3c.dom.Element) childNodes.item(j);

                    deviceBuilder.setTag(child.getNodeName(), getChildValue(paperElement, child.getNodeName()));
                    NodeList childChildNodes = child.getChildNodes();

                    for (int k = 0; k < childChildNodes.getLength(); k++) {
                        if (childChildNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                            Element childChild = (org.w3c.dom.Element) childChildNodes.item(k);
                            deviceBuilder.setTag(childChild.getNodeName(), getChildValue(child, childChild.getNodeName()));
                        }
                    }
                }
            }
            deviceBuilder.saveElement(paperElement.getNodeName());
        }
    }


    private String getChildValue(Element element, String name) {
        Element child = (Element) element.getElementsByTagName(name).item(0);
        if (child == null) {
            return new String();
        }
        Node node = child.getFirstChild();
        return node.getNodeValue();
    }


}