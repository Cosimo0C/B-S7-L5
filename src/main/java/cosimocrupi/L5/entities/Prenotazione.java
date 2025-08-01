package cosimocrupi.L5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Prenotazione {
    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    private Utente utente;
    @ManyToOne
    private Evento evento;
}
