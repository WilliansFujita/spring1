package com.curso.alura.domain.paciente;

import com.curso.alura.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity(name="pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;
    private String cpf;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAlteracaoPaciente dados) {
        if(dados.nome()!=null)
            this.nome = dados.nome();
        if(dados.telefone()!=null)
            this.telefone = dados.telefone();
        if(dados.email()!=null)
            this.email = dados.email();
        if(dados.cpf()!=null)
            this.cpf = dados.cpf();


    }

    public void desativar() {
        this.ativo = false;
    }
}
