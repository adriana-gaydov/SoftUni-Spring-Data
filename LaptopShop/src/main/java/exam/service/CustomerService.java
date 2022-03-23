package exam.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//ToDo - Implement all methods
public interface CustomerService {

    Path CUSTOMERS_PATH = Paths.get("src/main/resources/files/json/customers.json");

    boolean areImported();

    String readCustomersFileContent() throws IOException;

    String importCustomers() throws IOException;

}
