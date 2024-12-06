package appconsole;

import java.util.List;
import modelo.Candidato;
import modelo.Vaga;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar(){

		try {
			Fachada.inicializar();

			List<Candidato> candidatosTI = Fachada.listarCandidatosPorArea("TI");
			for (Candidato candidato : candidatosTI) {
				System.out.println(candidato);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("--------------");

		try {
			List<Vaga> vagasSemCandidaturas = Fachada.localizarVagaComMinimoCandidaturas(1);
			for (Vaga v : vagasSemCandidaturas) {
				System.out.println(v);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("--------------");

		try {
			List<Vaga> vagasRh = Fachada.localizarVagasPorArea("Rh");
			for (Vaga v : vagasRh) {
				System.out.println(v);
			}
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

