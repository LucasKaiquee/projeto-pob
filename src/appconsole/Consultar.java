package appconsole;

import regras_negocio.Fachada;

public class Consultar {

	public Consultar(){

		try {
			Fachada.inicializar();
			// System.out.println(Fachada.listarRecrutadores());
			System.out.println();
			System.out.println(Fachada.listarVagas());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa");
	}


	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}

