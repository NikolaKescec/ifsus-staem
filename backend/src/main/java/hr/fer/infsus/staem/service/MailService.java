package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.PurchasedArticles;

import java.util.List;

public interface MailService {

    public void sendMail(String email, List<PurchasedArticles> purchasedArticles);

}
