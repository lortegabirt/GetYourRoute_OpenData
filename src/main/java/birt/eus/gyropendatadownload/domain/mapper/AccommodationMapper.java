package birt.eus.gyropendatadownload.domain.mapper;

import birt.eus.gyropendatadownload.domain.OpenDataMapper;
import birt.eus.gyropendatadownload.domain.document.Accommodation;
import birt.eus.gyropendatadownload.domain.FeatureType;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import org.springframework.stereotype.Component;

@Component
public class AccommodationMapper implements OpenDataMapper<Accommodation> {
  @Override
  public Accommodation toDocument(OpenDataRaw origin) {
    Accommodation accommodation = new Accommodation();
    accommodation.setId(origin.id());
    accommodation.setType(FeatureType.ACCOMMODATION);
    accommodation.setName(origin.properties());
    accommodation.setWeb(origin.properties().get("web"));
    accommodation.setLocation(origin);
    accommodation.setSubtype(origin.properties().get("lodgingtype"));
    return accommodation;
  }

  @Override
  public Class<Accommodation> getMapFeatureClass() {
    return Accommodation.class;
  }
}
