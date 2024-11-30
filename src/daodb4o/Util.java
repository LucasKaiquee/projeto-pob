package daodb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

import modelo.Candidato;
import modelo.Recrutador;
import modelo.Vaga;
public class Util {
	private static ObjectContainer manager=null;

	public static ObjectContainer conectarBanco(){
		if (manager != null)
			return manager;		//ja tem uma conexao

		//---------------------------------------------------------------
		//configurar, criar e abrir banco local (pasta do projeto)
		//---------------------------------------------------------------
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// habilitar cascata para alterar, apagar e recuperar objetos
		config.common().objectClass(Candidato.class).cascadeOnDelete(false);
		config.common().objectClass(Candidato.class).cascadeOnUpdate(true);
		config.common().objectClass(Candidato.class).cascadeOnActivate(true);
		config.common().objectClass(Recrutador.class).cascadeOnDelete(true);
		config.common().objectClass(Recrutador.class).cascadeOnUpdate(true);
		config.common().objectClass(Recrutador.class).cascadeOnActivate(true);
		config.common().objectClass(Vaga.class).cascadeOnDelete(false);
		config.common().objectClass(Vaga.class).cascadeOnUpdate(true);
		config.common().objectClass(Vaga.class).cascadeOnActivate(true);		
		
		
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}
	
	public static ObjectContainer conectarBancoRemoto(){		
		if (manager != null)
			return manager;		

		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
		config.common().messageLevel(0);  // 0,1,2,3...

		config.common().objectClass(Candidato.class).cascadeOnDelete(false);
		config.common().objectClass(Candidato.class).cascadeOnUpdate(true);
		config.common().objectClass(Candidato.class).cascadeOnActivate(true);
		config.common().objectClass(Recrutador.class).cascadeOnDelete(false);
		config.common().objectClass(Recrutador.class).cascadeOnUpdate(true);
		config.common().objectClass(Recrutador.class).cascadeOnActivate(true);
		config.common().objectClass(Vaga.class).cascadeOnDelete(false);
		config.common().objectClass(Vaga.class).cascadeOnUpdate(true);
		config.common().objectClass(Vaga.class).cascadeOnActivate(true);		

		String ipservidor="localhost";
		manager = Db4oClientServer.openClient(config, ipservidor, 34000,"usuario1","senha1");
		return manager;
	}

	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
}
