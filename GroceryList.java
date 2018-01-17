public class GroceryList{
    int totalItems = 0;
    int index;
    double tax;
    double totalCost = 0.00;
    static final int MAX_ITEMS = 10;
    GroceryItemOrder[] groceryList;
    public GroceryList(){
        groceryList = new GroceryItemOrder[MAX_ITEMS];
    }
    public void add(GroceryItemOrder item){
        if(totalItems < MAX_ITEMS){
            groceryList[this.totalItems] = item;
            totalItems++;
        } else {
            System.out.println("Grocery list is full!");
        }
    }
    public void remove(int index){
        if(totalItems>0){
            for(int i=index;i<this.totalItems-1;i++) {
                groceryList[i] = groceryList[i+1];
            }
            groceryList[this.totalItems-1]=null;
            totalItems--;
        }
    }
    public double getTotalCost(){
        totalCost = 0;
       for(int i=0;i<this.totalItems;i++){
           totalCost += groceryList[i].getCost();
        }
        return totalCost;
    }
    public GroceryItemOrder item(int index){
        this.index = index;
        return groceryList[index];

    }
    public GroceryItemOrder[] clear(){
        groceryList = new GroceryItemOrder[MAX_ITEMS];
        totalItems = 0;
        return groceryList;
    }
}