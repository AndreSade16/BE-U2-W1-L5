package andreasaderi.L5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue
    @Column(name = "building_id")
    private UUID buildingId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;

    public Building(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }
}
