package utils;

import entities.Book;

public class BookUtils {
    /**
     * @param books is the array of Book objects to be displaed
     * @return a formatted String showing each Book's details or "No books found" if empty
     *
      */

    public static String toString(Book[] books){
       if (books==null||books.length==0){
           return "Nobooks found";
       }

       String result ="";
       boolean hasbook = false;

       for (int i=0;i< books.length;i++){
           result+=i;

           if (books[i]!=null){
               result+=books[i].format();
               hasbook=true;
           }
           else {
               result +=null;
           }
           result+="" ;

       }

return result;
    }
    /**
     * @param books the Array of Book object to be amended
     * @param pos the indexof the Books to owerwrite
     * @return the boook that was deleted
     */
    public static Book emptyIndex(Book[] books, int pos){
        if (books==null){
            throw new IllegalArgumentException("invalid");
        }
        if (pos<0||pos> books.length){
            throw new IllegalArgumentException("invalid pos");
        }
        Book ToDelete=books[pos];
        books[pos]=null;
        return ToDelete;

    }




/**
 * @param books the array of Book object
 * @param target the Book object to be deleted
 * @return if the book was founda and deleted false otherwise
 */
public static boolean delete(Book[] books, Book target){
    if (books==null||target==null){
        return false;
    }
    for (int i=0;i< books.length;i++){
        if (target.equals(books[i])){
            for (int j=i ;j< books.length-1;j++){
                books[j]=books[j+1];
            }
            books[books.length-1]=null;
            return true;
        }
    }
    return false;
}

/**
 * @param books the array of Book objects to be amended
 * @param target is the Book to be deleted
 * @return the number of instance removed the array
 *
 *
 *
 */


public static int deleteAll(Book[] books, Book target){
    if (books==null||target==null){
        throw new IllegalArgumentException("nothing to be found");

    }
    int nIndex =0;
    int dCount=0;

    for (int i=0;i< books.length;i++){
        if (books[i]!=null && books[i].equals(target)){
            dCount++;
        }
        else {
            books[nIndex++]=books[i];
        }
    }
    while (nIndex< books.length){
        books[nIndex++]=null;
    }
    return dCount;
}
/**
 * @param books the array of Book obj to be amended
 * @param oBook the Book to be  replaced
 * @param nBook the Book insert in ts place
 * @return the number of Books replaced in the array
 */


public static int replace(Book[] books,Book oBook, Book nBook){
    if (books==null||oBook==null||nBook==null){
        throw new IllegalArgumentException("invalid");
    }

    int replacedCount=0;
    for (int i=0;i< books.length;i++){
        if (books[i]!=null&&books[i].equals(oBook)){
            books[i]=nBook;
            replacedCount++;
        }
    }
    return replacedCount;
}




}
