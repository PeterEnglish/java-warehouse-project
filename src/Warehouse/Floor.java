package Warehouse;

public class Floor {

    //This is where you want to put your list of aisles.
    //You are going to create many floors, and each one contains a list of aisles.



    int floorLevel;
    String securityLevel;
    Double temperature;
    List<Aisle> listOfAisles;

    public Floor(int floorLevel, String securityLevel, Double temperature) {
        this.floorLevel = floorLevel;
        this.securityLevel = securityLevel;
        this.temperature = temperature;
        this.listOfAisles = new List<>();
    }

    public int getFloorLevel() {
        return floorLevel;
    }


    public String getSecurityLevel() {
        return securityLevel;
    }


    public Double getTemperature() {
        return temperature;
    }


    public void addAisle(Aisle aisleA){
        listOfAisles.addNode(aisleA);
    }


    public boolean containsAisle(String id){
        if(listOfAisles.isEmpty()){
            return false;
        }else{
            Node<Aisle> temp = listOfAisles.head;
            while(temp!=null){
                if(temp.getContents().getIdentifier().equals(id)){
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }


    public Aisle returnAisleByIdentifier(String id){
        Node<Aisle> temp = listOfAisles.accessFirst();
        if (!listOfAisles.isEmpty()) {
            while (temp != null) {
                if (temp.getContents().getIdentifier().equals(id)) {
                    return temp.getContents();
                } else {
                    temp = temp.next;
                }
            }
        }
        return null;


    }


    @Override
    public String toString() {
        return "Floor{" +
                "floorLevel=" + floorLevel +
                ", securityLevel='" + securityLevel + '\'' +
                ", temperature=" + temperature +
                '}';
    }


}

