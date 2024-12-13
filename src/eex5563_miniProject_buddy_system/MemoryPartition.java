package eex5563_miniProject_buddy_system;

public class MemoryPartition {
    private int size;  // Size of the memory block
    private boolean isFree;  // Allocation status
    private int jobId;  // ID of the job allocated 

    public MemoryPartition(int size) {
        this.size = size;
        this.isFree = true;
        this.jobId = -1;  // No job allocated
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        String status = isFree ? "Free" : "Allocated to Job " + jobId;
        return "Partition: " + size + " KB - " + status;
    }
}

