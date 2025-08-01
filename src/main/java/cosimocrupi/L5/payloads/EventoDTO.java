package cosimocrupi.L5.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventoDTO(
        @NotEmpty(message = "Il titolo è obbligatorio!")
        @Size(min=4, message = "Il titolo deve essere lungo almeno 4 caratteri!")
        String title,
        @FutureOrPresent(message = "La data non può essere di un giorno passato!")
        LocalDate dateEvent,
        @NotEmpty(message = "Il luogo è obbligatorio!")
        @Size(min=4, message = "Il luogo deve essere lungo almeno 4 caratteri!")
        String place,
        @NotEmpty(message = "Il numero massimo di posti è obbligatorio!")
        int sitMax
) {
}
