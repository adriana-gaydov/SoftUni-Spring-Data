package exam.service;


import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//ToDo - Implement all methods
public interface ShopService {

    Path SHOPS_PATH = Paths.get("src/main/resources/files/xml/shops.xml");
    String SHOPS_PATH_STR = "src/main/resources/files/xml/shops.xml";

    boolean areImported();

    String readShopsFileContent() throws IOException;

    String importShops() throws JAXBException, FileNotFoundException;
}
