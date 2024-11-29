package appconsole;

import regras_negocio.Fachada;

public class Listar {

	public Listar(){
		try {
			Fachada.inicializar();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
	}

	public static void main(String[] args) {
		new Listar();
	}
}

