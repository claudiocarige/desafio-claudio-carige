package com.claudiocarige.desafioStartDB.services;

import com.claudiocarige.desafioStartDB.models.ItemPedidoInfo;
import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.representation.PedidoRepresentation;

import java.util.List;

public interface PedidoService {
    Pedido findById(Long id);

    List<Pedido> findAll();

    Pedido insert(PedidoRepresentation obj);

    Pedido update(Long id, PedidoRepresentation obj);

    void delete(Long id);

    void listIsEmpty(List<ItemPedidoInfo> listPedidos);

    Pedido calcularValorParaPagamento(Long id);
}
