package birt.eus.gyropendatadownload.batch.writer;

import birt.eus.gyropendatadownload.domain.document.PointOfInterest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MongoItemWriterFactory {

  private final MongoOperations mongoOperations;

  public MongoItemWriter<PointOfInterest> getWriter() {
    MongoItemWriter<PointOfInterest> writer = new MongoItemWriter<>();
    writer.setTemplate(mongoOperations);
    writer.setCollection(PointOfInterest.getDocumentName());
    return writer;
  }

}
