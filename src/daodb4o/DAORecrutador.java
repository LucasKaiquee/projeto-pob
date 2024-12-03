package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Recrutador;

public class DAORecrutador  extends DAO<Recrutador>{
	
	//nome � usado como campo unico 
	public Recrutador read (String cpf) {
		Query q = manager.query();
		q.constrain(Recrutador.class);
		q.descend("cpf").constrain(cpf);
		List<Recrutador> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
}

