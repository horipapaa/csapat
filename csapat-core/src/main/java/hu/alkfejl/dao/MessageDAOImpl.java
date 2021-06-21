package hu.alkfejl.dao;

import hu.alkfejl.config.CsapatConfiguration;
import hu.alkfejl.model.Message;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    private static final String SELECT_TEAM_MESSAGE = "SELECT * FROM MESSAGE WHERE team_id=?";
    private static final String SELECT_PLAYER_MESSAGE = "SELECT * FROM MESSAGE WHERE from=? OR to=?";
    private static final String SAVE_MESSAGE = "INSERT INTO MESSAGE (from_player, to_player, team_id, content) VALUES (?,?,?,?)";

    private static MessageDAOImpl instance;
    private String connectionURL;

    public MessageDAOImpl() {
        connectionURL = CsapatConfiguration.getValue("db.url");
    }

    public static MessageDAOImpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
            instance = new MessageDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Message> getTeamMessages(Team team) {
        List<Message> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(SELECT_TEAM_MESSAGE)
        ) {
            stmt.setInt(1, team.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setFrom(rs.getInt("from_player"));
                message.setTo(rs.getInt("to_player"));
                message.setTeamId(rs.getInt("team_id"));
                message.setContent(rs.getString("content"));

                result.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    public List<Message> getPlayerMessages(Player player) {
        List<Message> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(SELECT_PLAYER_MESSAGE)
        ) {
            stmt.setInt(1, player.getId());
            stmt.setInt(2, player.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setFrom(rs.getInt("from_player"));
                message.setTo(rs.getInt("to_player"));
                message.setTeamId(rs.getInt("team_id"));
                message.setContent(rs.getString("content"));

                result.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    public Message saveMessage(Message message) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(SAVE_MESSAGE)
        ) {
            stmt.setInt(1, message.getFrom());
            stmt.setInt(2, message.getTo());
            stmt.setInt(3, message.getTeamId());
            stmt.setString(4, message.getContent());
            stmt.executeUpdate();
            return message;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
