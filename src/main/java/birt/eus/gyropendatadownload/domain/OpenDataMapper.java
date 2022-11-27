package birt.eus.gyropendatadownload.domain;

import birt.eus.gyropendatadownload.domain.document.PointOfInterest;

public interface OpenDataMapper {
  PointOfInterest toDocument(OpenDataRaw origin);
  FeatureType getType();
}
