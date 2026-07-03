package andreasaderi.L5.services;

import andreasaderi.L5.entities.Reservation;
import andreasaderi.L5.entities.User;
import andreasaderi.L5.entities.Workstation;
import andreasaderi.L5.exceptions.RequiredFieldIsNullException;
import andreasaderi.L5.exceptions.UserAlreadyHasReservationThatDayException;
import andreasaderi.L5.exceptions.WorkstationAlreadyBookedException;
import andreasaderi.L5.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void saveReservation(User user, Workstation workstation, LocalDate reservationDay) {
        if (user == null) throw new RequiredFieldIsNullException("User field can't be null");
        if (workstation == null) throw new RequiredFieldIsNullException("Workstation field can't be null");
        if (reservationDay == null) throw new RequiredFieldIsNullException("Reservation field can't be null");
        if (reservationDay.isBefore(LocalDate.now()))
            throw new RuntimeException("Reservation day can't be set in the past.");
        else if (reservationRepository.existsByWorkstationAndReservationDay(workstation, reservationDay))
            throw new WorkstationAlreadyBookedException(reservationDay);
        else if (reservationRepository.existsByUserAndReservationDay(user, reservationDay))
            throw new UserAlreadyHasReservationThatDayException(user, reservationDay);
        else {
            reservationRepository.save(new Reservation(user, workstation, reservationDay));
            System.out.println("Reservation saved.");
        }

    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation findById(String reservationId) {
        return reservationRepository.findById(UUID.fromString(reservationId)).orElseThrow();
    }
}
