package Parser.SAX;

import Parser.DeviceBuilder;
import Parser.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxXmlParser implements XMLParser {
    private DeviceBuilder deviceBuilder;
    public SaxXmlParser(DeviceBuilder deviceBuilder){
        this.deviceBuilder = deviceBuilder;
    }

    @Override
    public void parseXmlDocument(String pathToXmlDocument) throws IllegalArgumentException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SaxHandler saxHandler = new SaxHandler(deviceBuilder);
            saxParser.parse(new File(pathToXmlDocument), saxHandler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error: " + e.getMessage());
        }
    }
}