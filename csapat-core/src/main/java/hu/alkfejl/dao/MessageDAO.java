package hu.alkfejl.dao;

import hu.alkfejl.model.Message;
import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;

import java.util.List;

public interface MessageDAO {
    List<Message> getTeamMessages(Team team);
    List<Message> getPlayerMessages(Player player);
    Message saveMessage(Message message);
}
