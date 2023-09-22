/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Algorithm;
import common.Input;
import java.util.ArrayList;
import java.util.Hashtable;
import model.Fruit;
import model.Order;
import view.Menu;

/**
 *
 * @author GoldCandy
 */
public class Program extends Menu{
    private ArrayList<Fruit> fruitData;
    private Hashtable<String, ArrayList<Order>> hashTable;
    private static String[] mc = {"Create Fruit", "View orders", "Shopping", "Exit"};
    private Input input;
    private Algorithm algorithm;
    
    public Program(){
        super("FRUIT SHOP SYSTEM", mc);
        input = new Input();
        algorithm = new Algorithm();
        fruitData = new ArrayList();
        hashTable = new Hashtable();
    }
    
    @Override
    public void execute(int choice) {
        switch(choice){
            case 1:{
                System.out.println("CREATING");
                algorithm.addFruit(fruitData);
                break;
            }
            case 2:{
                System.out.println("VIEW ORDERS");
                algorithm.show(hashTable);
                break;
            }
            case 3:{
                System.out.println("SHOPPING");
                algorithm.shoppingOrder(fruitData, hashTable);
                break;
            }
            case 4:{
                System.out.println("EXIT PROGRAM...");
                System.exit(0);
            }
            default:{
                System.out.println("No such choice");
            }
        }
    }
    
}
