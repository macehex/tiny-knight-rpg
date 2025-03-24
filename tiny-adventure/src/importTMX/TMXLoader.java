package importTMX;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import java.io.File;

public class TMXLoader {
    public static void main(String[] args) {
        try {
            File file = new File("/maps/maptesttxt.txt");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList tileLayers = doc.getElementsByTagName("layer");
            for (int i = 0; i < tileLayers.getLength(); i++) {
                Element layer = (Element) tileLayers.item(i);
                String layerName = layer.getAttribute("name");
                System.out.println("Layer: " + layerName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
