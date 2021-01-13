package com.stashaway.controllers;

import com.stashaway.pojo.Pojo_customer;

import java.util.HashMap;
import java.util.Map;

/**
 * Dummy class to simulate a database of customers. This class uses a HashMap to store all customers,
 * ideally this should be pulled from a database. Current logics only add new customers as new reference
 * id's come. However, in a real case scenario this refernce id would be validated against a
 * customer database
 */
public class Controller_customer_reference_resolver {

    static Map<String, Pojo_customer> all_customer_data = new HashMap<>();

    public static Pojo_customer get_and_validate_customer_from_reference_id(String reference_id) {

        if (all_customer_data.containsKey(reference_id)) {
            return all_customer_data.get(reference_id);
        }

        Pojo_customer customer = new Pojo_customer(reference_id);
        all_customer_data.put(reference_id, customer);
        return customer;

    }

}
