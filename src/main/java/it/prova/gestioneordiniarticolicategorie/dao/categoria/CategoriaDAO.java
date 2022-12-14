package it.prova.gestioneordiniarticolicategorie.dao.categoria;

import java.util.Date;
import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.IBaseDAO;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface CategoriaDAO extends IBaseDAO<Categoria>{
	
	public Categoria findByIdFetchingArticoli(Long id) throws Exception;
	
	public void deleteCategoriaFromJoinTable(Long idCategoria) throws Exception;
	
	public List<Categoria> findAllArticolisCategorieOfAnOrdine(Ordine ordineInstance) throws Exception;	
	
	public List<String> findCategoriesCodiciOfOrdiniMadeInMonth(Date input) throws Exception;


}
