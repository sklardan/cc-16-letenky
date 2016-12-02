package cz.codecamp.services;

import cz.codecamp.model.Location;

import java.util.List;

/**
 * Created by jakubbares on 12/1/16.
 */
public interface LocationService {

    public List<Location> addLocation(String cityTo, String emailLogin);
}
