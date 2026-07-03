package andreasaderi.L5.runners;

import andreasaderi.L5.entities.Building;
import andreasaderi.L5.exceptions.BuildingAlreadyExistsException;
import andreasaderi.L5.services.BuildingService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private BuildingService buildingService;
    private Faker faker;

    public Runner(BuildingService buildingService, Faker faker) {
        this.buildingService = buildingService;
        this.faker = faker;
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
        

//endregion


    }
}
