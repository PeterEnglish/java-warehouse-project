package Warehouse;

import java.util.Iterator;

public class    myIterator<K> implements Iterator<K> {

    private Node<K> pos;

    public myIterator(Node<K> node){pos=node;}

    @Override
    public boolean hasNext() {
        return pos!=null;
    }

    @Override
    public K next() {
        Node<K> temp = pos;
        pos = pos.next;
        return temp.getContents();
    }
}
