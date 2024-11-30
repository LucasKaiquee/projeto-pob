package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOCandidato;
import daodb4o.DAOVaga;
import daodb4o.DAORecrutador;

import modelo.Candidato;
import modelo.Vaga;
import modelo.Recrutador;

public class Fachada {

	private Fachada() {}

	private static DAOCandidato daocandidato = new DAOCandidato();
	private static DAORecrutador daorecrutador = new DAORecrutador();
	private static DAOVaga daovaga = new DAOVaga();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static List<Candidato> listarCandidatos(){
		List<Candidato> candidatos = daocandidato.readAll();
		return candidatos;
	}
	
	public static List<Vaga> listarVagas(){
		List<Vaga> vagas = daovaga.readAll();
		return vagas;
	}
	
	public static List<Recrutador> listarRecrutadores(){
		List<Recrutador> recrutadores = daorecrutador.readAll();
		return recrutadores;
	}
	

	public static Candidato localizarCandidato(String nome) throws Exception {
		Candidato c = daocandidato.read(nome);
		if (c == null) {
			throw new Exception("Candidato inexistente:" + nome);
		}
		return c;
	}
	public static Recrutador localizarRecrutador(String cpf) throws Exception {
		Recrutador r = daorecrutador.read(cpf);
		if (r == null) {
			throw new Exception("Recrutador inexistente:" + cpf);
		}
		return r;
	}

	public static List<Vaga> localizarVaga(int id) throws Exception {
		List<Vaga> v = daovaga.read(id);
		if(v == null){
			throw new Exception("Vaga inexistente: " + id);
		}
		return v;
	}

	public static void criarRecrutador(String cpf, String nome, String email, String empresa) throws Exception {
		DAO.begin();

		Recrutador r = daorecrutador.read(cpf); // nome de qualquer pessoa
		if (r != null) {
			DAO.rollback();
			throw new Exception("criar recrutador - cpf ja existe:" + cpf);
		}

		r = new Recrutador(cpf, nome, email, empresa);
		daorecrutador.create(r);
		DAO.commit();
	}

	public static void criarVaga(String descricao, double salario, String area, List<String> requisitos, Recrutador recrutador) throws Exception {
		DAO.begin();

		Recrutador recru = daorecrutador.read(recrutador.getCpf());
		
		// if (recru.getVagaGerenciada() != null) {
		// 	DAO.rollback();
		// 	throw new Exception("criar vaga - vaga ja existe:" + recru.getVagaGerenciada().getId() + " - " + recru.getVagaGerenciada().getDescricao());
		// }

		if (recru.getVagaGerenciada() != null){
			DAO.rollback();
			throw new Exception("criar vaga - recrutador " + recru.getCpf() + " já possui uma vaga.");
		}

		Vaga v = new Vaga(descricao, salario, area, requisitos, recrutador);
		recru.setVagaGerenciada(v);
		daovaga.create(v);
		DAO.commit();
	}

	public static void removerVaga(int id) throws Exception{
		Vaga vaga = daovaga.read(id).get(0);

		if (vaga == null){
			DAO.rollback();
			throw new Exception("remover Vaga - vaga não existe : " + id);
		}
		vaga.getRecrutador().setVagaGerenciada(null);
		daovaga.delete(vaga);
		DAO.commit();
	}

	public static void removerRecrutador(String cpf) throws Exception{
		Recrutador recru = daorecrutador.read(cpf);

		if (recru == null){
			DAO.rollback();
			throw new Exception("remover Recrutador - recrutador não existe : " + cpf);
		}
		daorecrutador.delete(recru);
		DAO.commit();
	}




}
