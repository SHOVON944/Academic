package attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private DataStorage dataStorage;
    private DataManager dataManager;

    public TaskService(DataStorage dataStorage, DataManager dataManager) {
        this.dataStorage = dataStorage;
        this.dataManager = dataManager;
    }

    public void addTask(String taskName, TaskCategory category, LocalTime startTime, LocalTime endTime, LocalDate date) {
        Task newTask = new Task(taskName, category, startTime, endTime, date);
        dataStorage.getTasks().add(newTask);
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Task '" + taskName + "' added successfully." + ConsoleColors.RESET);
    }

    public List<Task> getTasksForDate(LocalDate date) {
        return dataStorage.getTasks().stream()
                .filter(task -> task.getDate() != null && task.getDate().isEqual(date))
                .collect(Collectors.toList());
    }

    // You can add more methods here like updateTaskStatus, deleteTask, etc.
    public void updateTaskStatus(Task task, TaskStatus newStatus) {
        task.setStatus(newStatus);
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Task '" + task.getTaskName() + "' status updated to " + newStatus + "." + ConsoleColors.RESET);
    }

    public void deleteTask(Task task) {
        dataStorage.getTasks().remove(task);
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Task '" + task.getTaskName() + "' deleted successfully." + ConsoleColors.RESET);
    }

    public void deleteAllTasks(TaskCategory category) {
        List<Task> tasksToKeep = dataStorage.getTasks().stream()
                .filter(task -> !task.getCategory().equals(category))
                .collect(Collectors.toList());
        int deletedCount = dataStorage.getTasks().size() - tasksToKeep.size();
        dataStorage.setTasks(tasksToKeep);
        dataManager.saveData(dataStorage);
        if (deletedCount > 0) {
            System.out.println(ConsoleColors.GREEN + deletedCount + " tasks from category '" + category.name() + "' deleted successfully." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW + "No tasks found for category '" + category.name() + "' to delete." + ConsoleColors.RESET);
        }
    }

    // Method to move completed/incomplete tasks to history (simulated for now, actual history feature to be implemented later)
    public void moveOldTasksToHistory() {
        // For now, let's just clear tasks older than today.
        // A proper history section will involve moving them to a separate historical list.
        LocalDate today = LocalDate.now();
        List<Task> currentTasks = dataStorage.getTasks();
        List<Task> tasksToKeep = new ArrayList<>();
        List<Task> historicalTasks = new ArrayList<>(); // This list would eventually go into a separate history collection

        for (Task task : currentTasks) {
            if (task.getDate().isBefore(today)) {
                // If the task is older than today, consider it "historical"
                historicalTasks.add(task);
            } else {
                tasksToKeep.add(task);
            }
        }
        dataStorage.setTasks(tasksToKeep); // Only keep current and future tasks
        // Logic to store historicalTasks would go here, e.g., dataStorage.getHistory().add(historicalTasks);
        if (!historicalTasks.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + historicalTasks.size() + " old tasks moved to simulated history." + ConsoleColors.RESET);
        }
        dataManager.saveData(dataStorage);
    }

    public void updateOverdueTasks() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        boolean changed = false;

        System.out.println(ConsoleColors.YELLOW_BOLD + "\n✨ Checking for overdue tasks..." + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "  Current System Date: " + Utils.formatDateWithDay(today) + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "  Current System Time: " + Utils.formatTime(now) + ConsoleColors.RESET);

        if (dataStorage.getTasks().isEmpty()) {
            System.out.println(ConsoleColors.CYAN + "  No tasks to evaluate." + ConsoleColors.RESET);
            return;
        }

        System.out.println(ConsoleColors.PURPLE + "\n  Evaluating tasks:" + ConsoleColors.RESET);
        for (Task task : dataStorage.getTasks()) {
            // Only show relevant evaluation messages, not for every task if not overdue
            if (task.getStatus() == TaskStatus.PENDING && task.getDate() != null) {
                if (task.getDate().isBefore(today)) {
                    System.out.println(ConsoleColors.RED + "    🚨 Task '" + task.getTaskName() + "' (due " + Utils.formatDateWithDay(task.getDate()) + ") is overdue. Marking INCOMPLETE." + ConsoleColors.RESET);
                    task.setStatus(TaskStatus.INCOMPLETE);
                    changed = true;
                } else if (task.getDate().isEqual(today)) {
                    if (task.getEndTime() != null && now.isAfter(task.getEndTime())) {
                        System.out.println(ConsoleColors.RED + "    🚨 Task '" + task.getTaskName() + "' (due " + Utils.formatTime(task.getEndTime()) + " today) is overdue. Marking INCOMPLETE." + ConsoleColors.RESET);
                        task.setStatus(TaskStatus.INCOMPLETE);
                        changed = true;
                    }
                }
            }
        }

        if (changed) {
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN_BOLD + "\n✅ Overdue PENDING tasks updated and saved successfully!" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.CYAN + "\n  No pending tasks found to be overdue." + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.YELLOW_BOLD + "✨ Overdue task check complete." + ConsoleColors.RESET);
    }
}
