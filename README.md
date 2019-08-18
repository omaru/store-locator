# Store Locator Service
[![codecov](https://codecov.io/gh/omaru/store-locator/branch/master/graph/badge.svg)](https://codecov.io/gh/omaru/store-locator)
[![build](https://travis-ci.org/omaru/store-locator.svg?branch=master)](https://travis-ci.org/omaru/store-locator)[![maintainability](https://api.codeclimate.com/v1/badges/907e2222bdc0209b2865/maintainability)](https://codeclimate.com/github/omaru/store-locator/maintainability)
## Usage
- Development
```bash
./gradlew  bootRun
```
- Package
```bash
./gradlew  build
``` 
   -  jar file will be contained inside `$PROJECT_HOME/build/libs` folder, once task is done,execute it with 
   `java -jar $PROJECTNAME.jar`.
   ![image](https://user-images.githubusercontent.com/877539/63227997-2d4af900-c1aa-11e9-9913-9f8cd29ad077.png)

#### Tooling
- gradle
- spring (test,rest,hateoas,data,actuator)
- commons-cli
- mongodb
- assertj
- junit5
- lombok
- swagger2
- travis
- codecov
- codeclimate
#### Documentation
To see the swagger api documentation, access swagger's default route `http://localhost:8080/swagger-ui.html`
#### Actuator Endpoints
Active actuator endpoints are `health` and `metrics`, going to `/actuator` gives available hypermedia links for them.
![image](https://user-images.githubusercontent.com/877539/63227824-01c70f00-c1a8-11e9-9d51-a3138e714972.png)
#### Configuration
Application specific  keys in `application.properties`
- `command.line.data.ingester.default-script` configures the `classpath` file name archive that contains
 the store information to be ingested in mongo (current filename is `store-test.json`).
- `default.page.request.size` amount of resources to display to the user when queries for stores near a location.

Spring customized keys in `application.properties`
- `management.endpoints.web.exposure.include` active spring actuator health metrics. 
- `spring.application.name` application name also used for showing the app name in the command line 
options utility display.
#### Data Ingestion
To ingest data provide the param `-i` followed by the `json` file `path`, current `$PROJECTNAME` is `store-locator-0.0.1-SNAPSHOT`
```bash 
java -jar $PROJECTNAME.jar -i /path/to/file.json
```
```bash 
java -jar store-locator-0.0.1-SNAPSHOT.jar -i /path/to/file.json
```
If no option is provided , data is populated from `store-test.json` 
located in  `resources` folder.
#### CRUD Operations
##### Get Stores
###### Retrieves all available stores.
![image](https://user-images.githubusercontent.com/877539/63227936-5dde6300-c1a9-11e9-83a7-64c0f4d0865d.png)
##### Get Store
###### Retrieves  a store information by id.
![image](https://user-images.githubusercontent.com/877539/63227957-92521f00-c1a9-11e9-844a-1c1b43290779.png)
##### Get Stores nearby by location
###### Retrieves  5 stores given a location point (latitude,longitude) and its distance.
![image](https://user-images.githubusercontent.com/877539/63227965-bada1900-c1a9-11e9-9293-b49ec1884b2b.png)

