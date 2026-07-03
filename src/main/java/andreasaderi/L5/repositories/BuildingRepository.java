package andreasaderi.L5.repositories;

import andreasaderi.L5.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BuildingRepository extends JpaRepository<Building, UUID> {
    boolean existsByNameAndCity(String name, String city);
}
