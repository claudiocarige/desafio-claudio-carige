package com.claudiocarige.desafioStartDB.models;

import com.claudiocarige.desafioStartDB.models.enums.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> listItensCardapio = new ArrayList<>();

    private Float valorTotalPedido;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataPedido = LocalDateTime.now();

    public Pedido(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
        this.valorTotalPedido = 0.0f;
    }

    public void addListItensCardapio(ItemPedido item){
        listItensCardapio.add(item);
    }
    public void calcularValorPedido(){
        if (!listItensCardapio.isEmpty()) {
            valorTotalPedido = listItensCardapio.stream()
                    .map(item -> item.getItem().getValor() * item.getQuantidade())
                    .reduce(0.0f, Float::sum);
        }
    }

}
