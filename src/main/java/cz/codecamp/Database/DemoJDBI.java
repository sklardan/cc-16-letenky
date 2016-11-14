package cz.codecamp.Database;

import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringColumnMapper;

import javax.sql.DataSource;

/**
 * Created by jbares on 24.10.2016.
 */
public class DemoJDBI {
    public static void main(String[] args){
        DataSource ds = JdbcConnectionPool.create("jdbc:h2:/Users/jakubbares/IdeaProjects/cc-16-letenky-master/letenky",
                //"jdbc:h2:mem:test"
                "jbares",
                "aaaa");
        DBI dbi = new DBI(ds);
        Handle h = dbi.open();
        h.execute("create table users (id int primary key, name varchar(100))");
        h.execute("insert into users (id, name) values (?, ?)", 1, "Brian");

        String name = h.createQuery("select name from something where id = :id")
                .bind("id", 1)
                .map(StringColumnMapper.INSTANCE)
                .first();

        System.out.println(name);

        h.close();
    }
}
