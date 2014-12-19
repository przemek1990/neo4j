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
	private String title;
	private int articleId;

	public Article() {
	}

	public Article(String title, int articleId) {
		this.title = title;
		this.articleId = articleId;
	}

	@RelatedTo(type = "REALATED", direction = Direction.BOTH)
	@Fetch
	private Set<Article> relatedArticles;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public Set<Article> getRelatedArticles() {
		return relatedArticles;
	}

	public void setRelatedArticles(Set<Article> relatedArticles) {
		this.relatedArticles = relatedArticles;
	}

	public void related(Article article) {
		if (relatedArticles == null) {
			relatedArticles = new HashSet<>();
		}
		relatedArticles.add(article);
	}
}
