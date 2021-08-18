package in.bala.taskms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.bala.taskms.model.Task;
import in.bala.taskms.service.TaskService;

@Controller
public class TaskController {
	@Autowired
	private TaskService service;
	
	
	@GetMapping("/")
	public String showHomePage(Model model) {
		model.addAttribute("tasks",service.getAllTasks());
		return "home-page";
	}
	
	
	@PostMapping("/add")
	//@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addNewTask(@RequestParam("name") String taskname,@RequestParam("status") String status) {
		
		String response="home-page";
		
		System.out.println("received data : "+ taskname+ "status "+status);
		Task newTask=new Task();
		newTask.setName(taskname);
		newTask.setStatus(status);
		if(service.saveTask(newTask)) {
			response="redirect:/";
		}
		
		return response;
	}
	
	//@GetMapping("/delete/{id}")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteTask(@PathVariable("id") int taskid) {
		String status="home-page";
		if(service.removeTask(taskid)) {
			status="redirect:/";
		}
		
		return status;
	}

}
