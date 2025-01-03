package com.example.demo.service;

import java.util.List;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TaskEntity;
import com.example.demo.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor// これがコンストラクタインジェクションを自動化(finalが付いているフィールドに対するコンストラクタが自動生成される)
//Serviceの中でのみrepositoryを定義してControllerではServiceを使ってデータベースとのやり取りを行う。
//ビジネスロジックを処理するクラス
public class TaskService {
	
	private final TaskRepository repository;
	
	/* @RequiredArgsConstructorは下の記述を省略できる
	 * public TaskService(TaskRepository repository) {
		this.repository = repository;
	}
	*/
	
	//タスクを登録するメソッド
	public void save(String title, String memo) {
		TaskEntity task = new TaskEntity();
		task.setTask_title(title);
		task.setTask_memo(memo);
		repository.save(task);
	}
	
	//タスク一覧を取得するメソッド
	public List<TaskEntity> findAll(){
		return repository.findAll();
	}
	
	//タスクを取得するメソッド
	public TaskEntity findById(int id) {
		return repository.findById(id).orElseThrow(() -> new IllegalIdentifierException("タスクが見つかりません：ID" + id));
	}
	
	//タスクを編集するメソッド
	 public void updateTask(int id, String newTitle, String newMemo) {
	        TaskEntity task = repository.findById(id).orElseThrow(
	            () -> new IllegalArgumentException("タスクが見つかりませんでした: ID=" + id)
	        );
	        task.setTask_title(newTitle);
	        task.setTask_memo(newMemo);
	        repository.save(task);
	    }
	
	//タスクを削除するメソッド
	 public void deleteTask(int id) {
	        if (!repository.existsById(id)) {
	            throw new IllegalArgumentException("タスクが見つかりませんでした: ID=" + id);
	        }
	        repository.deleteById(id);
	    }
	
}
