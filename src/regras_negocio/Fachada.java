package regras_negocio;

import daodb4o.DAO;
import daodb4o.DAOCandidato;
import java.util.List;
import modelo.Candidato;

public class Fachada {
	private Fachada() {}

	private static DAOCandidato daocandidato = new DAOCandidato();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static Candidato localizarCandidato(String cpf) throws Exception {
		Candidato c = daocandidato.read(cpf);
		if(c == null) {
			throw new Exception("CPF não cadastrado");
		}
		return c;
	}

	public static void criarCandidato(String nome, String cpf, String email, List<String> habilidades, String area) throws Exception {
		DAO.begin();
		Candidato c = daocandidato.read(cpf);
		if(c != null) {
			DAO.rollback();
			throw new Exception("Já existe um candidato com esse CPF");
		}

		c = new Candidato(cpf, nome, email, habilidades, area);
		daocandidato.create(c);
		DAO.commit();
	}

	public static void alterarCandidato(String cpf, String habilidade, String area) throws Exception {
		DAO.begin();
		Candidato c = daocandidato.read(cpf);
		if(c == null) {
			DAO.rollback();
			throw new Exception("CPF não cadastrado");
		}

		if(habilidade != null) 
			c.addHabilidade(habilidade);

		if(area != null) 
			c.setArea(area);

		daocandidato.update(c);
		DAO.commit();
	}

	public static void excluirCandidato(String cpf) throws Exception {
		DAO.begin();
		Candidato c = daocandidato.read(cpf);
		if(c == null) {
			DAO.rollback();
			throw new Exception("CPF não cadastrado");
		}

		// Excluir candidato da vaga

		daocandidato.delete(c);
		DAO.commit();
	}

	public static List<Candidato> listarCandidato(){
		List<Candidato> candidatos = daocandidato.readAll();
		return candidatos;
	}

}
