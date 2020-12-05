import Parser.DevicesParser;
import Devices.Computer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    public static void main(String[]args) throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        DevicesParser devicesParser = new DevicesParser();

        devicesParser.setTypeOfParser("STAX");
        Computer result = devicesParser.parseXmlDocument("src/main/resources/Devices.xml",
                "src/main/resources/Devices.xsd");
        result.writeInFile("Devices.txt");

        devicesParser.setTypeOfParser("DOM");
        result = devicesParser.parseXmlDocument("src/main/resources/Devices.xml",
                "src/main/resources/Devices.xsd");
        result.writeInFile("Devices.txt");

        devicesParser.setTypeOfParser("SAX");
        result = devicesParser.parseXmlDocument("src/main/resources/Devices.xml",
                "src/main/resources/Devices.xsd");
        result.writeInFile("Devices.txt");
    }
}
