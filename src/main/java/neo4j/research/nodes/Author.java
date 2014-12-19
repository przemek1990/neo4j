package neo4j.research.nodes;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Author {

	@GraphId
	Long id;
	private String name;
	private String surname;

	public Author() {}

	public Author(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	@RelatedTo(type = "OWNER", direction = Direction.OUTGOING)
	@Fetch
	public Set<Article> articles;

	public void owner(Article article) {
		if (articles == null) {
			articles = new HashSet<>();
		}
		articles.add(article);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String toString() {
		String results = name + "'s articles include\n";
		if (articles != null) {
			for (Article article : articles) {
				results += "\t- " + article.getTitle() + "\n";
			}
		}
		return results;
	}

}
