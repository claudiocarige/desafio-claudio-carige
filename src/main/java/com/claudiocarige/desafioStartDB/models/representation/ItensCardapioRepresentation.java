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

    protected Integer id;
    protected String codigo;
    protected String descricao;
    protected Float valor;
}
