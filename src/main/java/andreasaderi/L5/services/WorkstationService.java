package andreasaderi.L5.services;

import andreasaderi.L5.entities.Building;
import andreasaderi.L5.entities.Workstation;
import andreasaderi.L5.enums.WorkstationType;
import andreasaderi.L5.repositories.WorkstationRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkstationService {

    private final WorkstationRepository workstationRepository;

    public WorkstationService(WorkstationRepository workstationRepository, Faker faker) {
        this.workstationRepository = workstationRepository;
    }

    public void saveWorkstation(String description, WorkstationType workstationType, int maxOccupancy, Building building) {
        workstationRepository.save(new Workstation(description, workstationType, maxOccupancy, building));
    }

    public List<Workstation> findAll() {
        return workstationRepository.findAll();
    }
}
