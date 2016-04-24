package org.example.cards.api;

import static org.example.cards.api.Utils.asResponse;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.example.cards.abc.deposits.DepositDto;
import org.example.cards.abc.deposits.DepositFacade;
import org.example.cards.annotation.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deposits")
public class DepositsResource {

    @Autowired
    private DepositFacade depositFacade;

    @RequestMapping(method = GET)
    public ResponseEntity<List<DepositDto>> getAll(@UserId Long userId) {
        return asResponse(depositFacade.getDeposits(userId));
    }
}
