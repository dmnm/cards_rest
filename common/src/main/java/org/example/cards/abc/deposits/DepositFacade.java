package org.example.cards.abc.deposits;

import java.util.List;

public interface DepositFacade {
    List<DepositDto> getDeposits(Long userId);
}
