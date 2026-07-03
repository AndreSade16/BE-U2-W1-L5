package andreasaderi.L5.services;

import andreasaderi.L5.repositories.WorkstationRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkstationService {

    private final WorkstationRepository workstationRepository;

    public WorkstationService(WorkstationRepository workstationRepository) {
        this.workstationRepository = workstationRepository;
    }
}
