package com.curso.alura.paciente;

import com.curso.alura.endereco.DadosCadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPaciente(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,

        @NotNull
        @Valid
        DadosCadastroEndereco endereco) {
}
