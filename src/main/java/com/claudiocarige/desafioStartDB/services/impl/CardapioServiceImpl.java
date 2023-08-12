package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.Cardapio;
import com.claudiocarige.desafioStartDB.repositories.CardapioRepository;
import com.claudiocarige.desafioStartDB.services.CardapioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardapioServiceImpl implements CardapioService {

    private final CardapioRepository cardapioRepository;

    @Override
    public Cardapio findById(Long id) {
        Optional<Cardapio> cardapioObject = cardapioRepository.findById(id);
        return cardapioObject.orElseThrow(() -> new RuntimeException("Objeto não encontrado."));
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
