package nextstep.subway.line.domain;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.util.Assert;

import nextstep.subway.common.domain.BaseEntity;
import nextstep.subway.common.domain.Name;

@Entity
public class Line extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Color color;

    @Embedded
    private Sections sections;

    public Line() {
    }

    private Line(Name name, Color color) {
        this.name = name;
        this.color = color;
    }

    public static Line of(Name name, Color color) {
        return new Line(name, color);
    }

    private Line(Name name, Color color, Sections sections) {
        Assert.notNull(name, "이름은 필수입니다.");
        Assert.notNull(color, "색상은 필수입니다.");
        Assert.notNull(sections, "구간들은 필수입니다.");
        this.name = name;
        this.color = color;
        setSections(sections);
    }

    private void setSections(Sections sections) {
        sections.setLine(this);
        this.sections = sections;
    }

    public static Line of(Name name, Color color, Sections sections) {
        return new Line(name, color, sections);
    }

    public void update(Name name, Color color) {
        Assert.notNull(name, "이름은 필수입니다.");
        Assert.notNull(color, "색상은 필수입니다.");
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.toString();
    }

    public String getColor() {
        return color.toString();
    }

    public List<Section> getSections() {
        return sections.getList();
    }
}