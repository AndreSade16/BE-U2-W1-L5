package andreasaderi.L5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private UUID reservationId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "workstation_id", nullable = false)
    private Workstation workstation;
    @Column(name = "reservation_day", nullable = false)
    private LocalDate reservationDay;

    public Reservation(User user, Workstation workstation, LocalDate reservationDay) {
        this.user = user;
        this.workstation = workstation;
        this.reservationDay = reservationDay;
    }
}
