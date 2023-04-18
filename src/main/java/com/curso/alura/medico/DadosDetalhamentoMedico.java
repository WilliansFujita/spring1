package com.curso.alura.medico;

import com.curso.alura.endereco.Endereco;
import com.curso.alura.medico.Especialidade;
import com.curso.alura.medico.Medico;

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

