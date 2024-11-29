package modelo;

import java.util.List;

public class Recrutador {
    private int id;
    private String nome;
    private String email;
    private Empresa empresa;
    private Vaga vagaGerenciada;

    public Recrutador(int id, String nome, String email, Empresa empresa, Vaga vaga) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.empresa = empresa;
        this.vagaGerenciada = vaga;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setVagaGerenciada(Vaga v) {
        this.vagaGerenciada = v;
    }

    public Vaga getVagaGerenciada() {
        return this.vagaGerenciada;
    }
}
