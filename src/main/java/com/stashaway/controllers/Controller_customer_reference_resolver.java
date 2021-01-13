package com.stashaway.controllers;

import com.stashaway.pojo.Pojo_customer;
import com.stashaway.pojo.Pojo_portfolio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Dummy class to simulate a database of customers. This class uses a HashMap to store all customers,
 * ideally this should be pulled from a database.
 */
public class Controller_customer_reference_resolver {

    static Map<String, Pojo_customer> all_customer_data = new HashMap<>();

    public static Pojo_customer get_and_validate_customer_from_reference_id(String reference_id) {

        if (all_customer_data.containsKey(reference_id)) {
            return all_customer_data.get(reference_id);
        }

        return null;

    }

    public static boolean add_customer(String reference_id, List<Pojo_portfolio> portfolios) {

        Pojo_customer customer;

        if (all_customer_data.containsKey(reference_id)) {
            customer = all_customer_data.get(reference_id);
        } else {
            customer = new Pojo_customer(reference_id);
            all_customer_data.put(reference_id,customer);
        }

        Iterator<Pojo_portfolio> portfolioIterator = portfolios.listIterator();
        while (portfolioIterator.hasNext()) {
            customer.addPortfolio(portfolioIterator.next());
        }

        all_customer_data.replace(reference_id, customer);
        return true;

    }

}
