package com.curso.alura.medico;

import com.curso.alura.endereco.DadosCadastroEndereco;

public record DadosAtualizacaoMedico(
        String nome,
        String telefone,
        DadosCadastroEndereco endereco
) {
}
