package bg.softuni.patfinder2.web.rest;

import bg.softuni.patfinder2.model.dto.CommentCreationDto;
import bg.softuni.patfinder2.model.dto.CommentMessageDto;
import bg.softuni.patfinder2.model.views.CommentDisplayView;
import bg.softuni.patfinder2.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/{routeId}/comments",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComment(@PathVariable("routeId") Long routeId,
                                                           @AuthenticationPrincipal UserDetails userDetails,
                                                           @RequestBody CommentMessageDto commentDto) {
        CommentCreationDto commentCreationDto = new CommentCreationDto(
                userDetails.getUsername(),
                routeId,
                commentDto.getMessage()
        );

        CommentDisplayView comment = this.commentService.createCommend(commentCreationDto);

        return ResponseEntity
                .created(URI.create(String.format("/api/%d/comments/%d", routeId, comment.getId())))
                .body(comment);
    }

}
