package cosimocrupi.L5.services;

import cosimocrupi.L5.entities.Evento;
import cosimocrupi.L5.exceptions.BadRequestException;
import cosimocrupi.L5.exceptions.NotFoundException;
import cosimocrupi.L5.payloads.EventoDTO;
import cosimocrupi.L5.repositories.EventoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Evento save(EventoDTO payload){
        this.eventoRepository.findByPlace(payload.place()).ifPresent(evento -> {
            throw new BadRequestException("C'è già un viaggio intestato");
        });
        Evento newE = new Evento(payload.title(), payload.dateEvent(), payload.place(), payload.sitMax());
        Evento saveE = this.eventoRepository.save(newE);
        log.info("Il viaggio con id " + saveE.getId() + " è stato salvato correttamente!");
        return saveE;
    }

    public Evento findById(UUID eventoId){
        return this.eventoRepository.findById(eventoId).orElseThrow(()-> new NotFoundException(eventoId));
    }
    public Evento findByIdAndUpdate(UUID eventoId, EventoDTO payload){
        Evento fnd =this.findById(eventoId);

        if (!fnd.getPlace().equals(payload.place()))
            this.eventoRepository.findByPlace(payload.place()).ifPresent(evento -> {
                throw new BadRequestException("Il luogo " + payload.place() + " è già in uso");
            });
        fnd.setTitle(payload.title());
        fnd.setDateEvent(payload.dateEvent());
        fnd.setPlace(payload.place());
        fnd.setSitMax(payload.sitMax());

        Evento modEve = this.eventoRepository.save(fnd);
        log.info("Il viaggio con id " + fnd.getId() + " è stato modificato correttamente!");
        return modEve;
    }
    public void findByIdAndDelete(UUID eventoId){
        Evento fnd = this.findById(eventoId);
        this.eventoRepository.delete(fnd);
    }
}
