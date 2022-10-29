package it.prova.gestioneordiniarticolicategorie.dao.ordine;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.IBaseDAO;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine>{
	
	public Ordine findByIdFetchingArticoli(Long id) throws Exception;

	public List<Ordine> findAllOrdiniMadeForCategoria(Categoria categoriaInstance) throws Exception;
	
	public Ordine findMostRecentOrdineWithCategoria(Categoria categoriaInstance) throws Exception;
	
	public List<String> findAllIndirizziOfOrdiniHavingAStringInTheSerialNumberOfHisArticoli(String input) throws Exception;



}
