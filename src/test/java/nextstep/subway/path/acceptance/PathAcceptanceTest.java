package nextstep.subway.path.acceptance;

import static nextstep.subway.utils.LineAcceptanceUtils.*;
import static nextstep.subway.utils.PathAcceptanceUtils.*;
import static nextstep.subway.utils.SectionAcceptanceUtils.*;
import static nextstep.subway.utils.StationAcceptanceUtils.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.AcceptanceTest;
import nextstep.subway.line.dto.LineCreateRequest;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.line.dto.SectionRequest;
import nextstep.subway.station.dto.StationResponse;

@DisplayName("지하철 경로 조회")
class PathAcceptanceTest extends AcceptanceTest {

	private LineResponse 신분당선;
	private LineResponse 이호선;
	private LineResponse 삼호선;
	private StationResponse 강남역;
	private StationResponse 양재역;
	private StationResponse 교대역;
	private StationResponse 남부터미널역;

	/**
	 * 교대역    --- *2호선* (10)---      강남역
	 * |                                |
	 * *3호선*                      *신분당선*
	 * (5)                             (10)
	 * |                                |
	 * 남부터미널역  --- *3호선* (10) ---   양재
	 */
	@BeforeEach
	public void setUp() {
		super.setUp();
		강남역 = 지하철역_등록되어_있음("강남역").as(StationResponse.class);
		양재역 = 지하철역_등록되어_있음("양재역").as(StationResponse.class);
		교대역 = 지하철역_등록되어_있음("교대역").as(StationResponse.class);
		남부터미널역 = 지하철역_등록되어_있음("남부터미널역").as(StationResponse.class);

		신분당선 = 지하철_노선_등록되어_있음(new LineCreateRequest("신분당선", "red", 강남역.getId(), 양재역.getId(), 10));
		이호선 = 지하철_노선_등록되어_있음(new LineCreateRequest("이호선", "green", 교대역.getId(), 강남역.getId(), 10));
		삼호선 = 지하철_노선_등록되어_있음(new LineCreateRequest("삼호선", "orange", 교대역.getId(), 양재역.getId(), 15));

		지하철_노선에_구간이_추가되어_있음(삼호선, new SectionRequest(교대역.getId(), 남부터미널역.getId(), 5));
	}

	/**
	 * Scenario: 최단 경로 조회
	 * When 최단 경로를 조회한다.
	 * Then 최단 경로를 응답한다.
	 */
	@Test
	@DisplayName("최단 경로를 조회")
	void findShortestPath() {
	}

	/**
	 * Scenario: 최단 경로 조회 실패 - 출발역과 도착같은 경우
	 * When 동일한 출발역과 도착역으로 최단 경로를 조회한다.
	 * Then 최단 경로를 응답을 실패한다.
	 */
	@Test
	@DisplayName("최단 경로 조회 실패 - 출발역과 도착역이 같은 경우")
	void findPathWithSameUpAndDownStationTest() {
		// when
		ExtractableResponse<Response> 최단_경로_조회 = 최단_경로_조회(강남역.getId(), 강남역.getId());

		// then
		최단_경로_조회_실패(최단_경로_조회);
	}

	/**
	 * Scenario: 최단 경로 조회 실패 - 출발역과 도착역이 연결이 되어 있지 않은 경우
	 * When 연결되어 있지 않은 출발역과 도착역으로 최단 경로를 조회한다.
	 * Then 최단 경로를 응답을 실패한다.
	 */
	@Test
	@DisplayName("최단 경로 조회 실패 - 출발역과 도착역이 연결이 되어 있지 않은 경우")
	void findPathWithNotConnectedUpAndDownStationTest() {
		//  given
		StationResponse 역삼역 = 지하철역_등록되어_있음("역삼역").as(StationResponse.class);

		// when
		ExtractableResponse<Response> 최단_경로_조회 = 최단_경로_조회(강남역.getId(), 역삼역.getId());

		// that
		최단_경로_조회_실패(최단_경로_조회);
	}

	/**
	 * Scenario: 최단 경로 조회 실패 - 존재하지 않는 출발역이나 도착역을 조회 할 경우
	 * When 최단 경로를 조회한다.
	 * When 존재하지 않는 출발역으로 조회한다.
	 * When 존재하지 않는 도착역으로 조회한다.
	 * Then 최단 경로를 응답을 실패한다.
	 */
	@ParameterizedTest
	@MethodSource
	@DisplayName("최단 경로 조회 실패 - 존재하지 않는 출발역이나 도착역을 조회 할 경우")
	void findPathWithNotExistedUpAndDownStationTest(Long upStationId, Long downStationId) {
		// when
		ExtractableResponse<Response> 최단_경로_조회 = 최단_경로_조회(upStationId, downStationId);

		// then
		최단_경로_조회_실패(최단_경로_조회);
	}

	private static Stream<Arguments> findPathWithNotExistedUpAndDownStationTest() {
		return Stream.of(
			Arguments.of(1L, Long.MAX_VALUE),
			Arguments.of(Long.MAX_VALUE, 1L)
		);
	}
}
