package Warehouse;

public class Aisle {

    String identifier;
    private final int aisleWidth;
    private final int aisleDepth;
    public List<Shelf> listOfShelves;
    private final int maxNumberOfShelves;



    //insert list of shelves
    //insert static field that increments by one for identifier.

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getAisleWidth() {
        return aisleWidth;
    }

    public int getAisleDepth() {
        return aisleDepth;
    }

    public Aisle(String identifier, int aisleWidth, int aisleDepth) {
        this.identifier = identifier;
        this.aisleWidth = aisleWidth;
        this.aisleDepth = aisleDepth;
        this.listOfShelves = new List<Shelf>();
        this.maxNumberOfShelves=4;

    }

    public int getAisleArea(){return aisleDepth*aisleWidth;}

    public int getMaxNumberOfShelves(){return maxNumberOfShelves;}

    public void addShelf(Shelf shelfA){
        listOfShelves.addNode(shelfA);
    }



    @Override
    public String toString() {
        return "Aisle: " +
                "Identifier: " + identifier;
    }


}

