package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar(){
		try {
			
		} catch (Exception e) 	{
			System.out.println(e.getMessage());
		}

		try {
		} catch (Exception e) 	{
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("fim do programa");
	}

	
	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


