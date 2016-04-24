package org.example.cards.abc.deposits;

import java.util.Date;
import java.util.List;

import org.example.cards.annotation.Simplified;

@Simplified
public class DepositDto {
    public Long id;
    public String name;
    public List<Date> terms;
}
