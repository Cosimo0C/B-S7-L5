package cosimocrupi.L5.payloads;

import jakarta.validation.constraints.NotEmpty;

public record PrenotazioneDTO(
        @NotEmpty(message = "L'evento è obbligatorio!")
        String eventoId,
        @NotEmpty(message = "L'utente è obbligatorio!")
        String utenteId) {
}
