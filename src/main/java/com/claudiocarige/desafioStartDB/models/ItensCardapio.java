package com.claudiocarige.desafioStartDB.models;

import com.claudiocarige.desafioStartDB.models.enums.ItemCategoria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class ItensCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String codigo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private ItemCategoria itemCategoria;
    private Float valor;


    public ItensCardapio(){
    }

    public ItensCardapio(String codigo, String descricao, Float valor, ItemCategoria itemCategoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.itemCategoria = itemCategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensCardapio cardapio = (ItensCardapio) o;
        return Objects.equals(id, cardapio.id) && Objects.equals(codigo, cardapio.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo);
    }
}
