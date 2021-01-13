package com.stashaway.controllers;

import com.stashaway.pojo.Pojo_customer;
import com.stashaway.pojo.Pojo_deposit;
import com.stashaway.pojo.Pojo_portfolio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Controller_depositTest {

    Pojo_customer pojo_customer;

    @BeforeEach
    void setUp() {
        String customer_reference_id = "sample_reference_id";
        pojo_customer = new Pojo_customer(customer_reference_id);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void process() {
        Pojo_portfolio pojo_portfolio = new Pojo_portfolio("sample_portfolio_id", "high_risk", 8);
        pojo_customer.addPortfolio(pojo_portfolio);

        Pojo_deposit pojo_deposit = new Pojo_deposit("sample_transaction_id", pojo_portfolio.getUuid(), 100l,
                "sample_reference_id");
        boolean status = Controller_deposit.process(pojo_customer,pojo_deposit);

        assert (
                pojo_customer.getPortfolios().get(pojo_portfolio.getUuid()).equals(100l) &&
                status == true
        );
    }

    @Test
    void process_fail() {
        Pojo_portfolio pojo_portfolio = new Pojo_portfolio("sample_portfolio_id", "high_risk", 8);
        pojo_customer.addPortfolio(pojo_portfolio);

        Pojo_deposit pojo_deposit = new Pojo_deposit(
                "sample_transaction_id", "incorrect_portfolio_id", 100l,
                "sample_reference_id");

        boolean status = Controller_deposit.process(pojo_customer,pojo_deposit);

        assert (!pojo_customer.getPortfolios().containsKey(pojo_deposit.getPortfolio_id()) &&
                status == false);
    }
}