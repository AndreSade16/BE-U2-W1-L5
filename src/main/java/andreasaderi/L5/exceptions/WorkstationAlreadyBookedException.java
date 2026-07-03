package andreasaderi.L5.exceptions;

import java.time.LocalDate;

public class WorkstationAlreadyBookedException extends RuntimeException {
    public WorkstationAlreadyBookedException(LocalDate reservationDay) {
        super("This workstation is already booked on day " + reservationDay);
    }
}
