package org.example.cards.api;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.example.cards.api.Utils.*;

import java.util.List;

import org.example.cards.abc.cards.CardDto;
import org.example.cards.abc.cards.CardsFacade;
import org.example.cards.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
public class CardsResource {

    @Autowired
    private CardsFacade cardsFacade;

    @RequestMapping(method = GET)
    public ResponseEntity<List<CardDto>> getAll(@UserId Long userId) {
        return asResponse(cardsFacade.getUserCards(userId));
    }

    @RequestMapping(path = "{cardId}/block", method = PUT)
    public ResponseEntity<CardDto> block(@PathVariable Long cardId, @UserId Long userId) {
        return asResponse(cardsFacade.blockCard(cardId, userId));
    }

    @RequestMapping(path = "{cardId}/unblock", method = PUT)
    public ResponseEntity<CardDto> unblock(@PathVariable Long cardId, @UserId Long userId) {
        return asResponse(cardsFacade.unblockCard(cardId, userId));
    }
}
