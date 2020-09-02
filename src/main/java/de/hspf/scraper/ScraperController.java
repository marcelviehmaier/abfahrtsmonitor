package de.hspf.scraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScraperController {
    @Autowired
    ScraperService service;

    @GetMapping(value = "/departures/{station}")
    @ResponseStatus(HttpStatus.OK)
    public List<Departure> postConnections(@PathVariable String station) {
        return service.getdepartures(station);
    }
}
