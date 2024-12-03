package modelo;

import java.util.ArrayList;
import java.util.List;

public class Vaga {
    private int id;
    private String descricao;
    private double salario;
    private String area;
    private List<String> requisitos;
    private List<Candidato> candidaturas = new ArrayList<>();
    private Recrutador recrutador;

    public Vaga(String descricao, double salario, String area, List<String> requisitos, Recrutador recrutador) {
        this.descricao = descricao;
        this.salario = salario;
        this.area = area;
        this.requisitos = requisitos;
        this.recrutador = recrutador;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<String> getRequisitos() {
        return this.requisitos;
    }

    public void setRequisitos(List<String> requisitos) {
        this.requisitos = requisitos;
    }

    public void candidatar(Candidato c) {
        this.candidaturas.add(c);
        c.candidatar(this);
    }

    public void removerCandidatura(Candidato c) {
        this.candidaturas.remove(c);
        c.removerCandidatura(this);
    }

    public List<Candidato> getCandidaturas() {
        return this.candidaturas;
    }
 
    public Recrutador getRecrutador() {
        return this.recrutador;
    }

    public void setRecrutador(Recrutador r) {
        this.recrutador = r;
    }

    @Override
    public String toString() {
        return "Vaga [id=" + id + ", descricao=" + descricao + ", salario=" + salario + ", area=" + area
                + ", requisitos=" + requisitos + ", candidaturas=" + candidaturas + ", recrutador=" + recrutador.getNome() + "]";
    }


}
