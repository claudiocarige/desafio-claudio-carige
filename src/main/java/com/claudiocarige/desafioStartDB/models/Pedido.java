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

    @OneToMany
    @CollectionTable(name = "lista_itens")
    private List<ItensCardapio> listItensCardapio = new ArrayList<>();

    private Float valorTotalPedido;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataPedido = LocalDateTime.now();

    public Pedido(FormaPagamento formaPagamento, List<ItensCardapio> listItensCardapio) {
        this.formaPagamento = formaPagamento;
        this.listItensCardapio = listItensCardapio;
        this.valorTotalPedido = calcularValorPedido(listItensCardapio);
    }

    public Float calcularValorPedido(List<ItensCardapio> list){
        if (!list.isEmpty()){
            return list.stream()
                    .map(item -> item.getValor() * item.getQuantidade())
                    .reduce(0.0f, Float::sum);
        }
        return 0.0f;
    }

}
