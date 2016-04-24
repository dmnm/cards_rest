package org.example.cards.abc.cards;

import java.math.BigDecimal;

import org.example.cards.annotation.Simplified;

@Simplified
public class CardDto {
    public Long id;
    public String number;
    public Long bankAccountId;
    public BigDecimal balance;
    public boolean blocked;
}
