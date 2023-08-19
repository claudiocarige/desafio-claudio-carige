package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.ItemPedido;
import com.claudiocarige.desafioStartDB.models.ItemPedidoInfo;
import com.claudiocarige.desafioStartDB.models.ItensCardapio;
import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.enums.FormaPagamento;
import com.claudiocarige.desafioStartDB.models.enums.ItemCategoria;
import com.claudiocarige.desafioStartDB.models.representation.PedidoRepresentation;
import com.claudiocarige.desafioStartDB.repositories.ItemPedidoRepository;
import com.claudiocarige.desafioStartDB.repositories.ItensCardapioRepository;
import com.claudiocarige.desafioStartDB.repositories.PedidoRepository;
import com.claudiocarige.desafioStartDB.services.PedidoService;
import com.claudiocarige.desafioStartDB.services.exceptions.IllegalArgumentException;
import com.claudiocarige.desafioStartDB.services.exceptions.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ItensCardapioRepository itensCardapioRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Pedido findById(Long id) throws IllegalArgumentException {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido não encontrado."));
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido insert(PedidoRepresentation pedidoRepresentation) {
        Pedido pedido = new Pedido();
        mapearPedidoRepresentation(pedido, pedidoRepresentation);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(Long id, PedidoRepresentation pedidoRepresentation) {
        listIsEmpty(pedidoRepresentation.getListPedidos());
        Pedido pedido = findById(id);
        List<Integer> listItemRemove = pedido.getListPedidos().stream().map(ItemPedido::getId).collect(Collectors.toList());
        pedido.limparListaPedido();
        mapearPedidoRepresentation(findById(id), pedidoRepresentation);
        listItemRemove.forEach(itemPedidoRepository::deleteById);
        return pedidoRepository.save(pedido);
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido calcularValorParaPagamento(Long id) {
        Pedido pedido = findById(id);
        calcularValorDaCompra(pedido);
        return pedidoRepository.save(pedido);
    }

    @Override
    public void listIsEmpty(List<ItemPedidoInfo> listPedidos) throws IllegalArgumentException {
        if (listPedidos.isEmpty()) {
            throw new IllegalArgumentException("Não há itens no carrinho de compra!");
        }
    }

    private void mapearPedidoRepresentation(Pedido pedido, PedidoRepresentation pedidoRepresentation) throws IllegalArgumentException {

        pedido.setId(pedidoRepresentation.getId());
        pedido.setDataPedido(pedidoRepresentation.getDataPedido());
        FormaPagamento formaPagamento;
        try {
            formaPagamento = FormaPagamento.valueOf(pedidoRepresentation.getFormaPagamento());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Forma de pagamento inválida!");
        }
        pedido.setFormaPagamento(formaPagamento);
        inserirItemInPedido(pedido, pedidoRepresentation);
        pedido.calcularValorPedido();
    }

    private ItensCardapio findByCodigo(String codigo) throws NoSuchElementException {
        return itensCardapioRepository.findByCodigo(codigo)
                .orElseThrow(() -> new NoSuchElementException("O item " + codigo + " é inválido!"));
    }

    private void inserirItemInPedido(Pedido pedido, PedidoRepresentation pedidoRepresentation){
        ItensCardapio itensCardapio;
        for (ItemPedidoInfo item : pedidoRepresentation.getListPedidos()) {
            itensCardapio = findByCodigo(item.getCodigoItem());
            if (item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida do item: " + item.getCodigoItem());
            }
            ItemPedido itemPedido = new ItemPedido(itensCardapio, item.getQuantidade(), pedido);
            isItemPrincipal(itemPedido, pedido, pedidoRepresentation);
        }
    }

    protected void calcularValorDaCompra(Pedido pedido) {
        if (pedido.getFormaPagamento().equals(FormaPagamento.DINHEIRO)) {
            pedido.setValorTotalPagamento(pedido.getValorPedido() * 0.95f);
        } else if (pedido.getFormaPagamento().equals(FormaPagamento.CREDITO)) {
            pedido.setValorTotalPagamento(pedido.getValorPedido() * 1.03f);
        } else {
            pedido.setValorTotalPagamento(pedido.getValorPedido());
        }
    }

    private void isItemPrincipal(ItemPedido item, Pedido pedido, PedidoRepresentation pedidoRepresentation) throws IllegalArgumentException {
        if (ItemCategoria.PRINCIPAL.equals(item.getItem().getItemCategoria())) {
            if (pedido.getListPedidos().stream().noneMatch(x -> x.getItem().getCodigo().equals(item.getItem().getCodigo()))) {
                pedido.addItensPedido(item);
            }
        } else {
            boolean existPrincipal = pedidoRepresentation.getListPedidos().stream()
                    .anyMatch(x -> {
                        String codigoItem = x.getCodigoItem();
                        ItensCardapio cardapioItem = itensCardapioRepository.findItemByCodigo(codigoItem);
                        return cardapioItem.getItemCategoria() == ItemCategoria.PRINCIPAL;
                    });
            if (existPrincipal) {
                pedido.addItensPedido(item);
            } else {
                throw new IllegalArgumentException("Item extra não pode ser pedido sem o principal");
            }
        }
    }
}
