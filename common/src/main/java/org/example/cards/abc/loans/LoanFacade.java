package org.example.cards.abc.loans;

import java.util.List;

public interface LoanFacade {
    List<LoanDto> getLoans(Long userId);
}
