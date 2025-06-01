package com.company.ecommerce.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "CART", indexes = {
        @Index(name = "IDX_CART_CUSTOMER", columnList = "CUSTOMER_ID"),
        @Index(name = "IDX_CART_STATUS", columnList = "STATUS_ID")
})
@Entity
public class Cart {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "CHECKED_OUT_DATE")
    private LocalDate checkedOutDate;

    @Column(name = "TOTAL")
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private User customer;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @JoinColumn(name = "STATUS_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Status status;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDate getCheckedOutDate() {
        return checkedOutDate;
    }

    public void setCheckedOutDate(LocalDate checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    public PaymentType getPaymentType() {
        return paymentType == null ? null : PaymentType.fromId(paymentType);
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.getId();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}