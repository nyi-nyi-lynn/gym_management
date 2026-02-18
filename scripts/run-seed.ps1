$ErrorActionPreference = "Stop"
$env:SPRING_PROFILES_ACTIVE = "seed"
./mvnw spring-boot:run
