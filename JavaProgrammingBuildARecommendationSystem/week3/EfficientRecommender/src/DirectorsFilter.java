import java.util.Arrays;

public class DirectorsFilter implements Filter {
	private String myDirectors;

	public DirectorsFilter(String directors) {
		myDirectors = directors;
	}
	
	@Override
	public boolean satisfies(String id) {
		String[] directorArray = myDirectors.split(",");
		String movieDirectors = MovieDatabase.getDirector(id);
		for (String director:directorArray){
			if (movieDirectors.indexOf(director.trim()) != -1){
				return true;
			}
		}
		return false;
	}

}
