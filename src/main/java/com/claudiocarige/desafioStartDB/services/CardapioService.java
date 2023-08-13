package com.claudiocarige.desafioStartDB.services;

import com.claudiocarige.desafioStartDB.models.Cardapio;
import com.claudiocarige.desafioStartDB.models.representation.CardapioRepresentation;

import java.util.List;

public interface CardapioService {

    Cardapio findById(Long id);

    List<Cardapio> findAll();

    Cardapio insert(CardapioRepresentation obj);

    Cardapio update(CardapioRepresentation obj);

    void delete(Long id);

}
