package br.com.bankeng.limitessvc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table
@Data
@EqualsAndHashCode(of = "id")
public class LimiteDiario {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private Long agencia;

    private Long conta;

    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
