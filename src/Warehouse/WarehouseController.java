package Warehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.scene.layout.HBox;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.util.Random;


public class WarehouseController {





    public List<Floor> floorsList = new List<>();
    public Floor currFloor;
    public Aisle currAisle;
    public Shelf currShelf;

    @FXML
    HBox radioHBox;
    @FXML
    RadioButton radioHigh, radioMed, radioLow, smartRadioLow, smartRadioMed, smartRadioHigh;
    @FXML
    TableView<Floor> floorTable;
    @FXML
    TableView<Aisle>aisleTable;
    @FXML
    TableView<Shelf>shelfTable;
    @FXML
    TableView<Pallet>palletTable;
    @FXML
    TableView<Pallet> allStockTable;
    @FXML
    TableView<SearchedGoods>searchTable;
    @FXML
    TextField textFNumber, textTemperature;
    @FXML
    TextField textAisleWidth, textAisleDepth;
    @FXML
    TextField textShelfNumber;
    @FXML
    TextField textDescription, textQuantity, textMinTemp, textMaxTemp;
    @FXML
    TextField searchedTextField, smartAddDescription, smartAddQuantity, smartAddMinTemp, smartAddMaxTemp, smartAddSecurityLevel;
    @FXML
    TableColumn<Floor, String> securityLevel, floorNumber, temperature;
    @FXML
    TableColumn<Aisle, String> aisleIdentifier, aisleWidth, aisleDepth;
    @FXML
    TableColumn<Shelf, String> shelfNumber;
    @FXML
    TableColumn<Pallet, String> maxTemp, minTemp, quantity, description, goods, quantityOfGoods;
    @FXML
    TableColumn<SearchedGoods, String> searchedDescription, searchedQuantity, searchedFloorNumber, searchedAisleIdentifier, searchedShelfNumber;
    @FXML
    Label warningLabel1, warningLabel2, warningLabel3, smartAddWarning;


    ObservableList<Floor> myObsList = FXCollections.observableArrayList();
    ObservableList<Aisle> myObsList2 = FXCollections.observableArrayList();
    ObservableList<Shelf> myObsList3 = FXCollections.observableArrayList();
    ObservableList<Pallet> myObsList4 = FXCollections.observableArrayList();
    ObservableList<Pallet> myObsList5 = FXCollections.observableArrayList();
    ObservableList<SearchedGoods> myObsList6 = FXCollections.observableArrayList();

    @FXML
    TabPane tabPane;
    @FXML
    Tab aisleTab, shelfTab, palletTab, allStockTab;
    ToggleGroup g = new ToggleGroup();
    ToggleGroup g0 = new ToggleGroup();


    public String generateAisleID(){
        String ID;

        Random rand = new Random();
        int intPart = rand.nextInt(10);
        Random rand2 = new Random();
        int stringIndex = rand2.nextInt(26);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char charPart = alpha.charAt(stringIndex);
        String returnValue = String.valueOf(charPart) + intPart;
        for(Aisle aisle: currFloor.listOfAisles){
            if (aisle.getIdentifier().equals(returnValue)){
                generateAisleID();
            }
        }
        return returnValue;
    }

    public void updateWarnings(String warning){
        warningLabel1.setText(warning);
        warningLabel2.setText(warning);
        warningLabel3.setText(warning);
    }

    public void updateWarning(Label label, String warning){
        label.setText(warning);
    }


    //-------------------Floor Methods----------------------
    /**
     *
     */


    public void updateFloorsTable(){
        myObsList.clear();
        floorNumber.setCellValueFactory(new PropertyValueFactory<Floor,String >("floorLevel"));
        securityLevel.setCellValueFactory(new PropertyValueFactory<Floor, String>("securityLevel"));
        temperature.setCellValueFactory(new PropertyValueFactory<Floor, String>("temperature"));
        for(Floor floor: floorsList){
            myObsList.add(floor);
        }
        System.out.println(floorsList.printList());
        floorTable.setItems(myObsList.sorted());
    }


