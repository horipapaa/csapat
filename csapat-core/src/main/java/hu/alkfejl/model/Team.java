package hu.alkfejl.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class Team {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty teamName = new SimpleStringProperty(this, "teamName");
    private StringProperty successes = new SimpleStringProperty(this, "successes");
    private StringProperty nationality = new SimpleStringProperty(this, "nationality");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public String getSuccesses() {
        return successes.get();
    }

    public StringProperty successesProperty() {
        return successes;
    }

    public void setSuccesses(String successes) {
        this.successes.set(successes);
    }

    public String getNationality() {
        return nationality.get();
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }
}
