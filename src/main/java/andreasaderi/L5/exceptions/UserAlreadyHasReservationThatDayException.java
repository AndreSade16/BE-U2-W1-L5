package andreasaderi.L5.exceptions;

import andreasaderi.L5.entities.User;

import java.time.LocalDate;

public class UserAlreadyHasReservationThatDayException extends RuntimeException {
    public UserAlreadyHasReservationThatDayException(User user, LocalDate reservationDay) {
        super("User " + user.getName() + " already has a reservation on day " + reservationDay);
    }
}
