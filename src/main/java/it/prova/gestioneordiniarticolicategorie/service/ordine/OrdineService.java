package it.prova.gestioneordiniarticolicategorie.service.ordine;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;


public interface OrdineService {

	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloOrdine(Long id) throws Exception;
	
	public Ordine caricaSingoloOrdineConArticoli(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Ordine ordineInstance) throws Exception;
	
	//per injection
	public void setOrdineDAO(OrdineDAO ordineDAO);
}