    /**
     *
     */
    public void addFloor()  {
        try{
        int floor = Integer.parseInt(textFNumber.getText());
        String securityLevel;
        double temp = Double.parseDouble(textTemperature.getText());

        Toggle selectedToggle = g.getSelectedToggle();
        if (radioLow.equals(selectedToggle)) {
            securityLevel = "Low";
        } else if (radioMed.equals(selectedToggle)) {
            securityLevel = "Medium";
        } else if (radioHigh.equals(selectedToggle)) {
            securityLevel = "High";
        } else {
            securityLevel = "Low";
        }



        /*switch(g.getSelectedToggle()){
            case radioLow:
                securityLevel="Low";
                break;
            case radioMed:
                securityLevel="Medium";
                break;
            case radioHigh:
                securityLevel="High";
                break;
            default:
                securityLevel="Low";
        }Don't know why this isn't working*/

        Floor myFloor = new Floor(floor,securityLevel,temp);
        floorsList.addNode(myFloor);
        updateFloorsTable();
        textFNumber.clear();
        textTemperature.clear();
        }catch(Exception e){
            System.out.println("You need to input the appropriate values into the boxes!");
        }
    }


    /**
     *
     */



    /**
     *
     */
    public void removeFloor() {
        try {
            int selectFloorLevel = floorTable.getSelectionModel().getSelectedItem().getFloorLevel();
            for (int i = 0; i < floorsList.length(); i++) {
                if (floorsList.accessAtIndex(i).getContents().getFloorLevel() == selectFloorLevel) {
                    floorsList.removeNode(i); //this line isn't working properly
                    System.out.println();
                    System.out.println("Removed Floor at index" + i);
                    updateFloorsTable();
                    System.out.println ("Removed floor at index" + i);
                }
            }
            System.out.println("There was no floor at matching that name! ");
            System.out.println ("There was no floor matching that Number!");
        }catch(Exception e){
            System.out.println("You have not selected a Floor!");
        }
    }

    /**
     *
     */
    public void selectFloor(){
        currFloor = floorTable.getSelectionModel().getSelectedItem();
        System.out.println("Current Floor is: " + currFloor);
        updateAisleTable();
        updateShelfTable();
        updatePalletTable();
        updateWarnings( "You have selected floor: " + currFloor.getFloorLevel());
    }

    public void floorClicked(){
            updateFloorsTable();
            radioLow.setToggleGroup(g);
            radioHigh.setToggleGroup(g);
            radioMed.setToggleGroup(g);
    }



    //-------------------Aisle Methods----------------------


    /**
     *
     */
    public void updateAisleTable(){
        myObsList2.clear();
        aisleIdentifier.setCellValueFactory(new PropertyValueFactory<Aisle, String>("identifier"));
        aisleWidth.setCellValueFactory(new PropertyValueFactory<Aisle, String>("aisleWidth"));
        aisleDepth.setCellValueFactory(new PropertyValueFactory<Aisle, String>("aisleDepth"));
        if(currFloor!=null) {
            for (Aisle aisle : currFloor.listOfAisles) {
                myObsList2.add(aisle);
            }
            System.out.println(currFloor.listOfAisles.printList());
        }
        aisleTable.setItems(myObsList2);

    }

    /**
     *
     */
    public void addAisle() throws Exception {
        try {
            int aisleWidth = Integer.parseInt(textAisleWidth.getText());
            int aisleDepth = Integer.parseInt(textAisleDepth.getText());
            Aisle myAisle = new Aisle(generateAisleID(), aisleWidth, aisleDepth);
            currFloor.addAisle(myAisle);
            updateAisleTable();
            textAisleWidth.clear();
            textAisleDepth.clear();

        }catch(Exception e){
            System.out.println("You have not picked a Floor, or have incorrectly entered the Values!");
        }
    }


    /**
     *
     */
    public void removeAisle() {
        try {
            String aisleIdentifier = aisleTable.getSelectionModel().getSelectedItem().getIdentifier();
            List<Aisle> aisles = currFloor.listOfAisles;
            for (int i = 0; i < aisles.length(); i++) {
                if (aisles.accessAtIndex(i).getContents().getIdentifier() == aisleIdentifier) {
                    aisles.removeNode(i); //this line isn't working properly
                    System.out.println("Removed Aisle at index" + i);
                    updateAisleTable();
                    System.out.println ("Removed Aisle at index" + i);

                }

            }
            System.out.println("There was no Aisle at matching that name! ");
            System.out.println("There was no Aisle matching that Number!");
        } catch (Exception e) {
            System.out.println("You have not chosen an aisle!");
        }
    }



