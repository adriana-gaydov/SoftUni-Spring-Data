package exam.service.impl;

import exam.model.dto.ImportShopDTO;
import exam.model.dto.ImportShopRootDTO;
import exam.model.entities.Shop;
import exam.model.entities.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;
    private TownRepository townRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, ValidationUtil validationUtil, ModelMapper mapper, TownRepository townRepository) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(SHOPS_PATH);
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ImportShopRootDTO rootDTO = XmlParser.fromFile(SHOPS_PATH_STR, ImportShopRootDTO.class);

        return rootDTO.getShops().stream()
                .map(this::validateAndSaveShop)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String validateAndSaveShop(ImportShopDTO d) {
        if (!validationUtil.isValid(d)) {
            return "Invalid Shop";
        }

        Optional<Shop> optionalShop = this.shopRepository.findByName(d.getName());
        if(optionalShop.isPresent()) {
            return "Invalid Shop";
        }

        Shop shop = this.mapper.map(d, Shop.class);
        Town town = this.townRepository.findByName(d.getTownName().getName()).get();

        shop.setTown(town);

        this.shopRepository.save(shop);

        return String.format("Successfully imported Shop %s - %s", shop.getName(), shop.getIncome());
    }
}
