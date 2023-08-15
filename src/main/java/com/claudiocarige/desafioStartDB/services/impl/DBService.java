package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.ItemPedido;
import com.claudiocarige.desafioStartDB.models.ItensCardapio;
import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.enums.FormaPagamento;
import com.claudiocarige.desafioStartDB.models.enums.ItemCategoria;
import com.claudiocarige.desafioStartDB.repositories.ItensCardapioRepository;
import com.claudiocarige.desafioStartDB.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DBService {

    private final ItensCardapioRepository itensCardapioRepository;
    private final PedidoRepository pedidoRepository;
    public void startDB() {
        ItensCardapio itensCardapio01 = new ItensCardapio("cafe", "Café", 3.00f, ItemCategoria.PRINCIPAL);
        ItensCardapio itensCardapio02 = new ItensCardapio("chantily", "Chantily (extra do Café)", 1.50f, ItemCategoria.EXTRA);
        ItensCardapio itensCardapio03 = new ItensCardapio("suco", "Suco Natural", 6.20f, ItemCategoria.PRINCIPAL);
        ItensCardapio itensCardapio04 = new ItensCardapio("sanduiche", "Sanduíche", 6.50f, ItemCategoria.PRINCIPAL);
        ItensCardapio itensCardapio05 = new ItensCardapio("queijo", "Queijo (extra do Sanduíche)f", 2.00f, ItemCategoria.EXTRA);
        ItensCardapio itensCardapio06 = new ItensCardapio("salgado", "Salgado", 7.25f, ItemCategoria.PRINCIPAL);
        ItensCardapio itensCardapio07 = new ItensCardapio("combo1", "1 Suco e 1 Sanduíche", 9.50f, ItemCategoria.COMBO);
        ItensCardapio itensCardapio08 = new ItensCardapio("combo2", "1 Café e 1 Sanduíche", 7.50f, ItemCategoria.COMBO);

        itensCardapioRepository.saveAll(Arrays.asList(itensCardapio01, itensCardapio02, itensCardapio03, itensCardapio04
                ,itensCardapio05, itensCardapio06, itensCardapio07, itensCardapio08));
        Pedido pedido01 = new Pedido();
        Pedido pedido02 = new Pedido();

        ItemPedido item01 = new ItemPedido(itensCardapio01, 2, pedido01);
        ItemPedido item02 = new ItemPedido(itensCardapio04, 1, pedido01);
        ItemPedido item03 = new ItemPedido(itensCardapio07, 2, pedido02);
        ItemPedido item04 = new ItemPedido(itensCardapio03, 2, pedido02);

        pedido01.setFormaPagamento(FormaPagamento.DEBITO);
        pedido01.addItensPedido(item01);
        pedido01.addItensPedido(item02);
        pedido01.calcularValorPedido();
        pedido02.setFormaPagamento(FormaPagamento.CREDITO);
        pedido02.addItensPedido(item03);
        pedido02.addItensPedido(item04);
        pedido02.calcularValorPedido();

        pedidoRepository.saveAll(Arrays.asList(pedido01,pedido02));
    }

}
