package MatteoOrlando.CapStone.runners;

import MatteoOrlando.CapStone.repositories.CategoryDAO;
import MatteoOrlando.CapStone.repositories.PlatformDAO;
import MatteoOrlando.CapStone.repositories.ProductDAO;
import MatteoOrlando.CapStone.repositories.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import MatteoOrlando.CapStone.entities.Category;
import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.entities.Product;
import MatteoOrlando.CapStone.entities.Review;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private PlatformDAO platformDAO;
    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public void run(String... args) throws Exception {
        seedProducts();
    }

    private void seedProducts() {
        if (productDAO.count() == 1000) {  // Verifico se il database è già popolato
            List<Product> products = Arrays.asList(
                    createProduct("The Witcher 3: Wild Hunt", "Immergiti nell'universo vasto e ricco di storie di The Witcher 3: Wild Hunt. Questo gioco di ruolo open-world ti porta in un mondo fantasy pieno di pericolose creature, incantesimi potenti e decisioni morali complesse. Sviluppato con una grafica mozzafiato e un gameplay profondo che ti tiene incollato allo schermo per ore.", new BigDecimal("39.99"), "Giochi di ruolo"),
                    createProduct("FIFA 24", "FIFA 24 porta l'esperienza calcistica a un nuovo livello con miglioramenti grafici, modalità di gioco rinnovate e un'IA avanzata. Sperimenta la gioia del calcio giocando con le squadre aggiornate, nelle leghe più famose del mondo e in stadi incredibilmente realistici.", new BigDecimal("59.99"), "Sport"),
                    createProduct("Call of Duty: Future Warfare", "Call of Duty: Future Warfare è uno sparatutto in prima persona che ti catapulta in un futuro distopico. Dotato di armamenti avanzati e tattiche di combattimento innovative, il gioco offre una narrazione intensa e missioni che metteranno alla prova le tue abilità di combattimento in scenari futuristici.", new BigDecimal("69.99"), "Sparatutto"),
                    createProduct("Animal Crossing: Island Life", "Animal Crossing: Island Life espande l'amato universo di Animal Crossing con nuove attività da esplorare e isole da scoprire. Personalizza il tuo villaggio e interagisci con una varietà di personaggi carismatici in un mondo ricco di dettagli e sorprese.", new BigDecimal("59.99"), "Simulazione"),
                    createProduct("Minecraft 2", "Minecraft 2 porta la creatività del sandbox game originale a nuovi livelli con grafica migliorata e meccaniche innovative. Costruisci, esplora e sopravvivi in mondi generati dinamicamente, pieni di nuove risorse e avventure.", new BigDecimal("29.99"), "Avventura"),
                    createProduct("Cyberpunk 2078", "Esplora un futuro ancora più immersivo e dettagliato in Cyberpunk 2078. Naviga tra città neon-lit e distretti ombrosi, mentre interagisci con personaggi intricati e affronti questioni etiche profonde nel tuo percorso verso il potere e la gloria.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Assassin's Creed Ragnarok", "Assassin's Creed Ragnarok ti invita a esplorare l'epico mondo dei vichinghi. Combatti, esplora e scopri miti nordici mentre vivi una storia avvincente di conquista e scoperta in un vasto mondo aperto pieno di pericoli e misteri.", new BigDecimal("69.99"), "Azione"),
                    createProduct("Forza Horizon 6", "Forza Horizon 6 ti porta a correre in location esotiche con auto da sogno. Goditi la fisica di guida realistica e la grafica sbalorditiva mentre competi in eventi adrenalinici in tutto il mondo.", new BigDecimal("69.99"), "Corse"),
                    createProduct("The Legend of Zelda: Echoes of the Past", "Scopri un Hyrule reinventato e più vasto in The Legend of Zelda: Echoes of the Past. Questa nuova leggenda ti porta attraverso foreste, deserti e mari mentre risolvi enigmi complessi e combatti nemici iconici in una trama profondamente coinvolgente.", new BigDecimal("59.99"), "Avventura"),
                    createProduct("Dark Souls IV", "Dark Souls IV porta la serie a nuove altezze con sfide ancora più difficili e inquietanti. Affronta nemici terrificanti e boss epici in un mondo oscuro e intricato, mentre scopri storie nascoste di desolazione e speranza.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Elder Scrolls VI: Argonia", "Esplora le paludi misteriose di Argonia in Elder Scrolls VI. Questo nuovo capitolo dell'epica serie RPG ti immerge in un'avventura attraverso un territorio sconosciuto, pieno di nuove creature, magie e antiche civiltà da scoprire.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Horizon Forbidden West", "Unisciti a Aloy in un'avventura verso territori proibiti e meraviglie sconosciute in Horizon Forbidden West. Scopri nuovi paesaggi mozzafiato, affronta macchine feroci e risolvi misteri in un mondo post-apocalittico ricco di sfide.", new BigDecimal("59.99"), "Azione"),
                    createProduct("God of War: Ragnarok", "La saga di Kratos e Atreus raggiunge il suo culmine nell'imminente battaglia del Ragnarok in God of War: Ragnarok. Affronta divinità e mostri in una narrazione epica di sacrificio e lotta, con un gameplay che combina azione brutalmente soddisfacente e storie emotivamente potenti.", new BigDecimal("69.99"), "Azione"),
                    createProduct("Grand Theft Auto VI", "Grand Theft Auto VI ti riporta a Vice City in una storia avvincente di crimine e sopravvivenza. Esplora un mondo aperto vasto e dettagliato, partecipa a missioni audaci e costruisci il tuo impero criminale in uno dei giochi d'azione-avventura più attesi.", new BigDecimal("69.99"), "Azione"),
                    createProduct("Final Fantasy XVI", "Final Fantasy XVI introduce un mondo dove magia e tecnologia si intrecciano in un nuovo capitolo della storica serie. Partecipa a combattimenti dinamici, esplora paesaggi sbalorditivi e immergiti in una storia di amicizia, guerra e magia.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Resident Evil: Rebirth", "Resident Evil: Rebirth rinnova la serie di survival horror con un'atmosfera ancora più tetra e pericolosa. Sopravvivi contro orde di nemici in scenari raccapriccianti mentre scopri i segreti di una trama oscura e complessa.", new BigDecimal("59.99"), "Horror"),
                    createProduct("Splatoon 3", "Splatoon 3 eleva l'azione multiplayer con nuovi livelli e armi, mantenendo il divertimento caratteristico. Unisciti alla battaglia in coloratissime arene, sfida avversari in tutto il mondo e mostra il tuo stile unico in questo sparatutto dinamico.", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Marvel's Spider-Man 2", "Marvel's Spider-Man 2 ti permette di oscillare tra i grattacieli di New York con meccaniche di gioco migliorate e nuove intriganti storyline. Scopri la dualità di essere Peter Parker e l'iconico supereroe mentre affronti nemici classici e nuove minacce.", new BigDecimal("69.99"), "Azione"),
                    createProduct("Battlefield 2042", "Battlefield 2042 ti porta in scenari futuristici dove nuove modalità di gioco e tecnologie avanzate sfidano le tue abilità strategiche e combattive. Lotta in battaglie massive, gestisci risorse e collabora con la tua squadra per dominare il campo di battaglia.", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Mass Effect: Next Generation", "Mass Effect: Next Generation esplora nuovi livelli di narrazione e interazione interstellare. Come capitano della tua nave, prendi decisioni che influenzano interi sistemi stellari, forma alleanze o crea nemici, mentre scopri i segreti dell'universo.", new BigDecimal("69.99"), "Giochi di ruolo"),
                    createProduct("Starfield", "Starfield offre un'esperienza di esplorazione spaziale in un universo vasto e dettagliato. Sopravvivi in ambienti ostili, costruisci basi spaziali, negozia con alieni e scopri misteri cosmici in un'avventura sandbox senza precedenti.", new BigDecimal("69.99"), "Avventura"),
                    createProduct("Halo Infinite", "Continua la saga di Master Chief in Halo Infinite. Con grafica mozzafiato e nuove missioni, il gioco offre un'esperienza di sparatutto in prima persona epica. Lotta per la sopravvivenza dell'umanità in un universo vasto e in continua evoluzione.", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Overwatch 2", "Overwatch 2 rinnova il popolare sparatutto multiplayer con nuovi eroi, mappe e modalità. Immergiti in battaglie veloci, strategiche e sempre diverse, mentre porti la tua squadra alla vittoria in scenari visivamente stupefacenti.", new BigDecimal("39.99"), "Sparatutto"),
                    createProduct("Diablo IV", "Diablo IV ritorna alle radici oscure della serie con un mondo aperto, interconnesso e più profondo che mai. Affronta orde di demoni, scopri antichi segreti e raccogli potenti artefatti in un RPG d'azione che redefine il genere.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("The Elder Scrolls V: Skyrim", "Scopri nuove avventure nel vasto mondo di Skyrim. Dalla scalata di montagne innevate alla scoperta di antiche cripte, ogni angolo di questo mondo aperto nasconde pericoli e meraviglie. Personalizza il tuo eroe e scrivi la tua leggenda.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Red Dead Redemption 3", "Vivi la vita di un fuorilegge nel selvaggio West in Red Dead Redemption 3. Esplora un mondo aperto ricco di dettagli, affronta missioni di banda, duelli e cacce al tesoro in una storia avvincente di redenzione e sopravvivenza.", new BigDecimal("69.99"), "Avventura"),
                    createProduct("The Sims 5", "The Sims 5 porta la simulazione di vita a nuovi livelli con dettagli incredibilmente ricchi. Costruisci la casa dei tuoi sogni, gestisci relazioni complesse e guida i tuoi Sims attraverso le sfide della vita quotidiana in un mondo vibrante e dinamico.", new BigDecimal("49.99"), "Simulazione"),
                    createProduct("Bloodborne 2", "Bloodborne 2 ti invita a esplorare un mondo oscuro e gotico, ricco di sfide mortali e terrore. Affronta creature mostruose in combattimenti brutali e scopri segreti inquietanti in questo action RPG avvincente e intensamente atmosferico.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("NBA 2K24", "NBA 2K24 ti permette di vivere l'esperienza del campionato NBA con una fedeltà visiva e un realismo senza precedenti. Gestisci la tua squadra, partecipa a partite epiche e affronta le leggende del basket in modalità di gioco innovative e coinvolgenti.", new BigDecimal("59.99"), "Sport"),
                    createProduct("Far Cry 7", "Far Cry 7 ti porta in nuove terre esotiche dove ogni decisione conta. Sviluppa strategie di sopravvivenza mentre esplori ambienti vasti e ostili, affronta nemici pericolosi e scopri storie di resistenza e ribellione in questo sparatutto open world avvincente.", new BigDecimal("69.99"), "Azione"),
                    createProduct("Elden Ring", "Scopri un mondo aperto creato da Hidetaka Miyazaki e George R.R. Martin in Elden Ring. Questo gioco di ruolo ti sfida con combattimenti epici, esplorazioni mozzafiato e enigmi complessi in un universo dove ogni scelta ha conseguenze profonde.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Mortal Kombat 12", "Mortal Kombat 12 eleva il combattimento a nuovi livelli con grafica straordinaria e una meccanica di lotta migliorata. Partecipa a combattimenti brutali, utilizza mosse spettacolari e svela la trama di questo torneo interdimensionale con personaggi iconici e nuovi arrivati.", new BigDecimal("49.99"), "Combattimento"),
                    createProduct("Super Mario Odyssey 2", "Unisciti a Mario in una nuova avventura attraverso mondi fantastici in Super Mario Odyssey 2. Salta, vola e risolvi enigmi in scenari creativi e colorati, mentre salvi la Principessa Peach e sfidi Bowser in sfide epiche e divertenti.", new BigDecimal("59.99"), "Piattaforme"),
                    createProduct("The Last of Us Part III", "Continua l'epica saga di sopravvivenza in un mondo post-apocalittico con The Last of Us Part III. Segui Joel ed Ellie in una storia di sacrificio e speranza, affrontando insieme sfide emotive e fisiche in un mondo devastato da una pandemia.", new BigDecimal("69.99"), "Azione"),
                    createProduct("Gears of War 6", "Gears of War 6 ti porta a combattere contro l'orda locusta in un nuovo capitolo carico di azione e strategia. Con nuove armi, tattiche e una trama coinvolgente, preparati a sfide intense in questo famoso sparatutto.", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Fable IV", "Fable IV ti offre un'avventura magica in un mondo fantasy dove ogni scelta influisce sulla storia. Sviluppa il tuo personaggio, esplora terre incantate e affronta conseguenze morali in un universo vibrante e ricco di folklore.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Hitman 3", "Assumi il ruolo dell'Agente 47 in Hitman 3 e viaggia in location esotiche per completare missioni di assassinio altamente complesse. Usa ingegnosità e strumenti specializzati per eliminare i tuoi bersagli in modo creativo e discreto.", new BigDecimal("59.99"), "Azione"),
                    createProduct("Pokemon Legends: Arceus", "Esplora il mondo di Sinnoh come mai prima in Pokemon Legends: Arceus. Questa avventura open-world ti porta in un passato mitico, dove potrai catturare, combattere e studiare Pokemon in un nuovo formato di gioco rivoluzionario.", new BigDecimal("59.99"), "Avventura"),
                    createProduct("Cyberpunk 2080", "Cyberpunk 2080 ti immerge in un futuro distopico dove tecnologia e caos si intrecciano. Vivi in una metropoli densa di pericoli e intrighi, affronta gang violente, corpi corporativi corrotti e svela complotti che potrebbero cambiare il destino della città.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Battlefield: Bad Company 3", "Rivivi le avventure del team di Bad Company in Battlefield: Bad Company 3. Con una trama ricca di azione e umorismo, affronta missioni audaci in ambientazioni variegate, utilizzando un arsenale di armi e veicoli in un gameplay esplosivo.", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Dragon Age 4", "Dragon Age 4 ti invita a esplorare un nuovo continente e affrontare minacce antiche in un epico gioco di ruolo. Forma alleanze, combatti mostri temibili e scopri segreti nascosti in un mondo ricco di magia e pericolo.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Metroid Prime 4", "Unisciti a Samus Aran in una nuova avventura spaziale con Metroid Prime 4. Esplora pianeti alieni, combatti nemici bio-meccanici e risolvi enigmi in ambienti ostili mentre scopri la verità dietro minacce misteriose.", new BigDecimal("59.99"), "Avventura"),
                    createProduct("Silent Hill: Requiem", "Silent Hill: Requiem ti porta a sopravvivere agli orrori di Silent Hill in un nuovo capitolo carico di suspense e terrore psicologico. Affronta enigmi intricati e nemici spaventosi mentre scopri la verità dietro la nebbia in questo survival horror iconico.", new BigDecimal("49.99"), "Horror"),
                    createProduct("Star Wars: Knights of the Old Republic III", "Star Wars: Knights of the Old Republic III ti offre una nuova storia epica nell'universo di Star Wars. Scegli di essere un Jedi o un Sith e influenzare il destino della galassia attraverso le tue scelte in questo gioco di ruolo ricco di azione e avventura.", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Watch Dogs: Infinite", "Watch Dogs: Infinite esplora una città futuristica usando le tue abilità di hacking per combattere il crimine. Infiltra sistemi sicuri, controlla dispositivi e manipola l'ambiente urbano per smascherare complotti e proteggere gli innocenti in questo gioco d'azione innovativo.", new BigDecimal("59.99"), "Azione"),
                    createProduct("Splinter Cell: Revival", "Splinter Cell: Revival marca il ritorno della serie di stealth-action. Con nuove missioni globali e una serie di gadget tecnologici, usa l'ombra a tuo vantaggio per infiltrarti e svelare cospirazioni in un mondo dove la tensione è sempre alta.", new BigDecimal("59.99"), "Azione"),
                    createProduct("Hogwarts Legacy", "Hogwarts Legacy ti permette di esplorare la scuola di magia di Hogwarts come mai prima. Studia incantesimi, scopri creature magiche e vivi avventure nel mondo di Harry Potter, dove ogni scelta può rivelare nuovi misteri o scatenare potenti magie.", new BigDecimal("59.99"), "Avventura"),
                    createProduct("Star Wars: Jedi Survivor", "Star Wars: Jedi Survivor continua l'epica avventura di un giovane Jedi mentre affronta nuove sfide e nemici. Con una trama avvincente e un gameplay arricchito, esplora angoli remoti della galassia e combatti per la sopravvivenza dell'Ordine Jedi.", new BigDecimal("69.99"), "Azione"),
                    createProduct("Need for Speed: Underground 3", "Need for Speed: Underground 3 ti porta a correre per le strade della città di notte in sfide adrenaliniche. Personalizza la tua auto con estetica e prestazioni, sfida altri corridori e domina la scena underground in questo ritorno della celebre serie di corse.", new BigDecimal("49.99"), "Corse"),
                    createProduct("Monster Hunter Rise", "Caccia mostri giganteschi in un mondo ispirato alla tradizione giapponese con Monster Hunter Rise. Equipaggia un vasto arsenale di armi, unisciti a cacciatori in tutto il mondo e affronta creature spettacolari in battaglie memorabili.", new BigDecimal("59.99"), "Azione"),
                    createProduct("Ghost of Tsushima 2", "Ghost of Tsushima 2 continua l'epica storia di Jin Sakai mentre difende la sua terra dai nemici. Scopri paesaggi mozzafiato del Giappone feudale, affina le tue abilità di samurai e affronta sfide morali e fisiche in questo action-adventure affascinante.", new BigDecimal("69.99"), "Azione"),
                    createProduct("BioShock 4", "BioShock 4 esplora una nuova città sottomarina con una trama oscura e complessa. Scopri segreti sepolti, affronta nemici mutati e risolvi enigmi ambientali in questo ritorno della serie di giochi con una profonda narrazione immersiva e momenti di gameplay intensi.", new BigDecimal("59.99"), "Avventura"),
                    createProduct("FIFA Street 5", "FIFA Street 5 rivitalizza il gioco del calcio di strada con mosse spettacolari e ambientazioni urbane vivaci. Sfida avversari in piccoli campi in tutto il mondo, mostra le tue abilità e diventa una leggenda del freestyle in questo ritorno energico della serie.", new BigDecimal("49.99"), "Sport")

            );


            productDAO.saveAll(products);
        }
    }

    private Product createProduct(String name, String description, BigDecimal price, String categoryName) {
        Category category = findOrCreateCategory(categoryName);
        Set<Platform> platforms = findOrCreatePlatforms(Arrays.asList("PC", "PlayStation 5"));
        Set<Review> reviews = createReviews();  // Crea alcune recensioni di esempio

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setPlatforms(platforms);
        product.setReviews(reviews);

        return product;
    }

    private Category findOrCreateCategory(String categoryName) {
        return categoryDAO.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    return categoryDAO.save(newCategory);
                });
    }

    private Set<Platform> findOrCreatePlatforms(List<String> platformNames) {
        Set<Platform> platforms = new HashSet<>();
        for (String name : platformNames) {
            Platform platform = platformDAO.findByName(name)
                    .orElseGet(() -> {
                        Platform newPlatform = new Platform();
                        newPlatform.setName(name);
                        return platformDAO.save(newPlatform);
                    });
            platforms.add(platform);
        }
        return platforms;
    }

    private Set<Review> createReviews() {
        Set<Review> reviews = new HashSet<>();

        Review review = new Review();
        review.setContent("Great game!");

        reviews.add(review);
        return reviews;
    }
}
