package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BalloonRepository extends JpaRepository<Balloon,Long> {
    @Query("select u from Balloon u where u.name= :text or u.description = :text")
    List<Balloon> findAllByNameOrDescription(String text);
}
