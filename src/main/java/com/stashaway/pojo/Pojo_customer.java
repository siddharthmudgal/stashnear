package com.stashaway.pojo;

import com.stashaway.enums.Enum_amounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Pojo representing the details of a customer
 */
public class Pojo_customer {

    private String reference_code;
    /**
     * String -> portfolio id
     * Long -> amount in the portfolio
     */
    private Map<String, Long> portfolios;

    public Pojo_customer(String reference_code) {
        this.reference_code = reference_code;
    }

    public String getReference_code() {
        return reference_code;
    }

    public Map<String, Long> getPortfolios() {
        return portfolios;
    }

    public void addPortfolio(Pojo_portfolio pojo_portfolio) {
        if (portfolios == null)
            portfolios = new HashMap<>();
        portfolios.put(pojo_portfolio.getUuid(), Enum_amounts.MINIMUM_AMOUNT.getValue());
    }
}
