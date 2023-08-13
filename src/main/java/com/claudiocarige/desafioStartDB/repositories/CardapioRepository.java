package com.claudiocarige.desafioStartDB.repositories;

import com.claudiocarige.desafioStartDB.models.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

    Optional<Cardapio> findByCodigo(String codigo);
}
