package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.PurchasedArticles;
import hr.fer.infsus.staem.service.MailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Override
    public void sendMail(List<PurchasedArticles> purchasedArticles) {
        final SimpleMailMessage message = new SimpleMailMessage();
        try {
            final Jwt token = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            message.setTo(token.getClaimAsString("email"));
            message.setSubject("You have bought new articles!");
            message.setText(prepareText(purchasedArticles));
            message.setFrom("staem-store");

            javaMailSender.send(message);
        } catch (Exception e) {
            logger.error("Error while sending email", e);
        }

    }

    private String prepareText(List<PurchasedArticles> articles) {
        StringBuilder sb = new StringBuilder();
        sb.append("You have bought following articles: \n");
        for (PurchasedArticles article : articles) {
            sb.append(article.getArticle().getTitle()).append(", price: ").append(article.getArticle().getPrice())
                .append(article.getArticle().getCurrency()).append("\n");
        }

        return sb.toString();
    }

}
