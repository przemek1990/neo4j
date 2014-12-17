package neo4j.research.repository;

import org.springframework.data.repository.CrudRepository;

import neo4j.research.nodes.Article;


public interface ArticleRepository extends CrudRepository<Article, String> {
}
