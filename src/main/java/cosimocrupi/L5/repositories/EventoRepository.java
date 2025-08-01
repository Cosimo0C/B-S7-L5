package cosimocrupi.L5.repositories;

import cosimocrupi.L5.entities.Evento;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventoRepository {
    Optional<Evento> findByPlace(String place);
}
