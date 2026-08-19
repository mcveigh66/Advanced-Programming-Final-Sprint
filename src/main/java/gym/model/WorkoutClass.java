package gym.model;

public class WorkoutClass {
    private int id;
    private String className;
    private String schedule;
    private int trainerId;

    public WorkoutClass(int id, String className, String schedule, int trainerId) {
        this.id = id;
        this.className = className;
        this.schedule = schedule;
        this.trainerId = trainerId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
}