    /**
     *
     */
    public void selectAisle(){

        currAisle = aisleTable.getSelectionModel().getSelectedItem();
        System.out.println("Current Aisle is: " + currAisle);
        updateShelfTable();
        updatePalletTable();
        updateWarning(warningLabel2, "You have selected Aisle: "+ currAisle.getIdentifier() + " on Floor: " + currFloor.getFloorLevel());
    }


    /**
     *
     */
    public void aisleClicked(){
        if (currFloor!=null){
            updateAisleTable();
        }else{
            updateWarning(warningLabel1, "You have not selected a Floor!");
            updateWarning(warningLabel2, "You have not selected an Aisle!");
            updateWarning(warningLabel3, "You have not selected a Shelf!");
        }
    }



    //-------------------Shelf Methods----------------------

    public void shelfClicked(){
        if (shelfTab.isSelected()&&currAisle!=null) {
            updateShelfTable();
            updateWarning(warningLabel3, "You have selected Aisle" + currAisle.getIdentifier());
        }else{
            updateWarning(warningLabel1, "");
            updateWarning(warningLabel2, "You have not selected an Aisle!");
            updateWarning(warningLabel3, "You have not selected a Shelf!");
        }
    }

    /**
     *
     */
    public void updateShelfTable(){
        myObsList3.clear();
        shelfNumber.setCellValueFactory(new PropertyValueFactory<Shelf, String>("shelfNumber"));

        if(currAisle!=null) {
            for (Shelf shelf : currAisle.listOfShelves) {
                myObsList3.add(shelf);
            }
            System.out.println(currAisle.listOfShelves.printList());
        }

        shelfTable.setItems(myObsList3);

    }


    /**
     *
     */
    public void addShelf() throws Exception {
        try {
            int aisleCapacity = currAisle.getAisleArea();
            int shelfNumber = Integer.parseInt(textShelfNumber.getText());
            Shelf myShelf = new Shelf(shelfNumber,currAisle.getAisleWidth(),currAisle.getAisleDepth(), aisleCapacity);
            currFloor.returnAisleByIdentifier(currAisle.getIdentifier()).addShelf(myShelf);
            //can above line be reduced to currAisle.getIdentifier?
            updateShelfTable();
            textShelfNumber.clear();


        }catch(Exception e){
            System.out.println("You have not selected an Aisle, or have entered values in the appropriate boxes!");
        }
    }


    /**
     *
     */
    public void removeShelf() {
        try {
            int shelfNumber = shelfTable.getSelectionModel().getSelectedItem().getShelfNumber();
            List<Shelf> shelves = currAisle.listOfShelves;
            for (int i = 0; i <= shelves.length(); i++) {
                if (shelves.accessAtIndex(i).getContents().getShelfNumber() == shelfNumber) {
                    shelves.removeNode(i); //this line isn't working properly
                    System.out.println("Removed shelf at index" + i);
                    updateShelfTable();
                    System.out.println ("Removed Shelf at index" + i);
                }

            }
            System.out.println ("There was no Shelf matching that Number!");
        }catch(Exception e){
            System.out.println("You have not selected a shelf to delete!");
        }
    }

    public void selectShelf(){
        currShelf = shelfTable.getSelectionModel().getSelectedItem();
        System.out.println("Current Shelf is: " + currShelf);
    }

    //-------------------Pallet Methods----------------------

