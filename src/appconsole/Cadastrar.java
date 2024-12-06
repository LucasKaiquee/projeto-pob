package appconsole;

import java.util.ArrayList;
import java.util.List;
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


			Fachada.criarVaga("Engenheiro de Software", 6000.0, "TI",skills , Fachada.localizarRecrutador("1234"));
			Fachada.criarVaga("Recrutador", 4000.0, "Rh",skills , Fachada.localizarRecrutador("4321"));
			Fachada.criarVaga("Cientista de Dados", 8000.0, "Dados", skills, Fachada.localizarRecrutador("cancelado"));

			List<String> habilidades = new ArrayList<>();
			habilidades.add("Java");
			habilidades.add("SQL");
			
			Fachada.criarCandidato("Lucas", "082", "lucas@teste.com", habilidades, "TI");
			Fachada.criarCandidato("Lorena", "1234", "lorena@teste.com", habilidades, "RH");
			Fachada.criarCandidato("Lucas", "132", "lucas@teste.com", habilidades, "Dados");

			Fachada.candidatar(Fachada.localizarCandidato("082"), Fachada.localizarVaga(1));
			Fachada.candidatar(Fachada.localizarCandidato("132"), Fachada.localizarVaga(1));

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


