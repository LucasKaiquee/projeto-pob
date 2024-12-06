package daodb4o;

import java.util.List;

import com.db4o.query.Query;
import modelo.Candidato;

public class DAOCandidato extends DAO<Candidato>{
	public Candidato read (String cpf) {
		Query q = manager.query();
		q.constrain(Candidato.class);
		q.descend("cpf").constrain(cpf);
		List<Candidato> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	public List<Candidato> readByArea(String area) {
		Query q = manager.query();
		q.constrain(Candidato.class);
		q.descend("area").constrain(area);
		return q.execute();
	}
	
}