    public void addPallet() throws Exception {
        try {
            String description = textDescription.getText();
            int quantity = Integer.parseInt(textQuantity.getText());
            double minTemp = Double.parseDouble(textMinTemp.getText());
            double maxTemp = Double.parseDouble(textMaxTemp.getText());
            int palletWidth = currShelf.shelfWidth-currShelf.getPalletCount()/2;
                Pallet pallet = new Pallet(description, quantity, minTemp, maxTemp,currAisle.getAisleWidth(),currAisle.getAisleDepth());
                if(minTemp<currFloor.getTemperature()&&maxTemp>currFloor.getTemperature()){
                    currShelf.addPallet(pallet);
                    currShelf.incrementPalletCount();
                    updatePalletTable();
                    textDescription.clear();
                    textQuantity.clear();
                    textMinTemp.clear();
                    textMaxTemp.clear();
                }else{
                    updateWarning(warningLabel3, "Current Floor does not have a suitable temperature: Change temperature or change current floor.");
                }


        }catch(Exception e){
            System.out.println("You have not selected a Shelf, or have not entered the appropriate values!");
        }
    }

    public void updatePalletTable(){
        myObsList4.clear();
        quantity.setCellValueFactory(new PropertyValueFactory<Pallet, String>("numberOfItems"));
        description.setCellValueFactory(new PropertyValueFactory<Pallet, String>("contentsDescription"));
        minTemp.setCellValueFactory(new PropertyValueFactory<Pallet, String>("minTemp"));
        maxTemp.setCellValueFactory(new PropertyValueFactory<Pallet, String>("maxTemp"));


        if (currShelf!=null) {
            for (Pallet pallet : currShelf.listOfPallets) {
                myObsList4.add(pallet);
            }
            System.out.println(currShelf.listOfPallets.printList());
        }

        palletTable.setItems(myObsList4);

    }

    public void palletClicked(){
        if (palletTab.isSelected()&&currShelf!=null){
            updateWarning(warningLabel3, "You have selected Shelf: " + currShelf.getShelfNumber());
            updatePalletTable();

        }else{
            updateWarning(warningLabel1, "");
            updateWarning(warningLabel2, "");
            updateWarning(warningLabel3, "You have not selected a Shelf!");
        }
    }

    public void removePallet() {
        try {
            String pallet = palletTable.getSelectionModel().getSelectedItem().getContentsDescription();
            List<Pallet> pallets = currShelf.listOfPallets;
            for (int i = 0; i <= pallets.length(); i++) {
                if (pallets.accessAtIndex(i).getContents().getContentsDescription() == pallet) {
                    pallets.removeNode(i); //this line isn't working properly
                    currShelf.decrementPalletCount();
                    System.out.println("Removed pallet at index" + i);
                    updatePalletTable();
                }

            }
            System.out.println("There was no Pallet matching that Description! ");
        }catch(Exception e){
            System.out.println("You have not selected a Pallet to Delete!");
        }

    }

    //-------------------View All Stock Methods----------------------

    public void allStockClicked(){
            updateAllPalletTable();
    }


    public void updateAllPalletTable(){
        myObsList5.clear();

        goods.setCellValueFactory(new PropertyValueFactory<Pallet, String>("contentsDescription"));
        quantityOfGoods.setCellValueFactory(new PropertyValueFactory<Pallet, String>("numberOfItems"));



        for(Floor floor: floorsList){
            for(Aisle aisle: floor.listOfAisles){
                for(Shelf shelf: aisle.listOfShelves){
                    for(Pallet pallet: shelf.listOfPallets){
                        myObsList5.add(pallet);
                        System.out.println(pallet);
                    }
                }
            }
        }

        allStockTable.setItems(myObsList5);

    }

    //-------------------Search Methods----------------------


    public void searchForGoods(){
        myObsList6.clear();

        String description = searchedTextField.getText();
        searchedDescription.setCellValueFactory(new PropertyValueFactory<SearchedGoods, String>("description"));
        searchedQuantity.setCellValueFactory(new PropertyValueFactory<SearchedGoods, String>("quantity"));
        searchedFloorNumber.setCellValueFactory(new PropertyValueFactory<SearchedGoods, String>("floorNumber"));
        searchedAisleIdentifier.setCellValueFactory(new PropertyValueFactory<SearchedGoods, String>("aisleIdentifier"));
        searchedShelfNumber.setCellValueFactory(new PropertyValueFactory<SearchedGoods, String>("shelfNumber"));

        for(Floor floor: floorsList){
            for(Aisle aisle: floor.listOfAisles){
                for(Shelf shelf: aisle.listOfShelves){
                    for(Pallet pallet: shelf.listOfPallets){
                        if (pallet.getContentsDescription().equals(description)) {
                            SearchedGoods mySearchedGoods = new SearchedGoods(pallet.getContentsDescription(), pallet.getNumberOfItems(), floor.getFloorLevel(), aisle.getIdentifier(), shelf.getShelfNumber());
                            myObsList6.add(mySearchedGoods);
                            System.out.println(mySearchedGoods);
                        }
                    }
                }
            }
        }

        searchTable.setItems(myObsList6);

    }

