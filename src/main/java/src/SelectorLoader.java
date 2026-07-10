package src;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SelectorLoader {
    private static final Map<String, By> SELECTORS = new HashMap<>();

    static {
        loadSelectors();
    }

    private static void loadSelectors() {
        try {
            InputStream inputStream = SelectorLoader.class.getClassLoader().getResourceAsStream("src/selector.xml");
            if (inputStream == null) {
                throw new RuntimeException("selector.xml not found in classpath");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);

            // Get all <element> tags anywhere in the document
            NodeList elements = doc.getElementsByTagName("element");
            for (int i = 0; i < elements.getLength(); i++) {
                Element element = (Element) elements.item(i);
                String id = element.getAttribute("id");
                String type = element.getAttribute("type");
                String value = element.getAttribute("value");

                // Skip elements that don't have id/type/value (just in case)
                if (id.isEmpty() || type.isEmpty() || value.isEmpty()) {
                    continue;
                }

                By by;
                switch (type) {
                    // Selenium locators
                    case "id":
                        by = By.id(value);
                        break;
                    case "name":
                        by = By.name(value);
                        break;
                    case "className":
                        by = By.className(value);
                        break;
                    case "xpath":
                        by = By.xpath(value);
                        break;
                    default:
                        throw new RuntimeException("Unknown selector type: " + type);
                }

                SELECTORS.put(id, by);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load selectors", e);
        }
    }

    public static By get(String id) {
        By by = SELECTORS.get(id);
        if (by == null) {
            throw new RuntimeException("Selector not found for id: " + id);
        }
        return by;
    }
}
