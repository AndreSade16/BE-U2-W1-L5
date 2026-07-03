package andreasaderi.L5.repositories;

import andreasaderi.L5.entities.Reservation;
import andreasaderi.L5.entities.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    boolean existsByWorkstationAndReservationDay(Workstation workstation, LocalDate reservationDay);
}
