package br.com.bankeng.transactionbff.api;

import br.com.bankeng.transactionbff.domain.TransactionService;
import br.com.bankeng.transactionbff.dto.RequestTransactionDto;
import br.com.bankeng.transactionbff.dto.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/transaction")
@Tag(name = "/transaction", description = "Grupo de API's para manipulação de tramsações financeiras")
public class TransactionController {

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    private TransactionService transactionService;

    @Operation(description = "Api para criar uma transação financeira")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno Ok com a transação criada."),
            @ApiResponse(responseCode = "401", description = "Erro de autorização dessa Api"),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa Api"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> enviarTransacao(@RequestBody final RequestTransactionDto requestTransactionDto) {
    final Optional<TransactionDto> transactionDto = transactionService.save(requestTransactionDto);
    if(transactionDto.isPresent()) {
        return Mono.just(transactionDto.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    @Operation(description = "Api para buscar as transações persistidas por id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno Ok com a transação criada."),
            @ApiResponse(responseCode = "401", description = "Erro de autorização dessa Api"),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa Api"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado")
    })
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> buscarTransacao(@PathVariable("id") final String uuid){
    final Optional<TransactionDto> transactionDto = transactionService.findById(uuid);
    if(transactionDto.isPresent()) {
        return Mono.just(transactionDto.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");

    }

    @Operation(description = "Api para remover as transações persistidas")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno Ok com a remoção"),
            @ApiResponse(responseCode = "401", description = "Erro de autorização dessa Api"),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa Api"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado")})
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionDto> removerTransacao(@PathVariable("id") final String uuid){
        return Mono.empty();
    }
    @Operation(description = "Api para autorizar a transação financeira")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno Ok, autorizado a transação financeira"),
            @ApiResponse(responseCode = "401", description = "Erro de autorização dessa Api"),
            @ApiResponse(responseCode = "403", description = "Erro de autorização dessa Api"),
            @ApiResponse(responseCode = "404", description = "recurso não encontrado")})
    @Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
    @PatchMapping(value = "/{id}/confirmar")
    public Mono<TransactionDto> confirmarTransacao(@PathVariable("id") final String uuid) {
        return Mono.empty();
    }
}