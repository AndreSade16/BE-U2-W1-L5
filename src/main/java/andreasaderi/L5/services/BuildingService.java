package andreasaderi.L5.services;

import andreasaderi.L5.repositories.BuildingRepository;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }
}
