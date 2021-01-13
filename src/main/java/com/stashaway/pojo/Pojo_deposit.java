package com.stashaway.pojo;

/**
 * Pojo for representing smallest unit of a deposit plan, the individual deposit itself
 */
public class Pojo_deposit {

    private String uuid;
    private String portfolio_id;
    private Long amount;

    public Pojo_deposit(String uuid, String portfolio_id, Long amount, String reference_id) {
        this.uuid = uuid;
        this.portfolio_id = portfolio_id;
        this.amount = amount;
        this.reference_id = reference_id;
    }

    public String getReference_id() {
        return reference_id;
    }

    private String reference_id;

    public String getUuid() {
        return uuid;
    }

    public String getPortfolio_id() {
        return portfolio_id;
    }

    public Long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Pojo_deposit{" +
                "uuid='" + uuid + '\'' +
                ", portfolio_id='" + portfolio_id + '\'' +
                ", amount=" + amount +
                ", reference_id='" + reference_id + '\'' +
                '}';
    }
}
