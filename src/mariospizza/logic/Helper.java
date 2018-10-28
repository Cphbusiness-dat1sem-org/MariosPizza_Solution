package mariospizza.logic;

import mariospizza.data.OrderLine;
import mariospizza.data.Pizza;
import mariospizza.data.Order;
import mariospizza.logic.BadOrderLineException;
import mariospizza.logic.BadOrderLineException.Problem;
import java.util.List;
import mariospizza.logic.OrderNotFoundException;
import mariospizza.logic.PizzaNotFoundException;

public class Helper {

    public OrderLine makeOrderLine(List<Pizza> pizzas, String input) throws BadOrderLineException{
        Pizza pizza;
        int count = 1;
        try {
            if(input.contains("*")){
                pizza = getPizza(pizzas, input.split("\\*")[1].trim());
                count = Integer.parseInt(input.split("\\*")[0].trim());
            } else {
                pizza = getPizza(pizzas, input.trim());
                count = 1;
            }
        } catch (PizzaNotFoundException ex) {
            throw new BadOrderLineException(Problem.PIZZA);
        }catch(NumberFormatException e){
            throw new BadOrderLineException(Problem.AMOUNT);
        }

        OrderLine ol = new OrderLine(pizza, count);
        return ol;
    }
    
    private Pizza getPizza(List<Pizza> pizzas, String pizzaId) throws PizzaNotFoundException{
        for(Pizza p : pizzas){
            try{
                if(p.getNo() == Integer.parseInt(pizzaId))
                    return p;
            }catch(NumberFormatException e){
                if(p.getName().equalsIgnoreCase(pizzaId))
                    return p;
            }
        }
        throw new PizzaNotFoundException();
    }

    public Order getOrder(List<Order> orders, String input) throws OrderNotFoundException {
        Order selectedOrder = null;
        for(Order order : orders){
            int orderNo = Integer.parseInt(input);
            if(orderNo == order.getOrderNo()){
                selectedOrder = order;
                break;
            }
        }
        if(selectedOrder == null){
            throw new OrderNotFoundException();
        }
        return selectedOrder;
    }
}
