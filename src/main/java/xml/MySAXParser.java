package xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class MySAXParser {
    public static void main(String[] args) throws IOException {
        String filepath = "C:\\Programming\\Java\\untitled\\resources\\1.xml";
        String content = new String(Files.readAllBytes(Paths.get(filepath)));


        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            final String[] lastModified = new String[1];
            DefaultHandler handler = new DefaultHandler() {

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("location")) {
                        lastModified[0] = attributes.getValue("lastModified");
                    }
                }

            };
            saxParser.parse(filepath, handler);
            System.out.println(lastModified[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

