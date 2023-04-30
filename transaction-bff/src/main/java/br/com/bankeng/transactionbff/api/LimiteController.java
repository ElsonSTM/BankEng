package br.com.bankeng.transactionbff.api;

import br.com.bankeng.transactionbff.domain.LimiteService;
import br.com.bankeng.transactionbff.dto.LimiteDiario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limites")
public class LimiteController {

    public LimiteController(LimiteService limiteService) {
        this.limiteService = limiteService;
    }

    private LimiteService limiteService;


    @GetMapping(value = "/{agencia}/{conta}")
   public LimiteDiario buscarLimiteDiario(@PathVariable("agencia") final Long agencia, @PathVariable("conta") final Long conta){

        return limiteService.buscarLimiteDiario(agencia, conta);
    }
}
