package ru.kpfu.listeners;

import ru.kpfu.config.DataSourceConfiguration;
import ru.kpfu.mapper.*;
import ru.kpfu.repositories.*;
import ru.kpfu.repositories.impl.*;
import ru.kpfu.services.security.SecurityService;
import ru.kpfu.services.security.impl.SecurityServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
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

        UserRepository userRepository =
                new UserRepositoryImpl(configuration.customDatasource(), new UserRowMapper());
        RatingRepository ratingRepository =
                new RatingRepositoryImpl(configuration.customDatasource(), new RatingRowMapper());
        ProfileRepository profileRepository =
                new ProfileRepositoryImpl(configuration.customDatasource(), new ProfileRowMapper());
        PhotoRepository photoRepository =
                new PhotoRepositoryImpl(configuration.customDatasource(), new PhotoRowMapper());
        ComplaintRepository complaintRepository =
                new ComplaintRepositoryImpl(configuration.customDatasource(), new ComplaintRowMapper());
        ActivityRepository activityRepository =
                new ActivityRepositoryImpl(configuration.customDatasource(), new ActivityRowMapper());


        SecurityService securityService =
                new SecurityServiceImpl(userRepository);


        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userRepository", userRepository);

        servletContext.setAttribute("securityService", securityService);
    }
}
