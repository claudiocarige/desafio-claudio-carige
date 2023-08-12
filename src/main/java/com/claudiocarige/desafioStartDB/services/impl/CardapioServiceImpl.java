package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.Cardapio;
import com.claudiocarige.desafioStartDB.services.CardapioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardapioServiceImpl implements CardapioService {


    @Override
    public Cardapio findById(Long id) {
        return null;
    }

    @Override
    public Cardapio findAll() {
        return null;
    }

    @Override
    public Cardapio create(Cardapio obj) {
        return null;
    }

    @Override
    public Cardapio Updat(Long id, Cardapio obj) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
