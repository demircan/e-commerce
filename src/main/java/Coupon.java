package main.java;

public class Coupon {

	private long minPurchase;
	private int amount;
	private DiscountType discountType;

	public Coupon(long minPurchase, int amount, DiscountType discountType) {
		super();
		this.minPurchase = minPurchase;
		this.amount = amount;
		this.discountType = discountType;
	}

	public long getMinPurchase() {
		return minPurchase;
	}

	public void setMinPurchase(long minPurchase) {
		this.minPurchase = minPurchase;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

}
