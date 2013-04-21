package bookstore;

public class CreditCard {

	public enum CreditCardStatus {
		NEW, INVALID, VALID
	};
	private String creditCardType = "";
	private boolean creditCardTypeMissing = true;
	private String cardNumber ="";
	private boolean cardNumberMissing = true;
	private String expMonth = "January";
	private String expYear = "2013";
	private boolean expDateMissing = true;
	private CreditCardStatus cardStatus = CreditCardStatus.NEW;

	public boolean validateCard () {
		
		if (creditCardTypeMissing ||
				cardNumberMissing ||
				expDateMissing) {
			cardStatus = CreditCardStatus.INVALID;
			return true;
		}
		else {
			cardStatus = CreditCardStatus.VALID;
			return true;
		}
	}
	
	public String getCreditCardType() {
		return creditCardType;
	}
	
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
		creditCardTypeMissing = (creditCardType == null || creditCardType.isEmpty()) ? true : false;
	}
	
	public boolean isCreditCardTypeMissing() {
		return (this.cardStatus == CreditCardStatus.NEW) ? false : creditCardTypeMissing;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
		cardNumberMissing = (cardNumber == null || cardNumber.isEmpty()) ? true : false;
	}
	
	public boolean isCardNumberMissing() {
		return (this.cardStatus == CreditCardStatus.NEW) ? false : cardNumberMissing;
	}
	
	public String getExpMonth() {
		return expMonth;
	}
	
	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
		expDateMissing = (expMonth == null || expYear == null || expMonth.isEmpty() || expYear.isEmpty()) ? true : false;
	}
	
	public String getExpYear() {
		return expYear;
	}
	
	public void setExpYear(String expYear) {
		this.expYear = expYear;
		expDateMissing = (expMonth == null || expYear == null || expMonth.isEmpty() || expYear.isEmpty()) ? true : false;
	}
	
	public boolean isExpDateMissing() {
		return (this.cardStatus == CreditCardStatus.NEW) ? false : expDateMissing;
	}
	
	public boolean isExpDateValid() {
//todo - check to make sure that the credit card exp date is today's month and year of later
		return true;
	}
}
