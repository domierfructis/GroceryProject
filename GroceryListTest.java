import java.io.*;
import java.util.*;
public class GroceryListTest{
    public static void main(String[] args){
        GroceryList list = new GroceryList();
        Scanner console = new Scanner(System.in);
        instructions(console, list);
    }
    public static void instructions(Scanner console, GroceryList list){
        System.out.println("Welcome to Dom's Grocery List Creator.");
        System.out.println("Please select an option to get started");
        System.out.println("[1] Start a new list");
        System.out.println("[2] Add an item to the list");
        System.out.println("[3] Remove an item from the list");
        System.out.println("[4] Adjust the price of an item");
        System.out.println("[5] Adjust the quantity of an item");
        System.out.println("[6] View your current list and its total cost");
        System.out.println("[7] Use an example grocery list");
        System.out.println("[help] View information about this program");
        System.out.println("[quit] Exit the program");
        userResponse(console,console.next(), list);
    }
    public static void userResponse(Scanner console, String response, GroceryList list){
        switch(response){
            case "0":
                instructions(console, list);
                userResponse(console, console.next(), list);
                break;
            case "1":
                start(console, list);
                userResponse(console, console.next(), list);
                break;
            case "2":
                addToList(console, list);
                userResponse(console, console.next(), list);
                break;
            case "3":
                removeFromList(console, list);
                userResponse(console, console.next(), list);
                break;
            case "4":
                adjustPrice(console,list);
                userResponse(console, console.next(), list);
                break;
            case "5":
                adjustQuantity(console, list);
                userResponse(console, console.next(), list);
                break;
            case "6":
                printList(list);
                userResponse(console, console.next(), list);
                break;
            case "7":
                exampleList(list);
                userResponse(console, console.next(), list);
                break;
            case "help":
                help();
                userResponse(console,console.next(),list);
            case "quit":
                System.exit(0);
                break;
            default: 
                System.out.println("invalid response. Press 0 to view options.");
                userResponse(console,console.next(), list);
        }
    }
    public static GroceryList start(Scanner console, GroceryList list){
        list.clear();
        System.out.println("You now have a new grocery list! press 2 to start adding items.");
        return list;
    }
    public static GroceryList addToList(Scanner console, GroceryList list){
        String name;
        int quantity = 0;
        double price = 0.;
        if(list.totalItems==10){
            System.out.println("List is full! Press 0 to view options");
            return list;
        }
        System.out.println("What item would you like to add to your list?");
        name = console.next();

        System.out.println("How much "+name+" would you like to purchase?");
        while(quantity<=0){
            if(console.hasNextInt()) {
                quantity = console.nextInt();
            }else{
            console.next();
            System.out.println("You must enter a positive integer for the quantity of "+name);
            }
        }
        
        System.out.println("What is the price of each unit of "+name+"?");
        while(price <= 0){
            if(console.hasNextDouble()) {
                price = console.nextDouble();
            }else{
                console.next();
                System.out.println("You must enter a positive number for the price of "+name);
            }
        }
        
        GroceryItemOrder a = new GroceryItemOrder(name,quantity,price);
        list.add(a);

        System.out.println(name+" has successfully been added to the list!");
        System.out.println("Press 2 to add another item or 0 to view options");
        return list;
    }
    public static GroceryList removeFromList(Scanner console, GroceryList list){
        System.out.println("Enter the name of the item you wish to remove: ");
        String name = console.next();
        int index = findItem(name,list);
        if(index>=0){
            list.remove(index);
            System.out.println(name+" has been removed from the list! Press 0 to view options");
        }else{
            System.out.println("Could not find item \""+name+"\" on the list.");
            System.out.println("Press 3 to try again or 0 to view options");
        }
        return list;
    }
    public static GroceryList adjustPrice(Scanner console, GroceryList list){
        System.out.println("Enter the name of the item you wish to adjust: ");
        String name = console.next();
        double price = 0.;
        int index = findItem(name,list);
        if(index>=0){
            System.out.printf("%s currently costs $%.2f each\n",name,list.item(index).pricePerUnit);
            System.out.println("What price would you like to change to?");
            while(price <= 0){
                if(console.hasNextDouble()) {
                    price = console.nextDouble();
                }else{
                    console.next();
                    System.out.println("You must enter a positive number for the price of "+name);
                }
            }
            list.item(index).setPrice(price);
            System.out.println("the price of "+name+" has been changed! Press 0 to view options");
        }else{
            System.out.println("Could not find item \""+name+"\" on the list.");
            System.out.println("Press 4 to try again or 0 to view options");
        }
        return list;
    }
    public static GroceryList adjustQuantity(Scanner console, GroceryList list){
        System.out.println("Enter the name of the item you wish to adjust: ");
        String name = console.next();
        int quantity = 0;
        int index = findItem(name,list);
        if(index>=0){
            System.out.printf("You currently have %d units of %s\n",list.item(index).quantity,name);
            System.out.println("What would you like to change the quantity to?");
            while(quantity<=0){
                if(console.hasNextInt()) {
                    quantity = console.nextInt();
                }else{
                    console.next();
                    System.out.println("You must enter a positive integer for the quantity of "+name);
                }
            }
            list.item(index).setQuantity(quantity);
            System.out.println("the quantity of "+name+" has been changed! Press 0 to view options");
        }else{
            System.out.println("Could not find item \""+name+"\" on the list.");
            System.out.println("Press 5 to try again or 0 to view options");
        }
        return list;
    }
    public static void printList(GroceryList list) {
        if (list.totalItems < 1) {
            System.out.println("Your list is empty!");
            System.out.println("Press 2 to add an item or 7 to use an example list");
        } else {
            for (int i = 0; i < list.totalItems; i++) {
                System.out.printf("%s\n", list.item(i).toString());
                System.out.printf("            $%6.2f\n", list.item(i).getCost());

            }
            System.out.printf("Total Cost: $%6.2f\n", list.getTotalCost());
            System.out.println("Press 0 to view options");
        }
    }
    public static int findItem(String input,GroceryList list){
        int foundIndex = -1;
        for(int i=0;i<list.totalItems;i++){
            if(input.equalsIgnoreCase(list.item(i).name)){
                foundIndex = i;
            }
        }
        return foundIndex;
    }
    public static GroceryList exampleList(GroceryList list){
        list.clear();
        Random r = new Random();
        GroceryItemOrder a = new GroceryItemOrder("milk",r.nextInt(5)+1,3.99);
        GroceryItemOrder b = new GroceryItemOrder("cereal",r.nextInt(5)+1,4.75);
        GroceryItemOrder c = new GroceryItemOrder("candy",r.nextInt(5)+1,0.99);
        GroceryItemOrder d = new GroceryItemOrder("steak",r.nextInt(5)+1,8.99);
        GroceryItemOrder e = new GroceryItemOrder("potatoes",r.nextInt(5)+1,0.25);
        GroceryItemOrder f = new GroceryItemOrder("eggs",r.nextInt(5)+1,5.99);
        GroceryItemOrder g = new GroceryItemOrder("bacon",r.nextInt(5)+1,6.75);
        GroceryItemOrder h = new GroceryItemOrder("beer",r.nextInt(5)+1,1.29);
        GroceryItemOrder i = new GroceryItemOrder("pasta",r.nextInt(5)+1,1.19);
        GroceryItemOrder j = new GroceryItemOrder("cheese",r.nextInt(5)+1,3.49);
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);
        list.add(h);
        list.add(i);
        list.add(j);
        System.out.println("List has been populated with 10 items!");
        System.out.println("Press 0 to view options");
        return list;
    }
    public static void help(){
        try {
            File readme = new File("readme.txt");
            Scanner f = new Scanner(readme);
            while(f.hasNextLine()){
                System.out.println(f.nextLine());
            }
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

}