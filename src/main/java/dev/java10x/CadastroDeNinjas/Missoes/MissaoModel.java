package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
public class MissaoModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String dificuldade;
    @OneToMany(mappedBy = "missao_id") // Uma missao pode ser designada para vários ninjas
    private List<NinjaModel> ninjas;

    public MissaoModel(){
    }

    public MissaoModel(String nome, String dificuldade){
        this.nome = nome;
        this.dificuldade = dificuldade;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDificuldade(){
        return dificuldade;
    }

    public void setDificuldade(String dificuldade){
        this.dificuldade = dificuldade;
    }
}
