package com.stashaway.controllers;

import com.stashaway.pojo.Pojo_portfolio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class Controller_customer_reference_resolverTest {

    String[] reference_ids;
    List<Pojo_portfolio> portfolioList;
    @BeforeEach
    void setUp() {
        reference_ids = new String[] {
                "reference-id-1",
                "reference-id-2",
                "reference-id-3"
        };
        portfolioList = new ArrayList<>();
        portfolioList.add(new Pojo_portfolio(UUID.randomUUID().toString(), "portfolio-name-one", 9));
        portfolioList.add(new Pojo_portfolio(UUID.randomUUID().toString(), "portfolio-name-two", 6));
        portfolioList.add(new Pojo_portfolio(UUID.randomUUID().toString(), "portfolio-name-three", 3));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get_and_validate_customer_from_reference_id() {

        String reference_id = "reference-id-1";
        Controller_customer_reference_resolver.add_customer(reference_id, portfolioList);

        String reference_id_two = "reference-id-4";
        assert (
                Controller_customer_reference_resolver.get_and_validate_customer_from_reference_id(reference_id) != null
                &&
                Controller_customer_reference_resolver.get_and_validate_customer_from_reference_id(reference_id_two) == null
        );

    }

    @Test
    void add_customer() {
        for ( int i=0; i<reference_ids.length; i++) {
            Controller_customer_reference_resolver.add_customer(reference_ids[i], portfolioList);

            assert (
                    Controller_customer_reference_resolver.
                            get_and_validate_customer_from_reference_id(reference_ids[i]) != null
            );

        }
    }
}