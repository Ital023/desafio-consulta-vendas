package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class SaleReportDTO {

    private Long id;
    private Double amount;
    private LocalDate date;
    private String name;

    public SaleReportDTO() {
    }

    public SaleReportDTO(Long id, Double amount, LocalDate date, String name) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
