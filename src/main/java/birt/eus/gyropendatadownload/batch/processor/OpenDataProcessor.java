package birt.eus.gyropendatadownload.batch.processor;


import birt.eus.gyropendatadownload.domain.OpenDataMapper;
import birt.eus.gyropendatadownload.domain.MapFeature;
import birt.eus.gyropendatadownload.domain.OpenDataRaw;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class OpenDataProcessor<T extends MapFeature> implements ItemProcessor<OpenDataRaw, T> {

  private final OpenDataMapper<T> mapper;

  @Override
  public T process(OpenDataRaw origin) {
    return mapper.toDocument(origin);
  }

}
