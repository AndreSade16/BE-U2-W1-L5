package andreasaderi.L5.exceptions;

public class WorkstationNotFoundException extends RuntimeException {
    public WorkstationNotFoundException(String workstationId) {
        super("Workstation with id " + workstationId + " not found.");
    }
}