    //------------------------Smart Add---------------------------

    public void smartAddClicked(){

        smartRadioLow.setToggleGroup(g0);
        smartRadioHigh.setToggleGroup(g0);
        smartRadioMed.setToggleGroup(g0);
    }

    public void smartAdd(){

        String smartDescription = smartAddDescription.getText();
        int smartQuantity = Integer.parseInt(smartAddQuantity.getText());
        double smartMin = Double.parseDouble(smartAddMinTemp.getText());
        double smartMax = Double.parseDouble(smartAddMaxTemp.getText());
        String smartSecurity;
        Toggle selectedToggle = g0.getSelectedToggle();
        if (smartRadioLow.equals(selectedToggle)) {
            smartSecurity = "Low";
        } else if (smartRadioMed.equals(selectedToggle)) {
            smartSecurity = "Medium";
        } else if (smartRadioHigh.equals(selectedToggle)) {
            smartSecurity = "High";
        } else {
            smartSecurity = "Low";
        }


        if(floorsList.isEmpty()) {
            smartAddWarning.setText("There are no Floors!");
            return;
        }else{

            for (Floor floor : floorsList) {
                if (floor.getSecurityLevel().equals(smartSecurity)
                        && smartMin <= floor.getTemperature()
                        && smartMax >= floor.getTemperature()) {
                    if (!floor.listOfAisles.isEmpty()) {
                        for (Aisle aisle : floor.listOfAisles) {
                            if (!aisle.listOfShelves.isEmpty()) {
                                for (Shelf shelf : aisle.listOfShelves) {
                                    if (shelf.getMaximumNumberOfPallets() > shelf.listOfPallets.length()) {
                                        Pallet pallet = new Pallet(smartDescription, smartQuantity, smartMin, smartMax, aisle.getAisleWidth(), aisle.getAisleDepth());
                                        shelf.addPallet(pallet);
                                        smartAddWarning.setText("Pallet was added to Shelf: " + shelf.getShelfNumber()
                                                + " on Aisle: " + aisle.getIdentifier()
                                                + " of Floor: " + floor.getFloorLevel());
                                        smartAddDescription.clear();
                                        smartAddQuantity.clear();
                                        smartAddMinTemp.clear();
                                        smartAddMaxTemp.clear();
                                        return;
                                    } else {
                                        smartAddWarning.setText("There is no aisle with enough shelf space!!");
                                        return;
                                    }
                                }
                            } else {
                                smartAddWarning.setText("There are no Available Shelves!");
                                return;
                            }
                        }
                    } else smartAddWarning.setText("There are no available Aisles!");
                    return;
                } else smartAddWarning.setText("There are no Suitable Floors to accommodate these goods!");
                return;
            }
        }
    }//NB HAVE IT UPDATE A TABLE WITH THE STORAGE DETAILS LIKE THE SEARCH TABLE!!





    //-------------------Save and Load Methods----------------------

    /**
     *
     */
    public void closeAll(){
        System.exit(0);
    }



    /**
     *loads the data from xml
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        try {
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("warehouse.xml"));
            floorsList = (List<Floor>) is.readObject();
            System.out.println(floorsList.printList());
            is.close();
            }catch(Exception e){
            System.out.println("There are no Floors in the Loaded Data!");
        }
    }


    /**
     *Saves the data to xml
     */
    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("warehouse.xml"));
        out.writeObject(floorsList);
        out.close();
        System.out.println("File has been saved");
    }

        public void reset(){
            floorsList.emptyList();
        }

    


}
