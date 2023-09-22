/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.ArrayList;
import java.util.Scanner;
import model.Fruit;

/**
 *
 * @author GoldCandy
 */
public class Input {
    
    public String String(String title){
        Scanner sc = new Scanner(System.in);
        String s = null;
        while(s == null || s.isEmpty() || s.isBlank()){
            System.out.print(title+": ");
            s = sc.nextLine();
        }
        return s;
    }
    
    public String StringMatch(String title, String regex){
        Scanner sc = new Scanner(System.in);
        String s = "";
        while(!s.matches(regex)){
            System.out.print(title+ ": ");
            s = sc.nextLine();
        }
        return s;
    }
    
    public int integer(String title){
        Scanner sc = new Scanner(System.in);
        int n = -1;
        while(n <= 0){
            System.out.print(title+": ");
            try{
                n = sc.nextInt();
            }
            catch(Exception e){
                n = -1;
            }
        }
        return n;
    }
    
    public int findValidID(String iD, ArrayList<Fruit> fruitData){
        for(int i = 0; i < fruitData.size(); i++){
            if(fruitData.get(i).getFruitID().equalsIgnoreCase(iD))
                return i;
        }
        return -1;
    }
    
    public int getChoiceTo(ArrayList<Fruit> fruitData){
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.print("Enter your choice: ");
            choice = sc.nextInt() - 1;
        }
        while(choice < -1 || choice > fruitData.size() - 1);
        return choice;
    }
}
