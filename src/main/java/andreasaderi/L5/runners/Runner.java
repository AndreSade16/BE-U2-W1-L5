package andreasaderi.L5.runners;

import andreasaderi.L5.entities.Building;
import andreasaderi.L5.entities.Reservation;
import andreasaderi.L5.entities.User;
import andreasaderi.L5.entities.Workstation;
import andreasaderi.L5.enums.WorkstationType;
import andreasaderi.L5.exceptions.*;
import andreasaderi.L5.services.BuildingService;
import andreasaderi.L5.services.ReservationService;
import andreasaderi.L5.services.UserService;
import andreasaderi.L5.services.WorkstationService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final Faker faker;
    private BuildingService buildingService;
    private UserService userService;
    private WorkstationService workstationService;
    private ReservationService reservationService;
    private Random random;

    public Runner(BuildingService buildingService, Faker faker, UserService userService, WorkstationService workstationService, ReservationService reservationService, Random random) {
        this.buildingService = buildingService;
        this.userService = userService;
        this.workstationService = workstationService;
        this.faker = faker;
        this.random = random;
        this.reservationService = reservationService;
    }

    @Override
    public void run(String... args) throws Exception {

        //region        POPOLAMENTO AUTOMATICO DEL DB
        try {
            if (buildingService.findAll().isEmpty()) {
                for (int i = 0; i < 20; i++) {
                    buildingService.saveBuilding(faker.gameOfThrones().house(), faker.address().streetAddress(), faker.address().city());
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

        try {
            if (reservationService.findAll().isEmpty()) {
                List<User> users = userService.findAll();
                List<Workstation> workstations = workstationService.findAll();
                for (int i = 0; i < 40; i++) {

                    reservationService.saveReservation(users.get(random.nextInt(0, users.size())), workstations.get(random.nextInt(0, workstations.size())), faker.date().future(150, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }
            }
        } catch (WorkstationAlreadyBookedException ex) {
            System.out.println(ex.getMessage());
        }
//endregion

        // region           TESTING
        User userFromDb = null;
        try {
            userFromDb = userService.findById("eb9a93db-9c92-428d-9231-c034664e315b");
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        Workstation workstationFromDb = null;
        try {
            workstationFromDb = workstationService.findById("01a47740-181a-402b-bb91-da07a98406dc");
        } catch (WorkstationNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            reservationService.saveReservation(userFromDb, workstationFromDb, LocalDate.of(2026, 10, 11));
        } catch (WorkstationAlreadyBookedException | UserAlreadyHasReservationThatDayException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            buildingService.saveBuilding("ciaone", null, null);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            userService.findById("d345a6a2-dc78-4d68-a3e8-ac3892de012a");
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            userService.saveUser(" ", "ciaone");
        } catch (RequiredFieldIsNullException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            workstationService.saveWorkstation(null, null, 0, null);
        } catch (RequiredFieldIsNullException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            Reservation reservationFromDb = reservationService.findById("d345a6a2-dc78-4d68-a3e8-ac3892de012b");
        } catch (ReservationNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

//endregion

    }
}
