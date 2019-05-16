package main.java;

public class Category {

	private String title;
	private Category parentCategory;

	public Category(String title, Category parentCategory) {
		super();
		this.title = title;
		this.parentCategory = parentCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

}
