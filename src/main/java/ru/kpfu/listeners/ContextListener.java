package ru.kpfu.listeners;

import ru.kpfu.config.DataSourceConfiguration;
import ru.kpfu.repositories.mapper.Impl.*;
import ru.kpfu.repositories.*;
import ru.kpfu.repositories.impl.*;
import ru.kpfu.security.SecurityService;
import ru.kpfu.security.impl.SecurityServiceImpl;
import ru.kpfu.services.InterestService;
import ru.kpfu.services.PersonalFormService;
import ru.kpfu.services.UserInterestsService;
import ru.kpfu.services.UserService;
import ru.kpfu.services.impl.InterestServiceImpl;
import ru.kpfu.services.impl.PersonalFormServiceImpl;
import ru.kpfu.services.impl.UserInterestsServiceImpl;
import ru.kpfu.services.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }

        DataSourceConfiguration configuration =
                new DataSourceConfiguration(properties);
        DataSource dataSource = configuration.customDatasource();

        UserRepository userRepository =
                new UserRepositoryImpl(dataSource, new UserRowMapper());
        PersonalFormRepository personalFormRepository =
                new PersonalFormRepositoryImpl(dataSource, new PersonalFormRowMapper());
        InterestRepository interestRepository =
                new InterestRepositoryImpl(dataSource, new InterestRowMapper());
        UserInterestsRepository userInterestsRepository =
                new UserInterestsRepositoryImpl(dataSource);


        UserInterestsService userInterestsService =
                new UserInterestsServiceImpl(userInterestsRepository);
        InterestService interestService =
                new InterestServiceImpl(interestRepository);
        PersonalFormService personalFormService =
                new PersonalFormServiceImpl(personalFormRepository);
        UserService userService =
                new UserServiceImpl(userRepository);

        SecurityService securityService =
                new SecurityServiceImpl(userService);


        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userInterestsService", userInterestsService);
        servletContext.setAttribute("interestService", interestService);
        servletContext.setAttribute("personalFormService", personalFormService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("securityService", securityService);
    }
}
