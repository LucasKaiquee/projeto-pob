package daodb4o;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;

	public static void open() {
		manager = Util.conectarBanco(); // banco local
		//manager = Util.conectarBancoRemoto(); //banco remoto
	}

	public static void close() {
		Util.desconectar();
	}

	// ----------CRUD-----------------------

	public void create(T obj) {
		manager.store(obj);
	}

	public T update(T obj) {
		manager.store(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.delete(obj);
	}

	public void refresh(T obj) {
		manager.ext().refresh(obj, Integer.MAX_VALUE);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		manager.ext().purge(); // limpar cache do manager

		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) q.execute();
	}

	// --------transa��o---------------
	public static void begin() {
	} // tem que ser vazio

	public static void commit() {
		manager.commit();
		manager.ext().purge();
	}

	public static void rollback() {
		manager.rollback();
	}

	/**
	 * gerar novo id para o tipo T, baseando-se no maior valor do id
	 * 
	 */
	public <X> int gerarId(Class<X> classe) {
		// Verificar se o banco está vazio
		if (manager.query(classe).isEmpty()) {
			return 0; // Retorna 0 como primeiro ID
		} else {
			// Obter o maior ID da classe
			Query q = manager.query();
			q.constrain(classe);
			q.descend("id").orderDescending();
			List<X> resultados = q.execute();
	
			if (resultados.isEmpty()) {
				return 0; // Retorna 0 como primeiro ID caso não haja resultados
			} else {
				try {
					// Obter o objeto com o maior ID
					X objeto = resultados.get(0);
					int id = -1;
	
					for (Field f : getAllFieldsList(classe)) {
						if (f.getName().equals("id")) {
							f.setAccessible(true); // Permitir acesso a atributos privados
							id = (Integer) f.get(objeto);
							break; // Campo 'id' encontrado, parar a busca
						}
					}
	
					if (id == -1) {
						throw new NoSuchFieldException("Campo 'id' não encontrado na classe " + classe.getName());
					}
	
					return id + 1; // Incrementar o maior ID encontrado
				} catch (NoSuchFieldException e) {
					throw new RuntimeException("Classe " + classe.getName() + " - não tem atributo 'id'", e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Classe " + classe.getName() + " - atributo 'id' inacessível", e);
				}
			}
		}
	}
	

	public static <X> List<Field> getAllFieldsList(final Class<X> cls) {
		// retorna uma lista com todos os campos do objeto
		final List<Field> allFields = new ArrayList<>();
		Class<?> currentClass = cls;
		while (currentClass != null) {
			final Field[] declaredFields = currentClass.getDeclaredFields();
			Collections.addAll(allFields, declaredFields);
			currentClass = currentClass.getSuperclass();
		}
		return allFields;
	}
}
