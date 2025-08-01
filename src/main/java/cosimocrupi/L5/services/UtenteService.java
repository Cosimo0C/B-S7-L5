package cosimocrupi.L5.services;

import cosimocrupi.L5.entities.Utente;
import cosimocrupi.L5.exceptions.BadRequestException;
import cosimocrupi.L5.exceptions.NotFoundException;
import cosimocrupi.L5.payloads.UtenteDTO;
import cosimocrupi.L5.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utente save(UtenteDTO payload){
        this.utenteRepository.findByEmail(payload.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + utente.getEmail() + " è già in uso!");
        });
        Utente newU = new Utente(payload.name(), payload.surname(), payload.email(), payload.password(), payload.tipo());
        Utente saveUt = this.utenteRepository.save(newU);
        log.info("L'utente è stato creato correttamente!");
        return saveUt;
    }
    public Utente findById(UUID utenteId) {
        return this.utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }
    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }
    public Utente findByIdAndUpdate(UUID utenteId, UtenteDTO payload) {
        Utente found = this.findById(utenteId);

        if (!found.getEmail().equals(payload.email())) // Il controllo dell'email lo faccio solo quando effettivamente mi sta passando una nuova email
            this.utenteRepository.findByEmail(payload.email()).ifPresent(utente -> {
                throw new BadRequestException("L'email " + utente.getEmail() + " è già in uso!");
            });

        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());
        found.setPassword(payload.password());
        found.setTipo(payload.tipo());

        Utente modUtente = this.utenteRepository.save(found);

        log.info("L'utente con id " + found.getId() + " è stato modificato!");

        return modUtente;
    }
}
