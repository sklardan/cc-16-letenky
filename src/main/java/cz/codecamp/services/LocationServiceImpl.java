package cz.codecamp.services;

import cz.codecamp.model.Location;
import cz.codecamp.model.User;
import cz.codecamp.repository.LocationRepository;
import cz.codecamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jakubbares on 12/1/16.
 */
public class LocationServiceImpl {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    public List<Location> addLocation(String cityTo, String emailLogin){
        User user = userRepository.findByEmailLogin(emailLogin);
        Location location = locationRepository.findByCity(cityTo);
        List<Location> locations = user.getCitiesTo();
        locations.add(location);
        user.setCitiesTo(locations);
        return locations;
    }

}
