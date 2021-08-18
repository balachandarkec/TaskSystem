package in.bala.taskms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bala.taskms.model.Task;
import in.bala.taskms.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repo;
	
	public boolean saveTask(Task task) {
		return repo.addTask(task);
	}
	
	
	public List<Task> getAllTasks(){
		return repo.getAllTask();
	}
}
