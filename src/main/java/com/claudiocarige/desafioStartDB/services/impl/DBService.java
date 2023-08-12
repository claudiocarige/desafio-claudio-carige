package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.Cardapio;
import com.claudiocarige.desafioStartDB.repositories.CardapioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DBService {

    private final CardapioRepository cardapioRepository;

    public void startDB() {
        Cardapio cardapio01 = new Cardapio("cafe", "Café", 3.00f);
        Cardapio cardapio02 = new Cardapio("chantily", "Chantily (extra do Café)", 1.50f);
        Cardapio cardapio03 = new Cardapio("suco", "Suco Natural", 6.20f);
        Cardapio cardapio04 = new Cardapio("sanduiche", "Sanduíche", 6.50f);
        Cardapio cardapio05 = new Cardapio("queijo", "Queijo (extra do Sanduíche)f", 2.00f);
        Cardapio cardapio06 = new Cardapio("salgado", "Salgado", 7.25f);
        Cardapio cardapio07 = new Cardapio("combo1", "1 Suco e 1 Sanduíche", 9.50f);
        Cardapio cardapio08 = new Cardapio("combo2", "1 Café e 1 Sanduíche", 7.50f);

        cardapioRepository.saveAll(Arrays.asList(cardapio01, cardapio02, cardapio03, cardapio04
                ,cardapio05, cardapio06, cardapio07, cardapio08));
    }

}
