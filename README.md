# Rest-Converter

A EUPL licensed, spring boot web service to convert
UBL, CII and FatturaPA XMLs into one another 
based on Martins Java11+ [fork](https://github.com/5now/EeISI-mapper) 
of [EEISI/EICAR](https://github.com/AgID/EeISI-mapper/).

Which probably has to be cached in MVN installed like this 
```
 mvn install:install-file -Dfile="target/eigor-api-4.4.4-SNAPSHOT.jar" -DgroupId="it.infocert.eigor" -DartifactId="eigor-api" -Dversion="4.4.4-SNAPSHOT" -Dpackaging=jar -DgeneratePom=true
```


Compile with
```
mvn clean package
```


and start with

```
java -jar target/mustang.war --spring.profiles.active=local -Dspring.profiles.active=local
```
