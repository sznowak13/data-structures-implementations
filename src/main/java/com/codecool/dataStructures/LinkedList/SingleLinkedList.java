package com.codecool.dataStructures.LinkedList;

/**
 * Implementation of Single Linked List data structure.
 * Linked List is a form of traversable data structure where every element is pointing to the next one.
 * It's used when we are always interested in iterating through every element and we want to dynamically
 * insert/append new elements to the list.
 *
 * It consists of Nodes, the building blocks of the Linked List - they store reference to the next Node
 * and own value.
 * @see Node
 *
 * @author Szymon Nowak
 * @version 1.0
 */

public class SingleLinkedList<T> {

    /*
    * Reference to the first added node. Its used for starting the
    * iteration through list.
     */
    private Node<T> head;

    /*
    * Reference to the last added node, used to quickly append
    * new elements to the end of list.
     */
    private Node<T> tail;

    /*
    * Current amount of elements.
     */
    private int length;

    /**
     * Returns first element.
     * @see #head
     * @return head
     */
    public T head() {
        return head != null ? head.getValue() : null;
    }

    /**
     * Returns number of elements.
     * @see #length
     * @return int length.
     */
    public int length() {
        return length;
    }

    /**
     * Returns the last element.
     * @see #tail
     * @return tail
     */
    public T tail() {
        return tail.getValue();
    }

    /**
     * Adds new node with value of passed element to the end of the list.
     *
     * It sets the next node of the tail to the new node and then
     * changes tail to this node.
     * If head is empty then new node becomes head and tail.
     * @see Node
     *
     * @param element value of the new Node.
     */
    public void add(T element) {
        if (head == null) {
            head = new Node<>(element);
            tail = head;
        } else {
            tail.setNext(new Node<>(element));
            tail = tail.getNext();
        }
        length++;
    }

    /**
     * Inserts a new node between node at passed index and node before that.
     * If the passed index is not present in the list, throws IndexOutOfBoundsException.
     * That means insert() cannot be used to append new values to the end of the list, as
     * it's not its purpose - use add() instead.
     * @see Node
     * @see #add(Object)
     *
     * @param index index where the new node should be stored at.
     * @param element value of the node
     */
    public void insert(int index, T element) {
        checkIfIndexIsPresent(index);
        if (index == 0) {
            insertNewHead(element);
            return;
        }
        Node<T> nodeToInsert = new Node<>(element);
        Node<T> nodeBefore = head;
        for (int i = 0; i < index - 1; i++) {
            nodeBefore = nodeBefore.getNext();
        }
        nodeToInsert.setNext(nodeBefore.getNext());
        nodeBefore.setNext(nodeToInsert);
        length++;
    }

    /**
     * Inserts new node before head and sets it as a new head, so:
     * @code [1, 2, 3, 4] insert(0, 9) -> [9, 1, 2, 3, 4]
     *
     * @see #insert(int, Object)
     * @see #head
     *
     * @param element value to be new head
     */
    private void insertNewHead(T element) {
        Node<T> newHead = new Node<>(element);
        newHead.setNext(head);
        head = newHead;
    }

    /**
     * Inserts a new node into list just like insert(), but allows appending new values
     * to the end of the list if passed index is not present in the list.
     * Can be used in places where you cannot define if the list has enough indices to insert between.
     * Keep in mind that it will use add() in that situation.
     * @see #add(Object)
     * @see #insert(int, Object)
     * @see Node
     *
     * @param index index where the new node should be stored at.
     * @param element value of the node
     */
    public void forceInsert(int index, T element) {
        if (index >= length) {
            add(element);
        } else {
            insert(index, element);
        }
    }


    /**
     * Returns value of the node at specified index.
     * Throws IndexOutOfBoundsException when index is not present in the list.
     *
     * @param index index of the searched node
     * @return value of found node
     */
    public T get(int index) throws IndexOutOfBoundsException {
        checkIfIndexIsPresent(index);
        Node<T> current = head;
        for (int j = 0; j < index; j++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    /**
     * Returns the index of the specified element from the list.
     * If the item is not present in the list returns -1.
     *
     * @param searched element to be searched for in the list
     * @return index of the element or -1 when not found
     */
    public int search(T searched) {
        Node<T> current = head;
        int index = 0;
        while (!searched.equals(current.getValue())) {
            current = current.getNext();
            index++;
            if (current == null) {
                return -1;
            }
        }
        return index;
    }

    /**
     * Returns String representation of the List in form of:
     * [e1, e2, e3, e4]
     * where eX is value of the node at every index.
     *
     * @return string representation of the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node el = head;
        while (el != null) {
            String toAppend =
                    el.getValue() instanceof String ?
            "\"" + el.getValue().toString() + "\"" : el.getValue().toString();
            sb.append(toAppend);
            el = el.getNext();
            if (el != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Removes element at specified index, rearranges the list accordingly
     * and reduces its length.
     * Throws IndexOutOfBounds when index is not present.
     *
     * @param index index of an element to be removed
     */
    public void remove(int index) {
        checkIfIndexIsPresent(index);
        if (index == 0) {
            removeHead();
            return;
        }
        Node<T> previous = head;
        Node<T> toDelete;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.getNext();
        }
        toDelete = previous.getNext();
        Node<T> next = toDelete.getNext();
        previous.setNext(next);
        toDelete.setNext(null);
        length--;
    }

    private void removeHead() {
        head = head.getNext();
        length--;
    }

    private void checkIfIndexIsPresent(int index) throws IndexOutOfBoundsException {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("No such index");
        }
    }
}
