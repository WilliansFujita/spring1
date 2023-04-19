package com.curso.alura.controller;

import com.curso.alura.domain.paciente.DadoListagemPaciente;
import com.curso.alura.domain.paciente.Paciente;
import com.curso.alura.domain.paciente.PacienteRepository;
import com.curso.alura.domain.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente cadastroPaciente, UriComponentsBuilder uriBuilder){
        Paciente paciente = new Paciente(cadastroPaciente);
        pacienteRepository.save(paciente);
        URI uri = uriBuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadoListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var listagemDadosPacientes = pacienteRepository.findAll(pageable).map(DadoListagemPaciente::new);
        return ResponseEntity.ok(listagemDadosPacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable(name = "id") Long id){
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(!paciente.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity alterar(@PathVariable(name = "id") Long id, @RequestBody DadosAlteracaoPaciente dados){
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(!pacienteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Paciente paciente = pacienteOptional.get();
        paciente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(pacienteOptional.get()));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable(name = "id") Long id){
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(!pacienteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Paciente paciente = pacienteOptional.get();
        paciente.desativar();
        return ResponseEntity.noContent().build();
    }


}
