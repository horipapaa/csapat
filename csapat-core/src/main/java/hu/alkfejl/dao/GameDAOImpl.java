package hu.alkfejl.dao;

import hu.alkfejl.config.CsapatConfiguration;
import hu.alkfejl.model.Game;
import hu.alkfejl.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameDAOImpl implements GameDAO {

    private static GameDAOImpl instance;

    private String connectionURL;

    public GameDAOImpl() {
        connectionURL = CsapatConfiguration.getValue("db.url");
    }

    public static GameDAOImpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
            instance = new GameDAOImpl();
        }
        return instance;
    }

    private static final String SELECT_FUTURE_GAMES = "SELECT * FROM GAME WHERE completed=0";
    private static final String SELECT_COMPLETED_GAMES = "SELECT * FROM GAME WHERE completed=1";
    private static final String ADD_GAME = "INSERT INTO GAME (game_date, referee, location, home_team, away_team, travel_info, completed) VALUES (?,?,?,?,?,?, false)";
    private static final String COMPLETE_GAME = "UPDATE GAME SET result=?, completed=1 WHERE id=?";

    @Override
    public List<Game> getFutureGames() {
        List<Game> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_FUTURE_GAMES)
        ){
            while(rs.next()){
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setDate(rs.getString("game_date"));
                game.setReferee(rs.getString("referee"));
                game.setLocation(rs.getString("location"));
                game.setHomeTeamId(rs.getInt("home_team"));
                game.setAwayTeamId(rs.getInt("away_team"));
                game.setTravelInfo(rs.getString("travel_info"));
                game.setCompleted(rs.getBoolean("completed"));
                result.add(game);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public List<Game> getCompletedGames() {
        List<Game> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_COMPLETED_GAMES)
        ){
            while(rs.next()){
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setDate(rs.getString("game_date"));
                game.setReferee(rs.getString("referee"));
                game.setLocation(rs.getString("location"));
                game.setHomeTeamId(rs.getInt("home_team"));
                game.setAwayTeamId(rs.getInt("away_team"));
                game.setTravelInfo(rs.getString("travel_info"));
                game.setCompleted(rs.getBoolean("completed"));
                game.setResult(rs.getString("result"));
                result.add(game);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean addGame(Game game) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(ADD_GAME)
        ){
            stmt.setString(1, game.getDate());
            stmt.setString(2, game.getReferee());
            stmt.setString(3, game.getLocation());
            stmt.setInt(4, game.getHomeTeamId());
            stmt.setInt(5, game.getAwayTeamId());
            stmt.setString(6, game.getTravelInfo());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean completeGame(Game game) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(COMPLETE_GAME)
        ){
            stmt.setInt(2, game.getId());
            stmt.setString(1, game.getResult());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
