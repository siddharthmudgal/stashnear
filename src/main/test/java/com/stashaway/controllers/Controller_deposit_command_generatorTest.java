package com.stashaway.controllers;

import com.stashaway.enums.Enum_deposit_type;
import com.stashaway.pojo.Pojo_deposit_plan;
import com.stashaway.pojo.Pojo_portfolio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This controller generates a series of deposit commands to be executed
 */
class Controller_deposit_command_generatorTest {

    Map<String, Long> deposits;
    String[] reference_ids;
    @BeforeEach
    void setUp() {

        //Create customers with sample portfolios
        reference_ids = new String[] {
                "reference-id-1",
                "reference-id-2",
                "reference-id-3"
        };
        String[] portfolio_ids = new String[] {
                "portfolio-id-1",
                "portfolio-id-2",
                "portfolio-id-3",
        };
        List<Pojo_portfolio>  portfolioList = new ArrayList<>();
        for ( int i=0; i<portfolio_ids.length; i++) {
            portfolioList.add(new Pojo_portfolio(UUID.randomUUID().toString(), portfolio_ids[i], 9));
        }

        for ( int i=0; i<reference_ids.length; i++) {
            Controller_customer_reference_resolver.add_customer(reference_ids[i], portfolioList);
        }

        //Create a sample deposits
        deposits = new HashMap<>();
        for ( int i=0; i<portfolio_ids.length; i++) {
            deposits.put(portfolio_ids[i], UUID.randomUUID().getMostSignificantBits());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void process() {
        for (int i=0;i<reference_ids.length;i++) {

            Pojo_deposit_plan pojo_deposit_plan = new Pojo_deposit_plan(
                    UUID.randomUUID().toString(),
                    reference_ids[i],
                    Enum_deposit_type.ONE_TIME.name(),
                    deposits
            );

            assert (Controller_deposit_command_generator.process(pojo_deposit_plan) != null);

        }
    }
}