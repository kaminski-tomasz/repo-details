package pl.allegro.repodetails.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,
                reason = "Repository not found")
public class RepositoryNotFoundException extends Exception {
}
