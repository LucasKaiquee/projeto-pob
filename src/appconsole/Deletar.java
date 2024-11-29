package appconsole;

import regras_negocio.Fachada;

public class Deletar {

	public Deletar(){
		Fachada.inicializar();
		try {
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
		System.out.println("fim do programa");
	}

	public static void main(String[] args) {
		new Deletar();
	}
}

