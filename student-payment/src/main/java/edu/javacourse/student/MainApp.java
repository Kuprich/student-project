package edu.javacourse.student;

import edu.javacourse.student.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        // Создаем контекст Spring
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

//        // Получаем сервис из контекста
//        BookService bookService = context.getBean(BookService.class);
//
//        // Работаем с БД
//        Book book = new Book();
//        book.setTitle("Spring in Action");
//        book.setAuthor("Craig Walls");
//
//        bookService.saveBook(book);
//
//        List<Book> books = bookService.findByAuthor("Craig Walls");
//        books.forEach(b -> System.out.println(b.getTitle()));

        // Закрываем контекст
        context.close();
    }
}
