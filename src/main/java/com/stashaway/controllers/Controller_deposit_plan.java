package com.stashaway.controllers;

import com.stashaway.pojo.Pojo_deposit;
import com.stashaway.pojo.Pojo_deposit_plan;

import java.util.Iterator;
import java.util.List;

public class Controller_deposit_plan {

    public static boolean execute(Pojo_deposit_plan pojo_deposit_plan) {

        /**
         * Generating deposit commands helps treat deposits as events and can later be put into a message bus
         * for an async execution
         */

        List<Pojo_deposit> deposits = Controller_deposit_command_generator.process(pojo_deposit_plan);

        if (deposits == null)
            return false;

        Iterator<Pojo_deposit> depositIterator = deposits.listIterator();
        while (depositIterator.hasNext()) {

            /**
             * Executing the controller responsible for executing individual deposits. A more scalable approach
             * would include pushing these commands to an async bus / thread. Micro-service oriented approach would
             * help with the load handling
             */
            Controller_deposit.process(

                    Controller_customer_reference_resolver.get_and_validate_customer_from_reference_id(
                      pojo_deposit_plan.getCustomer_reference_id()
                    ),
                    depositIterator.next()
            );

        }

        return true;
    }

}
