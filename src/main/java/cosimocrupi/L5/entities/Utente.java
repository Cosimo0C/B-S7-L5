package cosimocrupi.L5.entities;

import cosimocrupi.L5.enums.Tipo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@ToString
public class Utente {
    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Tipo tipo;

    public Utente(String name, String surname, String email, String password, Tipo tipo) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }
}
