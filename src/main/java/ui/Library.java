package ui;

import entities.Book;
import utils.BookGenerator;
import utils.BookUtils;

import java.util.Arrays;
import java.util.Scanner;

public class Library {


    public static void main(String[] args) {
        Scanner  sc = new Scanner(System.in);


        Book[] books= BookGenerator.getRandomArray();
        System.out.println("ARraya of books");
        System.out.println(BookUtils.toString(books));

        int rPos=(int) (Math.random())*books.length;
        Book dBook = BookUtils.emptyIndex(books,rPos);
        System.out.println(rPos);
        System.out.println("after ");
        System.out.println(BookUtils.toString(books));

        Arrays.sort(books);
        System.out.println("sorted");
        System.out.println(BookUtils.toString(books));

        if(dBook!=null) {
            Book gone = BookUtils.sortedInsert(books, dBook);
            System.out.println("inserted");
            if (gone != null) {
                System.out.println(gone.format());

            }
        }
            System.out.println("After inserted");
            System.out.println(BookUtils.toString(books));

            System.out.println(books.length-1);
            int pos = sc.nextInt();
            sc.nextLine();

            System.out.println("delete first or all instance");

            char choice = sc.next().toLowerCase().charAt(0);
            if(pos>=0&&pos< books.length&&books[pos]!=null){
                Book target =books[pos];

                if (choice=='m'){
                    BookUtils.delete(books,target);
                    System.out.println("Fist inst deleted or Not found");

                } else if (choice=='n') {
                    boolean delete =BookUtils.delete(books, target);
                    System.out.println("deleted");

                }
                else {
                    System.out.println("invalid or empty slot");
                }

                int randomIndex=(int)(Math.random()* books.length);
                Book oldBook=books[randomIndex];
                System.out.println(oldBook!=null? oldBook.format():"");

                System.out.println(BookUtils.toString(books));

                Book nBook = BookGenerator.generateBook();
                System.out.println("new one");
                System.out.println(nBook.format());

                if (oldBook != null) {

                    int replaced= BookUtils.replace(books,oldBook,nBook);
                }
                else {
                    System.out.println("slot was empty");
                }
                System.out.println("final");
                System.out.println(BookUtils.toString(books));


            }



        }

    }

