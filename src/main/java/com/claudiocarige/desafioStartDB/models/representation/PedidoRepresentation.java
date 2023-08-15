package com.claudiocarige.desafioStartDB.models.representation;

import com.claudiocarige.desafioStartDB.models.ItemPedidoInfo;
import com.claudiocarige.desafioStartDB.models.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PedidoRepresentation {
    protected Long id;
    protected String formaPagamento;
    protected List<ItemPedidoInfo> listPedidos = new ArrayList<>();
    protected Float valorPedido;
    protected Float valorTotalPagamento;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime dataPedido = LocalDateTime.now();

    public PedidoRepresentation(Pedido obj) {
        this.id = obj.getId();
        this.formaPagamento = obj.getFormaPagamento().toString();
        this.listPedidos = obj.getListPedidos().stream().map(itemPedido -> new ItemPedidoInfo(itemPedido.getItem().getCodigo(), itemPedido.getQuantidade()))
                .collect(Collectors.toList());
        this.valorPedido = obj.getValorPedido();
        this.valorTotalPagamento = obj.getValorTotalPagamento();
        this.dataPedido = obj.getDataPedido();
    }
}
