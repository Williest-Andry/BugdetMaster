package BugdetMaster;

import java.time.LocalDate;
import java.util.Objects;

public class Expense {
    private String description;
    private double amount;
    private Category category;
    private LocalDate date;

    public Expense(String description, double amount, Category category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Double.compare(amount, expense.amount) == 0 && Objects.equals(description, expense.description) && category == expense.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount, category);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                ", date=" + date +
                '}';
    }
}
