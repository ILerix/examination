package rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.dto.QuizDto;
import rest.dto.UserDto;

import java.util.UUID;


@RestController
public interface QuizRestController {

  @GetMapping("/api/v1/quiz/{id}")
  ResponseEntity<QuizDto> get(@PathVariable("id") UUID id);

}
