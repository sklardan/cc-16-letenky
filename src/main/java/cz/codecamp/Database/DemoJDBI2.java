package cz.codecamp.Database;
//
//
//import org.jooby.jdbi.Jdbi
//import org.skife.jdbi.v2.Handle;
//
//
////http://jooby.org/guides/restful-jdbi/
//
///**
// * Created by jakubbares on 11/13/16.
// */
//
//
//schema = """
//
//        create table if not exists pets (
//
//        id int not null auto_increment,
//
//        name varchar(255) not null,
//
//        primary key (id)
//
//        );
//        """
//
//
//public class DemoJDBI2 {
//    use(new Jdbi().doWith((dbi, conf) -> {
//        try (Handle handle = dbi.open()) {
//            handle.execute(conf.getString("schema"));
//        }
//    }));
//    get("/pets", req -> {
//        try (Handle h = req.require(Handle.class)) {
//            Query<Pet> q = h.createQuery("select * from pets limit :start, :max")
//                    .bind("start", req.param("start").intValue(0))
//                    .bind("max", req.param("max").intValue(20))
//                    .map(Pet.class);
//            return q.list();
//        }
//    });
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
//
//    post("/pets", req -> {
//        try (Handle handle = req.require(Handle.class)) {
//            // read post from HTTP body
//            Pet pet = req.body().to(Pet.class);
//
//            GeneratedKeys<Map<String, Object>> keys = handle
//                    .createStatement("insert into pets (name) values (:name)")
//                    .bind("name", pet.getName())
//                    .executeAndReturnGeneratedKeys();
//            Map<String, Object> key = keys.first();
//            // get and set the auto-increment key
//            Number id = (Number) key.values().iterator().next();
//            pet.setId(id.intValue());
//            return pet;
//        }
//    });
//
//    put("/pets", req -> {
//        try (Handle handle = req.require(Handle.class)) {
//            // read from HTTP body
//            Pet pet = req.body().to(Pet.class);
//
//            int rows = handle
//                    .createStatement("update pets p set p.name = :name where p.id = :id")
//                    .bind("id", pet.getId())
//                    .execute();
//
//            if (rows <= 0) {
//                throw new Err(Status.NOT_FOUND);
//            }
//            return pet;
//        }
//    });
//
//    delete("/pets/:id", req -> {
//        try (Handle handle = req.require(Handle.class)) {
//            int rows = handle
//                    .createStatement("delete pets where p.id = :id")
//                    .bind("id", req.param("id").intValue())
//                    .execute();
//
//            if (rows <= 0) {
//                throw new Err(Status.NOT_FOUND);
//            }
//            return pet;
//        }
//    });
//
//}
