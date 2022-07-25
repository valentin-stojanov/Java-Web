package bg.softuni.patfinder2.repository;

import bg.softuni.patfinder2.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
