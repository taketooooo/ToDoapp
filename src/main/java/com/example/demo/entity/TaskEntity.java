package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "task_info")
@Data
//データベースに登録する用のクラス
public class TaskEntity {

	@Id
	/*ここの変数名がデータベースと一致していない場合は、
	 *@Column(name="")で明示的に名前を設定する必要がある。
	 *そうするとEntityの変数名をHTML内で使える。
	 */
	//@Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int task_id;
	
	//@Column(name = "task_title")
	private String task_title;

	//@Column(name = "task_memo")
	private String task_memo;
}
