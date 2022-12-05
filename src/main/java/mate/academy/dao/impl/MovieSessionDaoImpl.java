package mate.academy.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import mate.academy.dao.MovieSessionDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.MovieSession;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add Movie Session: " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movieSession;
    }

    @Override
    public Optional<MovieSession> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(MovieSession.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all Movie Sessions from DB", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        LocalDateTime startDay = date.atStartOfDay();
        LocalDateTime endDay = date.atStartOfDay().plusDays(1).minusSeconds(1);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "SELECT ms FROM MovieSession ms "
                            + "join fetch ms.movie "
                            + "where ms.movie.id = :id and ms.showTime BETWEEN :start and :end");
            query.setParameter("id", movieId);
            query.setParameter("start", startDay);
            query.setParameter("end", endDay);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all movie sessions");
        }
    }
}
