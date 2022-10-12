package birt.eus.gyropendatadownload.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MapFeatureTest {

    @Test
    void getDocumentName() {
        String documentName = MapFeature.getDocumentName(Restaurant.class);
    }
}
