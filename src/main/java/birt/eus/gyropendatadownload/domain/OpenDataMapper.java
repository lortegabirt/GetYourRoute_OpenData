package birt.eus.gyropendatadownload.domain;

public interface OpenDataMapper<T extends MapFeature> {
  T toDocument(OpenDataRaw origin);

  Class<T> getMapFeatureClass();

  default String getDocumentName() {
    return MapFeature.getDocumentName(getMapFeatureClass());
  }
}
