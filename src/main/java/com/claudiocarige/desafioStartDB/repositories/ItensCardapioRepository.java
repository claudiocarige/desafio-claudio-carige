package com.claudiocarige.desafioStartDB.repositories;

import com.claudiocarige.desafioStartDB.models.ItensCardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItensCardapioRepository extends JpaRepository<ItensCardapio, Integer> {
    Optional<ItensCardapio> findByCodigo(String codigo);
}
