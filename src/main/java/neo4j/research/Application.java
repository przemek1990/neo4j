package neo4j.research;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.core.GraphDatabase;

import neo4j.research.model.Article;
import neo4j.research.model.Author;
import neo4j.research.repository.ArticleRepository;
import neo4j.research.repository.AuthorRepository;

@Configuration
@EnableNeo4jRepositories(basePackages = "neo4j.research")
public class Application extends Neo4jConfiguration implements CommandLineRunner {


	private static final String DB_PATH = "C:\\Users\\przemyslaw.pioro\\Documents\\Neo4j\\default.graphdb";

	public Application() {
		setBasePackage("neo4j.research");
	}

	@Bean
	GraphDatabaseService graphDatabaseService() {
		return new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
	}

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	GraphDatabase graphDatabase;

	public void run(String... args) throws Exception {
		Author ronneberg = new Author("Kristoffer ", "Rønneberg");
		Author sarah = new Author("Sarah", "Trine");

		List<Author> authors = Arrays.asList(ronneberg, sarah);

		System.out.println("Before linking up with Neo4j...");
		authors.forEach(System.out::println);

		List<Article> ronnebergArticles = Arrays.asList(new Article("title1", 9829608), new Article("title2", 6829608), new Article("title3", 5829608));
		List<Article> sarahArticles = Arrays.asList(new Article("sarahTitle", 1312), new Article("sarahTitle2", 242342), new Article("sarahTitle4", 58569608));

		try (Transaction tx = graphDatabase.beginTx()) {
			authorRepository.save(ronneberg);
			authorRepository.save(sarah);

			ronneberg = addArticlesToAuthor(ronneberg, ronnebergArticles);
			sarah = addArticlesToAuthor(sarah, sarahArticles);

			Article related1 = ronnebergArticles.get(0);
			Article related2 = ronnebergArticles.get(1);

			Article sarahRelated = sarahArticles.get(0);
			sarahRelated.related(related1);
			related1.related(related2);
			articleRepository.save(Arrays.asList(related1, sarahRelated));

			System.out.println("Lookup each person by name...");
			Arrays.asList(ronneberg.name, sarah.name).forEach((name) -> System.out.println(authorRepository.findByName(name)));

			tx.success();
		}

	}

	private Author addArticlesToAuthor(Author author, List<Article> articles) {
		author = authorRepository.findByName(author.name);
		for (Article sarahArticle : articles) {
			author.owner(sarahArticle);
		}
		authorRepository.save(author);
		return author;
	}

	public static void main(String[] args) throws Exception {
		FileUtils.deleteRecursively(new File(DB_PATH));
		SpringApplication.run(Application.class, args);
	}

}
