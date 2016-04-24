package org.example.cards.abc.deposits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class DepositFacadeMockImpl implements DepositFacade {
    List<DepositDto> list = new ArrayList<>();

    @Override
    public List<DepositDto> getDeposits(Long userId) {
        if (userId !=null && userId.equals(42L)) {
            return list;
        }
        return null;
    }

    private Random random = new Random();

    {
        for (int i = 0; i < 3; i++) {
            list.add(createDeposit());
        }
    }

    private DepositDto createDeposit() {
        DepositDto d = new DepositDto();
        d.id = (long) random.nextInt(Integer.MAX_VALUE);
        d.name = String.valueOf(d.id);
        d.terms = Arrays.asList(new Date(random.nextLong()), new Date(random.nextLong()));
        return d;
    }

}
