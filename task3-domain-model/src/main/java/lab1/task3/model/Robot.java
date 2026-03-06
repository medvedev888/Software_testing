package lab1.task3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Robot {
    private final UUID id;
    private RobotState state;
    private Position position;
    private Head head;

    public void setHead(Head head) {
        this.head = head;
    }

    private final List<String> trace = new ArrayList<>();

    public Robot(Position position) {
        this.id = UUID.randomUUID();
        this.position = position;
        this.state = RobotState.SITTING;
        this.head = new Head(this);
    }

    public void standUp() {
        if (state == RobotState.SITTING) {
            state = RobotState.STANDING;
            addTrace("робот встал");
        }
    }

    public void attemptWalk() {
        if (state == RobotState.STANDING) {
            state = RobotState.MOVING;
            addTrace("робот попытался пройти");
        }
    }

    public void stop() {
        state = RobotState.STOPPED;
        addTrace("робот остановился");
    }

    public void lookAt(Person person, FocusPoint focusPoint) {
        addTrace("робот смотрит на " + focusPoint.getDescription() + " " + person.getName());
    }

    void addTrace(String action) {
        trace.add(action);
    }

    public Head getHead() {
        return head;
    }

    public List<String> getTrace() {
        return trace;
    }

    public RobotState getState() {
        return state;
    }

    public Position getPosition() {
        return position;
    }

    public UUID getId() {
        return id;
    }

    public void setState(RobotState state) {
        this.state = state;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}