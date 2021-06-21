package hu.alkfejl.dao;

import hu.alkfejl.model.Team;

import java.util.List;

public interface TeamDAO {
    List<Team> findAll();
    String teamNameById(int id);
    Team save(Team team);
    void delete(Team team);
}
