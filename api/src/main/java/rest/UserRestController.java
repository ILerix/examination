package rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.dto.UserDto;

import java.util.UUID;


@RestController
public interface UserRestController {

  @GetMapping("/api/v1/user/{id}")
  ResponseEntity<UserDto> get(@PathVariable("id") UUID id);

  @PostMapping("/api/v1/user")
  ResponseEntity<UserDto> create(@RequestBody UserDto user);

  @PutMapping("/api/v1/user")
  ResponseEntity<UserDto> update(@RequestBody UserDto user);

}
