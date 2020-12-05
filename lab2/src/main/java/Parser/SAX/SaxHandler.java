package Parser.SAX;

import Parser.DeviceBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {
    private DeviceBuilder deviceBuilder;
    private StringBuilder data;

    public SaxHandler(DeviceBuilder deviceBuilder){
        this.deviceBuilder = deviceBuilder;
        data = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equalsIgnoreCase(deviceBuilder.getRootName()))
            deviceBuilder.saveElement(qName);
        deviceBuilder.setTag(qName, data.toString());
        data = new StringBuilder();
    }


    @Override
    public void characters(char ch[], int start, int length) {
        data.append(new String(ch, start, length));
    }


}