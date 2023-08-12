package com.claudiocarige.desafioStartDB.services;

import com.claudiocarige.desafioStartDB.models.Cardapio;

public interface CardapioService {

    Cardapio findById(Long id);

    Cardapio findAll();

    Cardapio create(Cardapio obj);

    Cardapio Updat(Long id, Cardapio obj);

    void delete(Long id);

}
