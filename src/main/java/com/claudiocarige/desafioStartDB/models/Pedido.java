package com.claudiocarige.desafioStartDB.models;

import com.claudiocarige.desafioStartDB.models.enums.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> listPedidos = new ArrayList<>();

    private Float valorTotalPedido;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataPedido = LocalDateTime.now();

    public Pedido(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
        this.valorTotalPedido = 0.0f;
    }

    public void addItensPedido(ItemPedido item) {
            listPedidos.add(item);
    }

    public void removerItemPedido(ItemPedido item) {
        listPedidos.remove(item);
    }

    public void calcularValorPedido() {
        if (!listPedidos.isEmpty()) {
            valorTotalPedido = listPedidos.stream()
                    .map(item -> item.getItem().getValor() * item.getQuantidade())
                    .reduce(0.0f, Float::sum);
        }
    }
}
