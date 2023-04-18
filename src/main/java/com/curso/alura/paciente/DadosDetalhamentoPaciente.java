package com.curso.alura.paciente;

import com.curso.alura.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String telefone, String email, String cpf, Endereco endereco, Boolean ativo) {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getCpf(),paciente.getEndereco(), paciente.getAtivo());
    }
}
