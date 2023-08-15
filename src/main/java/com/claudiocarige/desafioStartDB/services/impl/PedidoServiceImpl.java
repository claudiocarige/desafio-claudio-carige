package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.ItemPedido;
import com.claudiocarige.desafioStartDB.models.ItemPedidoInfo;
import com.claudiocarige.desafioStartDB.models.ItensCardapio;
import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.enums.FormaPagamento;
import com.claudiocarige.desafioStartDB.models.enums.ItemCategoria;
import com.claudiocarige.desafioStartDB.models.representation.PedidoRepresentation;
import com.claudiocarige.desafioStartDB.repositories.ItensCardapioRepository;
import com.claudiocarige.desafioStartDB.repositories.PedidoRepository;
import com.claudiocarige.desafioStartDB.services.PedidoService;
import com.claudiocarige.desafioStartDB.services.exceptions.IllegalArgumentException;
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
        Pedido pedido =  mapearPedidoRepresentation(pedidoRepresentation);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(Long id, PedidoRepresentation pedidoRepresentation) {
        Pedido pedido =  mapearPedidoRepresentation(pedidoRepresentation);
        return pedidoRepository.save(pedido);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void listIsEmpty(List<ItemPedidoInfo> listPedidos) {
        if(listPedidos.isEmpty()){
            throw new IllegalArgumentException("Não há itens no carrinho de compra!");
        }
    }
    private Pedido mapearPedidoRepresentation(PedidoRepresentation pedidoRepresentation){
        Pedido pedido = new Pedido();
        ItensCardapio itensCardapio;
        pedido.setId(pedidoRepresentation.getId());
        pedido.setDataPedido(pedidoRepresentation.getDataPedido());
        pedido.setFormaPagamento(FormaPagamento.valueOf(pedidoRepresentation.getFormaPagamento()));
        for (ItemPedidoInfo item:pedidoRepresentation.getListPedidos()) {
            itensCardapio = findByCodigo(item.getCodigoItem());
            if((item.getQuantidade() == 0) || (item.getQuantidade() < 0)){
                throw new IllegalArgumentException("Quantidade inválida do item: "+ item.getCodigoItem());
            }
            ItemPedido itemPedido = new ItemPedido(itensCardapio, item.getQuantidade(),pedido);
            isItemPrincipal(itemPedido, pedido);
        }
        pedido.calcularValorPedido();
        pedido.setValorTotalPagamento(calcularValorDaCompra(pedido));
        return pedido;
    }
    private ItensCardapio findByCodigo(String codigo) {
        Optional<ItensCardapio> itensCardapio  = itensCardapioRepository.findByCodigo(codigo);
        return itensCardapio.orElseThrow(() -> new NoSuchElementException("O item " + codigo + " é inválido!"));
    }

    private Float calcularValorDaCompra(Pedido pedido){
        if(pedido.getFormaPagamento().equals(FormaPagamento.DINHEIRO)){
            pedido.setValorTotalPagamento(pedido.getValorPedido() * 0.95f);
        }else if (pedido.getFormaPagamento().equals(FormaPagamento.CREDITO)){
            pedido.setValorTotalPagamento(pedido.getValorPedido() * 1.03f);
        }
        return pedido.getValorTotalPagamento();
    }

    private void isItemPrincipal(ItemPedido item, Pedido pedido) {
        if (ItemCategoria.PRINCIPAL.equals(item.getItem().getItemCategoria())) {
            pedido.addItensPedido(item);
        } else {
            boolean existPrincipal = pedido.getListPedidos().stream()
                    .anyMatch(itemPedido -> ItemCategoria.PRINCIPAL.equals(itemPedido.getItem().getItemCategoria()));
            if (existPrincipal) {
                pedido.addItensPedido(item);
            } else {
                throw new IllegalArgumentException("Item extra não pode ser pedido sem o principal");
            }
        }
    }
}
//1