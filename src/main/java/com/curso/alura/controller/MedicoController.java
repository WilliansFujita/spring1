package com.curso.alura.controller;

import com.curso.alura.domain.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico, UriComponentsBuilder uriBuilder){
        Medico medico = new Medico(dadosCadastroMedico);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadoListagemMedico>> listar(@PageableDefault(size=10,sort={"nome"}) Pageable pageable){
        Page<DadoListagemMedico> medicosRetornados = repository.findAllByAtivoTrue(pageable).map(DadoListagemMedico::new);
        return ResponseEntity.ok(medicosRetornados);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico){
        var medico = repository.getReferenceById(id);
        medico.atualizarInfomacoes(dadosAtualizacaoMedico);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.desativar();
        return ResponseEntity.noContent().build();
    }

}
