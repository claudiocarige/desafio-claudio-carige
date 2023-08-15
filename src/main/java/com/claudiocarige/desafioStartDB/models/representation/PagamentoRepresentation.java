package com.claudiocarige.desafioStartDB.models.representation;

import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.enums.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PagamentoRepresentation {
    protected Long numeroPedido;
    protected FormaPagamento formaPagamento;
    protected Float valorTotalPagamento;
    @JsonFormat(pattern = "HH:mm")
    protected LocalDateTime tempoPedido;


    public PagamentoRepresentation(Pedido pedido){
        this.numeroPedido = pedido.getId();
        this.formaPagamento = pedido.getFormaPagamento();
        this.valorTotalPagamento = pedido.getValorTotalPagamento();
        this.tempoPedido = acrescentarTempoPreparo(pedido.getDataPedido());
    }
    public LocalDateTime acrescentarTempoPreparo(LocalDateTime time){
        return time.plusMinutes(20);
    }
}
