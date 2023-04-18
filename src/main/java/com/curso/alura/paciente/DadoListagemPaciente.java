package com.curso.alura.paciente;

import com.curso.alura.endereco.Endereco;

public record DadoListagemPaciente(
        Long id,
        String nome,
        String telefone,
        String cpf,
        String email,
        Endereco endereco,

        Boolean ativo
) {

    public DadoListagemPaciente(Paciente paciente) {
        this(paciente.getId(),
                paciente.getNome(),
                paciente.getTelefone(),
                paciente.getCpf(),
                paciente.getEmail(),paciente.getEndereco(), paciente.getAtivo());
    }
}
