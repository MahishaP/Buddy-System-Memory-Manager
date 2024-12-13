package eex5563_miniProject_buddy_system;

public class Job {
    private int id;  // Job ID
    private int size;  // Required memory size

    public Job(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Job ID: " + id + ", Size: " + size + " KB";
    }
}

