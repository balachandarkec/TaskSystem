package in.bala.taskms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bala.taskms.model.Task;

@Repository
@Transactional
public class TaskRepository {
	
	@Autowired
	private SessionFactory factory;
	
	
	private Session getCurrentSession() {
		return factory.getCurrentSession();
	}
	
	
	// Add New Task
	
	public boolean addTask(Task task) {
		boolean status=false;
		if(getCurrentSession().save(task)!=null) {
			status=true;
		}
		return status;
	}
	
	//Get All tasks
	
	public List<Task> getAllTask() {
		List<Task> allTask=getCurrentSession().createQuery("from Task").list();
		return allTask;
		
	}
	
	// Delete  Task from ORM
	
	public boolean deleteTask(int id) {
		boolean status=false;
		
		Task target=getCurrentSession().get(Task.class, id);
		try {
			getCurrentSession().remove(target);
			status=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	

}
