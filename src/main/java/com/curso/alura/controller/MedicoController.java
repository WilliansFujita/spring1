package com.curso.alura.controller;

import com.curso.alura.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico){
        repository.save(new Medico(dadosCadastroMedico));
    }

    @GetMapping
    public Page<DadoListagemMedico> listar(@PageableDefault(size=10,sort={"nome"}) Pageable pageable){
        return repository.findAllByAtivoTrue(pageable).map(DadoListagemMedico::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico){
        var optionalMedico = repository.findById(id);
        if(!optionalMedico.isPresent()){
            return ResponseEntity.status(NOT_FOUND).body("Médico Não encontrado.");
        }else{
            Medico medico = optionalMedico.get();
            medico.atualizarInfomacoes(dadosAtualizacaoMedico);
            return ResponseEntity.status(OK).body("OK");
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var optionalMedico = repository.findById(id);
        if(!optionalMedico.isPresent()){
            return ResponseEntity.status(NOT_FOUND).body("Médico Não encontrado.");
        }else{
            Medico medico = optionalMedico.get();
            medico.desativar();
            return ResponseEntity.noContent().build();
        }
    }
}
