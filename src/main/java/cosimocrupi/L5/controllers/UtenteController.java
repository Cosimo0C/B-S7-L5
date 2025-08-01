package cosimocrupi.L5.controllers;

import cosimocrupi.L5.entities.Utente;
import cosimocrupi.L5.exceptions.ValidationException;
import cosimocrupi.L5.payloads.UtenteDTO;
import cosimocrupi.L5.payloads.UtenteRespDTO;
import cosimocrupi.L5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/me")
    public Utente getMeProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public Utente updatemeProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser, @RequestBody @Validated UtenteDTO payload) {
        return this.utenteService.findByIdAndUpdate(currentAuthenticatedUser.getId(), payload);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteRespDTO save(@RequestBody @Validated UtenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Utente newUser = this.utenteService.save(payload);
            return new UtenteRespDTO(newUser.getId());
        }
    }

    @GetMapping("/{userId}")
    public Utente getById(@PathVariable UUID userId) {
        return this.utenteService.findById(userId);
    }

    @PutMapping("/{userId}")
    public Utente getByIdAndUpdate(@PathVariable UUID userId, @RequestBody UtenteDTO payload) {
        return this.utenteService.findByIdAndUpdate(userId, payload);
    }

}
