package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.TeamDAO;
import hu.alkfejl.dao.TeamDAOImpl;
import hu.alkfejl.model.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddModifyTeamController {

    private TeamDAO teamDAO = new TeamDAOImpl();

    private Team team;

    public void setTeam(Team t) {
        this.team = t;

        teamNameTextField.textProperty().bindBidirectional(team.teamNameProperty());
        successesTextField.textProperty().bindBidirectional(team.successesProperty());
        nationalityTextField.textProperty().bindBidirectional(team.nationalityProperty());
    }

    @FXML
    private TextField teamNameTextField;
    @FXML
    private TextField successesTextField;
    @FXML
    private TextField nationalityTextField;

    @FXML
    public void onCancel() {
        App.loadFXML("/fxml/main_window.fxml");
    }

    @FXML
    public void onSave() {
        teamDAO.save(team);
        App.loadFXML("/fxml/main_window.fxml");
    }

}
