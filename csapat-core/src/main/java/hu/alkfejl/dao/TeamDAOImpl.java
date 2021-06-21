package hu.alkfejl.dao;

import hu.alkfejl.config.CsapatConfiguration;
import hu.alkfejl.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TeamDAOImpl implements TeamDAO {

    private static final String SELECT_ALL_TEAM = "SELECT * FROM TEAM";
    private static final String TEAMNAME_BY_ID = "SELECT name FROM TEAM WHERE id=?";
    private static final String INSERT_TEAM = "INSERT INTO TEAM (name, successes, nationality) VALUES (?,?,?)";
    private static final String UPDATE_TEAM = "UPDATE TEAM SET name=?, successes=?, nationality=? WHERE id=?";
    private static final String DELETE_TEAM = "DELETE FROM TEAM WHERE id=?";
    private String connectionURL;

    private static TeamDAOImpl instance;

    public TeamDAOImpl() {
        connectionURL = CsapatConfiguration.getValue("db.url");
    }

    public static TeamDAOImpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
            instance = new TeamDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Team> findAll() {

        List<Team> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_TEAM)
        ){
            while(rs.next()){
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setTeamName(rs.getString("name"));
                team.setSuccesses(rs.getString("successes"));
                team.setNationality(rs.getString("nationality"));

                result.add(team);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    public String teamNameById(int id) {
        String result = "";
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(TEAMNAME_BY_ID)
        ) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                result = rs.getString("name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    @Override
    public Team save(Team team) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = team.getId() <= 0 ? c.prepareStatement(INSERT_TEAM, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_TEAM)
        ){
            if(team.getId() > 0){ // UPDATE
                stmt.setInt(4, team.getId());
            }

            stmt.setString(1, team.getTeamName());
            stmt.setString(2, team.getSuccesses());
            stmt.setString(3, team.getNationality());


            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(team.getId() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    team.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return team;
    }

    @Override
    public void delete(Team team) {

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_TEAM)
        ) {
            stmt.setInt(1, team.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
