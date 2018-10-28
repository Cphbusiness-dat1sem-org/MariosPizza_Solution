package mariospizza.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileHandler {
    private static final String ordersFilename = "orders.bin";

    public void saveCompletedOrders(List<Order> completedOrders) throws IOException {
        ObjectOutputStream out = null;
        File file = new File(ordersFilename);
        out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(completedOrders);
        out.flush();
        out.close();
    }
    
    public List<Order> loadCompletedOrders() throws IOException{
        try {
            ObjectInputStream in = null;
            File file = new File(ordersFilename);
            in = new ObjectInputStream(new FileInputStream(file));
            List<Order> completedOrders = (List<Order>) in.readObject();
            in.close();
            return completedOrders;
        } catch (ClassNotFoundException ex) {
            throw new IOException();
        }
    }

}
