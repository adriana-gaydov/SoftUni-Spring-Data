package exam.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//ToDo - Implement all methods
public interface LaptopService {

    Path LAPTOPS_PATH = Paths.get("src/main/resources/files/json/laptops.json");

    boolean areImported();

    String readLaptopsFileContent() throws IOException;

    String importLaptops() throws IOException;

    String exportBestLaptops();
}
