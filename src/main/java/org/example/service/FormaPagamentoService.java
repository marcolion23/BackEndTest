package org.example.service;

import org.example.entities.FormaPagamento;
import org.example.repositories.FormaPagamentoRepository;
import org.example.service.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository repository;

    public List<FormaPagamento> getAll() {
        return repository.findAll();
    }

    public FormaPagamento findById(Long id) {
        Optional<FormaPagamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public FormaPagamento insert(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    public boolean update(Long id, FormaPagamento formaPagamento) {
        Optional<FormaPagamento>optional=repository.findById(id);
        if (optional.isPresent()){
            FormaPagamento formaPagamentoSistema = optional.get();
            formaPagamentoSistema.setFpgTipo(formaPagamento.getFpgTipo());
            formaPagamentoSistema.setFpgDescricao(formaPagamento.getFpgDescricao());
            formaPagamentoSistema.setFpgNumMaxParcelas(formaPagamento.getFpgNumMaxParcelas());
            formaPagamentoSistema.setFpgPermiteParcelamento(formaPagamento.getFpgPermiteParcelamento());
            formaPagamentoSistema.setFpgTaxaAdicional(formaPagamento.getFpgTaxaAdicional());
            repository.save(formaPagamentoSistema);
            return true;
        }
        return false;
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
