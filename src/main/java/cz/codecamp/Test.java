//package cz.codecamp;
//
//import cz.codecamp.model.Location;
//import cz.codecamp.model.User;
//import cz.codecamp.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by jakubbares on 12/1/16.
// */
//public class Test {
//
//    @Autowired
//    UserRepository userRepository;
//
//    public static void main(String[] args) throws ParseException {
//        User user = new User();
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//        String dateFromString = "05.12.2016";
//        String dateToString = "01.01.2017";
//        Date dateFrom = dateFormat.parse(dateFromString);
//        Date dateTo = dateFormat.parse(dateToString);
//
//        user.setDateTo(dateTo);
//        user.setDateFrom(dateFrom);
//        user.setCityFrom(new Location("Prague", "Czech republic", "prague_cz"));
//        List<Location> citiesTo = new ArrayList<Location>();
//        citiesTo.add(new Location("Wien", "Austria", "Wien_at"));
//        user.setCitiesTo(citiesTo);
//        user.setDateFrom(new Date());
//        user.setEmailLogin("kubres@gmail.com");
//        user.setNightsInDestinationMin(4);
//        user.setNightsInDestinationMax(8);
//        user.setFlyDurationHoursMax(3);
//        user.setPassword("123");
//        user.setUserName("kubres");
//        user.setPctAvgPriceMax(0.6);
//        user.setFlyDurationHoursToMinutesMax(3);
//
//        userRepository.save(user);
//    }
//}
