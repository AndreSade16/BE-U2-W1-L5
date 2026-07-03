package andreasaderi.L5.services;

import andreasaderi.L5.entities.Reservation;
import andreasaderi.L5.entities.User;
import andreasaderi.L5.entities.Workstation;
import andreasaderi.L5.exceptions.WorkstationAlreadyBookedException;
import andreasaderi.L5.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void saveReservation(User user, Workstation workstation, LocalDate reservationDay) {
        if (reservationDay.isBefore(LocalDate.now()))
            throw new RuntimeException("Reservation day can't be set in the past.");
        else if (reservationRepository.existsByWorkstationAndReservationDay(workstation, reservationDay))
            throw new WorkstationAlreadyBookedException(reservationDay);
        else {
            reservationRepository.save(new Reservation(user, workstation, reservationDay));
            System.out.println("Reservation saved.");
        }

    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
