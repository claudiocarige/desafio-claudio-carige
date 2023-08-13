package com.claudiocarige.desafioStartDB.resource;

import com.claudiocarige.desafioStartDB.models.representation.CardapioRepresentation;
import com.claudiocarige.desafioStartDB.services.CardapioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cardapio")
@RequiredArgsConstructor
public class CardapioController {

    private final CardapioService cardapioService;
    private final ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CardapioRepresentation> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.map(cardapioService.findById(id), CardapioRepresentation.class));
    }

    @GetMapping
    public ResponseEntity<List<CardapioRepresentation>> findAll(){
        return ResponseEntity.ok().body(cardapioService.findAll()
                .stream()
                .map(x -> mapper.map(x, CardapioRepresentation.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CardapioRepresentation>insert(@RequestBody CardapioRepresentation cardapioRepresentation){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(cardapioService.insert(cardapioRepresentation).getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<CardapioRepresentation> update(@PathVariable Long id,
                                    @RequestBody CardapioRepresentation cardapioRepresentation){
        cardapioRepresentation.setId(id);
        return ResponseEntity.ok().body(mapper.map(cardapioService.update(cardapioRepresentation),
                CardapioRepresentation.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CardapioRepresentation> delete(@PathVariable Long id){
        cardapioService.delete(id);
        return ResponseEntity.noContent().build();
    }
 }
