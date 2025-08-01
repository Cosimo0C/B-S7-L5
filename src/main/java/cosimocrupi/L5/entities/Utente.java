package cosimocrupi.L5.entities;

import cosimocrupi.L5.enums.Tipo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente implements UserDetails {
    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public Utente(String name, String surname, String email, String password, Tipo tipo) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.tipo = Tipo.UTENTE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.tipo.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
