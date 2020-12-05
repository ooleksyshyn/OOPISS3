package Parser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class StaxXmlParser implements XMLParser {
    private Logger log = Logger.getLogger(StaxXmlParser.class.getName());
    private DeviceBuilder deviceBuilder;

    StaxXmlParser(DeviceBuilder deviceBuilder) {
        this.deviceBuilder = deviceBuilder;
    }

    @Override
    public void parseXmlDocument(String pathToXmlDocument) {
        XMLInputFactory xmlReaderFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlReader = null;
        try {
            xmlReader = xmlReaderFactory.createXMLEventReader(new FileInputStream(pathToXmlDocument));
        } catch (FileNotFoundException | XMLStreamException e) {
            log.info(e.getMessage());
        }

        while (xmlReader.hasNext()) {
            try {
                XMLEvent event = xmlReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.END_DOCUMENT:
                        break;

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        event = xmlReader.nextEvent();
                        deviceBuilder.setTag(startElement.getName().getLocalPart(), event.asCharacters().getData());
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equalsIgnoreCase(deviceBuilder.getRootName())) {
                            deviceBuilder.saveElement(deviceBuilder.getRootName());
                        }
                        break;
                }
            } catch (XMLStreamException e) {
                log.info(e.getMessage());
            }
        }
        return;
    }

}