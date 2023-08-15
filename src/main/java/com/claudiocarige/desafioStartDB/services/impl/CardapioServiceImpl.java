package com.claudiocarige.desafioStartDB.services.impl;

import com.claudiocarige.desafioStartDB.models.ItensCardapio;
import com.claudiocarige.desafioStartDB.models.representation.ItensCardapioRepresentation;
import com.claudiocarige.desafioStartDB.repositories.ItensCardapioRepository;
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

    private final ItensCardapioRepository itensCardapioRepository;
    private final ModelMapper mapper;

    @Override
    public ItensCardapio findById(Integer id) {
        Optional<ItensCardapio> itensCardapio = itensCardapioRepository.findById(id);
        return itensCardapio.orElseThrow(() -> new NoSuchElementException("Objeto não encontrado."));
    }

    @Override
    public List<ItensCardapio> findAll() {
        return itensCardapioRepository.findAll();
    }

    @Override
    public ItensCardapio insert(ItensCardapioRepresentation itensCardapioRepresentation) {
        itensCardapioRepresentation.setId(null);
        findByCodigo(itensCardapioRepresentation);
        return itensCardapioRepository.save(mapper.map(itensCardapioRepresentation, ItensCardapio.class));
    }

    @Override
    public ItensCardapio update(ItensCardapioRepresentation itensCardapioRepresentation) {
        findById(itensCardapioRepresentation.getId());
        findByCodigo(itensCardapioRepresentation);
        return itensCardapioRepository.save(mapper.map(itensCardapioRepresentation, ItensCardapio.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        itensCardapioRepository.deleteById(id);
    }
    private void findByCodigo(ItensCardapioRepresentation itensCardapioRepresentation){
        Optional<ItensCardapio> itensCardapio = itensCardapioRepository.findByCodigo(itensCardapioRepresentation.getCodigo());
        if(itensCardapio.isPresent()){
            throw new DataIntegrityViolationException("Item já cadastrado.");
        }
    }
}
