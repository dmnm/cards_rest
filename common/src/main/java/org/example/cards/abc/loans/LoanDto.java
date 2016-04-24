package org.example.cards.abc.loans;

import java.util.Date;
import java.util.List;

import org.example.cards.annotation.Simplified;

@Simplified
public class LoanDto {
    public Long id;
    public String name;
    public List<Date> terms;
}
