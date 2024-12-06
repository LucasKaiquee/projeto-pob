package modelo;
public class Recrutador {
    private String cpf;
    private String nome;
    private String email;
    private String empresa;
    private Vaga vagaGerenciada;

    public Recrutador(String cpf, String nome, String email, String empresa) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.empresa = empresa;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCPF(String cpf) {
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

    public String getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setVagaGerenciada(Vaga v) {
        this.vagaGerenciada = v;
    }

    public Vaga getVagaGerenciada() {
        return this.vagaGerenciada;
    }

    @Override
    public String toString() {
        return "Recrutador [cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", empresa=" + empresa;
    }

    
}
