package andreasaderi.L5.exceptions;

public class BuildingAlreadyExistsException extends RuntimeException {
    public BuildingAlreadyExistsException(String name, String city) {
        super("Building named " + name + " already exists in " + city + " city.");
    }
}
