package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    private static final MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService =
            (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static final MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);

    public static void main(String[] args) {

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));

        Movie undergroundMan = new Movie("Underground Man");
        undergroundMan.setDescription("Film about dark future");
        movieService.add(undergroundMan);

        movieService.getAll().forEach(System.out::println);

        System.out.println("----------------------------------------------------------");

        CinemaHall firstHall = new CinemaHall(30, "Small hall with 3d screen");
        cinemaHallService.add(firstHall);
        System.out.println(movieService.get(firstHall.getId()));

        CinemaHall secondHall = new CinemaHall(90, "Huge hall with simple screen");
        cinemaHallService.add(secondHall);

        cinemaHallService.getAll().forEach(System.out::println);

        System.out.println("----------------------------------------------------------");

        MovieSession movieSession = new MovieSession(fastAndFurious, firstHall,
                LocalDateTime.of(2022, 12, 5, 13,30,00));
        movieSessionService.add(movieSession);
        System.out.println(movieSession.getId());

        MovieSession movieSession2 = new MovieSession(fastAndFurious, secondHall,
                LocalDateTime.of(2022, 12, 5, 22,00,00));
        movieSessionService.add(movieSession2);

        movieSessionService.findAvailableSessions(1L, LocalDate.of(2022,12,5))
                .forEach(System.out::println);
    }
}
