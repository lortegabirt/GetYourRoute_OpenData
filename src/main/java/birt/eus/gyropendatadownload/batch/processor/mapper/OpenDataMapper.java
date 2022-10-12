package birt.eus.gyropendatadownload.batch.processor.mapper;

import birt.eus.gyropendatadownload.domain.MapFeature;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;

public interface OpenDataMapper<T extends MapFeature> {
  T toDocument(OpenDataRaw origin);

  Class<T> getMapFeatureClass();

  default String getDocumentName() {
    return MapFeature.getDocumentName(getMapFeatureClass());
  }
}
