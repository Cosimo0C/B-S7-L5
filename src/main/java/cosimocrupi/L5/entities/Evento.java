package cosimocrupi.L5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@NoArgsConstructor
public class Evento {
    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String title;
    private String description;
    private LocalDate dateEvent;
    private String place;
    private int sitMax;

    @ManyToOne
    private Utente organizzatore;

    public Evento(String title, LocalDate dateEvent, String place, int sitMax) {
        this.title = title;
        this.dateEvent = dateEvent;
        this.place = place;
        this.sitMax = sitMax;
    }
}
