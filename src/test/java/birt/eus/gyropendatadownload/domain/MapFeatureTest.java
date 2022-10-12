package birt.eus.gyropendatadownload.domain;

import birt.eus.gyropendatadownload.domain.document.Restaurant;
import org.junit.jupiter.api.Test;

class MapFeatureTest {

    @Test
    void getDocumentName() {
        String documentName = MapFeature.getDocumentName(Restaurant.class);
    }
}
