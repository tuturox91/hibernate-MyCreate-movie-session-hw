package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;

public class Main {
    public static void main(String[] args) {
        MovieService movieService = null;

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService = null;

        CinemaHall blueHall = new CinemaHall(100);
        blueHall.setDescription("Blue hall");
        cinemaHallService.add(blueHall);
        System.out.println(cinemaHallService.get(blueHall.getId()));
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSessionService sessionService = null;

        MovieSession januarySession = new MovieSession();
        januarySession.setMovie(fastAndFurious);
        januarySession.setCinemaHall(blueHall);
        januarySession.setShowTime(LocalDateTime.parse("2021-01-10T10:15:00"));
        sessionService.add(januarySession);
        System.out.println(sessionService.get(januarySession.getId()));

        MovieSession marchSession = new MovieSession();
        marchSession.setMovie(fastAndFurious);
        marchSession.setCinemaHall(blueHall);
        marchSession.setShowTime(LocalDateTime.parse("2021-03-10T10:15:00"));
        sessionService.add(marchSession);

        sessionService
                .findAvailableSessions(fastAndFurious.getId(), LocalDate.parse("2021-01-10"))
                .forEach(System.out::println);
    }
}
