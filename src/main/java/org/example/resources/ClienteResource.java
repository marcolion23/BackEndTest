package org.example.resources;

import org.example.Dto.ClienteDto;
import org.example.entities.Cliente;
import org.example.service.ClienteService;
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
@RequestMapping(value = "/clientes")
public class ClienteResource {


    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDto> listDto = list.stream().map(obj -> service.toNewDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente obj = service.findById(id);
        ClienteDto dto = service.toNewDto(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody  ClienteDto objDto) {
        Cliente obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCliId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto objDto, @PathVariable Long id) {
        service.update(id, objDto);
            return ResponseEntity.noContent().build();
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        service.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}