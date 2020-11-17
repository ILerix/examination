package rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.dto.QuestionCreate;
import rest.dto.QuestionDto;
import rest.dto.UserDto;

import java.util.UUID;


@RestController
public interface QuestionRestController {

  @GetMapping("/api/v1/question/{id}")
  ResponseEntity<QuestionDto> get(@PathVariable("id") UUID id);

  @PostMapping("/api/v1/question")
  ResponseEntity<QuestionDto> create(@RequestBody QuestionCreate create);

}
