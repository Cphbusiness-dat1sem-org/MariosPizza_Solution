package mariospizza.logic;

import java.io.IOException;
import mariospizza.data.Pizza;
import mariospizza.presentation.Menu;
import java.util.ArrayList;
import java.util.List;
import mariospizza.data.FileHandler;
import mariospizza.data.Order;
import mariospizza.data.OrderLine;
import mariospizza.presentation.NoOrdersSelectedException;

public class MariosPizza {
    private List<Order> orders = new ArrayList<>();
    private List<Order> completedOrders;
    private FileHandler fh = new FileHandler();
    private Helper helper = new Helper();
    private Menu menu = new Menu();

    public MariosPizza() {
        List<Pizza> pizzas = makePizzas();
        
        try {
            completedOrders = fh.loadCompletedOrders();
        } catch (IOException ex) {
            completedOrders = new ArrayList<>();
        }

        List<OrderLine> orderlines = new ArrayList<>();
        menu.showWelcome();
        while(true){
            String input = menu.getUserInput();
            switch(input){
                case "afslut":
                case "slut":
                    menu.showAccept();
                    String customerName = menu.getName();
                    Order order = new Order(customerName, orderlines);
                    orders.add(order);
                    break;
                case "menu":
                    menu.showPizzas(pizzas);
                    break;
                case "bestilling": 
                case "bestillinger":
                case "b":
                    removeOrder();
                    System.out.println("Sådan.");
                    break;
                case "færdige bestillinger":
                case "fb":
                    menu.showCompleted(completedOrders);
                    break;
                default:
                    try{
                        OrderLine ol = helper.makeOrderLine(pizzas, input);
                        orderlines.add(ol);
                        menu.showConfirm(ol);
                        menu.askMore();
                        continue;
                    }catch(BadOrderLineException e){
                        menu.showOrderLineProblem(e.getProblem());
                    }
            }
            menu.showNextCustomer();
        }
    }
    
    private void removeOrder() {
        try{
            String completed = menu.showOrders(orders);
            Order order = helper.getOrder(orders, completed);
            orders.remove(order);
            completedOrders.add(order);
            fh.saveCompletedOrders(completedOrders);
        } catch (OrderNotFoundException ex) {
            menu.showOrderNotFoundMessage();
        } catch(IOException ex){
            menu.showRemoveIOErrorMessage();
        } catch (NoOrdersSelectedException ex) {
            menu.showNoOrderSelectedMessage();
        }
    }
    
    public List<Pizza> makePizzas(){
        List<Pizza> pizzas = new ArrayList<>();
        int i = 1;
        pizzas.add(new Pizza(i++, "Vesuvio", 57.0, "tomatsauce", "ost", "Skinke", "Oregano"));
        pizzas.add(new Pizza(i++, "Amerikaner", 53.0, "tomatsauce", "ost", "oksefars", "Oregano"));
        pizzas.add(new Pizza(i++, "Cacciatore", 57.0, "tomatsauce", "ost", "Pepperoni", "Oregano"));
        pizzas.add(new Pizza(i++, "Carbona", 63.0, "tomatsauce", "ost", "Kødsauce", "Spaghetti", "Cocktailpølser", "Oregano"));
        pizzas.add(new Pizza(i++, "Dennis", 65.0, "tomatsauce", "ost", "Skinke", "Pepperoni", "Cocktailpølser", "Oregano"));
        pizzas.add(new Pizza(i++, "Bertil", 57.0, "tomatsauce", "ost", "Bacon", "Oregano"));
        pizzas.add(new Pizza(i++, "Silvia", 61.0, "tomatsauce", "ost", "Pepperoni", "Rød peber", "Løg", "Oliven", "Oregano"));
        pizzas.add(new Pizza(i++, "Victoria", 61.0, "tomatsauce", "ost", "Skinke", "Ananas", "Champignon", "Løg", "Oregano"));
        pizzas.add(new Pizza(i++, "Toronfo", 61.0, "tomatsauce", "ost", "Skinke", "Bacon", "Kebab", "Chili", "Oregano"));
        pizzas.add(new Pizza(i++, "Capricciosa", 61.0, "tomatsauce", "ost", "Skinke", "Champignon", "Oregano"));
        pizzas.add(new Pizza(i++, "Hawai", 61.0, "tomatsauce", "ost", "Skinke", "Ananas", "Oregano"));
        pizzas.add(new Pizza(i++, "Le Blissola", 61.0, "tomatsauce", "ost", "Skinke", "Rejer", "Oregano"));
        pizzas.add(new Pizza(i++, "Venezia", 61.0, "tomatsauce", "ost", "Skinke", "Bacon", "Oregano"));
        pizzas.add(new Pizza(i++, "Mafia", 61.0, "tomatsauce", "ost", "Pepperoni", "Bacon", "Løg", "Oregano"));
        return pizzas;
    }
}

