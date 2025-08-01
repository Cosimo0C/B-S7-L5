package cosimocrupi.L5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Evento {
    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String title;
    private String description;
    private LocalDate dateEvent;
    private String place;
    private int sitMax;
    private String prenotazioneId;
    private String organizzatoreId;

    public Evento(String title, String description, LocalDate dateEvent, String place, int sitMax, String prenotazioneId, String organizzatoreId) {
        this.title = title;
        this.description = description;
        this.dateEvent = dateEvent;
        this.place = place;
        this.sitMax = sitMax;
        this.prenotazioneId = prenotazioneId;
        this.organizzatoreId = organizzatoreId;
    }
}
