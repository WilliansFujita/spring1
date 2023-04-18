package com.curso.alura.paciente;

import com.curso.alura.endereco.DadosCadastroEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAlteracaoPaciente(

        String nome, String cpf,
        @Email
        String email,
        String telefone, DadosCadastroEndereco endereco) {
}
