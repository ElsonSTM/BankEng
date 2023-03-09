package br.com.bankeng.transactionbff.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BeneficiarioDto {

    private static final long serialVersionUID = 2806421543985360625L;

    @Schema(description = "CPF do beneficiário")
    @NotNull(message = "Informar o CPF")
    private Long CPF;
    @NotNull(message = "Informar o código do banco de destino.")
    @Schema(description = "Código do banco destino")
    private Long codigoBanco;
    @NotNull(message = "Informar a agência de destino")
    @Schema(description = "Agência destino")
    private String agencia;
    @NotNull(message = "Informar o conta de destino.")
    @Schema(description = "Conta de destino")
    private String conta;
    @NotNull(message = "Informar o nome do Favorecio.")
    @Schema(description = "Nome do Favorecido")
    private String nomeFavorecido;
}
