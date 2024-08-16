package com.niantic.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReportLine {

    private String userName;
    private String categoryName;
    private String vendorName;
    private LocalDate date;
    private BigDecimal amount;
    private String notes;

    public ReportLine(String userName, String categoryName, String vendorName, LocalDate date, BigDecimal amount, String notes) {
        this.userName = userName;
        this.categoryName = categoryName;
        this.vendorName = vendorName;
        this.date = date;
        this.amount = amount;
        this.notes = notes;
    }

    public ReportLine() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
