import entities.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookUtilsDeleteAllTest {
    /**
     * Confirm method can correctly handle non-existent (null) arrays
     */
    @Test
    void delete_NullArray() {
        Book[] books = null;
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    BookUtils.deleteAll(books, b0);
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

        int count = BookUtils.deleteAll(books, b0);
        assertEquals(0, count);
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
                    BookUtils.deleteAll(books, null);
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

        int count = BookUtils.deleteAll(books, b0);
        // Confirm result
        assertEquals(1, count, "Could not correctly delete a single match at start of array");

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

        int count = BookUtils.deleteAll(books, b1);
        // Confirm result
        assertEquals(1, count, "Could not correctly delete a single match in middle of array");

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

        int count = BookUtils.deleteAll(books, b2);
        // Confirm result
        assertEquals(1, count, "Could not correctly delete a single match at end of array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final position
        assertNull(books[books.length-1], "Final slot in array was not blanked");
    }

    /**
     * Confirm method can correctly handle attempting to remove multiple copies of an existing book from across a
     * populated array (no nulls) - copies are spread across the array
     */
    @Test
    void delete_ExistingArray_RealBook_MultipleInstancesPresent_Separated() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b2, b1, b3, b4, b1};
        Book [] expUpdated = {b0, b2, b3, b4};

        int count = BookUtils.deleteAll(books, b1);
        // Confirm result
        assertEquals(3, count, "Could not correctly detect and delete multiple isolated matches in array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final positions
        for(int i = books.length - count; i < books.length; i++) {
            assertNull(books[i], "Slot " + i + " in array was not blanked");
        }
    }

    /**
     * Confirm method can correctly handle attempting to remove multiple copies of an existing book from across a
     * populated array (no nulls) - copies are clustered in a group
     */
    @Test
    void delete_ExistingArray_RealBook_MultipleInstancesPresent_Grouped() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b1, b1, b2, b3, b4};
        Book [] expUpdated = {b0, b2, b3, b4};

        int count = BookUtils.deleteAll(books, b1);
        // Confirm result
        assertEquals(3, count, "Could not correctly detect and delete multiple clustered matches in array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final positions
        for(int i = books.length - count; i < books.length; i++) {
            assertNull(books[i], "Slot " + i + " in array was not blanked");
        }
    }

    /**
     * Confirm method can correctly handle attempting to remove multiple copies of an existing book from across a
     * populated array (no nulls) - copies are clustered in a group at the end of the data
     */
    @Test
    void delete_ExistingArray_RealBook_MultipleInstancesPresent_GroupedAtEnd() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b2, b3, b4, b1, b1, b1};
        Book [] expUpdated = {b0, b2, b3, b4};

        int count = BookUtils.deleteAll(books, b1);
        // Confirm result
        assertEquals(3, count, "Could not correctly detect and delete multiple clustered matches in array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final positions
        for(int i = books.length - count; i < books.length; i++) {
            assertNull(books[i], "Slot " + i + " in array was not blanked");
        }
    }


    /**
     * Confirm method can correctly handle attempting to remove multiple copies of an existing book from across a
     * populated array (no nulls) - some copies are isolated, some are clustered in a group
     */
    @Test
    void delete_ExistingArray_RealBook_MultipleInstancesPresent_Mixed() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b1, b1, b2, b3, b4, b1};
        Book [] expUpdated = {b0, b2, b3, b4};

        int count = BookUtils.deleteAll(books, b1);
        // Confirm result
        assertEquals(4, count, "Could not correctly detect and delete multiple matches in array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final positions
        for(int i = books.length - count; i < books.length; i++) {
            assertNull(books[i], "Slot " + i + " in array was not blanked");
        }
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

        int count = BookUtils.deleteAll(books, b2);
        // Confirm result
        assertEquals(0, count, "Could not complete processing of array containing a null");
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

        int count = BookUtils.deleteAll(books, b2);
        // Confirm result
        assertEquals(1, count, "Could not delete an existing book from array containing null slot");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " was not shifted correctly");
        }

        // Confirm blanking of final position
        assertNull(books[books.length-1], "Final slot in array was not blanked");
    }
}