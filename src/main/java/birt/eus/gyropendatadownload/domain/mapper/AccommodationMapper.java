package birt.eus.gyropendatadownload.domain.mapper;

import birt.eus.gyropendatadownload.domain.OpenDataMapper;
import birt.eus.gyropendatadownload.domain.FeatureType;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import birt.eus.gyropendatadownload.domain.document.PointOfInterest;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class AccommodationMapper implements OpenDataMapper {

  @Getter
  private final FeatureType type = FeatureType.ACCOMMODATION;

  @Override
  public PointOfInterest toDocument(OpenDataRaw origin) {
    PointOfInterest accommodation = new PointOfInterest();
    accommodation.setId(type + origin.id());
    accommodation.setType(FeatureType.ACCOMMODATION);
    accommodation.setName(origin);
    accommodation.setLocation(origin);
    accommodation.getProperties().put("web", origin.properties().get("web"));
    accommodation.getProperties().put("subtype", origin.properties().get("lodgingtype"));
    return accommodation;
  }

}
