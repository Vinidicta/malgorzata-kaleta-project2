package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;


@Component
public class TrelloClient {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloApiKey;

    @Value("${trello.app.token}")
    private String trelloApiToken;

    @Value("${trello.app.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = getBoardsUri();

        return Arrays.asList(restTemplate.getForObject(url, TrelloBoardDto[].class));
    }

    private URI getBoardsUri() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/magorzatakaleta/boards")
                .queryParam("key", trelloApiKey)
                .queryParam("token", trelloApiToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();


    }
}
