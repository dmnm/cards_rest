package org.example.cards.abc.cards;

import java.util.List;

public interface CardsFacade {
    List<CardDto> getUserCards(Long userId);

    CardDto blockCard(Long cardId, Long userId);

    CardDto unblockCard(Long cardId, Long userId);
}
