package it.prova.gestioneordiniarticolicategorie.service.articolo;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public class ArticoloServiceImpl implements ArticoloService{

	private ArticoloDAO articoloDAO;
	
	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAO) {
		this.articoloDAO = articoloDAO;
		
	}
	
	@Override
	public List<Articolo> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articolo caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articolo caricaSingoloElementoEagerCategorie(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Articolo articoloInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Articolo articoloInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idArticolo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuoviTuttiGliArticoliDallaTabellaDiJoin() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuoviArticoloDallaTabellaDiJoin(Long idArticolo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiungiCategoria(Articolo articoloInstance, Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
