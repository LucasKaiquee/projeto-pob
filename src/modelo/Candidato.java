package modelo;

import java.util.ArrayList;
import java.util.List;

public class Candidato {
    private String cpf;
    private String nome;
    private String email;
    private List<String> habilidades = new ArrayList<>();
    private String area;
    private List<Vaga> vagasAplicadas = new ArrayList<>();

    public Candidato(String cpf, String nome, String email, List<String> habilidades, String area) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.habilidades = habilidades;
        this.area = area;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public List<String> getHabilidades() {
        return this.habilidades;
    }

    public void addHabilidade(String habilidades) {
        this.habilidades.add(habilidades);
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void candidatar(Vaga v) {
        this.vagasAplicadas.add(v);
    }

    public void removerCandidatura(Vaga v) {
        this.vagasAplicadas.remove(v);
    }

    public List<Vaga> getvagasAplicadas() {
        return this.vagasAplicadas;
    }

    @Override
    public String toString() {
        return "Candidato{" +
            "cpf='" + cpf + '\'' +
            ", nome='" + nome + '\'' +
            ", email='" + email + '\'' +
            ", area='" + area +
            '}';
    }
}
