package com.dsi.ecommerce.model.order;

import lombok.*;

import javax.persistence.*;

enum PaymentStatus {PENDING, PAID, REFUNDED}
enum PaymentMethod {Cash, bKash, Rocket, Nagad, Visa, Master}

@Setter
@Getter
@ToString
@Entity(name = "payments")
public class Payment {
    @Id
    @SequenceGenerator(name = "payment_id_sequence", sequenceName = "payment_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "transaction_id", columnDefinition = "VARCHAR(40)")
    private String transactionId;

    @Column(name = "paymentDateTimeMillis")
    private Long paymentDateTimeMillis;

    @OneToOne(mappedBy = "payment")
    private Order order;
}
