package daodb4o;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Query;

import modelo.Vaga;

public class DAOVaga  extends DAO<Vaga>{
	
	//nome � usado como campo unico 
	public List<Vaga> read (int id) {
		Query q = manager.query();
		q.constrain(Vaga.class);
		q.descend("id").constrain(id);
		List<Vaga> resultados = q.execute();
		if (resultados.size()>0)
			return resultados;
		else
			return null;
	}
	public void create(Vaga obj){
		int novoid = super.gerarId(Vaga.class);  	//gerar novo id da classe
		obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
		manager.store( obj );
	}

	public List<Vaga> readByArea(String area) {
        Query q = manager.query();
        q.constrain(Vaga.class);
        q.descend("area").constrain(area);
        return q.execute();
    }

	public List<Vaga> readVagasComMaisDeNCandidaturas(int numeroCandidaturasMinimo) {
		Query q = manager.query();
		q.constrain(Vaga.class);
	
		// Executa a consulta para obter todas as vagas
		List<Vaga> vagas = q.execute();
		List<Vaga> vagasComMaisDeNCandidaturas = new ArrayList<>();
		for (Vaga vaga : vagas) {
			if (vaga.getCandidaturas().size() > numeroCandidaturasMinimo) {
				vagasComMaisDeNCandidaturas.add(vaga);
			}
		}
	
		return vagasComMaisDeNCandidaturas;
	}
}

