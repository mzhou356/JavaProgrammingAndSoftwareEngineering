
public class MinutesFilter implements Filter {
	private int minMinutes;
	private int maxMinutes;

	public MinutesFilter(int minMin, int maxMin) {
		minMinutes = minMin;
		maxMinutes = maxMin;
	}
	
	@Override
	public boolean satisfies(String id) {
		int minutes = MovieDatabase.getMinutes(id);
		return minutes >= minMinutes && minutes <= maxMinutes;
	}

}
