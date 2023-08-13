package com.claudiocarige.desafioStartDB.repositories;

import com.claudiocarige.desafioStartDB.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
