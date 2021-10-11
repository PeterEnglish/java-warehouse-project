package Warehouse;

public class Pallet {
    String contentsDescription;
    int numberOfItems;
    double minTemp;
    double maxTemp;
    int positionX;
    int positionY;


    public Pallet(String contentsDescription, int numberOfItems, double minTemp, double maxTemp, int palletWidthLocation, int palletDepthLocation) {
        this.contentsDescription = contentsDescription;
        this.numberOfItems = numberOfItems;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.positionX=palletDepthLocation;
        this.positionY=palletWidthLocation;
    }
    public String getContentsDescription() {
        return contentsDescription;
    }

    public void setContentsDescription(String contentsDescription) {
        this.contentsDescription = contentsDescription;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    @Override
    public String toString() {
        return "A Pallet with " +
                numberOfItems + " units of " + contentsDescription
                + " with a temperature between: " + minTemp + " and "
                + maxTemp +
                '.';
    }
}
