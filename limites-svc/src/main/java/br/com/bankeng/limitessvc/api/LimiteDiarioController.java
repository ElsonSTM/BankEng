package br.com.bankeng.limitessvc.api;

import br.com.bankeng.limitessvc.entity.LimiteDiario;
import br.com.bankeng.limitessvc.service.LimiteDiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
public class LimiteDiarioController {

    LimiteDiarioService limiteDiarioService;

    public LimiteDiarioController(LimiteDiarioService limiteDiarioService) {
        this.limiteDiarioService = limiteDiarioService;
    }
    @GetMapping(value = "/limite-diario/{agencia}/{conta}")
    public LimiteDiario buscarLimiteDiario(@PathVariable("agencia") final Long agencia, @PathVariable("conta") final Long conta) {
        final Optional<LimiteDiario> limiteDiario = limiteDiarioService.buscarLimiteDiario(agencia, conta);
        if (limiteDiario.isPresent()) {
            return limiteDiario.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }
    @GetMapping(value = "/limite-diario/{id}")
    public LimiteDiario findById(@PathVariable("id") Long id) {

        Optional<LimiteDiario> limiteDiarioOpt = limiteDiarioService.findById(id);
        if (limiteDiarioOpt.isPresent()) {
            return limiteDiarioOpt.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }
}
