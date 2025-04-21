Implementation Manual - MovieMate

Project Structure
- `Movie.java`: Base class with movie data (title, genre, rating, etc.)
- `ActionMovie.java`, `ComedyMovie.java`, etc.: Genre-specific subclasses
- `User.java`: Handles user data and file-based preferences
- `RecommendationEngine.java`: Logic for loading movies and generating recommendations
- `MovieMateUI.java`: JavaFX GUI that connects everything together

Flow of the Program
1. The user logs in → `User.loadPreferences()` checks for existing genre file.
2. The user selects genres → UI collects this into a `User` object.
3. Clicking “Get Recommendations” → `RecommendationEngine.recommendMovies()` filters the list.
4. Clicking “Save Preferences” → selected genres saved to a `.txt` file named after the user.
5. “Clear Selections” simply resets the checkboxes in the UI.

Design Considerations
- Followed modular design: UI logic, data, and business logic are separated.
- Easily extendable: new genres or features (like filtering by year) can be added quickly.
