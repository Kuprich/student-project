package edu.javacourse.register.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // 1. Создаем реестр сервисов с настройками
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    // Настройки подключения к БД (PostgreSQL)
                    .applySetting("hibernate.connection.driver_class", "org.postgresql.Driver")
                    .applySetting("hibernate.connection.url", "jdbc:postgresql://localhost:5434/register-office")
                    .applySetting("hibernate.connection.username", "postgres")
                    .applySetting("hibernate.connection.password", "postgres")
                    // Диалект СУБД
                    .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect")
                    // Доп. настройки
                    .applySetting("hibernate.show_sql", "true")
                    .applySetting("hibernate.format_sql", "true")
                    .applySetting("hibernate.hbm2ddl.auto", "update") // validate | create | update
                    .build();

            // 2. Указываем классы-сущности (можно добавить несколько)
            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClass(edu.javacourse.register.domain.Person.class);

            // 3. Создаем фабрику сессий
            Metadata metadata = sources.getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Exception ex) {
            System.err.println("Ошибка при создании SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
