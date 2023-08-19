package com.claudiocarige.desafioStartDB.resource;

import com.claudiocarige.desafioStartDB.models.Pedido;
import com.claudiocarige.desafioStartDB.models.representation.PagamentoRepresentation;
import com.claudiocarige.desafioStartDB.models.representation.PedidoRepresentation;
import com.claudiocarige.desafioStartDB.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pedido")
@RequiredArgsConstructor
public class PedidoController {

    public static final String ID = "/{id}";
    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoRepresentation>> findAll(){
        return ResponseEntity.ok().body(pedidoService.findAll()
                .stream()
                .map(PedidoRepresentation::new)
                .collect(Collectors.toList()));
    }
    @GetMapping(value = ID)
    public ResponseEntity<PedidoRepresentation> findById(@PathVariable Long id){
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok().body(new PedidoRepresentation(pedido));
    }

    @PostMapping
    public ResponseEntity<PedidoRepresentation> insert(@RequestBody PedidoRepresentation pedidoRepresentation){
        pedidoService.listIsEmpty(pedidoRepresentation.getListPedidos());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(pedidoService.insert(pedidoRepresentation).getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<PedidoRepresentation> update( @PathVariable Long id, @RequestBody PedidoRepresentation pedidoRepresentation){
        pedidoService.listIsEmpty(pedidoRepresentation.getListPedidos());
        pedidoRepresentation.setId(id);
        return ResponseEntity.ok().body(new PedidoRepresentation(pedidoService.update(id, pedidoRepresentation)));
    }

    @GetMapping(value = "/pagamento/{id}")
    public ResponseEntity<PagamentoRepresentation> calcularValorParaPagamento(@PathVariable Long id){
        return ResponseEntity.ok().body(new PagamentoRepresentation(pedidoService.calcularValorParaPagamento(id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PedidoRepresentation> delete(@PathVariable Long id){
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
