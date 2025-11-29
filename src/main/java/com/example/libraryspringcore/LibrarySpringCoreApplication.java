package com.example.libraryspringcore;

import com.example.libraryspringcore.config.LoanBeanPostProcessor;
import com.example.libraryspringcore.config.LoanConfiguration;
import com.example.libraryspringcore.config.StarterLogger;
import com.example.libraryspringcore.dto.Book;
import com.example.libraryspringcore.dto.Enum.Type;
import com.example.libraryspringcore.dto.Loan;
import com.example.libraryspringcore.dto.Reader;
import com.example.libraryspringcore.repository.BookRepository;
import com.example.libraryspringcore.repository.ReaderRepository;
import com.example.libraryspringcore.service.LoanService;
import com.example.libraryspringcore.service.LoanServiceInterface;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class LibrarySpringCoreApplication {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

          String profile = System.getProperty("app.profile", "dev");
       // String profile = System.getProperty("app.profile", "prod");

        System.setProperty("logback.configurationFile",
                "logback-" + profile + ".xml");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.example.libraryspringcore");
        context.getEnvironment().setActiveProfiles(profile);

        System.out.println("Активные профили: " + Arrays.toString(context.getEnvironment().getActiveProfiles()));

        LoanServiceInterface loanService = context.getBean(LoanServiceInterface.class);

        System.out.println(loanService.getClass());
        System.out.println("Прокси класс: " + loanService.getClass());
        System.out.println("Это CGLIB: " + (AopUtils.isCglibProxy(loanService) ? "да" : "нет"));
        System.out.println("Это JDK Proxy: " + (AopUtils.isJdkDynamicProxy(loanService)? "да" : "нет"));

        /*
        BookRepository bookRepository = context.getBean(BookRepository.class);
        ReaderRepository readerRepository = context.getBean(ReaderRepository.class);

        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Война и мир");
        book1.setAuthor("Лев Толстой");
        book1.setAvailable(true);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Муму");
        book2.setAuthor("Иван Тургенев");
        book2.setAvailable(true);
        bookRepository.save(book2);

        Reader reader1 = new Reader();
        reader1.setId(1L);
        reader1.setName("Петя");
        reader1.setType(Type.REGULAR);
        readerRepository.save(reader1);

        Reader reader2 = new Reader();
        reader2.setId(2L);
        reader2.setName("Люда");
        reader2.setType(Type.VIP);
        readerRepository.save(reader2);

        Loan loan1 = loanService.loanBook(1L, 1L);
        Loan loan2 = loanService.loanBook(2L, 2L);
*/
        context.close();
    }

}
