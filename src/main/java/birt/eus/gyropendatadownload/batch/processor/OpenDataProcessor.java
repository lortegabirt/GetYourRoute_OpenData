package birt.eus.gyropendatadownload.batch.processor;


import birt.eus.gyropendatadownload.domain.OpenDataMapper;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import birt.eus.gyropendatadownload.domain.document.PointOfInterest;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class OpenDataProcessor implements ItemProcessor<OpenDataRaw, PointOfInterest> {

  private final OpenDataMapper mapper;

  @Override
  public PointOfInterest process(OpenDataRaw origin) {
    return mapper.toDocument(origin);
  }

}
