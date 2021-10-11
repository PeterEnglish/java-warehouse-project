package Warehouse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    Floor floor1;
    Floor floor2;
    Floor floor3;
    List<Floor> floorsList;
    @BeforeEach
    void setUp() {
        floor1=new Floor(1, "High", 22.0);
        floor2=new Floor(2, "Medium", 18.0);
        floor3=new Floor(3, "Low",12.0);
        floorsList = new List<>();
        floorsList.addNode(floor1);
        floorsList.addNode(floor2);
        floorsList.addNode(floor3);
    }

    @AfterEach
    void tearDown() {
        floor1 = floor2 = floor3=null;
        floorsList=null;
    }

    @Test
    void addNode() {
        assertEquals(3,floorsList.accessAtIndex(0).getContents().getFloorLevel());
        assertEquals(1,floorsList.accessAtIndex(2).getContents().getFloorLevel());
    }

    @Test
    void removeNode(){
        floorsList.removeNode(0);
        assertEquals(2,floorsList.accessAtIndex(0).getContents().getFloorLevel());
        assertEquals(1,floorsList.accessAtIndex(1).getContents().getFloorLevel());
        assertNull(floorsList.accessAtIndex(-1));
        assertNull(floorsList.accessAtIndex(2));
        System.out.println(floorsList.printList());
    }

    @Test
    void printList() {
        String listString = "";
        for(Floor floor: floorsList){
            listString+=floor.toString()+"\n";
        }
        assertEquals(listString, floorsList.printList());
    }
}