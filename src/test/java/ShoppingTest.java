package test.java;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.Campaign;
import main.java.Category;
import main.java.Coupon;
import main.java.DeliveryCostCalculator;
import main.java.DiscountType;
import main.java.Product;
import main.java.ShoppingCart;

class ShoppingTest {

	@Test
	void discountTest() {

		Category foodCategory = new Category("Food", null);
		Category perfumeCategory = new Category("Perfume", null);

		Product apple = new Product("apple", 20, foodCategory);
		Product lemon = new Product("lemon", 10, foodCategory);

		Campaign campaign = new Campaign(perfumeCategory, 30, 5, DiscountType.RATE);
		Coupon coupon = new Coupon(150, 10, DiscountType.RATE);

		ShoppingCart cart = new ShoppingCart();
		cart.addItem(apple, 6);
		cart.addItem(lemon, 8);

		cart.applyDiscounts(campaign);
		cart.applyCoupon(coupon);
		
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(1.80,1.90,2.99);
		cart.setDeliveryCostCalculator(deliveryCostCalculator);

		cart.print();

		assertEquals(140.00, cart.getTotalAmountAfterDiscounts());

	}


	@Test
	void couponMinAmountTest() {

		Category foodCategory = new Category("Food", null);
		Product apple = new Product("apple", 20, foodCategory);

		Coupon coupon = new Coupon(150, 10, DiscountType.RATE);

		ShoppingCart cart = new ShoppingCart();
		cart.addItem(apple, 6);

		cart.applyCoupon(coupon);

		cart.print();

		// total amount 20x6 = 120 less than coupon minAmount criteria 
		assertEquals(120.00, cart.getTotalAmountAfterDiscounts());
	}

	@Test
	void campaignMinQuantityTest() {

		Category foodCategory = new Category("Food", null);
		Product apple = new Product("apple", 20, foodCategory);

		Campaign campaign = new Campaign(foodCategory, 30, 5, DiscountType.RATE);

		ShoppingCart cart = new ShoppingCart();
		cart.addItem(apple, 4);

		cart.print();

		// total amount 20x4 = 80,  apple quantity less than campaign minquantity criteria 
		assertEquals(80.00, cart.getTotalAmountAfterDiscounts());
	}

	@Test
	void deliveryCostTest() {

		Category foodCategory = new Category("Food", null);

		Product apple = new Product("apple", 20, foodCategory);
		Product lemon = new Product("lemon", 10, foodCategory);

		ShoppingCart cart = new ShoppingCart();
		cart.addItem(apple, 6);
		cart.addItem(lemon, 8);
		
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(1.80,1.90,2.99);
		cart.setDeliveryCostCalculator(deliveryCostCalculator);

		// product count 2 x 1.90   + delivery count 1 x 1.80 + fixed cost 2.99
		assertEquals(8.59, cart.getDeliveryCost());
	}


}
