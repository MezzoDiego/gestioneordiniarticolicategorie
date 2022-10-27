package it.prova.gestioneordiniarticolicategorie.service.categoria;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;


public interface CategoriaService {
	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;
	
	public Categoria caricaSingoloElementoEagerArticoli(Long id) throws Exception;

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	public void rimuovi(Long idCategoria) throws Exception;
	
	public void aggiungiArticolo(Categoria appInstance, Articolo articoloInstance) throws Exception;
	
	public void rimuoviCategoriaDallaTabellaDiJoin(Long idCategoria) throws Exception;
	
	// per injection
		public void setCategoriaDAO(CategoriaDAO categoriaDAO);
}