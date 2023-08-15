package com.claudiocarige.desafioStartDB.resource;

import com.claudiocarige.desafioStartDB.models.representation.ItensCardapioRepresentation;
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
    public ResponseEntity<ItensCardapioRepresentation> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(cardapioService.findById(id), ItensCardapioRepresentation.class));
    }

    @GetMapping
    public ResponseEntity<List<ItensCardapioRepresentation>> findAll(){
        return ResponseEntity.ok().body(cardapioService.findAll()
                .stream()
                .map(x -> mapper.map(x, ItensCardapioRepresentation.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ItensCardapioRepresentation>insert(@RequestBody ItensCardapioRepresentation itensCardapioRepresentation){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(cardapioService.insert(itensCardapioRepresentation).getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<ItensCardapioRepresentation> update(@PathVariable Integer id,
                                                              @RequestBody ItensCardapioRepresentation itensCardapioRepresentation){
        itensCardapioRepresentation.setId(id);
        return ResponseEntity.ok().body(mapper.map(cardapioService.update(itensCardapioRepresentation),
                ItensCardapioRepresentation.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ItensCardapioRepresentation> delete(@PathVariable Integer id){
        cardapioService.delete(id);
        return ResponseEntity.noContent().build();
    }
 }
