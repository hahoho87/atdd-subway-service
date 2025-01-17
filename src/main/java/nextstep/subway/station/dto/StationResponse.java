package nextstep.subway.station.dto;

import java.time.LocalDateTime;
import java.util.List;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.Stations;

public class StationResponse {
    private Long id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static StationResponse of(Station station) {
        return new StationResponse(station.getId(), station.name().toString(), station.getCreatedDate(), station.getModifiedDate());
    }

    private StationResponse() {
    }

    public StationResponse(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static StationResponse from(Station station) {
        return new StationResponse(station.
            getId(),
            station.name().toString(),
            station.getCreatedDate(),
            station.getModifiedDate());
    }

    public static List<StationResponse> listOf(Stations stations) {
        return stations.mapToList(StationResponse::from);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
