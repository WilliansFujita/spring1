package com.curso.alura.domain.paciente;

import com.curso.alura.domain.endereco.DadosCadastroEndereco;
import jakarta.validation.constraints.Email;

public record DadosAlteracaoPaciente(

        String nome, String cpf,
        @Email
        String email,
        String telefone, DadosCadastroEndereco endereco) {
}
