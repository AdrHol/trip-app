package org.holubecky.adapters.out.persistance.repository.query;

import co.elastic.clients.elasticsearch._types.GeoDistanceType;
import co.elastic.clients.elasticsearch._types.LatLonGeoLocation;
import co.elastic.clients.elasticsearch._types.query_dsl.GeoDistanceQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Like;
import co.elastic.clients.elasticsearch._types.query_dsl.MoreLikeThisQuery;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class QueriesBuilder {

    public MoreLikeThisQuery fullTextQuery(String titleValue, String descriptionValue){
        return MoreLikeThisQuery.of(
                e -> e.fields("title", "description")
                        .like(new Like.Builder().text(titleValue).build(),
                                new Like.Builder().text(descriptionValue).build())
                        .minTermFreq(1)
                        .maxQueryTerms(12)
                        .minDocFreq(1)
                        .analyzer("standard")
        );
    }
    public GeoDistanceQuery findInArea(Double lon, Double lat, String area){
        return GeoDistanceQuery.of(query -> query.field("locationEntity.coordinates")
                .location(loc -> loc.latlon(new LatLonGeoLocation.Builder().lat(lat).lon(lon).build()))
                .distanceType(GeoDistanceType.Arc)
                .distance(area));
    }
}
