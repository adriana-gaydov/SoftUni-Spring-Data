package exam.service.impl;

import exam.model.dto.ImportTownDTO;
import exam.model.dto.ImportTownRootDTO;
import exam.model.entities.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ValidationUtil validationUtil, ModelMapper mapper) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(TOWNS_PATH);
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        ImportTownRootDTO rootDTO = XmlParser.fromFile(TOWNS_PATH_STR, ImportTownRootDTO.class);

        return rootDTO.getTowns().stream()
                .map(this::validateAndSaveTown)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String validateAndSaveTown(ImportTownDTO d) {
        if (!validationUtil.isValid(d)) {
            return "Invalid Town";
        }

        Town town = this.mapper.map(d, Town.class);
        this.townRepository.save(town);

        return String.format("Successfully imported Town %s", town.getName());
    }
}
