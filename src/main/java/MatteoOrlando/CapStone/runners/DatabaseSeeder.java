/*package MatteoOrlando.CapStone.runners;

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
      if (productDAO.count() == 0) {  // Verifico se il database è già popolato
          List<Product> products = Arrays.asList(
                  createProduct("FIFA 24", "L'ultimo capitolo della popolare serie di simulazione calcistica", new BigDecimal("59.99"), Set.of(2L).toString(), List.of("Il miglior gioco di calcio di sempre.", "Realismo eccezionale.")),
                  createProduct("Call of Duty: Future Warfare", "Uno sparatutto in prima persona ambientato in un futuro distopico", new BigDecimal("69.99"), Set.of(3L).toString(), List.of("Azione frenetica e adrenalinica.", "Grafica mozzafiato.")),
                  createProduct("Animal Crossing: Island Life", "Continua la tua avventura con nuove attività e isole da esplorare", new BigDecimal("59.99"), Set.of(4L).toString(), List.of("Un'esperienza rilassante e coinvolgente.", "Ideale per tutte le età.")),
                  createProduct("Minecraft 2", "La nuova generazione del celebre sandbox game con grafica migliorata e nuove meccaniche", new BigDecimal("29.99"), Set.of(5L).toString(), List.of("Creatività senza limiti.", "Un classico rivisitato.")),
                  createProduct("Cyberpunk 2078", "Esplora nuove città in un futuro ancora più immersivo e dettagliato", new BigDecimal("59.99"), Set.of(1L, 3L).toString(), List.of("Un mondo futuristico e affascinante.", "Storia profonda e coinvolgente.")),
                  createProduct("Assassin's Creed Ragnarok", "Viaggia nel mondo dei vichinghi e esplora miti nordici in un'avventura epica", new BigDecimal("69.99"), Set.of(1L).toString(), List.of("Un'epica avventura nei miti nordici.", "Combattimenti avvincenti e grafica eccellente.")),
                  createProduct("Forza Horizon 6", "Corri in nuove location esotiche con la fisica e la grafica più avanzate mai viste", new BigDecimal("69.99"), Set.of(6L).toString(), List.of("Il miglior gioco di corse sul mercato.", "Grafica e fisica incredibili.")),
                  createProduct("The Legend of Zelda: Echoes of the Past", "Una nuova leggenda attende in un Hyrule reinventato e più vasto", new BigDecimal("59.99"), Set.of(5L).toString(), List.of("Un altro capolavoro di Zelda.", "Esplorazione e avventura senza precedenti.")),
                  createProduct("Dark Souls IV", "Il ritorno della celebre serie con sfide ancora più difficili e inquietanti", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Una sfida per i giocatori più esperti.", "Atmosfera oscura e avvincente.")),
                  createProduct("Elder Scrolls VI: Argonia", "Esplora le paludi misteriose di Argonia in questo nuovo capitolo dell'epica serie RPG", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un mondo vasto e dettagliato.", "Una storia epica e coinvolgente.")),
                  createProduct("Horizon Forbidden West", "Unisciti a Aloy in un'avventura verso territori proibiti e meraviglie sconosciute", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un'avventura mozzafiato.", "Grafica e storia incredibili.")),
                  createProduct("God of War: Ragnarok", "La saga di Kratos e Atreus continua nell'imminente battaglia del Ragnarok", new BigDecimal("69.99"), Set.of(1L).toString(), List.of("Un capolavoro epico.", "Combattimenti avvincenti e storia emozionante.")),
                  createProduct("Grand Theft Auto VI", "Il nuovo capitolo della serie di giochi d'azione-avventura in un mondo aperto ambientato a Vice City", new BigDecimal("69.99"), Set.of(1L).toString(), List.of("Un mondo aperto ricco di dettagli.", "Storia e gameplay eccellenti.")),
                  createProduct("Final Fantasy XVI", "Un nuovo capitolo della storica serie di giochi di ruolo, ambientato in un mondo di magia e tecnologia", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Una storia emozionante e avvincente.", "Grafica straordinaria e gameplay coinvolgente.")),
                  createProduct("Resident Evil: Rebirth", "Sopravvivi in un nuovo capitolo della serie di survival horror con un'atmosfera ancora più tetra e pericolosa", new BigDecimal("59.99"), Set.of(7L).toString(), List.of("Atmosfera tesa e coinvolgente.", "Un ritorno alle radici del survival horror.")),
                  createProduct("Splatoon 3", "Unisciti alla battaglia con nuovi livelli, armi e la sempre divertente azione multiplayer", new BigDecimal("59.99"), Set.of(3L).toString(), List.of("Divertente e colorato.", "Ottimo per il multiplayer.")),
                  createProduct("Marvel's Spider-Man 2", "Swing through New York City with enhanced mechanics and new storylines", new BigDecimal("69.99"), Set.of(1L).toString(), List.of("Miglior gioco di Spider-Man di sempre.", "Storia e meccaniche di gioco eccellenti.")),
                  createProduct("Battlefield 2042", "Combatti in scenari futuristici con nuove modalità di gioco e tecnologia all'avanguardia", new BigDecimal("59.99"), Set.of(3L).toString(), List.of("Combattimenti intensi e coinvolgenti.", "Grafica e tecnologia di alto livello.")),
                  createProduct("Mass Effect: Next Generation", "L'esplorazione dello spazio e le interazioni interstellari raggiungono nuovi livelli in questa nuova saga", new BigDecimal("69.99"), Set.of(1L).toString(), List.of("Un'epica avventura spaziale.", "Storia e personaggi indimenticabili.")),
                  createProduct("Starfield", "L'esplorazione spaziale e la sopravvivenza in un universo nuovo e vasto", new BigDecimal("69.99"), Set.of(5L).toString(), List.of("Un nuovo standard per i giochi di esplorazione spaziale.", "Vasto e dettagliato.")),
                  createProduct("Halo Infinite", "Continua la leggendaria saga di Master Chief con grafica mozzafiato e nuove missioni", new BigDecimal("59.99"), Set.of(3L).toString(), List.of("La saga di Halo continua alla grande.", "Grafica e gameplay eccezionali.")),
                  createProduct("Overwatch 2", "L'evoluzione del popolare sparatutto multiplayer con nuovi eroi e mappe", new BigDecimal("39.99"), Set.of(3L).toString(), List.of("Divertente e competitivo.", "Nuovi eroi e mappe aggiungono freschezza al gioco.")),
                  createProduct("Diablo IV", "Il ritorno del classico RPG d'azione con un mondo più oscuro e profondo", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un'epica avventura oscura.", "Gameplay coinvolgente e storia avvincente.")),
                  createProduct("Red Dead Redemption 3", "Vivi l'avventura di un fuorilegge nel selvaggio West con una storia avvincente", new BigDecimal("69.99"), Set.of(5L).toString(), List.of("Un'epica avventura nel selvaggio West.", "Storia e grafica eccellenti.")),
                  createProduct("The Sims 5", "Costruisci e gestisci la vita dei tuoi Sims in una simulazione di vita ancora più dettagliata", new BigDecimal("49.99"), Set.of(4L).toString(), List.of("Un simulatore di vita ancora più dettagliato.", "Molte nuove opzioni di personalizzazione.")),
                  createProduct("Bloodborne 2", "Esplora un mondo oscuro e gotico in questo action RPG ricco di sfide", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un'avventura oscura e avvincente.", "Sfide impegnative e combattimenti emozionanti.")),
                  createProduct("NBA 2K24", "Gioca a basket con i tuoi campioni preferiti e vivi l'esperienza del campionato NBA", new BigDecimal("59.99"), Set.of(2L).toString(), List.of("Il miglior simulatore di basket.", "Grafica e gameplay realistici.")),
                  createProduct("Far Cry 7", "Esplora nuove terre esotiche e affronta nemici pericolosi in questo sparatutto open world", new BigDecimal("69.99"), Set.of(1L).toString(), List.of("Un'epica avventura in terre esotiche.", "Gameplay coinvolgente e storia avvincente.")),
                  createProduct("Elden Ring", "Scopri un mondo aperto creato da Hidetaka Miyazaki e George R.R. Martin", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un mondo aperto straordinario.", "Storia e gameplay eccellenti.")),
                  createProduct("Mortal Kombat 12", "Partecipa a combattimenti mozzafiato con personaggi iconici e mosse spettacolari", new BigDecimal("49.99"), Set.of(8L).toString(), List.of("Combattimenti spettacolari e violenti.", "Personaggi iconici e mosse straordinarie.")),
                  createProduct("Super Mario Odyssey 2", "Unisciti a Mario in una nuova avventura attraverso mondi fantastici", new BigDecimal("59.99"), Set.of(9L).toString(), List.of("Un'altra grande avventura di Mario.", "Mondi fantastici e gameplay coinvolgente.")),
                  createProduct("The Last of Us Part III", "Continua l'epica saga di sopravvivenza in un mondo post-apocalittico", new BigDecimal("69.99"), Set.of(1L).toString(), List.of("Un'epica storia di sopravvivenza.", "Grafica e narrazione eccezionali.")),
                  createProduct("Gears of War 6", "Combatti contro l'orda locusta in un nuovo capitolo della famosa serie di sparatutto", new BigDecimal("59.99"), Set.of(3L).toString(), List.of("Azione intensa e coinvolgente.", "Una grande aggiunta alla serie Gears of War.")),
                  createProduct("Fable IV", "Vivi un'avventura magica in un mondo fantasy dove le tue scelte influenzano la storia", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un mondo fantasy magico e affascinante.", "Le tue scelte influenzano la storia.")),
                  createProduct("Hitman 3", "Assumi il ruolo dell'Agente 47 e completa missioni di assassinio in location esotiche", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un grande ritorno per l'Agente 47.", "Missioni avvincenti e ambientazioni esotiche.")),
                  createProduct("Pokemon Legends: Arceus", "Esplora il mondo di Sinnoh in un'avventura open-world ambientata nel passato", new BigDecimal("59.99"), Set.of(5L).toString(), List.of("Un'avventura Pokemon senza precedenti.", "Esplorazione open-world e gameplay innovativo.")),
                  createProduct("Cyberpunk 2080", "Entra in un futuro distopico ancora più dettagliato e pieno di pericoli", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un futuro distopico ancora più affascinante.", "Storia e ambientazioni eccezionali.")),
                  createProduct("Battlefield: Bad Company 3", "Rivivi le avventure del team di Bad Company in questo sparatutto coinvolgente", new BigDecimal("59.99"), Set.of(3L).toString(), List.of("Un grande ritorno per Bad Company.", "Azione intensa e storia avvincente.")),
                  createProduct("Dragon Age 4", "Esplora un nuovo continente e affronta antiche minacce in questo gioco di ruolo epico", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un'epica avventura in un nuovo continente.", "Storia e personaggi coinvolgenti.")),
                  createProduct("Metroid Prime 4", "Unisciti a Samus Aran in una nuova avventura nello spazio", new BigDecimal("59.99"), Set.of(5L).toString(), List.of("Un'altra grande avventura spaziale con Samus.", "Gameplay avvincente e grafica straordinaria.")),
                  createProduct("Silent Hill: Requiem", "Sopravvivi agli orrori di Silent Hill in un nuovo capitolo della serie di survival horror", new BigDecimal("49.99"), Set.of(7L).toString(), List.of("Un ritorno agghiacciante a Silent Hill.", "Atmosfera e tensione incredibili.")),
                  createProduct("Star Wars: Knights of the Old Republic III", "Vivi una nuova storia nell'universo di Star Wars in questo RPG epico", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un'epica avventura nell'universo di Star Wars.", "Storia e gameplay eccezionali.")),
                  createProduct("Watch Dogs: Infinite", "Esplora una città futuristica e usa le tue abilità di hacking per combattere il crimine", new BigDecimal("59.99"), Set.of(1L).toString(), List.of("Un mondo futuristico e coinvolgente.", "Hacking e gameplay avvincenti."))
          );


          productDAO.saveAll(products);
      }
  }

  private Product createProduct(String name, String description, BigDecimal price, String categoryName, List<String> features) {
      Category category = findOrCreateCategory(categoryName);
      Set<Platform> platforms = findOrCreatePlatforms(List.of());
      Set<Review> reviews = createReviews();

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
}*/
