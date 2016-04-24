package org.example.cards.api;

import static org.example.cards.api.Utils.asResponse;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.example.cards.abc.loans.LoanDto;
import org.example.cards.abc.loans.LoanFacade;
import org.example.cards.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loans")
public class LoansResource {

    @Autowired
    private LoanFacade loanFacade;

    @RequestMapping(method = GET)
    public ResponseEntity<List<LoanDto>> getAll(@UserId Long userId) {
        return asResponse(loanFacade.getLoans(userId));
    }
}
