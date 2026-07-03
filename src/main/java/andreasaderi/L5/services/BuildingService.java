package andreasaderi.L5.services;

import andreasaderi.L5.entities.Building;
import andreasaderi.L5.exceptions.BuildingAlreadyExistsException;
import andreasaderi.L5.exceptions.RequiredFieldIsNullException;
import andreasaderi.L5.repositories.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public void saveBuilding(String name, String address, String city) {
        if (name == null) throw new RequiredFieldIsNullException("Building name can't be null");
        if (address == null) throw new RequiredFieldIsNullException("Building address can't be null");
        if (city == null) throw new RequiredFieldIsNullException("Building city can't be null");
        if (buildingRepository.existsByNameAndCity(name, city))
            throw new BuildingAlreadyExistsException(name, city);
        else {
            buildingRepository.save(new Building(name, address, city));
        }
    }

    public List<Building> findAll() {
        return buildingRepository.findAll();
    }
}
