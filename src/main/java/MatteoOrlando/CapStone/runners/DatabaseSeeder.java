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
        if (productDAO.count() == 100) {  // Verifica se il database è già popolato
            List<Product> products = Arrays.asList(
                    createProduct("The Witcher 3: Wild Hunt", "Un gioco di ruolo open-world ambientato in un universo fantasy", new BigDecimal("39.99"), "Giochi di ruolo"),
                    createProduct("FIFA 24", "L'ultimo capitolo della popolare serie di simulazione calcistica", new BigDecimal("59.99"), "Sport"),
                    createProduct("Call of Duty: Future Warfare", "Uno sparatutto in prima persona ambientato in un futuro distopico", new BigDecimal("69.99"), "Sparatutto"),
                    createProduct("Animal Crossing: Island Life", "Continua la tua avventura con nuove attività e isole da esplorare", new BigDecimal("59.99"), "Simulazione"),
                    createProduct("Minecraft 2", "La nuova generazione del celebre sandbox game con grafica migliorata e nuove meccaniche", new BigDecimal("29.99"), "Avventura"),
                    createProduct("Cyberpunk 2078", "Esplora nuove città in un futuro ancora più immersivo e dettagliato", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Assassin's Creed Ragnarok", "Viaggia nel mondo dei vichinghi e esplora miti nordici in un'avventura epica", new BigDecimal("69.99"), "Azione"),
                    createProduct("Forza Horizon 6", "Corri in nuove location esotiche con la fisica e la grafica più avanzate mai viste", new BigDecimal("69.99"), "Corse"),
                    createProduct("The Legend of Zelda: Echoes of the Past", "Una nuova leggenda attende in un Hyrule reinventato e più vasto", new BigDecimal("59.99"), "Avventura"),
                    createProduct("Dark Souls IV", "Il ritorno della celebre serie con sfide ancora più difficili e inquietanti", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Elder Scrolls VI: Argonia", "Esplora le paludi misteriose di Argonia in questo nuovo capitolo dell'epica serie RPG", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Horizon Forbidden West", "Unisciti a Aloy in un'avventura verso territori proibiti e meraviglie sconosciute", new BigDecimal("59.99"), "Azione"),
                    createProduct("God of War: Ragnarok", "La saga di Kratos e Atreus continua nell'imminente battaglia del Ragnarok", new BigDecimal("69.99"), "Azione"),
                    createProduct("Grand Theft Auto VI", "Il nuovo capitolo della serie di giochi d'azione-avventura in un mondo aperto ambientato a Vice City", new BigDecimal("69.99"), "Azione"),
                    createProduct("Final Fantasy XVI", "Un nuovo capitolo della storica serie di giochi di ruolo, ambientato in un mondo di magia e tecnologia", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Resident Evil: Rebirth", "Sopravvivi in un nuovo capitolo della serie di survival horror con un'atmosfera ancora più tetra e pericolosa", new BigDecimal("59.99"), "Horror"),
                    createProduct("Splatoon 3", "Unisciti alla battaglia con nuovi livelli, armi e la sempre divertente azione multiplayer", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Marvel's Spider-Man 2", "Swing through New York City with enhanced mechanics and new storylines", new BigDecimal("69.99"), "Azione"),
                    createProduct("Battlefield 2042", "Combatti in scenari futuristici con nuove modalità di gioco e tecnologia all'avanguardia", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Mass Effect: Next Generation", "L'esplorazione dello spazio e le interazioni interstellari raggiungono nuovi livelli in questa nuova saga", new BigDecimal("69.99"), "Giochi di ruolo"),
                    createProduct("Starfield", "L'esplorazione spaziale e la sopravvivenza in un universo nuovo e vasto", new BigDecimal("69.99"), "Avventura"),
                    createProduct("Halo Infinite", "Continua la leggendaria saga di Master Chief con grafica mozzafiato e nuove missioni", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Overwatch 2", "L'evoluzione del popolare sparatutto multiplayer con nuovi eroi e mappe", new BigDecimal("39.99"), "Sparatutto"),
                    createProduct("Diablo IV", "Il ritorno del classico RPG d'azione con un mondo più oscuro e profondo", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("The Elder Scrolls V: Skyrim", "Un nuovo capitolo della serie di RPG in un mondo aperto e nuove attività", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Red Dead Redemption 3", "Vivi l'avventura di un fuorilegge nel selvaggio West con una storia avvincente", new BigDecimal("69.99"), "Avventura"),
                    createProduct("The Sims 5", "Costruisci e gestisci la vita dei tuoi Sims in una simulazione di vita ancora più dettagliata", new BigDecimal("49.99"), "Simulazione"),
                    createProduct("Bloodborne 2", "Esplora un mondo oscuro e gotico in questo action RPG ricco di sfide", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("NBA 2K24", "Gioca a basket con i tuoi campioni preferiti e vivi l'esperienza del campionato NBA", new BigDecimal("59.99"), "Sport"),
                    createProduct("Far Cry 7", "Esplora nuove terre esotiche e affronta nemici pericolosi in questo sparatutto open world", new BigDecimal("69.99"), "Azione"),
                    createProduct("Elden Ring", "Scopri un mondo aperto creato da Hidetaka Miyazaki e George R.R. Martin", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Mortal Kombat 12", "Partecipa a combattimenti mozzafiato con personaggi iconici e mosse spettacolari", new BigDecimal("49.99"), "Combattimento"),
                    createProduct("Super Mario Odyssey 2", "Unisciti a Mario in una nuova avventura attraverso mondi fantastici", new BigDecimal("59.99"), "Piattaforme"),
                    createProduct("The Last of Us Part III", "Continua l'epica saga di sopravvivenza in un mondo post-apocalittico", new BigDecimal("69.99"), "Azione"),
                    createProduct("Gears of War 6", "Combatti contro l'orda locusta in un nuovo capitolo della famosa serie di sparatutto", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Fable IV", "Vivi un'avventura magica in un mondo fantasy dove le tue scelte influenzano la storia", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Hitman 3", "Assumi il ruolo dell'Agente 47 e completa missioni di assassinio in location esotiche", new BigDecimal("59.99"), "Azione"),
                    createProduct("Pokemon Legends: Arceus", "Esplora il mondo di Sinnoh in un'avventura open-world ambientata nel passato", new BigDecimal("59.99"), "Avventura"),
                    createProduct("Cyberpunk 2080", "Entra in un futuro distopico ancora più dettagliato e pieno di pericoli", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Battlefield: Bad Company 3", "Rivivi le avventure del team di Bad Company in questo sparatutto coinvolgente", new BigDecimal("59.99"), "Sparatutto"),
                    createProduct("Dragon Age 4", "Esplora un nuovo continente e affronta antiche minacce in questo gioco di ruolo epico", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Metroid Prime 4", "Unisciti a Samus Aran in una nuova avventura nello spazio", new BigDecimal("59.99"), "Avventura"),
                    createProduct("Silent Hill: Requiem", "Sopravvivi agli orrori di Silent Hill in un nuovo capitolo della serie di survival horror", new BigDecimal("49.99"), "Horror"),
                    createProduct("Star Wars: Knights of the Old Republic III", "Vivi una nuova storia nell'universo di Star Wars in questo RPG epico", new BigDecimal("59.99"), "Giochi di ruolo"),
                    createProduct("Watch Dogs: Infinite", "Esplora una città futuristica e usa le tue abilità di hacking per combattere il crimine", new BigDecimal("59.99"), "Azione"),
                    createProduct("Splinter Cell: Revival", "Un nuovo capitolo della serie di stealth-action con nuove missioni e gadget", new BigDecimal("59.99"), "Azione"),
                    createProduct("Hogwarts Legacy", "Esplora la scuola di magia di Hogwarts in un'avventura open-world", new BigDecimal("59.99"), "Avventura"),
                    createProduct("Star Wars: Jedi Survivor", "Continua l'epica avventura del giovane Jedi mentre affronta nuove sfide", new BigDecimal("69.99"), "Azione"),
                    createProduct("Need for Speed: Underground 3", "Corri per le strade della città di notte in questo gioco di corse adrenalinico", new BigDecimal("49.99"), "Corse"),
                    createProduct("Monster Hunter Rise", "Caccia mostri enormi in un mondo ispirato alla tradizione giapponese", new BigDecimal("59.99"), "Azione"),
                    createProduct("Ghost of Tsushima 2", "Continua l'epica storia di Jin Sakai mentre difende la sua terra dai nemici", new BigDecimal("69.99"), "Azione"),
                    createProduct("BioShock 4", "Scopri una nuova città sottomarina con una storia oscura e complessa", new BigDecimal("59.99"), "Avventura"),
                    createProduct("FIFA Street 5", "Gioca a calcio di strada con mosse spettacolari e ambientazioni urbane", new BigDecimal("49.99"), "Sport")
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
        // Aggiungi recensioni di esempio o implementa una logica simile a quella delle piattaforme per la creazione dinamica
        Review review = new Review();
        review.setContent("Great game!");
        // Imposta altri attributi necessari e salva nel DAO se richiesto
        reviews.add(review);
        return reviews;
    }
}
