package br.com.bankeng.transactionbff.api;

import br.com.bankeng.transactionbff.dto.LimiteDiario;
import br.com.bankeng.transactionbff.feign.LimiteClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limites")
public class LimiteController {

    public LimiteController(LimiteClient limiteClient) {
        this.limiteClient = limiteClient;
    }

    private LimiteClient limiteClient;


    @GetMapping(value = "/{agencia}/{conta}")
   public LimiteDiario buscarLimiteDiario(@PathVariable("agencia") final Long agencia, @PathVariable("conta") Long conta){

        return limiteClient.buscarLimiteDiario(agencia, conta);
    }
}