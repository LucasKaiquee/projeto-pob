package appconsole;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar(){
		try {
			Fachada.inicializar();

			Fachada.criarRecrutador("1234", "Luiz", "luiz@email.com", "ContratAe");
			Fachada.criarRecrutador("4321", "Lucas", "lucas@email.com", "ContratAe");
			Fachada.criarRecrutador("cancelado", "Marcelo", "marcelo@email", "ContratAe");
			
			List<String> skills = new ArrayList<>();
			skills.add("skill1");
			skills.add("skill2");
			skills.add("skill3");


			Fachada.criarVaga("Engenheiro de Dados", 6000.0, "Dados",skills , Fachada.localizarRecrutador("1234"));
			Fachada.criarVaga("Analista de Dados", 4000.0, "Dados",skills , Fachada.localizarRecrutador("4321"));
			Fachada.criarVaga("Cientista de Dados", 8000.0, "Dados", skills, Fachada.localizarRecrutador("cancelado"));

			
			
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


