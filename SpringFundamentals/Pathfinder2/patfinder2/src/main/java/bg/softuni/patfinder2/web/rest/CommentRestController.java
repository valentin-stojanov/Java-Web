package bg.softuni.patfinder2.web.rest;

import bg.softuni.patfinder2.exceptions.RouteNotFoundException;
import bg.softuni.patfinder2.model.dto.CommentCreationDto;
import bg.softuni.patfinder2.model.dto.CommentMessageDto;
import bg.softuni.patfinder2.model.views.CommentDisplayView;
import bg.softuni.patfinder2.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{routeId}/comments")
    ResponseEntity<List<CommentDisplayView>> getComments(@PathVariable("routeId") Long routeId){
        return ResponseEntity
                .ok(this.commentService.getAllCommentsForRoute(routeId));
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

    @ExceptionHandler({RouteNotFoundException.class})
    public ResponseEntity<ErrorApiResponse> handleRouteNotFound(){
        return ResponseEntity.status(404)
                .body(new ErrorApiResponse("Such route doesn't exists!", 1001));
    }

}
class ErrorApiResponse{
    private String message;
    private Integer errorCode;

    public ErrorApiResponse() {
    }

    public ErrorApiResponse(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public ErrorApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public ErrorApiResponse setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}