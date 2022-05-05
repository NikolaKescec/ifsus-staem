package hr.fer.infsus.staem.repository;

import hr.fer.infsus.staem.entity.PurchasedArticles;
import hr.fer.infsus.staem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedArticlesRepository extends JpaRepository<PurchasedArticles, Users> {

}