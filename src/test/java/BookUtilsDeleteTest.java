import entities.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookUtilsDeleteTest {
    /**
     * Confirm method can correctly handle non-existent (null) arrays
     */
    @Test
    void delete_NullArray() {
        Book[] books = null;
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        assertThrows(IllegalArgumentException.class,
            () -> {
                BookUtils.delete(books, b0);
            }, "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle deleting from empty arrays
     */
    @Test
    void delete_EmptyArray() {
        Book[] books = {};
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");

        boolean result = BookUtils.delete(books, b0);
        assertFalse(result, "Incorrect result when deleting from an empty array");
    }

    /**
     * Confirm method can correctly handle attempting to remove a non-existent (null) book from a populated array
     */
    @Test
    void delete_ExistingArray_NullBook() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book[] books = {b0, b1, b2};

        assertThrows(IllegalArgumentException.class,
                () -> {
                    BookUtils.delete(books, null);
                }, "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle attempting to remove an existing book from the start of a populated array (no
     * nulls)
     */
    @Test
    void delete_ExistingArray_RealBook_Present_Start() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b2, b3, b4};
        Book [] expUpdated = {b1, b2, b3, b4};

        boolean result = BookUtils.delete(books, b0);
        // Confirm result
        assertTrue(result, "Could not delete a match from start of array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final position
        assertNull(books[books.length-1], "Final slot in array was not blanked");
    }

    /**
     * Confirm method can correctly handle attempting to remove an existing book from the middle of a populated array
     * (no nulls)
     */
    @Test
    void delete_ExistingArray_RealBook_Present_Middle() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b2, b3, b4};
        Book [] expUpdated = {b0, b2, b3, b4};

        boolean result = BookUtils.delete(books, b1);
        // Confirm result
        assertTrue(result, "Could not delete a match from middle of the array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final position
        assertNull(books[books.length-1], "Final slot in array was not blanked");
    }

    /**
     * Confirm method can correctly handle attempting to remove an existing book from the end of a populated array (no
     * nulls)
     */
    @Test
    void delete_ExistingArray_RealBook_Present_End() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book[] books = {b0, b1, b2};
        Book [] expUpdated = {b0, b1};

        boolean result = BookUtils.delete(books, b2);
        // Confirm result
        assertTrue(result, "Could not delete a match from the end of the array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final position
        assertNull(books[books.length-1], "Final slot in array was not blanked");
    }

    /**
     * Confirm method can correctly handle attempting to remove a book that is not present from a populated array
     * containing null
     */
    @Test
    void delete_ExistingArrayContainingNullSlot_RealBook_NotPresent() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book[] books = {null, b0, b1};
        Book [] endResult = {null, b0, b1};

        boolean result = BookUtils.delete(books, b2);
        // Confirm result
        assertFalse(result, "Could not complete processing of array containing a null");
        // Confirm array was unchanged
        for (int i = 0; i < endResult.length; i++) {
            assertEquals(endResult[i], books[i], "Book at position "+i+ " has been changed from original when shift " +
                    "should not have occurred");
        }
    }

    /**
     * Confirm method can correctly handle attempting to remove an existing book from a populated array
     * containing null
     */
    @Test
    void delete_ExistingArrayContainingNullSlot_RealBook_Present() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book[] books = {null, b0, b1, b2};
        Book [] expUpdated = {null, b0, b1};

        boolean result = BookUtils.delete(books, b2);
        // Confirm result
        assertTrue(result, "Could not complete processing of array containing a null");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final position
        assertNull(books[books.length-1], "Final slot in array was not blanked");
    }
}