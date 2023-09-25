/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import model.Customer;
import model.Fruit;
import model.Order;

/**
 *
 * @author GoldCandy
 */
public class Algorithm {
    
    Input input = new Input();
    
    public void addFruit(ArrayList<Fruit> fruitData) {
        int location;
        String opt;
        do{
            Fruit fruit = new Fruit();
            String iD = input.inputString("Enter fruit ID");
            location = input.findValidID(iD, fruitData);
            if (location >= 0) {
                String s;
                s = input.StringMatch("Fruit already exist, add more? (Y/N)", "[YN]");
                if (s.equals("Y")) {
                    int amount = fruitData.get(location).getQuantity();
                    fruitData.get(location).setQuantity(amount + input.inputInteger("Enter amount to add"));
                    opt = input.StringMatch("Do you want to add more(Y/N)", "[YN]");
                    continue;
                }
                else return;
            }
            fruit.setFruitID(iD);
            fruit.setFruitName(input.inputString("Enter fruit name"));
            fruit.setPrice(input.inputInteger("Enter price"));
            fruit.setQuantity(input.inputInteger("Enter quantity"));
            fruit.setOrigin(input.inputString("Enter origin"));
            fruitData.add(fruit);
            opt = input.StringMatch("Do you want to add more(Y/N)", "[YN]");
        }
        while(opt.equals("Y"));
    }
    
    public void display(ArrayList<Fruit> fruitData){
        System.out.println("|++Item++|++Fruit ID++|++Fruit Name++|++Origin++|++Price++|++Quantity++|");
        int count = 0;
        for(Fruit fruit: fruitData){
            System.out.printf("%6d", ++count);
            System.out.println(fruit);
        }
    }
    
    public int findExist(ArrayList<Order> orders, String name){
        if(orders.isEmpty()) return -1;
        int location = -1;
        for(int i = 0; i < orders.size(); i++){
            if(name.equals(orders.get(i).getProduct())) 
                return i; 
        }
        return location;
    }
    
    public void newOrder(ArrayList<Order> orders, Order order, int location){
        if(location == -1){
            orders.add(order);
            return;
        }
        int quantity = order.getQuantity() + orders.get(location).getQuantity();
        orders.get(location).setQuantity(quantity);
        orders.get(location).setAmount(order.getPrice(), quantity);
    }
    
    public void shoppingOrder(ArrayList<Fruit> fruitData, Hashtable<String, ArrayList<Order>> hashTable){
        ArrayList<Order> orderData = new ArrayList();
        String s = "N";
        int total;
        int choice;
        int temp;
        int location;
        while(s.equals("N")){
            if(fruitData.isEmpty()){
                System.out.println("Not found any product");
                return;
            }
            display(fruitData);
            choice = input.getChoiceTo(fruitData);
            System.out.println("You selected: "+fruitData.get(choice).getFruitName());
            total = input.inputInteger("Please input quantity");
            while(total > fruitData.get(choice).getQuantity()){
                total = input.inputInteger("Your quantity is exceeded our roster, Please enter another quantity");
            }
            Order order = new Order();
            order.setProduct(fruitData.get(choice).getFruitName());
            order.setQuantity(total);
            order.setPrice(fruitData.get(choice).getPrice());
            order.setAmount(order.getPrice(), order.getQuantity());
            location = findExist(orderData, fruitData.get(choice).getFruitName());
            newOrder(orderData, order, location);
            temp = fruitData.get(choice).getQuantity();
            fruitData.get(choice).setQuantity(temp - total);
            if(fruitData.get(choice).getQuantity() == 0){
                fruitData.remove(choice);
            }
            if (!fruitData.isEmpty()) s = input.StringMatch("Do you want to order now(Y/N)", "[YN]");
            if(s.equals("Y") || fruitData.isEmpty()){
                Customer customer = new Customer();
                customer.setCustomerName(input.inputString("Enter your name"));
                hashTable.put(customer.getCustomerName(), orderData);
                break;
            } 
        }
    }
    
    public void show(Hashtable<String, ArrayList<Order>> hashTable){
        Set<String> keySet = hashTable.keySet();
        int total;
        for(String key: keySet){
            total = 0;
            System.out.println("Customer: "+key);
            System.out.println("Product | Quantity | Price | Amount");
            for(int i = 0; i < hashTable.get(key).size(); i++){
                String product = hashTable.get(key).get(i).getProduct();
                int quantity = hashTable.get(key).get(i).getQuantity();
                int price = hashTable.get(key).get(i).getPrice();
                int amount = hashTable.get(key).get(i).getAmount();
                total += amount;
                System.out.printf("%d.%s%12d%9d$%8d$",(i+1), product, quantity, price, amount);
                System.out.println("");
            }
            System.out.println("Total: "+total+"$");
        }
    }
}
