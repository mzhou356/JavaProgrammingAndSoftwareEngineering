import java.util.ArrayList;

public class RepeatFilter implements Filter {
	private ArrayList<String> watched;

	public RepeatFilter(Rater rater) {
		watched = rater.getItemsRated();
	}

	@Override
	public boolean satisfies(String id) {
		for (String movie : watched) {
			if (movie.equals(id)) {
				return false;
			}
		}

		return true;
	}
}
