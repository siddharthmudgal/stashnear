package com.stashaway.pojo;

import java.util.Map;

/**
 * Pojo holds the deposit plan that the system has received
 */
public class Pojo_deposit_plan {

    private String uuid;
    private String customer_reference_id;
    private String type;
    private Map<String, Long> deposits;

    public Pojo_deposit_plan(String uuid, String customer_reference_id, String type, Map<String, Long> deposits) {
        this.uuid = uuid;
        this.customer_reference_id = customer_reference_id;
        this.type = type;
        this.deposits = deposits;
    }

    public String getUuid() {
        return uuid;
    }

    public String getCustomer_reference_id() {
        return customer_reference_id;
    }

    public String getType() {
        return type;
    }

    public Map<String, Long> getDeposits() {
        return deposits;
    }

    @Override
    public String toString() {
        return "Pojo_deposit_plan{" +
                "uuid='" + uuid + '\'' +
                ", customer_reference_id='" + customer_reference_id + '\'' +
                ", type='" + type + '\'' +
                ", deposits=" + deposits +
                '}';
    }
}
