package com.example.demo.form;

import lombok.Data;

@Data
//登録するときのフォームに入力する情報用クラス
public class RegistForm {

	private int id;
	
	private String title;
	
	private String memo;
}
