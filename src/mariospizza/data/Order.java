package mariospizza.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{
    private static int nextOrderNo = 1;
    private int orderNo;
    private LocalDateTime dateTime;
    private String customerName;
    private List<OrderLine> orderlines;

    public Order(String customerName, List<OrderLine> orderlines) {
        this.orderNo = nextOrderNo++;
        this.dateTime = LocalDateTime.now();
        this.customerName = customerName;
        this.orderlines = new ArrayList<>();
        this.orderlines.addAll(orderlines);
    }
    
    public int getOrderNo(){ return orderNo; }
    public String getCustomerName() { return this.customerName; }
    public List<OrderLine> getOrderLines() { return this.orderlines; }
    public double getPrice() {
        double sum = 0;
        for(OrderLine ol : orderlines)
            sum += ol.getPrice();
        return sum;
    }
    
    public String toString(){
        final String hr = hr(71);
        StringBuilder builder = new StringBuilder();
        builder.append(hr).append("\n");
        builder.append(String.format("%04d", this.orderNo));
        
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM-yyyy HH:mm");
        String formattedDateTime = dateTime.format(formatter);
        
        
        
        builder.append(String.format("%67s" , formattedDateTime)).append("\n");
        builder.append(this.customerName).append("\n").append("\n");
        for(OrderLine ol : this.orderlines){
            builder.append("\t").append(ol.toString()).append("\n");
        }
        builder.append("\n");
        builder.append(String.format("\t%40s = kr. %7.2f", "Total", this.getPrice())).append("\n");
        builder.append(hr);
        return builder.toString();
    }
    
    private static String hr(int length){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++){
            builder.append("-");
        }
        return builder.toString();
    }
}
