package com.claudiocarige.desafioStartDB.services;

import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.representation.ItemPedidoRepresentation;

import java.util.List;

public interface PedidoService {
    Pedido findById(Long id);

    List<Pedido> findAll();

    Pedido insert(ItemPedidoRepresentation obj);

    Pedido update(ItemPedidoRepresentation obj);

    void delete(Long id);
}
