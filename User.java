package BugdetMaster;

import java.time.LocalDate;
import java.util.*;

public class User {
    private String name;
    private double monthlyBudget;
    private List<Expense> expenses;

    public User(String name, double monthlyBudget, List<Expense> expenses) {
        this.name = name;
        this.monthlyBudget = monthlyBudget;
        this.expenses = expenses;
    }

    public void addExpense(Expense e){
        expenses.add(e);
    }

    public void displayAllExpenses(){
        expenses.sort((exp1, exp2) -> exp1.getDate().compareTo(exp2.getDate()));
        System.out.println(expenses.toString());
    }

    public void getExpenseByCategory(Category c){
        List<Expense> expensesSorted = (List<Expense>) expenses.stream().filter(expense -> expense.getCategory() == c);
        System.out.println(expensesSorted.toString());
    }

    public double getTotalSpentThisMonth(){
        double totalSpent = 0;
        for(Expense e : expenses){
            if(e.getDate().getMonthValue() == LocalDate.now().getMonthValue()){
                totalSpent += e.getAmount();
            }
        }

        if(totalSpent > monthlyBudget){
            System.out.println("Error, your total spent is higher than your monthly budget");
            return 0;
        }
        else{
            return totalSpent;
        }
    }

//    public List<Category> getTopCategories(){
//
//    }

    public HashMap<Category, Double> calculateAverageSpendingPerCategory(){
        HashMap<Category, Double> categoryAverage = new HashMap<>();
        int nourriture = 0;
        double nourritureSpent = 0;
        int transport = 0;
        double transportSpent = 0;
        int divertissement = 0;
        double divertissementSpent = 0;
        int services = 0;
        double servicesSpent = 0;
        int autres = 0;
        double autresSpent = 0;

        for(Expense e : expenses){
            if(e.getCategory() == Category.NOURRITURE_ET_RESTAURATION){
                nourriture++;
                nourritureSpent += e.getAmount();
            }
            else if(e.getCategory() == Category.TRANSPORT){
                transport++;
                transportSpent += e.getAmount();
            }
            else if(e.getCategory() == Category.DIVERTISSEMENT){
                divertissement++;
                divertissementSpent += e.getAmount();
            }
            else if(e.getCategory() == Category.SERVICES_PUBLICS){
                services++;
                servicesSpent += e.getAmount();
            }
            else {
                autres++;
                autresSpent += e.getAmount();
            }
        }

        categoryAverage.put(Category.NOURRITURE_ET_RESTAURATION, nourritureSpent / nourriture);
        categoryAverage.put(Category.TRANSPORT, transportSpent / transport);
        categoryAverage.put(Category.DIVERTISSEMENT, divertissementSpent / divertissement);
        categoryAverage.put(Category.SERVICES_PUBLICS, servicesSpent / services);
        categoryAverage.put(Category.AUTRES, autresSpent / autres);

        return categoryAverage;
    }

    public double getRemainingBugdet(){
        return this.getTotalSpentThisMonth() - monthlyBudget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(monthlyBudget, user.monthlyBudget) == 0 && Objects.equals(name, user.name) && Objects.equals(expenses, user.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, monthlyBudget, expenses);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", monthlyBudget=" + monthlyBudget +
                ", expenses=" + expenses +
                '}';
    }
}
