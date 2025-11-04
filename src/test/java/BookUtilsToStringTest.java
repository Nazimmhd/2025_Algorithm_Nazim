import entities.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookUtilsToStringTest {

    /**
     * Confirm method can correctly handle empty arrays (arrays with length = 0)
     */
    @Test
    void testToString_EmptyArray() {
        Book [] books = new Book[0];
        String expResult = "No books found";
        String result = BookUtils.toString(books);
        assertEquals(expResult, result, "Result string was not correctly formatted");
    }

    /**
     * Confirm method can correctly handle non-existent (null) arrays
     */
    @Test
    void testToString_NullArray() {
        Book [] books = null;
        assertThrows(IllegalArgumentException.class,
            () -> {
                BookUtils.toString(books);
            }
        , "Incorrect (or no) exception thrown");
    }

    /**
     * Confirm method can correctly handle arrays containing only nulls
     */
    @Test
    void testToString_ArrayOfNulls() {
        Book [] books = new Book[3];
        String expResult = "0) null\n1) null\n2) null";
        String result = BookUtils.toString(books);
        assertEquals(expResult, result, "Result string was not correctly formatted");
    }

    /**
     * Confirm method can correctly handle arrays containing mixed data
     */
    @Test
    void testToString_ArrayOfSomeNulls() {
        Book b = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book [] books = {null, b, null};
        String expResult = """
                0) null
                1) "Jane Dickens" by Charles Eyre - ISBN: ASEF12NK4P
                2) null""";
        String result = BookUtils.toString(books);
        System.out.println(result);
        assertEquals(expResult, result, "Result string was not correctly formatted");
    }

    /**
     * Confirm method can correctly handle arrays containing valid data throughout
     */
    @Test
    void testToString_ArrayOfJustBooks() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book [] books = {b0, b1, b2};
        String expResult = """
                0) "Charles Bronte" by Emily Dickens - ISBN: ASEF12DK4Z
                1) "Jane Dickens" by Charles Eyre - ISBN: ASEF12NK4P
                2) "A Song of Endless Time" by George R. R. Waiting - ISBN: AXCF12NK4P""";
        String result = BookUtils.toString(books);
        System.out.println(result);
        assertEquals(expResult, result, "Result string was not correctly formatted");
    }

    /**
     * Confirm method can correctly handle arrays with final position set to null
     */
    @Test
    void testToString_ArrayEndingWithNull() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");
        Book b1 = new Book("ASEF12NK4P", "Jane Dickens", "Charles Eyre");
        Book b2 = new Book("AXCF12NK4P", "A Song of Endless Time", "George R. R. Waiting");
        Book [] books = {b0, b1, b2, null};
        String expResult = """
                0) "Charles Bronte" by Emily Dickens - ISBN: ASEF12DK4Z
                1) "Jane Dickens" by Charles Eyre - ISBN: ASEF12NK4P
                2) "A Song of Endless Time" by George R. R. Waiting - ISBN: AXCF12NK4P
                3) null""";
        String result = BookUtils.toString(books);
        System.out.println(result);
        assertEquals(expResult, result, "Result string was not correctly formatted");
    }

    /**
     * Confirm method can correctly handle arrays containing only one element
     */
    @Test
    void testToString_ArrayOfOneElement() {
        Book b0 = new Book("ASEF12DK4Z", "Charles Bronte", "Emily Dickens");

        Book [] books = {b0};
        String expResult = """
                0) "Charles Bronte" by Emily Dickens - ISBN: ASEF12DK4Z""";
        String result = BookUtils.toString(books);
        System.out.println(result);
        assertEquals(expResult, result, "Result string was not correctly formatted");
    }
}