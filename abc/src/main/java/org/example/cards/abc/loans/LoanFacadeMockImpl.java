package org.example.cards.abc.loans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class LoanFacadeMockImpl implements LoanFacade {
    private List<LoanDto> list = new ArrayList<>();

    @Override
    public List<LoanDto> getLoans(Long userId) {
        if (userId !=null && userId.equals(42L)) {
            return list;
        }
        return null;
    }

    private Random random = new Random();

    {
        for (int i = 0; i < 3; i++) {
            list.add(createLoan());
        }
    }

    private LoanDto createLoan() {
        LoanDto l = new LoanDto();
        l.id = (long) random.nextInt(Integer.MAX_VALUE);
        l.name = String.valueOf(l.id);
        l.terms = Arrays.asList(new Date(random.nextLong()), new Date(random.nextLong()));
        return l;
    }

}
