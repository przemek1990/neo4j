package neo4j.research.nodes;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Article {

	@GraphId
	Long id;
	public String title;
	public int articleId;

	public Article() {
	}

	public Article(String title, int articleId) {
		this.title = title;
		this.articleId = articleId;
	}

	@RelatedTo(type = "REALATED", direction = Direction.BOTH)
	@Fetch
	public Set<Article> relatedArticles;


	public void related(Article article) {
		if (relatedArticles == null) {
			relatedArticles = new HashSet<>();
		}
		relatedArticles.add(article);
	}
}
