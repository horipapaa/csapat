package hu.alkfejl.dao;

import hu.alkfejl.model.Game;

import java.util.List;

public interface GameDAO {
    List<Game> getFutureGames();
    List<Game> getCompletedGames();
    boolean addGame(Game game);
    boolean completeGame(Game game);
}
