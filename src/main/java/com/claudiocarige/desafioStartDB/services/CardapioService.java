package com.claudiocarige.desafioStartDB.services;

import com.claudiocarige.desafioStartDB.models.ItensCardapio;
import com.claudiocarige.desafioStartDB.models.representation.ItensCardapioRepresentation;

import java.util.List;

public interface CardapioService {

    ItensCardapio findById(Long id);

    List<ItensCardapio> findAll();

    ItensCardapio insert(ItensCardapioRepresentation obj);

    ItensCardapio update(ItensCardapioRepresentation obj);

    void delete(Long id);

}
