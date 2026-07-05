package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class AccountController {

	private final HttpSession session;
	private final Account account;
	
	public AccountController(HttpSession session, Account account) {
	    this.session = session;
	    this.account = account;
	}
	
	// 初日の追加作業です！
	// 2日目の追加作業です！
	// 2日目の追加作業です！2
	// 2日目の追加作業です！3
	// 3日目の追加作業です！
	// 3日目の追加作業です！2
	// 4日目の追加作業です！
	// 5日目の追加作業です！

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		// セッション情報を全てクリアする
		session.invalidate();

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam String name,
			Model model) {
		// 名前が空の場合にエラーとする
		if (name == null || name.length() == 0) {
			model.addAttribute("message", "名前を入力してください");
			return "login";
		}

		// セッション管理されたアカウント情報に名前をセット
		account.setName(name);

		// 「/items」へのリダイレクト
		return "redirect:/items";
	}
}
