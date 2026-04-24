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

### Natural Selection
```
curl -H "Authorization: Basic YXV0aDptbmJ2NXRnYg==" "http://localhost:8080/auth.txt"
```
```
curl -H "Authorization: Basic YXV0aDptbmJ2NXRnYg==" "http://localhost:8080/auth/version.txt"
```
```
curl -H "Content-Type: text/plain" --data "1?aliens?32?32?ns_eclipse?v3.2.0?60?0" "http://localhost:8080/cgi-bin/VictoryStats.pl"
```
```
curl -H "Content-Type: text/plain" --data "1?aliens?32?32?ns_eclipse?v3.2.0?60?0" "http://localhost:8080/cgi-bin/ikonboard/ikonboard.cgi"
```

### Zombie Panic!
```
curl "http://localhost:8080/donators/check/?steamid=1&v=3"
```