package mariospizza.data;

import java.io.Serializable;

public class OrderLine implements Serializable{
    private Pizza pizza;
    private int count;

    public OrderLine(Pizza pizza, int count) {
        this.pizza = pizza;
        this.count = count;
    }

    public int getCount() { return count; }
    public Pizza getPizza() { return pizza; }
    public double getPrice() { return count * pizza.getPrice(); }

    @Override
    public String toString() {
        return String.format("%2d stk %-15s á kr. %7.2f\t = kr. %7.2f",count, pizza.getName(), pizza.getPrice(), this.getPrice());
    }
}
