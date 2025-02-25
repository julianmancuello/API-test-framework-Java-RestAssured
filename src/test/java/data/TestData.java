package data;

import context.ContextStore;
import models.requests.Credentials;
import models.requests.Isbn;
import models.responses.*;
import setup.DependencyContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static common.Authentication.UserType;
import static common.Utils.*;

public class TestData {
    //Books
    public static final Book BOOK1 = new Book("9781449325862", "Git Pocket Guide", "A Working Introduction", "Richard E. Silverman", "2020-06-04T08:48:39.000Z", "O'Reilly Media", 234, "This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp", "http://chimera.labs.oreilly.com/books/1230000000561/index.html");
    public static final Book BOOK2 = new Book("9781449331818", "Learning JavaScript Design Patterns", "A JavaScript and jQuery Developer's Guide", "Addy Osmani", "2020-06-04T09:11:40.000Z", "O'Reilly Media", 254, "With Learning JavaScript Design Patterns, you'll learn how to write beautiful, structured, and maintainable JavaScript by applying classical and modern design patterns to the language. If you want to keep your code efficient, more manageable, and up-to-da", "http://www.addyosmani.com/resources/essentialjsdesignpatterns/book/");
    public static final Book BOOK3 = new Book("9781449337711", "Designing Evolvable Web APIs with ASP.NET", "Harnessing the Power of the Web", "Glenn Block et al.", "2020-06-04T09:12:43.000Z", "O'Reilly Media", 238, "Design and build Web APIs for a broad range of clients—including browsers and mobile devices—that can adapt to change over time. This practical, hands-on guide takes you through the theory and tools you need to build evolvable HTTP services with Microsoft", "http://chimera.labs.oreilly.com/books/1234000001708/index.html");
    public static final Book BOOK4 = new Book("9781449365035", "Speaking JavaScript", "An In-Depth Guide for Programmers", "Axel Rauschmayer", "2014-02-01T00:00:00.000Z", "O'Reilly Media", 460, "Like it or not, JavaScript is everywhere these days-from browser to server to mobile-and now you, too, need to learn the language or dive deeper than you have. This concise book guides you into and through JavaScript, written by a veteran programmer who o", "http://speakingjs.com/");
    public static final Book BOOK5 = new Book("9781491904244", "You Don't Know JS", "ES6 & Beyond", "Kyle Simpson", "2015-12-27T00:00:00.000Z", "O'Reilly Media", 278, "No matter how much experience you have with JavaScript, odds are you don’t fully understand the language. As part of the \\\"You Don’t Know JS\\\" series, this compact guide focuses on new features available in ECMAScript 6 (ES6), the latest version of the st", "https://github.com/getify/You-Dont-Know-JS/tree/master/es6%20&%20beyond");
    public static final Book BOOK6 = new Book("9781491950296", "Programming JavaScript Applications", "Robust Web Architecture with Node, HTML5, and Modern JS Libraries", "Eric Elliott", "2014-07-01T00:00:00.000Z", "O'Reilly Media", 254, "Take advantage of JavaScript's power to build robust web-scale or enterprise applications that are easy to extend and maintain. By applying the design patterns outlined in this practical book, experienced JavaScript developers will learn how to write flex", "http://chimera.labs.oreilly.com/books/1234000000262/index.html");
    public static final Book BOOK7 = new Book("9781593275846", "Eloquent JavaScript, Second Edition", "A Modern Introduction to Programming", "Marijn Haverbeke", "2014-12-14T00:00:00.000Z", "No Starch Press", 472, "JavaScript lies at the heart of almost every modern web application, from social apps to the newest browser-based games. Though simple for beginners to pick up and play with, JavaScript is a flexible, complex language that you can use to build full-scale ", "http://eloquentjavascript.net/");
    public static final Book BOOK8 = new Book("9781593277574", "Understanding ECMAScript 6", "The Definitive Guide for JavaScript Developers", "Nicholas C. Zakas", "2016-09-03T00:00:00.000Z", "No Starch Press", 352, "ECMAScript 6 represents the biggest update to the core of JavaScript in the history of the language. In Understanding ECMAScript 6, expert developer Nicholas C. Zakas provides a complete guide to the object types, syntax, and other exciting changes that E", "https://leanpub.com/understandinges6/read");
    public static final BookStore ALL_BOOKS = new BookStore(Arrays.asList(BOOK1, BOOK2, BOOK3, BOOK4, BOOK5, BOOK6, BOOK7, BOOK8));
    //Responses
    public static final Message ERROR_INVALID_ISBN = new Message(1205, "ISBN supplied is not available in Books Collection!");
    public static final Message ERROR_UNAUTHORIZED_USER = new Message(1200, "User not authorized!");
    //User's information
    public static final User MAIN_USER_INF = new User(ContextStore.get("main-user-id"), ContextStore.get("main-user"), Arrays.asList(BOOK2, BOOK3, BOOK4));
    public static final User EMPTY_USER_INF = new User(ContextStore.get("empty-user-id"), ContextStore.get("empty-user"), List.of());

    public static List<Isbn> selectRandomListOfIsbns() {
        List<Isbn> listOfIsbns = new ArrayList<>();
        int numberOfBooks = ALL_BOOKS.getBooks().size();
        List<Integer> indexes = generateRandomListOfIndexes(generateRandomPositiveInteger(numberOfBooks), numberOfBooks, true);
        for (Integer index : indexes) {
            listOfIsbns.add(new Isbn(ALL_BOOKS.getBooks().get(index).getIsbn()));
        }
        return listOfIsbns;
    }

    public static List<Isbn> listAllIsbns() {
        List<Isbn> allIsbns = new ArrayList<>();
        for (Book book : ALL_BOOKS.getBooks()) {
            allIsbns.add(new Isbn(book.getIsbn()));
        }
        return allIsbns;
    }

    public static int getNumberOfBooksOfUser(UserType userType) {
        return new DependencyContainer().provideAccountApi().getUser(userType).getBooks().size();
    }

    public static Credentials createNewRandomUser() {
        String newUsername = generateRandomUser();
        String newPassword = generateRandomPassword();
        ContextStore.put("newUsername", newUsername);
        ContextStore.put("newPassword", newPassword);
        return new Credentials(newUsername, newPassword);
    }

    public static UserWithTypo getNewUserInf() {
        return new UserWithTypo(ContextStore.get("newUserId"), ContextStore.get("newUsername"), List.of());
    }
}
