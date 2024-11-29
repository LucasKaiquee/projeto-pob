package regras_negocio;

import daodb4o.DAO;

public class Fachada {
	private Fachada() {}

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

}
