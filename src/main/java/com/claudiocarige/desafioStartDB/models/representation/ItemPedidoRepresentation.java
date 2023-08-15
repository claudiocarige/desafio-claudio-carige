package com.claudiocarige.desafioStartDB.models.representation;

import com.claudiocarige.desafioStartDB.models.ItemPedido;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class ItemPedidoRepresentation {
    private Integer id;
    private String item;
    private Integer quantidade;
    private Long numeroPedido;

    public ItemPedidoRepresentation (ItemPedido obj){
        this.id = obj.getId();
        this.item = obj.getItem().getCodigo();
        this.quantidade = obj.getQuantidade();
        this.numeroPedido = obj.getPedido().getId();

    }
}


