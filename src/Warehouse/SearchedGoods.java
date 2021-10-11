package Warehouse;

public class SearchedGoods {
    String description;
    int quantity;
    int floorNumber;
    String aisleIdentifier;
    int shelfNumber;

    public SearchedGoods(String description, int quantity, int floorNumber, String aisleIdentifier, int shelfNumber) {
        this.description = description;
        this.quantity = quantity;
        this.floorNumber = floorNumber;
        this.aisleIdentifier = aisleIdentifier;
        this.shelfNumber = shelfNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getAisleIdentifier() {
        return aisleIdentifier;
    }

    public void setAisleIdentifier(String aisleIdentifier) {
        this.aisleIdentifier = aisleIdentifier;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    @Override
    public String toString() {
        return "SearchedGoods{" +
                "description='" + description + '\'' +
                ", quantity=" + quantity +
                ", floorNumber=" + floorNumber +
                ", aisleIdentifier='" + aisleIdentifier + '\'' +
                ", shelfNumber=" + shelfNumber +
                '}';
    }
}
