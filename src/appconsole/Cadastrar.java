package appconsole;

import java.util.ArrayList;
import java.util.List;
import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar(){
		try {
			Fachada.inicializar();
			List<String> habilidades = new ArrayList<>();
			habilidades.add("Java");
			habilidades.add("sql");
			
			Fachada.criarCandidato("Lucas", "082", "lucas@teste.com", habilidades, "Dev");
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


