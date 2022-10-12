package birt.eus.gyropendatadownload.domain;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.mapping.Document;

public interface MapFeature {
  FeatureType getType();

  String getName();

  String getDescription();

  Point getLocation();

  static <T extends MapFeature> String getDocumentName(Class<T> clazz) {
    return clazz.getAnnotation(Document.class).value();
  }
}
