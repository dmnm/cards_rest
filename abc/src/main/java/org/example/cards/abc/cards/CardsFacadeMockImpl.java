package org.example.cards.abc.cards;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CardsFacadeMockImpl implements CardsFacade {
    private Map<Long, CardDto> repo = new LinkedHashMap<>();

    @Override
    public List<CardDto> getUserCards(Long userId) {
        if (userId != null && userId.equals(42L)) {
            return new ArrayList<>(repo.values());
        }
        return null;
    }

    @Override
    public CardDto blockCard(Long cardId, Long userId) {
        return doBlockUnblock(cardId, true);
    }

    @Override
    public CardDto unblockCard(Long cardId, Long userId) {
        return doBlockUnblock(cardId, false);
    }

    private CardDto doBlockUnblock(Long cardId, boolean value) {
        CardDto c = repo.get(cardId);
        if (c != null) {
            c.blocked = value;
        }
        return c;
    }

    private final Random random = new Random();

    {
        for (int i = 0; i < 3; i++) {
            CardDto c = createCard();

            // for test purposes only
            if (i == 0) {
                c.id = 42L;
            }
            //

            repo.put(c.id, c);
        }
    }

    private CardDto createCard() {
        CardDto c = new CardDto();
        c.id = (long) random.nextInt(Integer.MAX_VALUE);
        c.bankAccountId = (long) random.nextInt(Integer.MAX_VALUE);
        c.number = UUID.randomUUID().toString();
        c.balance = BigDecimal.valueOf((long) random.nextInt(Integer.MAX_VALUE), 2);
        return c;
    }

}
