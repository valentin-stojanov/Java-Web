package bg.softuni.patfinder2.service;

import bg.softuni.patfinder2.model.CommentEntity;
import bg.softuni.patfinder2.model.UserEntity;
import bg.softuni.patfinder2.model.dto.CommentCreationDto;
import bg.softuni.patfinder2.model.views.CommentDisplayView;
import bg.softuni.patfinder2.repository.CommentRepository;
import bg.softuni.patfinder2.repository.RouteRepository;
import bg.softuni.patfinder2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentService(RouteRepository routeRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDisplayView createCommend(CommentCreationDto commentDto){
        UserEntity author = this.userRepository.findByUsername(commentDto.getUsername()).get();

        CommentEntity comment = new CommentEntity();
        comment.setCreated(LocalDateTime.now());
        comment.setRoute(this.routeRepository.findById(commentDto.getRouteId()).get());
        comment.setAuthor(author);
        comment.setApproved(true);
        comment.setText(commentDto.getMessage());

        this.commentRepository.save(comment);

        return new CommentDisplayView(comment.getId(), author.getFullName(), comment.getText());
    }
}
