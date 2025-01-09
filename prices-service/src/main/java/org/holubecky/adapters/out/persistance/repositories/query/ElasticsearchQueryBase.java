package org.holubecky.adapters.out.persistance.repositories.query;

import co.elastic.clients.elasticsearch._types.GeoDistanceType;
import co.elastic.clients.elasticsearch._types.LatLonGeoLocation;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import org.elasticsearch.index.query.IntervalsSourceProvider;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchQueryBase {
    public TermQuery productIdQuery(String productId){
        return TermQuery.of(
                doc -> doc.field("productId").value(productId)
        );
    }
    public GeoDistanceQuery findInArea(Double lon, Double lat, String area){
        return GeoDistanceQuery.of(query -> query.field("locationEntity.coordinates")
                .location(loc -> loc.latlon(new LatLonGeoLocation.Builder().lat(lat).lon(lon).build()))
                .distanceType(GeoDistanceType.Arc)
                .distance(area));
    }

    public TermQuery matchCity(String city){
        return TermQuery.of(query-> query.field("locationEntity.city").value(city));
    }
    public TermQuery matchCountry(String country){
        return TermQuery.of(query-> query.field("locationEntity.country").value(country));
    }
}
