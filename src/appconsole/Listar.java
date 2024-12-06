package appconsole;

import regras_negocio.Fachada;

public class Listar {

	public Listar(){
		try {
			Fachada.inicializar();
			System.out.println(Fachada.listarRecrutadores());
			System.out.println(Fachada.listarCandidatos());
			System.out.println(Fachada.listarVagas());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
	}

	public static void main(String[] args) {
		new Listar();
	}
}

