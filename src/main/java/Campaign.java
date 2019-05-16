package main.java;

public class Campaign {

	private Category category;
	private double amount;
	private int minProductCount;
	private DiscountType discountType;

	public Campaign(Category category, double amount, int minProductCount, DiscountType discountType) {
		this.category = category;
		this.amount = amount;
		this.minProductCount = minProductCount;
		this.discountType = discountType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getMinProductCount() {
		return minProductCount;
	}

	public void setMinProductCount(int minProductCount) {
		this.minProductCount = minProductCount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

}
