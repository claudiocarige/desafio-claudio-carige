package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.ItensCardapio;
import com.claudiocarige.desafioStartDB.repositories.ItensCardapioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DBService {

    private final ItensCardapioRepository itensCardapioRepository;

    public void startDB() {
        ItensCardapio itensCardapio01 = new ItensCardapio("cafe", "Café", 3.00f);
        ItensCardapio itensCardapio02 = new ItensCardapio("chantily", "Chantily (extra do Café)", 1.50f);
        ItensCardapio itensCardapio03 = new ItensCardapio("suco", "Suco Natural", 6.20f);
        ItensCardapio itensCardapio04 = new ItensCardapio("sanduiche", "Sanduíche", 6.50f);
        ItensCardapio itensCardapio05 = new ItensCardapio("queijo", "Queijo (extra do Sanduíche)f", 2.00f);
        ItensCardapio itensCardapio06 = new ItensCardapio("salgado", "Salgado", 7.25f);
        ItensCardapio itensCardapio07 = new ItensCardapio("combo1", "1 Suco e 1 Sanduíche", 9.50f);
        ItensCardapio itensCardapio08 = new ItensCardapio("combo2", "1 Café e 1 Sanduíche", 7.50f);

        itensCardapioRepository.saveAll(Arrays.asList(itensCardapio01, itensCardapio02, itensCardapio03, itensCardapio04
                ,itensCardapio05, itensCardapio06, itensCardapio07, itensCardapio08));
    }

}
