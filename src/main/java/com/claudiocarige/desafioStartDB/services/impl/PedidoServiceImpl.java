package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.representation.ItemPedidoRepresentation;
import com.claudiocarige.desafioStartDB.repositories.PedidoRepository;
import com.claudiocarige.desafioStartDB.services.PedidoService;
import com.claudiocarige.desafioStartDB.services.exceptions.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ModelMapper mapper;
    @Override
    public Pedido findById(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new NoSuchElementException("Pedido não encontrado."));
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido insert(ItemPedidoRepresentation obj) {
        return null;
    }

    @Override
    public Pedido update(ItemPedidoRepresentation obj) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
