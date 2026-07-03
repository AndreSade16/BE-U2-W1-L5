package andreasaderi.L5.exceptions;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String reservationId) {
        super("Reservation with id " + reservationId + " not found");
    }
}
