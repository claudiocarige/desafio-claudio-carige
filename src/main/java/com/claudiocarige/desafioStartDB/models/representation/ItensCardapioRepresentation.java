package com.claudiocarige.desafioStartDB.models.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItensCardapioRepresentation {

    private Integer id;
    private String codigo;
    private String descricao;
    private Float valor;

}
