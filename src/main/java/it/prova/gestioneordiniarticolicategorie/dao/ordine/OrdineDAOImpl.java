package it.prova.gestioneordiniarticolicategorie.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO{

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	
	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();

	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);

	}

	@Override
	public void update(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}
		ordineInstance = entityManager.merge(ordineInstance);
		
	}

	@Override
	public void insert(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(ordineInstance);
		
	}

	@Override
	public void delete(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(ordineInstance));
		
	}

	@Override
	public Ordine findByIdFetchingArticoli(Long id) throws Exception {
		TypedQuery<Ordine> query = entityManager
				.createQuery("from Ordine o left join fetch o.articoli where o.id = ?1", Ordine.class);
		query.setParameter(1, id);
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<Ordine> findAllOrdiniMadeForCategoria(Categoria categoriaInstance) throws Exception {
		TypedQuery<Ordine> query = entityManager
				.createQuery("select o from Ordine o inner join fetch o.articoli a inner join fetch a.categorie c where c.id = ?1", Ordine.class);
		query.setParameter(1, categoriaInstance.getId());
		return query.getResultList();
	}

	@Override
	public Ordine findMostRecentOrdineWithCategoria(Categoria categoriaInstance) throws Exception {
		TypedQuery<Ordine> query = entityManager
				.createQuery("from Ordine o inner join fetch o.articoli a inner join fetch a.categorie c where o.dataSpedizione in ( select min(o.dataSpedizione) from Ordine o) and c.id = ?1 ", Ordine.class);
		query.setParameter(1, categoriaInstance.getId());
		return query.getSingleResult();
	}

}
