package andreasaderi.L5.runners;

import andreasaderi.L5.entities.Building;
import andreasaderi.L5.enums.WorkstationType;
import andreasaderi.L5.exceptions.BuildingAlreadyExistsException;
import andreasaderi.L5.exceptions.UserAlreadyExistsException;
import andreasaderi.L5.services.BuildingService;
import andreasaderi.L5.services.UserService;
import andreasaderi.L5.services.WorkstationService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class Runner implements CommandLineRunner {

    private final Faker faker;
    private BuildingService buildingService;
    private UserService userService;
    private WorkstationService workstationService;
    private Random random;

    public Runner(BuildingService buildingService, Faker faker, UserService userService, WorkstationService workstationService, Random random) {
        this.buildingService = buildingService;
        this.userService = userService;
        this.workstationService = workstationService;
        this.faker = faker;
        this.random = random;
    }

    @Override
    public void run(String... args) throws Exception {

        //region        POPOLAMENTO AUTOMATICO DEL DB
        try {
            if (buildingService.findAll().isEmpty()) {
                for (int i = 0; i < 20; i++) {
                    buildingService.saveBuilding(new Building(faker.gameOfThrones().house(), faker.address().streetAddress(), faker.address().city()));
                }
            }
        } catch (BuildingAlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            if (userService.findAll().isEmpty()) {
                for (int i = 0; i < 25; i++) {
                    userService.saveUser(faker.name().fullName(), faker.internet().emailAddress());
                }
            }
        } catch (UserAlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            if (workstationService.findAll().isEmpty()) {
                List<Building> buildings = buildingService.findAll();
                buildings.forEach(building -> {
                    for (int i = 0; i < 3; i++) {
                        WorkstationType workstationType = switch (i) {
                            case 0 -> WorkstationType.PRIVATE;
                            case 1 -> WorkstationType.MEETING_ROOM;
                            case 2 -> WorkstationType.OPEN_SPACE;
                            default -> throw new RuntimeException("Error in for loop.");
                        };
                        workstationService.saveWorkstation(faker.lorem().sentence(10), workstationType, random.nextInt(1, 15), building);
                    }
                });
            }
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }


//endregion


    }
}
