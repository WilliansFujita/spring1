package com.curso.alura.domain.medico;

import com.curso.alura.domain.endereco.DadosCadastroEndereco;

public record DadosAtualizacaoMedico(
        String nome,
        String telefone,
        DadosCadastroEndereco endereco
) {
}
