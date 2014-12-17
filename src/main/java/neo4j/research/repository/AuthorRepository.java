package neo4j.research.repository;

import org.springframework.data.repository.CrudRepository;

import neo4j.research.model.Author;

public interface AuthorRepository extends CrudRepository<Author, String> {

    Author findByName(String name);

}
