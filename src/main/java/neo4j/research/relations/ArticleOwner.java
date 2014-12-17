package neo4j.research.relations;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import neo4j.research.nodes.Article;
import neo4j.research.nodes.Author;

@RelationshipEntity(type = "ARTICLE_OWNER")
public class ArticleOwner {

	@GraphId
	private Long nodeId;

	Date date;
	@StartNode
	private Author author;
	@EndNode
	private Article article;


	public ArticleOwner() {
	}

	public ArticleOwner(Date date, Author author, Article article) {
		this.date = date;
		this.author = author;
		this.article = article;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}
