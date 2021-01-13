package com.stashaway.pojo;

/**
 * Pojo to hold details of a portfolio
 */
public class Pojo_portfolio {

    private String uuid;
    private String name;
    private int risk_index;

    public Pojo_portfolio(String uuid, String name, int risk_index) {
        this.uuid = uuid;
        this.name = name;
        this.risk_index = risk_index;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getRisk_index() {
        return risk_index;
    }

    @Override
    public String toString() {
        return "Pojo_portfolio{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", risk_index=" + risk_index +
                '}';
    }
}
