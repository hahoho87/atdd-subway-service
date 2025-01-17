package nextstep.subway.Favorite.dto;

import nextstep.subway.Favorite.domain.Favorite;
import nextstep.subway.station.dto.StationResponse;

public class FavoritesResponse {

	private Long id;
	private StationResponse source;
	private StationResponse target;

	private FavoritesResponse() {
	}

	private FavoritesResponse(Long id, StationResponse source, StationResponse target) {
		this.id = id;
		this.source = source;
		this.target = target;
	}

	public static FavoritesResponse of(Long id, StationResponse source, StationResponse target) {
		return new FavoritesResponse(id, source, target);
	}

	public static FavoritesResponse from(Favorite favorite) {
		return new FavoritesResponse(favorite.memberId(),
			StationResponse.from(favorite.source()),
			StationResponse.from(favorite.target())
		);
	}

	public Long getId() {
		return id;
	}

	public StationResponse getSource() {
		return source;
	}

	public StationResponse getTarget() {
		return target;
	}
}
