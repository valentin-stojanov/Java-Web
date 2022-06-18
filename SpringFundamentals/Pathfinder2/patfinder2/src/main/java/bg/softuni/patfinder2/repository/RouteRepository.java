package bg.softuni.patfinder2.repository;

import bg.softuni.patfinder2.model.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    @Query("SELECT r FROM RouteEntity AS r ORDER BY size(r.comments) DESC")
    List<RouteEntity> findMostCommented();
}
