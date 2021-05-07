# Hibernate - create CinemaHall and MovieSession

Let's continue working on our Cinema project.

Your task is to implement the following steps:
- implement `CinemaHall` class
- implement `MovieSession` class - be careful adding relations with `Movie` and `CinemaHall`
- add implementations of `getAll()` method to `MovieDaoImpl` and `MovieServiceImpl`
- implement `CinemaHallDaoImpl` and `CinemaHallServiceImpl`
- implement `MovieSessionDaoImpl` and `MovieSessionServiceImpl` - keep in mind 
that method `findAvailableSessions()` expects a `LocalDate` that represents the day 
chosen by a customer to visit our cinema, so your task here is to return all MovieSessions 
that will be running between 00:00 and 23:59:59 on that particular day
- make `mate/academy/Main.java` work (you should be able to run `main()` method without any errors)
- use annotations and the annotation injector located in the `lib` folder

#### [Try to avoid these common mistakes, while solving task](https://mate-academy.github.io/jv-program-common-mistakes/hibernate/add-movie-session/add_movie_session_checklist)
