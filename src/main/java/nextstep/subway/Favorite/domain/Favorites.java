package nextstep.subway.Favorite.domain;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

public class Favorites {

	private List<Favorite> favorites;

	private Favorites(List<Favorite> favorites) {
		Assert.notNull(favorites, "즐겨찾기 목록은 필수입니다.");
		this.favorites = favorites;
	}

	public static Favorites from(List<Favorite> favorites) {
		return new Favorites(favorites);
	}

	public <R> List<R> mapToList(Function<Favorite, R> mapper) {
		return favorites.stream()
			.map(mapper)
			.collect(Collectors.toList());
	}

}