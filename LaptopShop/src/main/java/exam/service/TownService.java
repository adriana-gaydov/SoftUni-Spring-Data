package exam.service;



import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//ToDo - Implement all methods
public interface TownService {

    Path TOWNS_PATH = Paths.get("src/main/resources/files/xml/towns.xml");
    String TOWNS_PATH_STR = "src/main/resources/files/xml/towns.xml";

    boolean areImported();

    String readTownsFileContent() throws IOException;
	
	String importTowns() throws JAXBException, FileNotFoundException;
}
