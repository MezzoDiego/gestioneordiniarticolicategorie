package it.prova.gestioneordiniarticolicategorie.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.exception.OrdineConArticoliAssociatiException;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;
import it.prova.gestioneordiniarticolicategorie.service.MyServiceFactory;
import it.prova.gestioneordiniarticolicategorie.service.articolo.ArticoloService;
import it.prova.gestioneordiniarticolicategorie.service.categoria.CategoriaService;
import it.prova.gestioneordiniarticolicategorie.service.ordine.OrdineService;

public class TestOrdine {

	public static void main(String[] args) {
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {

			System.out.println("##################################################################################");
			// testInserimentoNuovoOrdine(ordineServiceInstance);
			System.out.println("##################################################################################");
			// testAggiornamentoOrdineEsistente(ordineServiceInstance);
			System.out.println("##################################################################################");
			// testInserimentoNuovoArticolo(articoloServiceInstance, ordineServiceInstance);
			System.out.println("##################################################################################");
			// testAggiornamentoArticolo(articoloServiceInstance, ordineServiceInstance);
			System.out.println("##################################################################################");
			// testInserimentoNuovaCategoria(categoriaServiceInstance);
			System.out.println("##################################################################################");
			// testAggiornamentoCategoria(categoriaServiceInstance);
			System.out.println("##################################################################################");
			// testAggiungiArticoloACategoria(ordineServiceInstance,
			// articoloServiceInstance, categoriaServiceInstance);
			System.out.println("##################################################################################");
			// testAggiungiCategoriaAdArticolo(ordineServiceInstance,
			// articoloServiceInstance, categoriaServiceInstance);
			System.out.println("##################################################################################");
			// testRimozioneOrdineCustomExceptionSeArticoliPresenti(ordineServiceInstance,
			// articoloServiceInstance);
			System.out.println("##################################################################################");
			// testTrovaOrdiniEffettuatiPerCategoria(ordineServiceInstance,
			// articoloServiceInstance, categoriaServiceInstance);
			System.out.println("##################################################################################");
			// testTrovaCategorieDistinctArticoliDiOrdine(ordineServiceInstance,
			// articoloServiceInstance,
			// categoriaServiceInstance);
			System.out.println("##################################################################################");
			testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria(ordineServiceInstance, articoloServiceInstance,
					categoriaServiceInstance);
			System.out.println("##################################################################################");

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserimentoNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {

		System.out.println(".......testInserimentoNuovoOrdine inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego mezzo", "Via fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovoOrdine fallito ");

		// reset tabella
		ordineServiceInstance.rimuovi(ordineInstance.getId());
		System.out.println(".......testInserimentoNuovoOrdine fine: PASSED.............");

	}

	private static void testAggiornamentoOrdineEsistente(OrdineService ordineServiceInstance) throws Exception {

		System.out.println(".......testAggiornamentoOrdineEsistente inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego Mezzo", "Via Fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException("testAggiornamentoOrdineEsistente fallito, ordine non inserito ");

		// esecuzione update
		String vecchioNomeDestinatario = ordineInstance.getNomeDestinatario();
		ordineInstance.setNomeDestinatario("Alexandru Sava");
		ordineServiceInstance.aggiorna(ordineInstance);

		if (ordineInstance.getNomeDestinatario().equalsIgnoreCase(vecchioNomeDestinatario))
			throw new RuntimeException(
					"testAggiornamentoOrdineEsistente FAILED: si e' verificato un errore durante l'update.");

		// reset tabella
		ordineServiceInstance.rimuovi(ordineInstance.getId());

		System.out.println(".......testAggiornamentoOrdineEsistente fine: PASSED.............");

	}

	private static void testInserimentoNuovoArticolo(ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {

		System.out.println(".......testInserimentoNuovoArticolo inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego Mezzo", "Via Fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovoArticolo fallito, ordine non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance = new Articolo("Portatile asus", "65132a6845ddax", 750, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance);

		// verifica corretto inserimento
		if (articoloInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovoArticolo fallito, articolo non inserito ");

		// reset tabelle
		articoloServiceInstance.rimuovi(articoloInstance.getId());
		ordineServiceInstance.rimuovi(ordineInstance.getId());

		System.out.println(".......testInserimentoNuovoArticolo fine: PASSED.............");

	}

	private static void testAggiornamentoArticolo(ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {

		System.out.println(".......testAggiornamentoArticolo inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego Mezzo", "Via Fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException("testAggiornamentoArticolo fallito, ordine non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance = new Articolo("Portatile asus", "65132a6845ddax", 750, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance);

		// verifica corretto inserimento
		if (articoloInstance.getId() == null)
			throw new RuntimeException("testAggiornamentoArticolo fallito, articolo non inserito ");

		// esecuzione update articolo
		String vecchioNumeroSeriale = articoloInstance.getNumeroSeriale();
		articoloInstance.setNumeroSeriale("65132a6846dday");
		articoloServiceInstance.aggiorna(articoloInstance);

		if (articoloInstance.getNumeroSeriale().equalsIgnoreCase(vecchioNumeroSeriale))
			throw new RuntimeException(
					"testAggiornamentoArticolo FAILED: si e'verificato un errore durante l'update dell'articolo.");

		// reset tabelle
		articoloServiceInstance.rimuovi(articoloInstance.getId());
		ordineServiceInstance.rimuovi(ordineInstance.getId());

		System.out.println(".......testAggiornamentoArticolo fine: PASSED.............");

	}

	private static void testInserimentoNuovaCategoria(CategoriaService categoriaServiceInstance) throws Exception {

		System.out.println(".......testInserimentoNuovaCategoria inizio.............");

		// creo categoria e la inserisco
		Categoria categoriaInstance = new Categoria("Elettronica", "24e");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance);

		// verifica corretto inserimento
		if (categoriaInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovaCategoria fallito ");

		// reset tabella
		categoriaServiceInstance.rimuovi(categoriaInstance.getId());

		System.out.println(".......testInserimentoNuovaCategoria fine: PASSED.............");

	}

	private static void testAggiornamentoCategoria(CategoriaService categoriaServiceInstance) throws Exception {

		System.out.println(".......testAggiornamentoCategoria inizio.............");

		// creo categoria e la inserisco
		Categoria categoriaInstance = new Categoria("Elettronica", "24e");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance);

		// verifica corretto inserimento
		if (categoriaInstance.getId() == null)
			throw new RuntimeException("testAggiornamentoCategoria fallito, categoria non inserita. ");

		// esecuzione update categoria
		String vecchiaDescrizione = categoriaInstance.getDescrizione();
		categoriaInstance.setDescrizione("Portatili");
		categoriaServiceInstance.aggiorna(categoriaInstance);

		if (categoriaInstance.getDescrizione().equalsIgnoreCase(vecchiaDescrizione))
			throw new RuntimeException("testAggiornamentoCategoria FAILED: update non effettuato correttamente.");

		// reset tabella
		categoriaServiceInstance.rimuovi(categoriaInstance.getId());

		System.out.println(".......testAggiornamentoCategoria fine: PASSED.............");

	}

	private static void testAggiungiArticoloACategoria(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {

		System.out.println(".......testAggiungiArticoloACategoria inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego Mezzo", "Via Fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException("testAggiungiArticoloACategoria fallito, ordine non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance = new Articolo("Portatile asus", "65132a6845ddax", 750, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance);

		// verifica corretto inserimento
		if (articoloInstance.getId() == null)
			throw new RuntimeException("testAggiungiArticoloACategoria fallito, articolo non inserito ");

		// creo categoria e la inserisco
		Categoria categoriaInstance = new Categoria("Elettronica", "24e");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance);

		// verifica corretto inserimento
		if (categoriaInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovaCategoria fallito ");

		// aggiungo articolo a categoria
		categoriaServiceInstance.aggiungiArticolo(categoriaInstance, articoloInstance);

		// verifico avvenuta associazione
		Categoria categoriaReloaded = categoriaServiceInstance
				.caricaSingoloElementoEagerArticoli(categoriaInstance.getId());
		if (categoriaReloaded.getArticoli().isEmpty())
			throw new RuntimeException("testAggiungiArticoloACategoria FAILED: articolo non aggiunto.");

		// reset tabelle
		articoloServiceInstance.rimuoviTuttiGliArticoliDallaTabellaDiJoin();
		categoriaServiceInstance.rimuovi(categoriaInstance.getId());
		articoloServiceInstance.rimuovi(articoloInstance.getId());
		ordineServiceInstance.rimuovi(ordineInstance.getId());

		System.out.println(".......testAggiungiArticoloACategoria fine: PASSED.............");

	}

	private static void testAggiungiCategoriaAdArticolo(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {

		System.out.println(".......testAggiungiCategoriaAdArticolo inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego Mezzo", "Via Fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException("testAggiungiCategoriaAdArticolo fallito, ordine non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance = new Articolo("Portatile asus", "65132a6845ddax", 750, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance);

		// verifica corretto inserimento
		if (articoloInstance.getId() == null)
			throw new RuntimeException("testAggiungiCategoriaAdArticolo fallito, articolo non inserito ");

		// creo categoria e la inserisco
		Categoria categoriaInstance = new Categoria("Elettronica", "24e");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance);

		// verifica corretto inserimento
		if (categoriaInstance.getId() == null)
			throw new RuntimeException("testAggiungiCategoriaAdArticolo fallito, categoria non inserita ");

		// aggiungo categoria ad articolo
		articoloServiceInstance.aggiungiCategoria(articoloInstance, categoriaInstance);

		// verifico avvenuta associazione
		Articolo articoloReloaded = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstance.getId());
		if (articoloReloaded.getCategorie().isEmpty())
			throw new RuntimeException("testAggiungiCategoriaAdArticolo FAILED: categoria non aggiunta.");

		// reset tabelle
		articoloServiceInstance.rimuoviTuttiGliArticoliDallaTabellaDiJoin();
		categoriaServiceInstance.rimuovi(categoriaInstance.getId());
		articoloServiceInstance.rimuovi(articoloInstance.getId());
		ordineServiceInstance.rimuovi(ordineInstance.getId());

		System.out.println(".......testAggiungiCategoriaAdArticolo fine: PASSED.............");

	}

	private static void testRimozioneOrdineCustomExceptionSeArticoliPresenti(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance) throws Exception {

		System.out.println(".......testRimozioneOrdineCustomExceptionSeArticoliPresenti inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego Mezzo", "Via Fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException(
					"testRimozioneOrdineCustomExceptionSeArticoliPresenti fallito, ordine non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance = new Articolo("Portatile asus", "65132a6845ddax", 750, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance);

		// verifica corretto inserimento
		if (articoloInstance.getId() == null)
			throw new RuntimeException(
					"testRimozioneOrdineCustomExceptionSeArticoliPresenti fallito, articolo non inserito ");

		try {
			if (articoloInstance.getOrdine().equals(ordineInstance))
				throw new OrdineConArticoliAssociatiException(
						"testRimozioneOrdineCustomExceptionSeArticoliPresenti FAILED");
		} catch (OrdineConArticoliAssociatiException ex) {
			ex.printStackTrace();
			articoloServiceInstance.rimuovi(articoloInstance.getId());
			ordineServiceInstance.rimuovi(ordineInstance.getId());
		}

		System.out.println(".......testRimozioneOrdineCustomExceptionSeArticoliPresenti fine: PASSED.............");

	}

	private static void testTrovaOrdiniEffettuatiPerCategoria(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {

		System.out.println(".......testTrovaOrdiniEffettuatiPerCategoria inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego Mezzo", "Via Fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException("testTrovaOrdiniEffettuatiPerCategoria fallito, ordine non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance = new Articolo("Portatile asus", "65132a6845ddax", 750, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance);

		// verifica corretto inserimento
		if (articoloInstance.getId() == null)
			throw new RuntimeException("testTrovaOrdiniEffettuatiPerCategoria fallito, articolo non inserito ");

		// creo categoria e la inserisco
		Categoria categoriaInstance = new Categoria("Elettronica", "24e");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance);

		// verifica corretto inserimento
		if (categoriaInstance.getId() == null)
			throw new RuntimeException("testTrovaOrdiniEffettuatiPerCategoria fallito, categoria non inserita ");

		// aggiungo categoria ad articolo
		articoloServiceInstance.aggiungiCategoria(articoloInstance, categoriaInstance);

		// verifico avvenuta associazione
		Articolo articoloReloaded = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstance.getId());
		if (articoloReloaded.getCategorie().isEmpty())
			throw new RuntimeException("testTrovaOrdiniEffettuatiPerCategoria FAILED: categoria non aggiunta.");

		// esecuzione query
		List<Ordine> ordiniEffettuatiPerUnaDeterminataCategoria = ordineServiceInstance
				.trovaOrdiniEffettuatiPerCategoria(categoriaInstance);

		// verifica correttezza query
		if (ordiniEffettuatiPerUnaDeterminataCategoria.size() != 1)
			throw new RuntimeException(
					"testTrovaOrdiniEffettuatiPerCategoria FAILED: non ci sono ordini effettuati per quella determinata categoria.");

		// reset tabelle
		articoloServiceInstance.rimuoviTuttiGliArticoliDallaTabellaDiJoin();
		categoriaServiceInstance.rimuovi(categoriaInstance.getId());
		articoloServiceInstance.rimuovi(articoloInstance.getId());
		ordineServiceInstance.rimuovi(ordineInstance.getId());

		System.out.println(".......testTrovaOrdiniEffettuatiPerCategoria fine: PASSED.............");

	}

	private static void testTrovaCategorieDistinctArticoliDiOrdine(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {

		System.out.println(".......testTrovaCategorieDistinctArticoliDiOrdine inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego Mezzo", "Via Fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException("testTrovaCategorieDistinctArticoliDiOrdine fallito, ordine non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance = new Articolo("Portatile asus", "65132a6845ddax", 750, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance);

		// verifica corretto inserimento
		if (articoloInstance.getId() == null)
			throw new RuntimeException("testTrovaCategorieDistinctArticoliDiOrdine fallito, articolo non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance1 = new Articolo("Tosaerba", "654531sda3s4d5", 1500, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance1);

		// verifica corretto inserimento
		if (articoloInstance1.getId() == null)
			throw new RuntimeException("testTrovaCategorieDistinctArticoliDiOrdine fallito, articolo non inserito ");

		// creo categoria e la inserisco
		Categoria categoriaInstance = new Categoria("Elettronica", "24e");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance);

		// verifica corretto inserimento
		if (categoriaInstance.getId() == null)
			throw new RuntimeException("testTrovaCategorieDistinctArticoliDiOrdine fallito, categoria non inserita ");

		// creo categoria e la inserisco
		Categoria categoriaInstance1 = new Categoria("Giardinaggio", "15g");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance1);

		// verifica corretto inserimento
		if (categoriaInstance1.getId() == null)
			throw new RuntimeException("testTrovaCategorieDistinctArticoliDiOrdine fallito, categoria non inserita ");

		// aggiungo categoria ad articolo
		articoloServiceInstance.aggiungiCategoria(articoloInstance, categoriaInstance);

		// verifico avvenuta associazione
		Articolo articoloReloaded = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstance.getId());
		if (articoloReloaded.getCategorie().isEmpty())
			throw new RuntimeException("testTrovaCategorieDistinctArticoliDiOrdine FAILED: categoria non aggiunta.");

		// aggiungo categoria ad articolo
		articoloServiceInstance.aggiungiCategoria(articoloInstance1, categoriaInstance1);

		// verifico avvenuta associazione
		Articolo articoloReloaded1 = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstance1.getId());
		if (articoloReloaded1.getCategorie().isEmpty())
			throw new RuntimeException("testTrovaCategorieDistinctArticoliDiOrdine FAILED: categoria non aggiunta.");

		// esecuzione query di ricerca
		List<Categoria> categorieFacentiParteDellOrdine = categoriaServiceInstance
				.trovaCategorieDistinctArticoliDiOrdine(ordineInstance);

		// verifica correttezza query di ricerca
		if (categorieFacentiParteDellOrdine.size() != 2)
			throw new RuntimeException(
					"testTrovaCategorieDistinctArticoliDiOrdine FAILED: errore nella query di ricerca. ");

		// reset tabelle
		articoloServiceInstance.rimuoviTuttiGliArticoliDallaTabellaDiJoin();
		categoriaServiceInstance.rimuovi(categoriaInstance.getId());
		articoloServiceInstance.rimuovi(articoloInstance.getId());
		ordineServiceInstance.rimuovi(ordineInstance.getId());

		System.out.println(".......testTrovaCategorieDistinctArticoliDiOrdine fine: PASSED.............");

	}

	private static void testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {

		System.out.println(".......testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria inizio.............");

		// creo ordine e lo inserisco
		Date dataSpedizione = new SimpleDateFormat("dd-MM-yyyy").parse("03-01-2022");
		Date dataScadenza = new SimpleDateFormat("dd-MM-yyyy").parse("13-01-2022");
		Ordine ordineInstance = new Ordine("Diego Mezzo", "Via Fedro 41", dataSpedizione, dataScadenza);
		ordineServiceInstance.inserisciNuovo(ordineInstance);

		// verifica corretto inserimento
		if (ordineInstance.getId() == null)
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria fallito, ordine non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance = new Articolo("Portatile asus", "65132a6845ddax", 750, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance);

		// verifica corretto inserimento
		if (articoloInstance.getId() == null)
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria fallito, articolo non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance1 = new Articolo("Smartphone Samsung", "65132a6945ddaz", 950, new Date(),
				ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance1);

		// verifica corretto inserimento
		if (articoloInstance1.getId() == null)
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria fallito, articolo non inserito ");

		// creo articolo e lo associo all'ordine
		Articolo articoloInstance2 = new Articolo("Tosaerba", "654s63ad6as45", 2550, new Date(), ordineInstance);

		// inserisco articolo
		articoloServiceInstance.inserisciNuovo(articoloInstance2);

		// verifica corretto inserimento
		if (articoloInstance2.getId() == null)
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria fallito, articolo non inserito ");

		// creo categoria e la inserisco
		Categoria categoriaInstance = new Categoria("Elettronica", "24e");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance);

		// verifica corretto inserimento
		if (categoriaInstance.getId() == null)
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria fallito, categoria non inserita ");

		// creo categoria e la inserisco
		Categoria categoriaInstance1 = new Categoria("Giardinaggio", "15g");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance1);

		// verifica corretto inserimento
		if (categoriaInstance1.getId() == null)
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria fallito, categoria non inserita ");

		// aggiungo categoria ad articolo
		articoloServiceInstance.aggiungiCategoria(articoloInstance, categoriaInstance);

		// verifico avvenuta associazione
		Articolo articoloReloaded = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstance.getId());
		if (articoloReloaded.getCategorie().isEmpty())
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria FAILED: categoria non aggiunta.");

		// aggiungo categoria ad articolo
		articoloServiceInstance.aggiungiCategoria(articoloInstance1, categoriaInstance);

		// verifico avvenuta associazione
		Articolo articoloReloaded1 = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstance1.getId());
		if (articoloReloaded1.getCategorie().isEmpty())
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria FAILED: categoria non aggiunta.");

		// aggiungo categoria ad articolo
		articoloServiceInstance.aggiungiCategoria(articoloInstance2, categoriaInstance1);

		// verifico avvenuta associazione
		Articolo articoloReloaded2 = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstance2.getId());
		if (articoloReloaded2.getCategorie().isEmpty())
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria FAILED: categoria non aggiunta.");

		// esecuzione query
		int sommaPrezziDiArticoliDiUnaDeterminataCategoria = articoloServiceInstance
				.voglioLaSommaDeiPrezziDegliArticoliDellaCategoria(categoriaInstance);

		// verifica funzionamento query
		if (sommaPrezziDiArticoliDiUnaDeterminataCategoria != (articoloInstance.getPrezzoSingolo()
				+ articoloInstance1.getPrezzoSingolo()))
			throw new RuntimeException(
					"testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria FAILED: si e'verificato un errore durante l'esecuzione della query.");

		// reset tabelle
		articoloServiceInstance.rimuoviTuttiGliArticoliDallaTabellaDiJoin();
		categoriaServiceInstance.rimuovi(categoriaInstance.getId());
		categoriaServiceInstance.rimuovi(categoriaInstance1.getId());
		articoloServiceInstance.rimuovi(articoloInstance.getId());
		articoloServiceInstance.rimuovi(articoloInstance1.getId());
		articoloServiceInstance.rimuovi(articoloInstance2.getId());
		ordineServiceInstance.rimuovi(ordineInstance.getId());

		System.out.println(".......testVoglioLaSommaDeiPrezziDegliArticoliDellaCategoria fine: PASSED.............");

	}

}
