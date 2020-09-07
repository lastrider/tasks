package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.api.username}")
    private String username;

    @Autowired
    private RestTemplate restTemplate;

    private URI url () {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "members/" + username + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();
        return url;
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        TrelloBoardDto[] boardResponse = restTemplate.getForObject(url(),TrelloBoardDto[].class);
        Optional<List<TrelloBoardDto>> optionalTrelloBoardsList = Optional.of(Arrays.asList(boardResponse));
/*        if (boardResponse != null) {
            return Arrays.asList(boardResponse);
        } else {
            return new ArrayList<>();
        }*/
        return optionalTrelloBoardsList.orElse(new ArrayList<>());
    }
}
