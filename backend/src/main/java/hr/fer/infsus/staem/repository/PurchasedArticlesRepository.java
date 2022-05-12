package hr.fer.infsus.staem.repository;

import hr.fer.infsus.staem.entity.PurchasedArticles;
import hr.fer.infsus.staem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedArticlesRepository extends JpaRepository<PurchasedArticles, Users> {

    boolean existsByUser_IdAndArticle_IdIn(String userId, List<Long> articleIds);

    List<PurchasedArticles> findAllByUser_IdOrderByDateOfPurchaseAsc(String userId);

}