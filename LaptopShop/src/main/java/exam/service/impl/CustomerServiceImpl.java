package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.ImportCustomerDTO;
import exam.model.entities.Customer;
import exam.model.entities.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;
    private TownRepository townRepository;
    private final Gson gson;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ValidationUtil validationUtil, ModelMapper mapper, TownRepository townRepository, Gson gson) {
        this.customerRepository = customerRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.townRepository = townRepository;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(CUSTOMERS_PATH);
    }

    @Override
    public String importCustomers() throws IOException {
        ImportCustomerDTO[] dtos = this.gson.fromJson(readCustomersFileContent(), ImportCustomerDTO[].class);

        return Arrays.stream(dtos)
                .map(this::validateAndSaveCustomer)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String validateAndSaveCustomer(ImportCustomerDTO d) {
        if (!validationUtil.isValid(d)) {
            return "Invalid Customer";
        }

        Optional<Customer> optionalCustomer = this.customerRepository.findByEmail(d.getEmail());
        if (optionalCustomer.isPresent()) {
            return "Invalid Customer";
        }

        Customer customer = this.mapper.map(d, Customer.class);
        Town town = this.townRepository.findByName(d.getTownName().getName()).get();

        customer.setTown(town);

        this.customerRepository.save(customer);

        return String.format("Successfully imported Customer %s %s - %s",
                customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }
}
