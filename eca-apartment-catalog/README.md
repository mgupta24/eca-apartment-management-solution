# ECA APARTMENT SERVICE

This Application is to register tenants, flat owners in the apartments services.

## Steps to Setup

**1. Clone the repository**

```bash
git clone https://github.com/mgupta24/eca-apartment-management-system.git
```

**2. Run the app using maven**

```bash
cd eca
cd eca-apartment-catalog
mvn spring-boot:run
```

That's it! The application can be accessed at `http://localhost:6092`.

You may also package the application in the form of a jar and then run the jar file like so -

```bash
mvn clean package
java -jar target/eca-apartment-catalog*.jar
```

# OR

Simply run the docker image container using docker

```bash
docker build -t eca-apartment-catalog:latest .
docker run -d -p 6092:6092 eca-apartment-catalog:latest
```

