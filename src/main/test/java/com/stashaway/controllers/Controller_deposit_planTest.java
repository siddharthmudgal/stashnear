package com.stashaway.controllers;

import com.stashaway.enums.Enum_deposit_type;
import com.stashaway.pojo.Pojo_customer;
import com.stashaway.pojo.Pojo_deposit_plan;
import com.stashaway.pojo.Pojo_portfolio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Controller_deposit_planTest {

    String[] reference_ids;
    List<Pojo_deposit_plan> pojo_deposit_plan_list;
    String[] portfolio_ids;
    @BeforeEach
    void setUp() {

        //Create customers with sample portfolios
        reference_ids = new String[] {
                "reference-id-1",
                "reference-id-2",
                "reference-id-3"
        };
        portfolio_ids = new String[] {
                "portfolio-id-1",
                "portfolio-id-2",
                "portfolio-id-3",
        };
        List<Pojo_portfolio> portfolioList = new ArrayList<>();
        for ( int i=0; i<portfolio_ids.length; i++) {
            portfolioList.add(new Pojo_portfolio(portfolio_ids[i], portfolio_ids[i], 9));
        }

        for ( int i=0; i<reference_ids.length; i++) {
            Controller_customer_reference_resolver.add_customer(reference_ids[i], portfolioList);
        }

        //Create a sample deposits
        Map<String, Long> deposits = new HashMap<>();
        for ( int i=0; i<portfolio_ids.length; i++) {
            deposits.put(portfolio_ids[i], 100l);
        }

        //Creating sample deposit plans
        pojo_deposit_plan_list = new ArrayList<>();
        for (int i=0;i<reference_ids.length;i++) {

            pojo_deposit_plan_list.add(
                    new Pojo_deposit_plan(
                    UUID.randomUUID().toString(),
                    reference_ids[i],
                    Enum_deposit_type.ONE_TIME.name(),
                    deposits
                    )
            );

        }

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void execute() {

        int number_of_deposit_plans = pojo_deposit_plan_list.size();

        int amount = 100;
        int repeat = 1;
        while (repeat < 5) {

            for (int i = 0; i < number_of_deposit_plans; i++) {
                Controller_deposit_plan.execute(pojo_deposit_plan_list.get(i));
            }

            for (int i = 0; i < reference_ids.length; i++) {
                Pojo_customer customer = Controller_customer_reference_resolver.get_and_validate_customer_from_reference_id(reference_ids[i]);
                Map<String, Long> portfolios = customer.getPortfolios();
                for (int j = 0; j < portfolio_ids.length; j++) {

                    assert (portfolios.get(portfolio_ids[j]) == (amount * repeat));

                }
            }
            repeat++;

        }


    }
}