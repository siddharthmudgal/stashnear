package com.stashaway.controllers;

import com.stashaway.enums.Enum_amounts;
import com.stashaway.pojo.Pojo_customer;
import com.stashaway.pojo.Pojo_deposit;

import java.util.Map;

/**
 * Controller to manage modular transactions
 */
public class Controller_deposit {

    public static boolean process(Pojo_customer customer, Pojo_deposit deposit) {

        if (deposit.getAmount() <= 0)
            return false;

        Map<String, Long> customerPortfolio = customer.getPortfolios();
        /**
         * Validating presence of portfolio in customer profile and executing the order if the same exists
         */
        if (customerPortfolio.containsKey(deposit.getPortfolio_id())) {

            Long current_amount = customerPortfolio.get(deposit.getPortfolio_id());
            current_amount += deposit.getAmount();
            customerPortfolio.replace(deposit.getPortfolio_id(), current_amount);
            return true;

        } else {

            return false;

        }

    }

}
