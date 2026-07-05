package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminController {

	private final CategoryRepository categoryRepository;
	
	public CategoryAdminController(CategoryRepository categoryRepository) {
	    this.categoryRepository = categoryRepository;
	}

	// カテゴリー一覧表示
	@GetMapping
	public String index(
			@RequestParam(defaultValue = "") Integer categoryId,
			Model model) {

		// テーブルから全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		return "admin/categories";
	}

	// 新規登録画面の表示
	@GetMapping("/add")
	public String create() {

		return "admin/addCategory";
	}

	// 新規登録処理
	@PostMapping("/add")
	public String store(
			@RequestParam(defaultValue = "") String name,
			Model model) {

		Category category = new Category(name);

		// テーブルへの反映（INSERT）
		categoryRepository.save(category);
		return "redirect:/admin/categories";
	}

	// 更新画面表示
	@GetMapping("/{id}/edit")
	public String edit(
	        @PathVariable Integer id, 
	        Model model) {

		// テーブルをID（主キー）で検索
		Category category = categoryRepository.findById(id).get();
		model.addAttribute("category", category);

		return "admin/editCategory";
	}

	// 更新処理
	@PostMapping("/{id}/edit")
	public String update(
			@PathVariable Integer id,
			@RequestParam(defaultValue = "") String name,
			Model model) {

		// テーブルをID（主キー）で検索
		Category category = categoryRepository.findById(id).get();
		
		// セッターを利用して、categoryオブジェクトのフィールドを書き換える
		category.setName(name);
		
		// itemsテーブルへの反映（UPDATE）
		categoryRepository.save(category);
		return "redirect:/admin/categories";
	}

	// 削除処理
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Integer id, Model model) {

		// テーブルから削除（DELETE）
		categoryRepository.deleteById(id);
		return "redirect:/admin/categories";
	}

}
