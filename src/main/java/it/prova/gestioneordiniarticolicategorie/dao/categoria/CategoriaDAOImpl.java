package it.prova.gestioneordiniarticolicategorie.dao.categoria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class CategoriaDAOImpl implements CategoriaDAO{

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	
	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();

	}

	@Override
	public Categoria get(Long id) throws Exception {
		return entityManager.find(Categoria.class, id);

	}

	@Override
	public void update(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		categoriaInstance = entityManager.merge(categoriaInstance);
		
	}

	@Override
	public void insert(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(categoriaInstance);
		
	}

	@Override
	public void delete(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(categoriaInstance));
		
	}

	@Override
	public Categoria findByIdFetchingArticoli(Long id) throws Exception {
		TypedQuery<Categoria> query = entityManager
				.createQuery("select c FROM Categoria c left join fetch c.articoli a where c.id = :idCategoria", Categoria.class);
		query.setParameter("idCategoria", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public void deleteCategoriaFromJoinTable(Long idCategoria) throws Exception {
		entityManager.createNativeQuery("delete from articolo_categoria where categoria_id = ?1").setParameter(1, idCategoria).executeUpdate();

		
	}

	@Override
	public List<Categoria> findAllArticolisCategorieOfAnOrdine(Ordine ordineInstance) throws Exception {
		TypedQuery<Categoria> query = entityManager
				.createQuery("select distinct c FROM Categoria c inner join fetch c.articoli a inner join fetch a.ordine o where o.id = :idOrdine", Categoria.class);
		query.setParameter("idOrdine", ordineInstance.getId());
		return query.getResultList();
	}

}
