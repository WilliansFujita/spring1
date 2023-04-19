package com.curso.alura.domain.medico;

import com.curso.alura.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm=dados.crm();
        this.especialidade=dados.especialidade();
        this.ativo = true;
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInfomacoes(DadosAtualizacaoMedico dadosAtualizacaoMedico) {
        if(dadosAtualizacaoMedico.nome()!=null)
            this.nome = dadosAtualizacaoMedico.nome();

        if(dadosAtualizacaoMedico.endereco()!=null)
            this.endereco.atualizarInformacoes(dadosAtualizacaoMedico.endereco());

        if(dadosAtualizacaoMedico.telefone() != null)
            this.telefone = dadosAtualizacaoMedico.telefone();

    }

    public void desativar() {
        this.ativo= false;
    }
}
