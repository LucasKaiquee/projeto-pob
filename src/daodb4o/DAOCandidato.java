/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Candidato;

public class DAOCandidato  extends DAO<Candidato>{
		public Candidato read (String nome) {
		Query q = manager.query();
		q.constrain(Candidato.class);
		q.descend("nome").constrain(nome);
		List<Candidato> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
}

