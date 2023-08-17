package com.claudiocarige.desafioStartDB.models.representation;

import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.enums.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PagamentoRepresentation {
    protected Long numeroPedido;
    protected FormaPagamento formaPagamento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    protected BigDecimal valorTotalPagamento;
    @JsonFormat(pattern = "mm")
    protected LocalDateTime tempoPedido;

    public PagamentoRepresentation(Pedido pedido){
        this.numeroPedido = pedido.getId();
        this.formaPagamento = pedido.getFormaPagamento();
        this.valorTotalPagamento = new BigDecimal(pedido.getValorTotalPagamento()).setScale(2, RoundingMode.HALF_EVEN);
        this.tempoPedido = acrescentarTempoPreparo();
    }
    public LocalDateTime acrescentarTempoPreparo(){
        return LocalDateTime.now().plusMinutes(20);
    }
}
