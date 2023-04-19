package com.curso.alura.domain.medico;

import com.curso.alura.domain.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id,
                                      String nome,
                                      String email,
                                      String crm,
                                      Especialidade especialidade,
                                      Endereco endereco,
                                      String telefone) {
    public DadosDetalhamentoMedico(Medico medico) {
         this(medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getEndereco(),
                medico.getTelefone());
    }
}

