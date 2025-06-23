package org.example.resources;

import org.example.entities.Produto;
import org.example.service.ProdutoService;
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
    //@GetMapping("/estoque-baixo")
   // public ResponseEntity<Long> getProdutosComEstoqueBaixo() {
       // long count = produtoService.contarProdutosComEstoqueBaixo();
        //return ResponseEntity.ok(count);
   // }
    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto produto) {
        Produto createdProduto = produtoService.insert(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Produto produto) {
        if (produtoService.update(id, produto)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}