package Parser;

import Parser.SAX.SaxXmlParser;
import Devices.Computer;
import org.xml.sax.SAXException;


import javax.xml.parsers.*;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class DevicesParser {
    private XMLParser parser;
    private Computer result;
    private DeviceBuilder deviceBuilder;
    private String typeOfParser = "DOM";

    public void setTypeOfParser(String typeOfParser){
        this.typeOfParser = typeOfParser;
    }

    public Computer parseXmlDocument(String pathToXmlDocument, String pathToXsdFile) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {

        if(XmlFileValidator.validateXMLDocument(pathToXmlDocument,pathToXsdFile)) {
            deviceBuilder = new DeviceBuilder();
            switch (typeOfParser.toUpperCase()) {
                case "SAX": {
                    parser = new SaxXmlParser(deviceBuilder);
                    break;
                }
                case "DOM": {
                    parser = new DomXmlParser(deviceBuilder);
                    break;
                }
                case "STAX": {
                    parser = new StaxXmlParser(deviceBuilder);
                    break;
                }
                default:
                    break;
            }
            parser.parseXmlDocument(pathToXmlDocument);
            result = new Computer(deviceBuilder.getDevices());

            result.sort();
        }
        return result;
    }
}
