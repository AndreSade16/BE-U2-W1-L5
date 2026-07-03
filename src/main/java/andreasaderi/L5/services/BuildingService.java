package andreasaderi.L5.services;

import andreasaderi.L5.entities.Building;
import andreasaderi.L5.exceptions.BuildingAlreadyExistsException;
import andreasaderi.L5.repositories.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public void saveBuilding(Building newBuilding) {
        if (buildingRepository.existsByNameAndCity(newBuilding.getName(), newBuilding.getCity()))
            throw new BuildingAlreadyExistsException(newBuilding.getName(), newBuilding.getCity());
        else {
            buildingRepository.save(newBuilding);
        }
    }

    public List<Building> findAll() {
        return buildingRepository.findAll();
    }
}
