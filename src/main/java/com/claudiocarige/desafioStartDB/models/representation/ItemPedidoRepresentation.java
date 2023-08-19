package com.claudiocarige.desafioStartDB.models.representation;

import com.claudiocarige.desafioStartDB.models.ItemPedido;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoRepresentation {
    protected Integer id;
    protected String item;
    protected Integer quantidade;
    protected Long numeroPedido;

    public ItemPedidoRepresentation(ItemPedido obj) {
        this.id = obj.getId();
        this.item = obj.getItem().getCodigo();
        this.quantidade = obj.getQuantidade();
        this.numeroPedido = obj.getPedido().getId();

    }
}


