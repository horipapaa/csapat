package hu.alkfejl.model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class Player {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty position = new SimpleStringProperty(this, "position");
    private IntegerProperty birthYear = new SimpleIntegerProperty(this, "birthYear");
    private IntegerProperty currentTeamId = new SimpleIntegerProperty(this, "currentTeamId");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public int getBirthYear() {
        return birthYear.get();
    }

    public IntegerProperty birthYearProperty() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear.set(birthYear);
    }

    public int getCurrentTeamId() {
        return currentTeamId.get();
    }

    public IntegerProperty currentTeamIdProperty() {
        return currentTeamId;
    }

    public void setCurrentTeamId(int currentTeamId) {
        this.currentTeamId.set(currentTeamId);
    }
}


