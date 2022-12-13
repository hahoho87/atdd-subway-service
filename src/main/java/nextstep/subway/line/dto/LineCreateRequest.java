package nextstep.subway.line.dto;

import nextstep.subway.common.domain.Name;
import nextstep.subway.line.domain.Color;
import nextstep.subway.line.domain.Distance;

public class LineCreateRequest {
	private final String name;
	private final String color;
	private final Long upStationId;
	private final Long downStationId;
	private final int distance;
	private final Integer fare;

	public LineCreateRequest(String name, String color, Long upStationId, Long downStationId, int distance, int fare) {
		this.name = name;
		this.color = color;
		this.upStationId = upStationId;
		this.downStationId = downStationId;
		this.distance = distance;
		this.fare = fare;
	}

	public LineCreateRequest(String name, String color, Long upStationId, Long downStationId, int distance) {
		this.name = name;
		this.color = color;
		this.upStationId = upStationId;
		this.downStationId = downStationId;
		this.distance = distance;
		this.fare = null;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public Long getUpStationId() {
		return upStationId;
	}

	public Long getDownStationId() {
		return downStationId;
	}

	public int getDistance() {
		return distance;
	}

	public int getFare() {
		return fare;
	}

	public Name name() {
		return Name.from(name);
	}

	public Color color() {
		return Color.from(color);
	}

	public Distance distance() {
		return Distance.from(distance);
	}
}
