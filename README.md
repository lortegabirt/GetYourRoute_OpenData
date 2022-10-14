# GetYourRoute OpenData Batch
Este programa es un proceso batch para recoger datos GeoJson de OpenData Euskadi, transformalos y guardarlos en la base de datos de GetYourRoute, para su futuro uso.

El proceso batch se lanzará periódicamente para mantener los datos actualizados.

## Cómo añadir un nuevo archivo de GeoJson de OpenData
1. Añadir la url a la variable `euskadi.opendata.urls` del `application.properties`. Se añade un par llave-valor,
 donde la llave debe ser el nombre de la colección de MongoDb donde se guardará la info, y el valor será la url del
archivo GeoJson de Opendata.
```properties
euskadi.opendata.urls={\
  restaurants: 'https://www.opendata.euskadi.eus/contenidos/ds_recursos_turisticos/restaurantes_sidrerias_bodegas/opendata/restaurantes.geojson',\
  accommodations: 'https://www.opendata.euskadi.eus/contenidos/ds_recursos_turisticos/hoteles_de_euskadi/opendata/alojamientos.geojson', \
  nuevosDocumentos: 'httos://nueva.url'
  }
```
2. Añadir la clase documento del nuevo flujo en el paquete `domain.document` con los campos deseados
```java
@Getter
@Setter
@Document("nuevosDocumentos")
public final class NuevoDocumento extends MapFeature {}
```
3. Añadir el nuevo mapper que mapeará desde `OpenDataRaw` a la clase del nuevo ducumento. Este mapper Debe implementar la interfaz `OpenDataMapper`
```java
@Component
public class NuevoDocumentoMapper implements OpenDataMapper<NuevoDocumento> {
  @Override
  public NuevoDocumento toDocument(OpenDataRaw origin) {
    NuevoDocumento entity = new NuevoDocumento();
    entity.setId(origin.id());
    ...
    return entity;
  }

  @Override
  public Class<NuevoDocumento> getMapFeatureClass() {
    return NuevoDocumento.class;
  }
}
```
4. Por último añadir un nuevo `Step` al `Job` definido en `GyrOpenDataDownloadApplication`.
```java
@Bean
public Job mainJob(JobBuilderFactory jobBuilderFactory, StepFactory stepFactory) {
  return jobBuilderFactory.get("getOpenData")
  .start(stepFactory.createStep(Restaurant.class))
  .next(stepFactory.createStep(Accommodation.class))
  .next(stepFactory.createStep(NuevoDocumento.class))
  .build();
  }
```

### Tecnologías utilizadas
El proyecto se ha realizado utilizando **Spring Batch** y **MongoDB**.
