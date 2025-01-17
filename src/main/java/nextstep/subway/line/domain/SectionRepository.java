package nextstep.subway.line.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nextstep.subway.station.domain.Station;

public interface SectionRepository extends JpaRepository<Section, Long> {

	@Query("SELECT s FROM Section s "
		+ "WHERE ((s.upStation = :upStation OR s.downStation = :downStation) "
		+ "OR (s.upStation = :downStation OR s.downStation = :upStation))"
	    + "AND s.line = :line")
	List<Section> findAllByStations(
		@Param("upStation") Station upStation,
		@Param("downStation") Station downStation,
		@Param("line") Line line);

	Optional<Section> findByUpStationId(Long stationId);

	Optional<Section> findByDownStationId(Long stationId);
}
