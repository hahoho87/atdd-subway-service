package study.unit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nextstep.subway.generator.LineGenerator;
import nextstep.subway.line.application.LineService;
import nextstep.subway.line.application.SectionService;
import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.LineRepository;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.station.application.StationService;

@DisplayName("단위 테스트 - mockito의 MockitoExtension을 활용한 가짜 협력 객체 사용")
@ExtendWith(MockitoExtension.class)
class MockitoExtensionTest {
    @Mock
    private LineRepository lineRepository;
    @Mock
    private StationService stationService;
    @Mock
    private SectionService sectionService;

    @Test
    void findAllLines() {
        // given
        when(lineRepository.findAll()).thenReturn(Collections.singletonList(이호선()));
        LineService lineService = new LineService(lineRepository, stationService, sectionService);

        // when
        List<LineResponse> responses = lineService.findLines();

        // then
        assertThat(responses).hasSize(1);
    }

    private Line 이호선() {
        return LineGenerator.line("이호선", "green", "강남역", "역삼역", 10);
    }
}
