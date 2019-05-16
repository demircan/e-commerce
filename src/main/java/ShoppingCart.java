package main.java;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart implements IShoppingCart {

	private double totalAmount;
	private double campaignDiscount;
	private double couponDiscount;
	private DeliveryCostCalculator deliveryCostCalculator;
	private HashMap<Category, HashMap<Product, Integer>> productMap = new HashMap<Category, HashMap<Product, Integer>>();

	@Override
	public double getTotalAmountAfterDiscounts() {
		return totalAmount - (campaignDiscount + couponDiscount);
	}

	@Override
	public double getCouponDiscount() {
		return couponDiscount;
	}

	@Override
	public double getCampaignDiscount() {
		return campaignDiscount;
	}

	@Override
	public double getDeliveryCost() {

		if(deliveryCostCalculator != null) {
			return deliveryCostCalculator.calculateFor(this);	
		} else {
			return 0;
		}

	}

	@Override
	public void applyCoupon(Coupon coupon) {

		if ((totalAmount - campaignDiscount) > coupon.getMinPurchase()) {
			couponDiscount = coupon.getAmount();
			switch (coupon.getDiscountType()) {
			case RATE:
				couponDiscount = (coupon.getAmount() / 100.00) * (totalAmount - campaignDiscount);
				break;
			case AMOUNT:
				couponDiscount = coupon.getAmount();
				break;
			}
		}
	}

	@Override
	public void applyDiscounts(Campaign... campaign) {

		for (Campaign tempCampaign : campaign) {

			HashMap<Product, Integer> productObj = new HashMap<>();
			productMap.forEach((key,value) -> {
				if( (key.equals(tempCampaign.getCategory()) || tempCampaign.getCategory().equals(key.getParentCategory())) && (value.values().stream().mapToInt(Integer::intValue).sum() > tempCampaign.getMinProductCount())){

					for (Map.Entry<Product, Integer> entry : value.entrySet()) {

						Product product = entry.getKey();
						int quantity = entry.getValue();

						switch (tempCampaign.getDiscountType()) {
						case RATE:
							campaignDiscount += (product.getPrice() * quantity) * (tempCampaign.getAmount() / 100.00);
							break;
						case AMOUNT:
							campaignDiscount += tempCampaign.getAmount();
							break;
						}

					}
				}
			});

		}
	}

	@Override
	@SuppressWarnings("serial")
	public void addItem(Product product, Integer quantity) {

		if (productMap.containsKey(product.getCategory())) {
			productMap.get(product.getCategory()).put(product, quantity);
		} else {
			productMap.put(product.getCategory(), new HashMap<Product, Integer>() {{ put(product, quantity);}});
		}

		totalAmount += product.getPrice() * quantity;
	}

	public Map<Category, HashMap<Product, Integer>> getProductMap() {
		return productMap;
	}

	public DeliveryCostCalculator getDeliveryCostCalculator() {
		return deliveryCostCalculator;
	}

	public void setDeliveryCostCalculator(DeliveryCostCalculator deliveryCostCalculator) {
		this.deliveryCostCalculator = deliveryCostCalculator;
	}

	public void print() {

		for (Category category : productMap.keySet()) {

			System.out.println("Category: " + category.getTitle());

			HashMap<Product, Integer> productUnit = productMap.get(category);

			for (Product product : productUnit.keySet()) {
				System.out.println("Product : " + product.getTitle() + "Price: " + product.getPrice() + " Quantity: "
						+ productUnit.get(product) + " Unit Price: " + (product.getPrice() * productUnit.get(product)));
			}
		}

		System.out.println("Total Price: " + totalAmount);
		System.out.println("Total Discounts: " + (campaignDiscount + couponDiscount));
		System.out.println("Total Amount After Discounts: " + getTotalAmountAfterDiscounts());
		System.out.println("Delivery Cost: " + getDeliveryCost());
		System.out.println("***************************************");

	}

}
