package org.example.service;

import org.example.entities.Produto;
import org.example.repositories.ProdutoRepository;
import org.example.service.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> getAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Produto insert(Produto produto) {
        return repository.save(produto);
    }

    public boolean update(Long id, Produto produto) {
        Optional<Produto> optionalProduto = repository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produtoSistema = optionalProduto.get();
            produtoSistema.setProNome(produto.getProNome());
            produtoSistema.setProDescricao(produto.getProDescricao());
            produtoSistema.setProPrecoCusto(produto.getProPrecoCusto());
            produtoSistema.setProPrecoVenda(produto.getProPrecoVenda());
            produtoSistema.setProQuantidadeEstoque(produto.getProQuantidadeEstoque());
            produtoSistema.setProCategoria(produto.getProCategoria());
            produtoSistema.setProCodigoBarras(produto.getProCodigoBarras());
            produtoSistema.setProMarca(produto.getProMarca());
            produtoSistema.setProUnidadeMedida(produto.getProUnidadeMedida());
            produtoSistema.setProAtivo(produto.getProAtivo());
            produtoSistema.setProDataCadastro(produto.getProDataCadastro());
            produtoSistema.setProDataCadastro(produto.getProDataCadastro());
            repository.save(produtoSistema);
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    //aviso caso o produto estiiver abaixo do numero escolido
    //public long contarProdutosComEstoqueBaixo() {
    //return repository.countByProQuantidadeEstoqueLessThan(5);
    //}
}
