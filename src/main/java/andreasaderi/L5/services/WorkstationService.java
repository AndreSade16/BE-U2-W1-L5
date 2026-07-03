package andreasaderi.L5.services;

import andreasaderi.L5.entities.Building;
import andreasaderi.L5.entities.Workstation;
import andreasaderi.L5.enums.WorkstationType;
import andreasaderi.L5.exceptions.RequiredFieldIsNullException;
import andreasaderi.L5.repositories.WorkstationRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkstationService {

    private final WorkstationRepository workstationRepository;

    public WorkstationService(WorkstationRepository workstationRepository, Faker faker) {
        this.workstationRepository = workstationRepository;
    }

    public void saveWorkstation(String description, WorkstationType workstationType, int maxOccupancy, Building building) {
        if (workstationType == null) throw new RequiredFieldIsNullException("Workstation type can't be null");
        if (maxOccupancy < 1) throw new RequiredFieldIsNullException("Max occupancy can't be less than 1");
        if (building == null) throw new RequiredFieldIsNullException("Building can't be null");
        workstationRepository.save(new Workstation(description, workstationType, maxOccupancy, building));
    }

    public List<Workstation> findAll() {
        return workstationRepository.findAll();
    }

    public Workstation findById(String workstationId) {
        Optional<Workstation> result = workstationRepository.findById(UUID.fromString(workstationId));
        return result.orElseThrow();
    }
}
