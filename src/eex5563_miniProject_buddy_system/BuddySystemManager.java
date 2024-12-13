package eex5563_miniProject_buddy_system;

import java.util.ArrayList;

public class BuddySystemManager {
    private ArrayList<MemoryPartition> partitions;

    // Initialize memory with a single block
    public BuddySystemManager(int totalMemory) {
        partitions = new ArrayList<>();
        partitions.add(new MemoryPartition(totalMemory)); 
    }

    // Allocate memory to a job
    public void allocateJob(Job job) {
        if (job.getSize() <= 0) {
            System.out.println("Error: Job size must be greater than 0.");
            return;
        }

        for (int i = 0; i < partitions.size(); i++) {
            MemoryPartition partition = partitions.get(i);

            if (partition.isFree() && partition.getSize() >= job.getSize()) {
                // Split the block until the size matches the job size
                while (partition.getSize() / 2 >= job.getSize() && partition.getSize() > 0) {
                    splitPartition(i);
                    partition = partitions.get(i); 
                }

                if (partition.getSize() > 0) {
                    // Allocate the block
                    partition.setFree(false);
                    partition.setJobId(job.getId());
                    System.out.println("Job " + job.getId() + " allocated to partition of size " + partition.getSize() + " KB.");
                    return;
                }
            }
        }
        System.out.println("Failed to allocate Job " + job.getId() + ": Insufficient memory.");
    }

    // Split a partition into two buddies
    private void splitPartition(int index) {
        MemoryPartition partition = partitions.get(index);

        if (partition.getSize() <= 0) {
            System.out.println("Error: Cannot split a block of size " + partition.getSize() + ".");
            return; 
        }

        int newSize = partition.getSize() / 2;

        // Replace the original block with two smaller blocks
        partitions.set(index, new MemoryPartition(newSize)); 
        partitions.add(index + 1, new MemoryPartition(newSize)); 
        System.out.println("Partition of size " + partition.getSize() + " KB split into two partitions of size " + newSize + " KB.");
    }

    // Free memory allocated to a job
    public void freeJob(int jobId) {
        for (int i = 0; i < partitions.size(); i++) {
            MemoryPartition partition = partitions.get(i);

            if (!partition.isFree() && partition.getJobId() == jobId) {
                partition.setFree(true);
                partition.setJobId(-1);
                System.out.println("Job " + jobId + " freed from partition of size " + partition.getSize() + " KB.");
                mergePartitions();
                return;
            }
        }
        System.out.println("Job " + jobId + " not found.");
    }

    // Merge adjacent free partitions
    private void mergePartitions() {
        for (int i = 0; i < partitions.size() - 1; i++) {
            MemoryPartition current = partitions.get(i);
            MemoryPartition next = partitions.get(i + 1);

            if (current.isFree() && next.isFree() && current.getSize() == next.getSize()) {
                // Merge the buddies
                current.setSize(current.getSize() * 2);
                partitions.remove(i + 1);
                System.out.println("Merged two partitions into one of size " + current.getSize() + " KB.");
                i--; 
            }
        }
    }

    // Display the current memory state
    public void displayMemoryState() {
        System.out.println("\nCurrent Memory State:");
        for (MemoryPartition partition : partitions) {
            System.out.println(partition);
        }
    }
}



