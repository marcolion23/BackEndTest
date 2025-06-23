package org.example.resources;

import org.example.Dto.ClienteDto;
import org.example.Dto.FornecedorDto;
import org.example.entities.Cliente;
import org.example.entities.Fornecedor;
import org.example.service.ClienteService;
import org.example.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorResource {


    @Autowired
    private FornecedorService service;

    @GetMapping
    public ResponseEntity<List<FornecedorDto>> getAll() {
        List<Fornecedor> list = service.findAll();
        List<FornecedorDto> listDto = list.stream().map(obj -> service.toNewDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
        Fornecedor obj = service.findById(id);
        FornecedorDto dto = service.toNewDto(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody  FornecedorDto objDto) {
        Fornecedor obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getForId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody FornecedorDto objDto, @PathVariable Long id) {
        service.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        service.deleteFornecedor(id);
        return ResponseEntity.noContent().build();
    }
}