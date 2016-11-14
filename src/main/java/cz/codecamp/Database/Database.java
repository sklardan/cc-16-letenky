package cz.codecamp.Database;

/**
 * Created by jakubbares on 11/13/16.
 */
//public class Database {

//    get("/pets/:id", req -> {
//        try (Handle h = req.require(Handle.class)) {
//            Query<Pet> q = h.createQuery("select * from pets p where p.id = :id")
//                    .bind("id", req.param("id").intValue())
//                    .map(Pet.class);
//            Pet pet = q.first();
//            if (pet == null) {
//                throw new Err(Status.NOT_FOUND);
//            }
//            return pet;
//        }
//    });
//}
//<