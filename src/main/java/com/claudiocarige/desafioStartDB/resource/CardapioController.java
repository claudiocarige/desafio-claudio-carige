package com.claudiocarige.desafioStartDB.resource;

import com.claudiocarige.desafioStartDB.models.Cardapio;
import com.claudiocarige.desafioStartDB.services.CardapioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cardapio")
@RequiredArgsConstructor
public class CardapioController {

    private final CardapioService cardapioService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cardapio> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(cardapioService.findById(id));
    }

}
