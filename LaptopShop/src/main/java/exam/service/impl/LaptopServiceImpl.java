package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.ImportLaptopDTO;
import exam.model.entities.Laptop;
import exam.model.entities.Shop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.LaptopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository, ValidationUtil validationUtil, ModelMapper mapper, Gson gson) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(LAPTOPS_PATH);
    }

    @Override
    public String importLaptops() throws IOException {
        ImportLaptopDTO[] dtos = gson.fromJson(readLaptopsFileContent(), ImportLaptopDTO[].class);

        return Arrays.stream(dtos)
                .map(this::validateAndSaveLaptop)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String validateAndSaveLaptop(ImportLaptopDTO d) {
        if (!validationUtil.isValid(d)) {
            return "Invalid Laptop";
        }

        Optional<Laptop> optionalLaptop = this.laptopRepository.findByMacAddress(d.getMacAddress());
        if (optionalLaptop.isPresent()) {
            return "Invalid Laptop";
        }

        Laptop laptop = this.mapper.map(d, Laptop.class);

        Shop shop = this.shopRepository.findByName(d.getShop().getName()).get();
        laptop.setShop(shop);

        this.laptopRepository.save(laptop);

        return String.format("Successfully imported Laptop %s - %.2f %d %d",
                laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRam(), laptop.getStorage());
    }

    @Override
    public String exportBestLaptops() {
        List<Laptop> laptops = this.laptopRepository.findByOrderByCpuSpeedDescRamDescStorageDescMacAddress();

        return laptops.stream()
                .map(Laptop::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
