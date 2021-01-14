package com.stashaway.controllers;

import com.stashaway.pojo.Pojo_customer;
import com.stashaway.pojo.Pojo_deposit;
import com.stashaway.pojo.Pojo_deposit_plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Generates a set of deposit commands to be executed
 */
public class Controller_deposit_command_generator {

    public static List<Pojo_deposit> process(Pojo_deposit_plan pojo_deposit_plan) {

        Pojo_customer customer = Controller_customer_reference_resolver.get_and_validate_customer_from_reference_id(
                pojo_deposit_plan.getCustomer_reference_id()
        );

        if (customer == null)
            return null;

        // This list will hold modular level transactions to be done for the customer
        List<Pojo_deposit> deposit_commands = new ArrayList<>();

        for (Map.Entry<String, Long> entry : pojo_deposit_plan.getDeposits().entrySet()) {

            Pojo_deposit deposit = new Pojo_deposit(
                    UUID.randomUUID().toString(),
                    entry.getKey(),
                    entry.getValue(),
                    pojo_deposit_plan.getCustomer_reference_id()
            );
            deposit_commands.add(deposit);

        }

        return deposit_commands;

    }

}
