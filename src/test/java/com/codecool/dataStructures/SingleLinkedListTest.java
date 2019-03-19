package com.codecool.dataStructures;

import com.codecool.dataStructures.LinkedList.SingleLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTest {

    private SingleLinkedList<String> ll = new SingleLinkedList<>();

    @Test
    void startsWithANullHead() {
        assertNull(ll.head());
    }

    @Test
    void headPointsToTheFirstAddedElement() {
        String expected = "Test";
        ll.add(expected);
        assertEquals(expected, ll.head());
        ll.add("Test2");
        assertEquals(expected, ll.head());
    }

    @Test
    void tailPointsToTheLastAddedElement() {
        ll.add("2");
        ll.add("4");
        ll.add("8");
        ll.add("5");
        assertEquals("5", ll.tail());
        ll.add("7");
        assertEquals("7", ll.tail());
    }

    @Test
    void getsItemsByIndex() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        assertEquals("3", ll.get(3));
    }

    @Test
    void throwsExceptionWhenTryingToReachIndexOutOfBounds() {
        ll.add("1");
        ll.add("2");
        // we have two indices 0 and 1.
        assertThrows(IndexOutOfBoundsException.class,
                () -> ll.get(2));
    }

    @Test
    void hasCorrectLengthAfterAdd() {
        ll.add("1");
        assertEquals(1, ll.length());
    }

    @Test
    void insertsNewObjectsToCorrectIndex() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        ll.insert(3, "100");
        assertEquals("100", ll.get(3));
        ll.insert(3, "99");
        assertEquals("99", ll.get(3));
    }

    @Test
    void insertExpandsListCorrectly() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        assertEquals("3", ll.get(3));
        ll.insert(3, "44");
        assertEquals("3", ll.get(4));
        assertEquals("44", ll.get(3));
    }

    @Test
    void insertingToEmptyListThrowsException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> ll.insert(0, "test"));
    }

    @Test
    void insertingToNonExistingIndexThrowsException() {
        ll.add("A");
        ll.add("B");
        assertThrows(IndexOutOfBoundsException.class,
                () -> ll.insert(5, "test"));
    }

    @Test
    void insertingToTheFirstIndexWorks() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        ll.insert(0, "test");
        assertEquals("test", ll.get(0));
        assertEquals("0", ll.get(1));

    }

    @Test
    void forceInsertingNonExistingIndexAddsToTheEnd() {
        ll.forceInsert(0, "test");
        assertEquals("test", ll.get(0));
        ll.forceInsert(100, "test2");
        assertEquals("test2", ll.get(1));
    }

    @Test
    void hasCorrectLenAfterInsert() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        ll.insert(2, "B");
        assertEquals(6, ll.length());
    }

    @Test
    void searchReturnsCorrectIndexOfItem() {
        for (int i = 0; i < 10; i++) {
            ll.add(String.valueOf(i));
        }
        ll.insert(4, "searched");
        assertEquals(4, ll.search("searched"));
    }

    @Test
    void searchReturnsNegativeWhenItemIsNotFound() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        assertEquals(-1, ll.search("test"));
    }

    @Test
    void hasReadableStringRepresentation() {
        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }
        assertEquals("[0, 1, 2, 3, 4]", linkedList.toString());
    }

    @Test
    void representsStringsProperly() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        assertEquals("[\"0\", \"1\", \"2\", \"3\", \"4\"]", ll.toString());
    }

    @Test
    void removesElementsByIndex() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        ll.remove(3);
        assertEquals("4", ll.get(3));
        assertEquals("[\"0\", \"1\", \"2\", \"4\"]", ll.toString());
    }

    @Test
    void removesLastIndexProperly() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        ll.remove(4);
        assertEquals("[\"0\", \"1\", \"2\", \"3\"]", ll.toString());
        assertThrows(IndexOutOfBoundsException.class,
                () -> ll.get(4));
    }

    @Test
    void removesFirstIndexProperly() {
        for (int i = 0; i < 5; i++) {
            ll.add(String.valueOf(i));
        }
        ll.remove(0);
        assertEquals("[\"1\", \"2\", \"3\", \"4\"]", ll.toString());
        assertEquals("1", ll.get(0));
        assertThrows(IndexOutOfBoundsException.class,
                () -> ll.get(4));
    }
}