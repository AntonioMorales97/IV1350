package se.kth.iv1350.processofsale.model;

import java.util.List;

import se.kth.iv1350.processofsale.integration.CustomerDTO;

/**
 * This is the receipt for the finished {@link Sale}.
 */
public class Receipt {
	private static int receiptNumber = 0;
	private CustomerDTO customer;
	private List<Item> items;
	private String date;
	private Costs costs;
	private CashPayment payment;

	Receipt(CustomerDTO customer, List<Item> items, String date, Costs costs, CashPayment payment) {
		this.customer = customer;
		this.items = items;
		this.date = date;
		this.costs = costs;
		this.payment = payment;
		Receipt.receiptNumber++;
	}

	@Override
	public String toString() {
		return buildReceipt();
	}

	private String buildReceipt() {
		StringBuilder receipt = new StringBuilder();
		receipt.append("RECEIPT #" + Receipt.receiptNumber + "\n");
		receipt.append(frame('=') + "\n");
		receipt.append(this.date + "\n");
		receipt.append(itemsString());
		receipt.append(customerString());
		receipt.append(paymentString());
		receipt.append(frame('='));
		return receipt.toString();
	}

	private String paymentString() {
		StringBuilder payment = new StringBuilder();
		payment.append("Payment\n");
		double runningTotal = this.costs.getRunningTotal();
		double totalCost = this.costs.getTotalCost();
		double paidAmount = this.payment.getPaidAmount();
		double change = this.payment.getChange();
		double tax = this.costs.getTax();
		double valueAddedTax = this.costs.getValueAddedTax();
		double discountAmount = this.costs.getDiscountAmount();
		payment.append("Running total: " + runningTotal + "\n");
		payment.append("VAT: " + String.format("%.2f", valueAddedTax) + " (" + tax * 100 + " %)\n");
		payment.append("Discount: " + String.format("%.2f", discountAmount) + "\n");
		payment.append("Total cost: " + String.format("%.2f", totalCost) + "\n");
		payment.append("Paid: " + paidAmount + "\n");
		payment.append("Change: " + String.format("%.2f", change) + "\n");
		return payment.toString();
	}

	private String customerString() {
		if (this.customer == null) {
			return "";
		}
		StringBuilder customer = new StringBuilder();
		customer.append("Customer\n");
		customer.append(this.customer + "\n");
		customer.append(frame('-') + "\n");
		return customer.toString();
	}

	private String itemsString() {
		StringBuilder itemsString = new StringBuilder();
		itemsString.append(frame('-') + "\n");
		itemsString.append("Items\n");
		Item item;
		for (int i = 0; i < this.items.size(); i++) {
			item = items.get(i);
			itemsString.append(item + "\n");
			itemsString.append(frame('-') + "\n");
		}
		return itemsString.toString();
	}

	private String frame(char frameType) {
		StringBuilder frame = new StringBuilder();
		for (int i = 0; i < 20; i++) {
			frame.append(frameType);
		}
		return frame.toString();
	}

}
