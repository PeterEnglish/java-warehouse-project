package Warehouse;


public class Shelf{
    public int shelfNumber;
    public List<Pallet> listOfPallets;
    public int shelfWidth;
    public int shelfDepth;
    public int maximumNumberOfPallets;
    private int palletCount;


    public Shelf(int shelfNumber, int shelfWidth, int shelfDepth, int maximumNumberOfPallets){

        this.shelfNumber  = shelfNumber;
        this.listOfPallets = new List<Pallet>();
        this.shelfDepth=shelfDepth;
        this.shelfWidth=shelfWidth;
        this.maximumNumberOfPallets = maximumNumberOfPallets;
        this.palletCount=0;

    }

    public int getMaximumNumberOfPallets() {
        return maximumNumberOfPallets;
    }

    @Override
    public String toString() {
        return "Shelf:" +
                shelfNumber + " has a max capacity of " + maximumNumberOfPallets + " Pallets";
    }
    public void incrementPalletCount(){palletCount++;}
    public void decrementPalletCount(){palletCount--;}

    public int getPalletCount() {
        return palletCount;
    }
    public int getShelfNumber() {
        return shelfNumber;
    }

    public void addPallet(Pallet pallet){
        listOfPallets.addNode(pallet);
    }


}
