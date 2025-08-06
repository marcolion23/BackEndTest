package org.example.repositories;

import org.example.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    long countByProQuantidadeEstoqueLessThan(int quantidade);

    List<Produto> findByProQuantidadeEstoqueLessThan(int quantidade);
}