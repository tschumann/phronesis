phronesis
=========

Build
-----
```
./gradlew bootJar
```

Initialise
----------
Note that this seems to spew out exceptions but seems to work:
```
./gradlew build -x test createDatabase migrateDatabase
```

Test
----
```
./gradlew build test
```

Run
---
You may need to point directly to your installation of Java e.g. `"C:\Program Files\Java\jdk-21\bin\java"`
```
java -jar ./build/libs/phronesis.jar
```

```
curl "http://localhost:8080/donators/check/?steamid=1&v=3"
```