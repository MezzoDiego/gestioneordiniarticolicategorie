package it.prova.gestioneordiniarticolicategorie.dao.articolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public class ArticoloDAOImpl implements ArticoloDAO{

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	
	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();

	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);

	}

	@Override
	public void update(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		articoloInstance = entityManager.merge(articoloInstance);		
	}

	@Override
	public void insert(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(articoloInstance);
		
	}

	@Override
	public void delete(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(articoloInstance));
		
	}

	@Override
	public Articolo findByIdFetchingCategorie(Long id) throws Exception {
		TypedQuery<Articolo> query = entityManager
				.createQuery("select a FROM Articolo a left join fetch a.categorie c where a.id = :idArticolo", Articolo.class);
		query.setParameter("idArticolo", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public void deleteAllFromJoinTable() throws Exception {
		entityManager.createNativeQuery("delete from articolo_categoria").executeUpdate();
		
	}

	@Override
	public void deleteArticoloFromJoinTable(Long idArticolo) throws Exception {
		entityManager.createNativeQuery("delete from articolo_categoria where articolo_id = ?1").setParameter(1, idArticolo).executeUpdate();

		
	}

	@Override
	public int giveMetheSumOfPricesOfCategoriasArticoli(Categoria categoriaInstance) throws Exception {
		TypedQuery<Long> query = entityManager
				.createQuery("select sum(a.prezzoSingolo) FROM Articolo a left join a.categorie c where c.id = :idCategoria", Long.class);
		query.setParameter("idCategoria", categoriaInstance.getId());
		return query.getSingleResult().intValue();
	}

}
