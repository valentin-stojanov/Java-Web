package bg.softuni.patfinder2.repository;

import bg.softuni.patfinder2.model.CommentEntity;
import bg.softuni.patfinder2.model.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    Optional<List<CommentEntity>> findAllByRoute(RouteEntity route);
}
