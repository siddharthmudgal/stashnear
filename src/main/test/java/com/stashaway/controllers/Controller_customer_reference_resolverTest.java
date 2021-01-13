package com.stashaway.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Controller_customer_reference_resolverTest {

    @BeforeEach
    void setUp() {
        String[] reference_ids = new String[] {
                "reference-id-1",
                "reference-id-2",
                "reference-id-3"
        };

        for ( int i=0; i<reference_ids.length; i++)
            Controller_customer_reference_resolver.get_and_validate_customer_from_reference_id(reference_ids[i]);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get_and_validate_customer_from_reference_id() {

        String reference_id = "reference-id-1";
        String reference_id_two = "reference-id-4";
        assert (
                Controller_customer_reference_resolver.get_and_validate_customer_from_reference_id(reference_id) != null
                &&
                Controller_customer_reference_resolver.get_and_validate_customer_from_reference_id(reference_id_two) != null
        );

    }
}