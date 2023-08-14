package com.claudiocarige.desafioStartDB.services;

import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.representation.PedidoRepresentation;

import java.util.List;

public interface PedidoService {
    Pedido findById(Long id);

    List<Pedido> findAll();

    Pedido insert(PedidoRepresentation obj);

    Pedido update(PedidoRepresentation obj);

    void delete(Long id);
}
