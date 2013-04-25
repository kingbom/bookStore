package bookstore;

public class CreditCard {

	public enum CreditCardStatus {
		NEW, INVALID, VALID
	};

	private String addressFirstLine;
	private boolean addressFirstLineMissing=true;
	private String addressSecondLine;
	private boolean addressSecondLineMissing=true;	
	private String city;
	private Boolean cityMissing=true;
	private String state;
	private boolean stateMissing = true;
	private String zipcode;
	private boolean zipcodeMissing = true;
	
	private String creditCardType = "";
	private boolean creditCardTypeMissing = true;
	private String cardNumber ="";
	private boolean cardNumberMissing = true;
	private String expMonth = "January";
	private String expYear = "2013";
	private boolean expDateMissing = true;
	private CreditCardStatus cardStatus = CreditCardStatus.NEW;

	public void validateCard () {
		
		if (creditCardTypeMissing ||
				cardNumberMissing ||
				expDateMissing ||
				addressFirstLineMissing ||
				addressSecondLineMissing ||
				cityMissing ||
				stateMissing ||
				zipcodeMissing) {
			cardStatus = CreditCardStatus.INVALID;
		}
		else {
			cardStatus = CreditCardStatus.VALID;
		}
	}
	
	public boolean isCreditCardValid () {
		return (cardStatus == CreditCardStatus.INVALID) ? false : true;
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
//todo - check to make sure that the credit card exp date is today's month and year or later
		return true;
	}

	public String getAddressFirstLine() {
		return addressFirstLine;
	}

	public void setAddressFirstLine(String addressFirstLine) {
		this.addressFirstLine = addressFirstLine;
		this.addressFirstLineMissing = (addressFirstLine == "null" || addressFirstLine.isEmpty()) ? true : false;

	}

	public String getAddressSecondLine() {
		return addressSecondLine;
	}

	public void setAddressSecondLine(String addressSecondLine) {
		this.addressSecondLine = addressSecondLine;
		this.addressSecondLineMissing = (addressSecondLine == null || addressSecondLine.isEmpty()) ? true : false;

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		this.cityMissing = (city == null || city.isEmpty()) ? true : false;

	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		this.stateMissing = (state == null || state.isEmpty()) ? true : false;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
		this.zipcodeMissing = (zipcode == null || zipcode.isEmpty()) ? true : false;
	}

	public boolean isAddressFirstLineMissing() {
		return (this.cardStatus == CreditCardStatus.NEW) ? false : addressFirstLineMissing;
	}

	public boolean isAddressSecondLineMissing() {
		return (this.cardStatus == CreditCardStatus.NEW) ? false : addressSecondLineMissing;
	}
	
	public boolean isCityMissing () {
		return (this.cardStatus == CreditCardStatus.NEW) ? false : cityMissing;
	}

	public boolean isStateMissing() {
		return (this.cardStatus == CreditCardStatus.NEW) ? false : stateMissing;
	}

	public boolean isZipcodeMissing() {
		return (this.cardStatus == CreditCardStatus.NEW) ? false : zipcodeMissing;
	}
	public boolean isAddressFirstLineEmpty() {
		return this.addressFirstLineMissing;
	}

	public boolean isAddressSecondLineEmpty() {
		return this.addressSecondLineMissing;
	}
	
	public boolean isCityEmpty () {
		return this.cityMissing;
	}

	public boolean isStateEmpty() {
		return this.stateMissing;
	}

	public boolean isZipcodeEmpty() {
		return this.zipcodeMissing;
	}
}
