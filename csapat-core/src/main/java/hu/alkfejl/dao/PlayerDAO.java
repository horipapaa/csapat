package hu.alkfejl.dao;

import hu.alkfejl.model.Player;
import hu.alkfejl.model.Team;

import java.util.List;

public interface PlayerDAO {
    List<Player> findAll();
    List<Player> findPlayersOfTeam(Team team);
    Player save(Player player);
    void delete(Player player);
}
