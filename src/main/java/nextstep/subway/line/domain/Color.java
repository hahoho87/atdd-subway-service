package nextstep.subway.line.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.Assert;

@Embeddable
public class Color {

	@Column(name = "color")
	private String value;

	protected Color() {
	}

	private Color(String value) {
		Assert.hasText(value, "색상은 필수 값입니다.");
		this.value = value;
	}

	public static Color from(String value) {
		return new Color(value);
	}

	public String value() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Color color = (Color)o;
		return Objects.equals(value, color.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return value;
	}
}
