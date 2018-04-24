package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.*;


@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);
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
        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private URI getBoardsUri() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloApiKey)
                .queryParam("token", trelloApiToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();


    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = getCardUri(trelloCardDto);
        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }

    private URI getCardUri(TrelloCardDto trelloCardDto) {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloApiKey)
                .queryParam("token", trelloApiToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();
    }
}
