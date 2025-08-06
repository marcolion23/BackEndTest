package org.example.resources;

import org.example.entities.Produto;
import org.example.service.ProdutoService;
import org.example.service.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {


    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        List<Produto> funcoes = produtoService.getAll();
        return ResponseEntity.ok(funcoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto obj = produtoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/estoque-baixo")
    public ResponseEntity<List<Produto>> getProdutosComEstoqueBaixo(@RequestParam(defaultValue = "5") int limite) {
        List<Produto> produtos = produtoService.findProdutosComEstoqueBaixo(limite);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/estoque-baixo/count")
    public ResponseEntity<Long> contarProdutosComEstoqueBaixo() {
        long count = produtoService.contarProdutosComEstoqueBaixo();
        return ResponseEntity.ok(count);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Produto> insert(@RequestBody Produto produto) {
        Long forId = produto.getFornecedor().getForId();
        Produto newProduct = produtoService.insert(forId, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            produto.setProId(id);
            boolean atualizado = produtoService.update(id, produto);
            if (atualizado) {
                return ResponseEntity.ok(produto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}