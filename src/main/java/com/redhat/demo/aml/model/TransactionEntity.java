package com.redhat.demo.aml.model;

import javax.persistence.*;

@Entity
@Table(name="TRANSACTIONS")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_id")
    private Long custId;

    @Column(name="amount")
    private Double amount;

    @Column(name="customer_country")
    private String customerCountry;

    @Column(name="customer_industry")
    private String customerIndustry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerIndustry() {
        return customerIndustry;
    }

    public void setCustomerIndustry(String customerIndustry) {
        this.customerIndustry = customerIndustry;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", custId=" + custId +
                ", amount=" + amount +
                ", customerCountry='" + customerCountry + '\'' +
                ", customerIndustry='" + customerIndustry + '\'' +
                '}';
    }
}
