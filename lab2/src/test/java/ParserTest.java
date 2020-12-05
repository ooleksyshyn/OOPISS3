import Devices.*;
import Parser.DeviceBuilder;
import Parser.DevicesParser;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    DevicesParser periodicalParser;
    Device diskDrive;
    Device monitor;

    public ParserTest(){
        periodicalParser = new DevicesParser();
        diskDrive = new Device();
        monitor = new Device();

        diskDrive.setName("Disk drive");
        diskDrive.setOrigin("Ukraine");
        diskDrive.setPrice(100);
        diskDrive.setCritical(false);
        diskDrive.setType(new Type(true, 15, false, Group.IODevices, Port.USB));

        monitor.setName("Monitor");
        monitor.setOrigin("China");
        monitor.setPrice(400);
        monitor.setCritical(true);
        monitor.setType(new Type(true, 45, false, Group.Multimedia, Port.LPT));
    }

    @Test
    public void DOMParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        periodicalParser.setTypeOfParser("DOM");
        Computer computer = periodicalParser.parseXmlDocument("src/main/resources/test/Devices.xml",
                "src/main/resources/Devices.xsd");
        assertEquals(computer.getDevices().get(1), monitor);
        assertEquals(computer.getDevices().get(0), diskDrive);
    }

    @Test
    public void SAXParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        periodicalParser.setTypeOfParser("SAX");
        Computer computer = periodicalParser.parseXmlDocument("src/main/resources/test/Devices.xml",
                "src/main/resources/Devices.xsd");
        assertEquals(computer.getDevices().get(1), monitor);
        assertEquals(computer.getDevices().get(0), diskDrive);
    }

    @Test
    public void StaxParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        periodicalParser.setTypeOfParser("STAX");
        Computer computer = periodicalParser.parseXmlDocument("src/main/resources/test/Devices.xml",
                "src/main/resources/Devices.xsd");
        assertEquals(computer.getDevices().get(1), monitor);
        assertEquals(computer.getDevices().get(0), diskDrive);
    }

    @Test
    public void XMLHandlerTest(){
        DeviceBuilder builder = new DeviceBuilder();
        builder.setTag("name", "Disk drive");
        builder.setTag("origin", "Ukraine");
        builder.setTag("price", "100");
        builder.setTag("critical","false");
        builder.setTag("peripheral", "true");
        builder.setTag("consumption", "15");
        builder.setTag("cooler", "false");
        builder.setTag("group", "IODevices");
        builder.setTag("port", "USB");
        builder.saveElement("device");

        builder.setTag("name", "Monitor");
        builder.setTag("origin", "China");
        builder.setTag("price", "400");
        builder.setTag("critical","true");
        builder.setTag("peripheral", "true");
        builder.setTag("consumption", "45");
        builder.setTag("cooler", "false");
        builder.setTag("group", "Multimedia");
        builder.setTag("port", "LPT");
        builder.saveElement("device");

        assertEquals(diskDrive, builder.getDevices().get(0));
        assertEquals(monitor, builder.getDevices().get(1));
    }
}