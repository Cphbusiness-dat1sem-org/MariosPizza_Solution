package mariospizza.presentation;

import mariospizza.logic.BadOrderLineException;
import java.util.List;
import java.util.Scanner;
import mariospizza.data.Order;
import mariospizza.data.OrderLine;
import mariospizza.logic.OrderNotFoundException;
import mariospizza.data.Pizza;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    
    public void showPizzas(List<Pizza> pizzas){
        for(Pizza p : pizzas){
            System.out.println(p);
        }
    }
    public void showWelcome() {
        System.out.println("Hvad sku' det vær'?");
        System.out.println("Tast \"pizza nummer/navn\" eller) \"antal\" * \"pizza nummer/navn\" for at bestille.");
        System.out.println("Tast \"afslut\" for at afslutte eller \"menu\" for at se menuen.");
    }
    public void showCompleted(List<Order> completedOrders){
        for(Order order : completedOrders){
            System.out.println(order);
        }
        if(completedOrders.isEmpty()){
            System.out.println("Der er ikke noget der er færdigt chef!");
        }
        System.out.println("Værsgo' chef");
    }
    public void showAccept() {
        System.out.println("Så går der lige 10 minutte");
    }
    public String getName() {
        System.out.println("Hvad sagde du at dit navn var?");
        String customerName = scanner.nextLine();
        return customerName;
    }
    public String showOrders(List<Order> orders) throws OrderNotFoundException, NoOrdersSelectedException {
        for(Order order : orders){
            System.out.println(order);
        }
        if(orders.isEmpty()){
            System.out.println("Ingen bestillinger boss.");
            throw new OrderNotFoundException();
        } else {
            System.out.println("Er en af dem færdig chef?");
        }
        String input = scanner.nextLine();
        if("glem det".equals(input)){
            throw new NoOrdersSelectedException();
        }
        return input;
    }
    public String getUserInput() {
        String input = scanner.nextLine().toLowerCase();
        return input;
    }
    public void showConfirm(OrderLine ol) {
        System.out.println((ol.getCount() == 1 ? "En " : ol.getCount()+" styk ")+ol.getPizza().getName()+ " s'gerne.");
    }
    public void askMore() {
        System.out.println("Ellers andet?");
    }
    public void showOrderLineProblem(BadOrderLineException.Problem problem) {
        switch(problem){
            case AMOUNT: System.out.println("Hvor mange sagde du?"); break;
            case PIZZA:  System.out.println("Øhh, sådan en har jeg ikke. Vil du have noget andet?"); break;
        }
    }
    public void showOrderNotFoundMessage() {
        System.out.println("Sorry boss, jeg kan ikke finde den bestilling!");
    }
    public void showRemoveIOErrorMessage() {
        System.out.println("Pokkers, den vil ikke gemme filen!");
    }
    public void showNoOrderSelectedMessage() {
        System.out.println("OK, chef.");
    }
    public void showNextCustomer() {
        System.out.println("Næste kunde, hvad kan jeg gøre for dig?");
    }

}
