package de.smotastic.ticketprinter.features.printer.data.datasources;

import de.smotastic.ticketprinter.features.printer.domain.TicketEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class FindTicketDsTrello implements FindTicketDs {

    private final Environment env;

    private static final String trelloUri = "https://api.trello.com/1/cards/{id}?key={apikey}&token={token}";

    @Override
    public TicketEntity by(String id) {
        String apikey = env.getProperty("trello.apikey", "");
        String token = env.getProperty("trello.token", "");

        RestTemplate restTemplate = new RestTemplate();
        String url = trelloUri
                .replace("{id}", id)
                .replace("{apikey}", apikey)
                .replace("{token}", token);
        TrelloModel result = restTemplate.getForObject(url, TrelloModel.class);

        return TicketEntity.builder()
                .id(id)
                .title(result.name())
                .description(result.desc())
                .assignee("")
                .build();
    }
}
