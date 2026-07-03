package andreasaderi.L5.entities;

import andreasaderi.L5.enums.WorkstationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "workstations")
public class Workstation {
    @Id
    @GeneratedValue
    @Column(name = "workstation_id")
    private UUID workstationId;
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkstationType type;
    @Column(name = "max_occupancy", nullable = false)
    private int maxOccupancy;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public Workstation(String description, WorkstationType type, int maxOccupancy, Building building) {
        this.description = description;
        this.type = type;
        this.maxOccupancy = maxOccupancy;
        this.building = building;
    }
}
