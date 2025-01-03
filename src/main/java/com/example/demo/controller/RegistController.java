package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.TaskEntity;
import com.example.demo.form.RegistForm;
import com.example.demo.service.TaskService;

import lombok.RequiredArgsConstructor;

//home TODOを追加するページ
//Controllerは「HTTPリクエストを処理する」役割を持つだけのクラス
@Controller
@RequiredArgsConstructor// これがコンストラクタインジェクションを自動化（finalが付いているフィールドに対するコンストラクタが自動生成される）
public class RegistController {

	private final TaskService taskService;
	
/* @RequiredArgsConstructorは下の記述を省略できる
 * 	public RegistController(TaskService taskService) {
 * 		this.taskService = taskService;
*/	
	
	@GetMapping("/home")
	public String view(Model model, RegistForm form){
		return "home";
	}
		
	@PostMapping("/home")
	public String regist(RegistForm form) {
		taskService.save(form.getTitle(), form.getMemo());
		return "redirect:/taskList";
	}
	
	@GetMapping("/taskList")
	public String viewList(Model model) {
		model.addAttribute("tasks", taskService.findAll());
		return "taskList";
	}
	
	@GetMapping("/taskEdit")
	public String taskEdit(@RequestParam("id") int id, Model model) {
		TaskEntity task = taskService.findById(id);
		
		RegistForm form = new RegistForm();
		form.setId(task.getTask_id());
		form.setTitle(task.getTask_title());
		form.setMemo(task.getTask_memo());
		model.addAttribute("form", form);
		return "taskEdit";
	}
	
	@PostMapping("/taskEdit")
	public String taskUpdate(RegistForm form) {
		taskService.updateTask(form.getId(), form.getTitle(), form.getMemo());
		return "redirect:/taskList";
	}

	@PostMapping("/taskDelete")
	public String taskDelete(@RequestParam("id") int id, Model model){
		taskService.deleteTask(id);
		return "redirect:/taskList";
	}
}


