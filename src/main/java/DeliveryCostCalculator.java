package main.java;

import java.util.HashMap;

public class DeliveryCostCalculator {

	private double costPerDelivery;
	private double costPerProduct;
	private double fixedCost;

	public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
		super();
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
		this.fixedCost = fixedCost;
	}

	public double getCostPerDelivery() {
		return costPerDelivery;
	}

	public void setCostPerDelivery(double costPerDelivery) {
		this.costPerDelivery = costPerDelivery;
	}

	public double getCostPerProduct() {
		return costPerProduct;
	}

	public void setCostPerProduct(double costPerProduct) {
		this.costPerProduct = costPerProduct;
	}

	public double getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(double fixedCost) {
		this.fixedCost = fixedCost;
	}

	public double calculateFor(ShoppingCart cart) {

		// find parent category count , condition belonging to distinct category
		long parentCategoryCount = cart.getProductMap().keySet().stream().filter(x -> x.getParentCategory() != null)
				.count();

		int numberOfDeliveries = cart.getProductMap().size() + (int) parentCategoryCount;
		int numberOfProducts = 0;
		
		// find distinct product count
		for(HashMap<Product, Integer> entry : cart.getProductMap().values()) {
			numberOfProducts += entry.size();
		}

		return (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost;

	}

}
