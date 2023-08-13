package com.claudiocarige.desafioStartDB.models.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardapioRepresentation {

    private Long id;
    private String codigo;
    private String descricao;
    private Float valor;

}
