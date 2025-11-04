import entities.Book;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookUtilsSortedInsertTest {
    /**
     * Confirm method can correctly handle non-existent (null) arrays
     */
    @Test
    void sortedInsert_NullArray() {
        Book[] books = null;
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        assertThrows(IllegalArgumentException.class,
            () -> {
                BookUtils.sortedInsert(books, b0);
            }, "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle inserting in sorted order in empty arrays
     */
    @Test
    void sortedInsert_EmptyArray() {
        Book[] books = {};
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");

        Book result = BookUtils.sortedInsert(books, b0);
        assertNull(result, "Incorrect result when doing sorted insert into an empty array");
    }

    /**
     * Confirm method can correctly handle attempting to insert a non-existent (null) book into a populated array
     */
    @Test
    void sortedInsert_ExistingArray_NullBook() {

        Book b0 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b1 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b2 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book[] books = {b0, b1, b2};

        assertThrows(IllegalArgumentException.class,
                () -> {
                    BookUtils.sortedInsert(books, null);
                }, "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle attempting to insert an existing book in sorted order to the start of a
     * populated array (no nulls)
     */
    @Test
    void sortedInsert_ExistingArray_RealBook_InsertAtStart() {
        Book b0 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b1 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b2 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book b3 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");

        Book[] books = {b0, b1, b2, b3, b4};

        Book toBeInserted = new Book("JF0KWP1JS4", "A Christmas Carol", "Charles Dickens");
        Book [] expUpdated = {toBeInserted, b0, b1, b2, b3};
        Book expLoss = b4;

        Book result = BookUtils.sortedInsert(books, toBeInserted);
        // Confirm result
        assertEquals(expLoss, result, "Could not perform a sorted insert into start of array");

        // Confirm shift and positions of all data
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to insert an existing book in sorted order into the middle of a
     * populated array (no nulls)
     */
    @Test
    void sortedInsert_ExistingArray_RealBook_InsertAtMiddle() {
        Book b0 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b1 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b2 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book b3 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book[] books = {b0, b1, b2, b3, b4};

        Book toBeInserted = new Book("JF0KWP1JS4", "Breaking Dawn", "Ridiculousity Cullen");
        Book [] expUpdated = {b0, b1, toBeInserted, b2, b3};
        Book expLoss = b4;

        Book result = BookUtils.sortedInsert(books, toBeInserted);
        // Confirm result
        assertEquals(expLoss, result, "Could not perform a sorted insert into middle of array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to insert an existing book in sorted order into the end of a
     * populated array (no nulls)
     */
    @Test
    void sortedInsert_ExistingArray_RealBook_InsertAtEnd() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book b2 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book[] books = {b0, b1, b2};

        Book toBeInserted = new Book("JF0KWP1JS4", "Hunger Games", "Katarina Mellark");
        Book [] expUpdated = {b0, b1, toBeInserted};
        Book expLoss = b2;

        Book result = BookUtils.sortedInsert(books, toBeInserted);
        // Confirm result
        assertEquals(expLoss, result, "Could not perform a sorted insert into end of array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }


    /**
     * Confirm method can correctly handle attempting to insert an existing book with title matching a book already
     * in the array and author coming BEFORE that book's author
     */
    @Test
    void sortedInsert_ExistingArray_MatchingTitle_NewAuthorComesFirst() {
        Book b0 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b1 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b2 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book b3 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");

        Book[] books = {b0, b1, b2, b3, b4};

        Book toBeInserted = new Book("JF0KWP1JS4", "Charles Bronte", "Dickens Philips");
        Book [] expUpdated = {b0, b1, toBeInserted, b2, b3};
        Book expLoss = b4;

        Book result = BookUtils.sortedInsert(books, toBeInserted);
        // Confirm result
        assertEquals(expLoss, result, "Could not perform a sorted insert into array where titles match and new author" +
                " comes first");

        // Confirm shift and positions of all data
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to insert an existing book with title matching a book already
     * in the array and author coming AFTER that book's author
     */
    @Test
    void sortedInsert_ExistingArray_MatchingTitle_NewAuthorComesLast() {
        Book b0 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b1 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b2 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book b3 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");

        Book[] books = {b0, b1, b2, b3, b4};

        Book toBeInserted = new Book("JF0KWP1JS4", "Charles Bronte", "Philips Dickens");
        Book [] expUpdated = {b0, b1, b2, toBeInserted, b3};
        Book expLoss = b4;

        Book result = BookUtils.sortedInsert(books, toBeInserted);
        // Confirm result
        assertEquals(expLoss, result, "Could not perform a sorted insert into array where titles match and new author" +
                " comes second");

        // Confirm shift and positions of all data
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to insert an existing book in sorted order into an unsorted
     * populated array where book should not be added.
     */
    @Test
    void sortedInsert_ExistingArray_UnsortedArray_NoBookAdded() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book b2 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        // Add data in unsorted order
        Book[] books = {b2, b1, b0};

        Book toBeInserted = new Book("JF0KWP1JS4", "Jumping Jeannie Jones", "Melina Humphreys");
        Book [] expUpdated = {b2, b1, b0};
        Book [] sortedUpdated = Arrays.copyOf(expUpdated, expUpdated.length);
        Arrays.sort(sortedUpdated);

        Book result = BookUtils.sortedInsert(books, toBeInserted);
        // Confirm result
        assertNull(result, "Could not perform a sorted insert into an unsorted array where the book should not be added");

        // Confirm array was not sorted before insert
        assertFalse(Arrays.equals(expUpdated, sortedUpdated), "Current logic cannot handle performing action on " +
                "unsorted array - array must not be sorted within the sortedInsert() action");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to insert an existing book in sorted order into an unsorted
     * populated array where book should be added.
     */
    @Test
    void sortedInsert_ExistingArray_UnsortedArray_BookAdded() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book b2 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        // Add data in unsorted order
        Book[] books = {b2, b1, b0};

        Book toBeInserted = new Book("JF0KWP1JS4", "Hunger Games", "Katarina Mellark");
        Book [] expUpdated = {toBeInserted, b2, b1};
        Book [] sortedUpdated = Arrays.copyOf(expUpdated, expUpdated.length);
        Arrays.sort(sortedUpdated);
        Book expLoss = b0;

        Book result = BookUtils.sortedInsert(books, toBeInserted);
        // Confirm result
        assertEquals(expLoss, result, "Could not perform a sorted insert into an unsorted array where the book should" +
                " be added (even though it will be in the wrong logical order)");

        // Confirm array was not sorted before insert
        assertFalse(Arrays.equals(expUpdated, sortedUpdated), "Current logic cannot handle performing action on " +
                "unsorted array - array must not be sorted within the sortedInsert() action");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to insert an existing book that belongs after the end of a
     * populated array (no nulls)
     */
    @Test
    void sortedInsert_ExistingArray_RealBook_InsertAfterEnd() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book b2 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book[] books = {b0, b1, b2};

        Book toBeInserted = new Book("JF0KWP1JS4", "The Hunger Games", "Katarina Mellark");
        Book [] expUpdated = {b0, b1, b2};

        Book result = BookUtils.sortedInsert(books, toBeInserted);
        // Confirm result
        assertNull(result, "Could not handle not inserting a value that belongs after end of array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to insert a book in sorted order into a
     * populated array containing null slots
     */
    @Test
    void sortedInsert_ExistingArrayContainingNullSlot_RealBook() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book[] books = {null, b0, b1};


        Book toBeInserted = new Book("JF0KWP1JS4", "A Christmas Carol", "Charles Dickens");
        Book [] endResult = {null, toBeInserted, b0};
        Book expLoss = b1;

        Book result = BookUtils.sortedInsert(books, toBeInserted);
        // Confirm result
        assertEquals(expLoss, result, "Failed to complete processing of array containing a null - encountering a " +
                "null slot should not cause method to fail");
        // Confirm array was appropriately changed
        for (int i = 0; i < endResult.length; i++) {
            assertEquals(endResult[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }


}