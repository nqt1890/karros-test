## karros-backend-test

# Description
- I'm confuse with the mockups and I have no idea to change data from GPX file into maps in mockup so I just build APIs to return data as json like a backend service
- I'm not sure what you expected on the Workflow and System diagrams. Beside that, there are 3 API with not many action cases from requirement so I will make the workflow very simple, so I attached System diagram and ERD to describe code base

![System_diagram](https://github.com/nqt1890/karros-test/blob/main/diagram/System-diagram.JPG)

-------------------------------------------------------------------------

![ERD](https://github.com/nqt1890/karros-test/blob/main/diagram/ERD.JPG)

# API Documentation

**_Uploads_** 

Url `/upload`: render a page that allow user attach and submit a GPX file. After submiting file info is save into database
Current this function always use userId 1 to simulate system user

**_Lastest Tracks of user with pagination_**

Send a `GET` request to `user/{id}/lastesttrack?page=0`
Use to query tracks from previous upload file and return a list with paging order by lastest to oldest

Sample Request Body
```json
[ {
  "id" : 1264,
  "lat" : 42.22087836969921,
  "lon" : -1.4580971002578733,
  "ele" : "316.27",
  "time" : 1508675444000
}, {
  "id" : 1263,
  "lat" : 42.22068,
  "lon" : -1.4582263,
  "ele" : "316.02752",
  "time" : 1508675440000
}, 
...
]
```

**_Detail info of gpx file from a suer_**

Send a `GET` request to `user/{id}/detail`
Use to query data from fpx file previous uploaded and return all info

Sample Request Body
```json
{
  "id" : 1,
  "userId" : 1,
  "version" : "1.1",
  "creator" : "OruxMaps v.7.1.6 Donate",
  "metadata" : {
    "id" : 17,
    "name" : "Bardenas Reales: Piskerra y el Paso de los Ciervos",
    "desc" : "Este espectacular Parque Natural semidesértico de belleza salvaje fue declarado Reserva de la Biosfera por la UNESCO. Un espectáculo insólito al sureste de Navarra próximo a Tudela, que a pesar de contar con una apariencia desnuda e inhóspita, esconde grandes valores naturales. La erosión de sus suelos arcillosos, yesos y areniscas ha esculpido caprichosas formas creando un mundo de apariencia casi lunar poblado de barrancos, mesetas planas y cerros solitarios. Es por ello por lo que ha servido como fuente de inspiración a pintores y escritores, además de ser escenario de anuncios televisivos, videoclips musicales y películas. Las Bárdenas carecen de núcleos urbanos, su vegetación es muy escasa y las múltiples corrientes de agua que surcan el territorio tienen un caudal marcadamente irregular, permaneciendo secos la mayor parte del año. \r\nEl paisaje está marcado por la erosión, la cual crea un paisaje que es uno de sus principales atractivos ",
    "author" : "",
    "time" : 1508665293000,
    "link" : {
      "id" : 18,
      "href" : "http://www.oruxmaps.com",
      "text" : "OruxMaps"
    }
  },
  "wpt" : [ {
    "id" : 2,
    "lat" : 42.2205377,
    "lon" : -1.4564538,
    "name" : "Sorteamos por arriba",
    "sym" : "/static/wpt/Waypoint"
  }, 
  ....
```
