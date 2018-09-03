package vn.com.khoibv.spring.log4j2demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);


    @GetMapping("")
    public String hello() {

        Marker daoMarker = MarkerFactory.getMarker("DAO");
        Marker serviceMarker = MarkerFactory.getMarker("SERVICE");
        Marker controllerMarker = MarkerFactory.getMarker("CONTROLLER");

        LOG.info(daoMarker, "SELECT * FROM MGR_USER");
        LOG.warn(serviceMarker, "This is a warning in service");
        LOG.debug(controllerMarker, "Start an action in controller");

        return "Hello, world";
    }

}
