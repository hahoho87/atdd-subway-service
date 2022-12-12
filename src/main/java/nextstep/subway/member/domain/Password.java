package nextstep.subway.member.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.Assert;

@Embeddable
public class Password {

	@Column(name = "password", nullable = false)
	private String value;

	protected Password() {
	}

	private Password(String value) {
		Assert.hasText(value, "비밀번호 값은 필수입니다.");
		this.value = value;
	}

	public static Password from(String value) {
		return new Password(value);
	}

	public boolean notEquals(Password password) {
		return !this.equals(password);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Password password = (Password) o;
		return value.equals(password.value);
	}

	@Override
	public String toString() {
		return value;
	}
}
