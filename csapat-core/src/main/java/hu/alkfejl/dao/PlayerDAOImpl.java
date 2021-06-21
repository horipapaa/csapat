package hu.alkfejl.dao;

import hu.alkfejl.config.CsapatConfiguration;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {

    private static final String SELECT_ALL_PLAYER = "SELECT * FROM PLAYER";
    private static final String SELECT_PLAYERS_OF_TEAM = "SELECT * FROM PLAYER WHERE team_id=?";
    private static final String INSERT_PLAYER = "INSERT INTO PLAYER (name, pos, birth_year, team_id) VALUES (?,?,?,?)";
    private static final String UPDATE_PLAYER = "UPDATE PLAYER SET name=?, pos=?, birth_year=?, team_id=? WHERE id=?";
    private static final String DELETE_PLAYER = "DELETE FROM PLAYER WHERE id=?";

    private static PlayerDAOImpl instance;

    private String connectionURL;

    public PlayerDAOImpl() {
        connectionURL = CsapatConfiguration.getValue("db.url");
    }

    public static PlayerDAOImpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
            instance = new PlayerDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Player> findAll() {
        List<Player> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_PLAYER)
        ){
            while(rs.next()){
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("name"));
                player.setPosition(rs.getString("pos"));
                player.setBirthYear(rs.getInt("birth_year"));
                player.setCurrentTeamId(rs.getInt("team_id"));
                result.add(player);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Player> findPlayersOfTeam(Team team) {
        List<Player> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(SELECT_PLAYERS_OF_TEAM)
        ) {
            stmt.setInt(1, team.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("name"));
                player.setPosition(rs.getString("pos"));
                player.setBirthYear(rs.getInt("birth_year"));
                player.setCurrentTeamId(team.getId());

                result.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    public Player save(Player player) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = player.getId() <= 0 ? c.prepareStatement(INSERT_PLAYER, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_PLAYER)
        ){
            if(player.getId() > 0){ // UPDATE
                stmt.setInt(5, player.getId());
            }
            stmt.setString(1, player.getName());
            stmt.setString(2, player.getPosition());
            stmt.setInt(3, player.getBirthYear());
            stmt.setInt(4, player.getCurrentTeamId());


            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(player.getId() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    player.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return player;
    }

    @Override
    public void delete(Player player) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_PLAYER)
        ) {
            stmt.setInt(1, player.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
