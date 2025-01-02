package ru.kpfu.listeners;

import ru.kpfu.repositories.mapper.Impl.*;
import ru.kpfu.repositories.*;
import ru.kpfu.repositories.impl.*;
import ru.kpfu.services.*;
import ru.kpfu.services.impl.*;
import ru.kpfu.util.DriverManagerDataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final String POSTGRE_DRIVER = "org.postgresql.Driver";
    private static final String POSTGRE_URL = "jdbc:postgresql://localhost:5432/WebAppORIS";
    private static final String POSTGRE_USER = "postgres";
    private static final String POSTGRE_PASSWORD = "998123";
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriver(POSTGRE_DRIVER);
        dataSource.setUrl(POSTGRE_URL);
        dataSource.setUsername(POSTGRE_USER);
        dataSource.setPassword(POSTGRE_PASSWORD);

        UserRepository userRepository =
                new UserRepositoryImpl(dataSource, new UserRowMapper());
        PersonalFormRepository personalFormRepository =
                new PersonalFormRepositoryImpl(dataSource, new PersonalFormRowMapper());
        InterestRepository interestRepository =
                new InterestRepositoryImpl(dataSource, new InterestRowMapper());
        UserInterestsRepository userInterestsRepository =
                new UserInterestsRepositoryImpl(dataSource);
        ComplaintRepository complaintRepository =
                new ComplaintRepositoryImpl(dataSource, new ComplaintRowMapper());
        RatingRepository ratingRepository =
                new RatingRepositoryImpl(dataSource, new RatingRowMapper());


        RatingService ratingService =
                new RatingServiceImpl(ratingRepository);
        ComplaintService complaintService =
                new ComplaintServiceImpl(complaintRepository);
        UserInterestsService userInterestsService =
                new UserInterestsServiceImpl(userInterestsRepository);
        InterestService interestService =
                new InterestServiceImpl(interestRepository);
        PersonalFormService personalFormService =
                new PersonalFormServiceImpl(personalFormRepository);
        UserService userService =
                new UserServiceImpl(userRepository);


        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("ratingService", ratingService);
        servletContext.setAttribute("complaintService", complaintService);
        servletContext.setAttribute("userInterestsService", userInterestsService);
        servletContext.setAttribute("interestService", interestService);
        servletContext.setAttribute("personalFormService", personalFormService);
        servletContext.setAttribute("userService", userService);
    }
}
