package main.java;

public interface IShoppingCart {

	double getTotalAmountAfterDiscounts();

	double getCouponDiscount();

	double getCampaignDiscount();

	double getDeliveryCost();

	void applyCoupon(Coupon coupon);

	void applyDiscounts(Campaign... campaign);

	void addItem(Product product, Integer quantity);
}
