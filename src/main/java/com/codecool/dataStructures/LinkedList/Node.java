package com.codecool.dataStructures.LinkedList;

/**
 * Nodes are what the Linked List uses to store and find values.
 * They got reference to the next Node (or null, if they are the last Node) and holds the value of the
 * stored element.
 *
 * @see SingleLinkedList
 */
class Node<T> {

    private Node<T> next;
    private T value;

    Node(T value) {
        this.value = value;
    }

    Node<T> getNext() {
        return next;
    }

    void setNext(Node<T> next) {
        this.next = next;
    }

    T getValue() {
        return value;
    }
}
