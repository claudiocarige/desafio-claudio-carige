package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.ItemPedido;
import com.claudiocarige.desafioStartDB.models.ItemPedidoInfo;
import com.claudiocarige.desafioStartDB.models.ItensCardapio;
import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.enums.FormaPagamento;
import com.claudiocarige.desafioStartDB.models.representation.PedidoRepresentation;
import com.claudiocarige.desafioStartDB.repositories.ItensCardapioRepository;
import com.claudiocarige.desafioStartDB.repositories.PedidoRepository;
import com.claudiocarige.desafioStartDB.services.PedidoService;
import com.claudiocarige.desafioStartDB.services.exceptions.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ItensCardapioRepository itensCardapioRepository;
    private final PedidoRepository pedidoRepository;

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
    public Pedido insert(PedidoRepresentation pedidoRepresentation) {
        Pedido pedido = new Pedido();
        ItensCardapio itensCardapio;
        pedido.setId(null);
        pedido.setFormaPagamento(FormaPagamento.valueOf(pedidoRepresentation.getFormaPagamento()));
        for (ItemPedidoInfo item:pedidoRepresentation.getListPedidos()) {
            itensCardapio = findByCodigo(item.getCodigoItem());
            ItemPedido itemPedido = new ItemPedido(itensCardapio, item.getQuantidade(),pedido);
            pedido.addItensCardapio(itemPedido);
        }
        pedido.calcularValorPedido();
        pedido.setValorTotalPedido(calcularValorDaCompra(pedido));
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(PedidoRepresentation obj) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    public ItensCardapio findByCodigo(String codigo) {
        Optional<ItensCardapio> itensCardapio  = itensCardapioRepository.findByCodigo(codigo);
        return itensCardapio.orElseThrow(() -> new NoSuchElementException("Pedido não encontrado."));
    }

    private Float calcularValorDaCompra(Pedido pedido){
        if(pedido.getFormaPagamento().equals("DEBITO")){
            pedido.setValorTotalPedido(pedido.getValorTotalPedido() * 0.95f);
        }else if (pedido.getFormaPagamento().equals("CREDITO")){
            pedido.setValorTotalPedido(pedido.getValorTotalPedido() * 1.3f);
        }
        return pedido.getValorTotalPedido();
    }
}
