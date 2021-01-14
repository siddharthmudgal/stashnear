package com.simulate;


import com.stashaway.controllers.Controller_customer_reference_resolver;
import com.stashaway.controllers.Controller_deposit_plan;
import com.stashaway.pojo.Pojo_customer;
import com.stashaway.pojo.Pojo_deposit_plan;
import com.stashaway.pojo.Pojo_portfolio;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PROBLEM STATEMENT :
 * Your input consists of a list of one one-time and/or one monthly deposit plan (maximum two deposit plans total),
 * as well as a list of fund deposits for a particular customer. When you design the data structures, each deposit plan
 * must at least contain the portfolio as well as the absolute amount of money to allocate to that portfolio,
 * and fund deposits must at least contain an absolute money amount deposited. Please build a method that can be called
 * with the described input of both one-time and monthly deposit plans as well as a list of fund deposits, that returns
 * the allocation of funds amongst the customer’s portfolios. Keep in mind that all deposits must be distributed fully.
 *
 * SOLUTION :
 * 1. Main.java -> Takes input from a file and reconstructs the string input to objects
 * 2. com.stashaway.controllers -> Holds controllers for various actions
 *  a. deposit -> process the deposit amount against a portfolio for a customer
 *  b. deposit_plan -> processes an entire deposit plan of a customer; deposit by deposit
 *  c. deposit_command_generator -> creates a list of deposit commands to be executed. List of commands allows the
 *      software to use buses / async tasks to process individual transactions
 *  d. customer_reference_resolver -> controller that stores and retrieves customer information. Currently this
 *      controller stores and generates dummy users and portfolios due to absence of database.
 * 3. enums -> Holds various enums required by the software
 * 4. pojo -> holds data models / data structures for representing relations and values
 * 5. resources/inputFile.txt -> holds input for Main.java.
 *
 * ASSUMPTIONS :
 * 1. Customer reference ID is not part of input. However, the code is designed such that with minimum modifications it
 * can start processing deposit plans for multiple customers with multiple deposit plans
 * 2. No pre-existing customers exist and their profiles are created at runtime. I have used input to generate a
 * customer profile.
 * 3. Input format specifications
 *
 * INTERESTING FEATURES :
 * 1. The code can handle more than just a pair of deposit plans
 * 2. With a small modification, the code can handle multiple deposit plans / deposits and customers
 * 3. Deposit_command_generator would allow async execution of deposits. In a real case scenario this can be bundled
 * with a message bus. This would allow an independent microservice to process deposits.
 *
 * PLEASE NOTE :
 * 1. Logging functionality is not implemented in this source currently. Not sure, if it was part of the scope of this
 * task
 * 2. This snippet is used to express my ability to understand data structure and design. There could be edge cases
 * that I haven't thought through.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        File file = new File(Main.class.getClassLoader().getResource("inputFile.txt").getPath());
        if (!file.exists())
            return;

        Scanner scanner = new Scanner(file);

        /**
         * generating random customer profile with a random customer reference, since it is not mentioned
         * as part of input in task.
         */
        String customer_reference = UUID.randomUUID().toString();

        /**
         * proceeding to reading deposit plans individually from the file
         */
        while (scanner.hasNext()) {
            parseLine(scanner.nextLine(), customer_reference);
        }


        /**
         * Output entire customer cache to console
         */
        Controller_customer_reference_resolver.dump_entire_customer_db();
    }

    public static void parseLine(String line, String customer_reference) {

        /**
         * Assuming these separators, since input is not very clearly defined
         */
        String DEPOSIT_SEPERATOR = ", ";
        String PORTFOLIO_AMOUNT_SEPERATOR = ":";

        /**
         * Proceeding to extract deposits from deposit plans using regular expressions
         */
        String regex = "(?:((One\\s+time)|(Monthly))\\s+\\()(.*)(?:\\))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            /**
             * extracting type of deposit and other deposit details
             */
            String type = matcher.group(1);
            String deposit_raw = matcher.group(4);

            String[] deposits = deposit_raw.split(DEPOSIT_SEPERATOR);

            if (deposits == null || deposits.length == 0)
                return;

            /**
             * Generating a new customer using customer reference ID
             */
            Controller_customer_reference_resolver.add_customer(customer_reference);
            Pojo_customer customer = Controller_customer_reference_resolver.
                    get_and_validate_customer_from_reference_id(customer_reference);

            Map<String, Long> deposits_map = new HashMap<>();

            for (int i=0;i< deposits.length;i++) {

                String[] portfolio_and_amount = deposits[i].split(PORTFOLIO_AMOUNT_SEPERATOR);

                if (portfolio_and_amount == null || portfolio_and_amount.length == 0)
                    continue;

                /**
                 * Adding portfolios to the newly generated customer using portfolios from input
                 * These portfolios are generated with 0 amount
                 */
                customer.addPortfolio(
                        new Pojo_portfolio(portfolio_and_amount[0],
                                portfolio_and_amount[0],
                                8)
                );

                /**
                 * Generating a deposits map, which combine under a single deposit plan
                 */
                deposits_map.put(
                        portfolio_and_amount[0].trim(),
                        Long.valueOf(portfolio_and_amount[1].replaceAll("[^0-9]",""))
                );

            }

            if (deposits_map == null || deposits_map.isEmpty())
                return;

            /**
             * Generating a deposit plan pojo after parsing the textual input from the file
             */
            Pojo_deposit_plan depositPlan = new Pojo_deposit_plan(
                    UUID.randomUUID().toString(),
                    customer_reference,
                    type,
                    deposits_map
            );


            /**
             * Executing the controller responsible to execute deposit plan for a customer
             */
            Controller_deposit_plan.execute(depositPlan);

        }
        return;

    }

}
