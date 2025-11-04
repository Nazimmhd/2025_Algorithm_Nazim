import entities.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookUtilsReplaceTest {
    /**
     * Confirm method can correctly handle non-existent (null) arrays
     */
    @Test
    void replace_NullArray() {
        Book[] books = null;
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    BookUtils.replace(books, b0, replacement);
                }, "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle replacing in empty arrays
     */
    @Test
    void replace_EmptyArray() {
        Book[] books = {};
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");

        int count = BookUtils.replace(books, b0, replacement);
        assertEquals(0, count);
    }

    /**
     * Confirm method can correctly handle attempting to replace a non-existent (null) book from a populated array
     */
    @Test
    void replace_ExistingArray_NullBookToBeReplaced() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book[] books = {b0, b1, b2};
        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");

        assertThrows(IllegalArgumentException.class,
                () -> {
                    BookUtils.replace(books, null, replacement);
                }, "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle attempting to replace an existing book from a populated array with null
     */
    @Test
    void replace_ExistingArray_ExistingBookToBeReplaced_NullBookToBeInserted() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book[] books = {b0, b1, b2};

        assertThrows(IllegalArgumentException.class,
                () -> {
                    BookUtils.replace(books, null, null);
                }, "Incorrect (or no) exception thrown"
        );
    }

    /**
     * Confirm method can correctly handle attempting to replace a single copy of an existing book from the start of a
     * populated array (no nulls)
     */
    @Test
    void replace_ExistingArray_RealBook_Present_Start() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b2, b3, b4};

        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        Book [] expUpdated = {replacement, b1, b2, b3, b4};

        int count = BookUtils.replace(books, b0, replacement);
        int expCount = 1;
        // Confirm result
        assertEquals(expCount, count, "Could not correctly replace a single match at start of array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to replace a single instance of an existing book from the middle
     * of a populated array (no nulls)
     */
    @Test
    void replace_ExistingArray_RealBook_Present_Middle() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b2, b3, b4};

        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        Book [] expUpdated = {b0, b1, replacement, b3, b4};

        int count = BookUtils.replace(books, b2, replacement);
        // Confirm result
        assertEquals(1, count, "Could not correctly replace a single match in middle of array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to replace a single instance of an existing book from the end
     * of a populated array (no nulls)
     */
    @Test
    void replace_ExistingArray_RealBook_Present_End() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book[] books = {b0, b1, b2};

        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        Book [] expUpdated = {b0, b1, replacement};

        int count = BookUtils.replace(books, b2, replacement);
        int expCount = 1;

        // Confirm result
        assertEquals(expCount, count, "Could not correctly delete a single match at end of array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to replace multiple copies of an existing book from across a
     * populated array (no nulls) - copies are spread across the array
     */
    @Test
    void replace_ExistingArray_RealBook_MultipleInstancesPresent_Separated() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b2, b1, b3, b4, b1};

        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        Book [] expUpdated = {b0, replacement, b2, replacement, b3, b4, replacement};

        int count = BookUtils.replace(books, b1, replacement);
        // Confirm result
        assertEquals(3, count, "Could not correctly detect and replace multiple isolated matches in array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to replace multiple copies of an existing book from across a
     * populated array (no nulls) - copies are clustered in a group
     */
    @Test
    void replace_ExistingArray_RealBook_MultipleInstancesPresent_Grouped() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b1, b1, b2, b3, b4};

        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        Book [] expUpdated = {b0, replacement, replacement, replacement, b2, b3, b4};

        int count = BookUtils.replace(books, b1, replacement);
        int expCount = 3;
        // Confirm result
        assertEquals(expCount, count, "Could not correctly detect and replace multiple clustered matches in array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to replace multiple copies of an existing book from across a
     * populated array (no nulls) - copies are clustered in a group
     */
    @Test
    void replace_ExistingArray_RealBook_MultipleInstancesPresent_GroupedAtEnd() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b2, b3, b4, b1, b1, b1};

        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        Book [] expUpdated = {b0, b2, b3, b4, replacement, replacement, replacement};

        int count = BookUtils.replace(books, b1, replacement);
        int expCount = 3;
        // Confirm result
        assertEquals(expCount, count, "Could not correctly detect and replace multiple clustered matches in array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to replace multiple copies of an existing book from across a
     * populated array (no nulls) - some copies are isolated, some are clustered in a group
     */
    @Test
    void replace_ExistingArray_RealBook_MultipleInstancesPresent_Mixed() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book b3 = new Book("DRWJ113SLB", "A Tale of Endless Woe", "Suzanne Poe");
        Book b4 = new Book("EWDPVN2K45", "Ghostly Wanderings", "Alpha Sue");
        Book[] books = {b0, b1, b1, b1, b2, b3, b4, b1};

        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        Book [] expUpdated = {b0, replacement, replacement, replacement, b2, b3, b4, replacement};

        int count = BookUtils.replace(books, b1, replacement);
        // Confirm result
        assertEquals(4, count, "Could not correctly detect and replace multiple matches in array");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located within the array");
        }
    }

    /**
     * Confirm method can correctly handle attempting to replace a book that is not present from a populated array
     * containing null
     */
    @Test
    void replace_ExistingArrayContainingNullSlot_RealBook_NotPresent() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book[] books = {null, b0, b1};

        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        Book [] endResult = {null, b0, b1};

        int count = BookUtils.replace(books, b2, replacement);
        int expCount = 0;

        // Confirm result
        assertEquals(expCount, count, "Could not complete processing of array containing a null");
        // Confirm array was unchanged
        for (int i = 0; i < endResult.length; i++) {
            assertEquals(endResult[i], books[i], "Book at position "+i+ " has been changed from original when shift " +
                    "should not have occurred");
        }
    }

    /**
     * Confirm method can correctly handle attempting to replace an existing book in a populated array
     * containing null
     */
    @Test
    void replace_ExistingArrayContainingNullSlot_RealBook_Present() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book[] books = {null, b0, b1, b2};

        Book replacement = new Book("ASEF19283Z", "Wilford Bugle", "Charlie Factor");
        Book [] expUpdated = {null, b0, b1};

        int count = BookUtils.replace(books, b2, replacement);
        int expCount = 1;
        // Confirm result
        assertEquals(1, count, "Could not replace an existing book in array containing null slot");

        // Confirm shift
        for (int i = 0; i < expUpdated.length; i++) {
            assertEquals(expUpdated[i], books[i], "Book at position "+i+ " is not correctly located in array");
        }
    }
}