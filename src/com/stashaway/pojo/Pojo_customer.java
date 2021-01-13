package com.stashaway.pojo;

import com.stashaway.enums.Enum_amounts;

import java.util.HashMap;
import java.util.Map;

public class Pojo_customer {

    private String reference_code;
    private Map<Pojo_portfolio, Long> portfolios;

    public String getReference_code() {
        return reference_code;
    }

    public Map<Pojo_portfolio, Long> getPortfolios() {
        return portfolios;
    }

    public void addPortfolio(Pojo_portfolio pojo_portfolio) {
        if (portfolios == null)
            portfolios = new HashMap<>();
        portfolios.put(pojo_portfolio, Enum_amounts.MINIMUM_AMOUNT.getValue());
    }
}
