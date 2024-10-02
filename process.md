# Process of this crash course

```sh
quarkus create app amt:crash-course --extension='rest'
```

JDBC driver for Postgresql
```sh
quarkus ext add io.quarkus:quarkus-jdbc-postgresql
```
[Hibernate ORM plugin](https://quarkus.io/extensions/io.quarkus/quarkus-hibernate-orm/)
```sh
quarkus ext add io.quarkus:quarkus-hibernate-orm
```

Start the server and make sure `localhost:8000/hello` answers.
```sh
quarkus dev
```

Create a class `Probe.java`
```java
public class Probe {
    private URL url;
}
```

Create a class `Status.java`
```java
public class Status {
    private Long id;
    private Probe probe;
    private boolean isUp;
    private Instant timestamp;
    private Duration duration;
}
```

Add `@Entity` and import on those 2 classes
```java
import jakarta.persistence.Entity;

@Entity
class ...
```


Adding `@Id` to `Status.id` and `Probe.url`

```
Could not determine recommended JdbcType for Java type 'amt.entities.Probe'
```
Adding `@ManyToOne` on `Status.probe`
```java
    @ManyToOne
    private Probe probe;
```

Restarting the app with quitting and launching `quarkus dev` again.

You can then connect with any SQL client (here vim-dadbod), database, username and passwords are all `quarkus`.
```
postgres://quarkus:quarkusq@localhost:32773/quarkus
```

**Warning: the port is changing at each reload**.

Changing the `src/main/ressources/application.properties` with this line, we can fix the port
```
quarkus.datasource.devservices.port=5432
```

## ProbeService

We need to annotate `ProbeService` with `@ApplicationScoped` so we can `@Inject` it into `ProbeServiceTest`.


more steps undocumented...
