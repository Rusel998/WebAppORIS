package ru.kpfu.listeners;

import ru.kpfu.config.DataSourceConfiguration;
import ru.kpfu.mapper.UserMapper;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.repositories.impl.UserRepositoryImpl;
import ru.kpfu.services.security.SecurityService;
import ru.kpfu.services.security.impl.SecurityServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class Initializer implements ServletContextListener {
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

        UserRepository userRepository =
                new UserRepositoryImpl(configuration.customDatasource(), new UserMapper());

        SecurityService securityService =
                new SecurityServiceImpl(userRepository);


        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userRepository", userRepository);

        servletContext.setAttribute("securityService", securityService);
    }
}
