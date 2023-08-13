package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.Cardapio;
import com.claudiocarige.desafioStartDB.models.representation.CardapioRepresentation;
import com.claudiocarige.desafioStartDB.repositories.CardapioRepository;
import com.claudiocarige.desafioStartDB.services.CardapioService;
import com.claudiocarige.desafioStartDB.services.exceptions.DataIntegrityViolationException;
import com.claudiocarige.desafioStartDB.services.exceptions.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardapioServiceImpl implements CardapioService {

    private final CardapioRepository cardapioRepository;
    private final ModelMapper mapper;

    @Override
    public Cardapio findById(Long id) {
        Optional<Cardapio> cardapioObject = cardapioRepository.findById(id);
        return cardapioObject.orElseThrow(() -> new NoSuchElementException("Objeto não encontrado."));
    }

    @Override
    public List<Cardapio> findAll() {
        return cardapioRepository.findAll();
    }

    @Override
    public Cardapio insert(CardapioRepresentation cardapioRepresentation) {
        cardapioRepresentation.setId(null);
        findByCodigo(cardapioRepresentation);
        return cardapioRepository.save(mapper.map(cardapioRepresentation, Cardapio.class));
    }

    @Override
    public Cardapio update(CardapioRepresentation cardapioRepresentation) {
        findById(cardapioRepresentation.getId());
        findByCodigo(cardapioRepresentation);
        return cardapioRepository.save(mapper.map(cardapioRepresentation, Cardapio.class));
    }

    @Override
    public void delete(Long id) {

    }
    private void findByCodigo(CardapioRepresentation cardapioRepresentation){
        Optional<Cardapio> cardapio = cardapioRepository.findByCodigo(cardapioRepresentation.getCodigo());
        if(cardapio.isPresent()){
            throw new DataIntegrityViolationException("Item já cadastrado.");
        }
    }
}
