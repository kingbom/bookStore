package bookstore;

public class UserBean {

	public enum UserStatus {
		NEW, INVALID, VALID, LOGGED_IN
	};
	
	private String emailAddress;
	boolean emailAddressMissing = true;
	boolean emailAddressDupl = true;
	private String password;
	boolean passwordMissing = true;;
	private String firstName;
	boolean firstNameMissing = true;
	private String lastName;
	boolean lastNameMissing = true;
	private Address address;
	private UserStatus userStatus = UserStatus.NEW;

	public UserBean() {
		this.setAddress(new Address());
	}
	
	static UserBean createUser(String emailAddress, String password, String firstName, String lastName,
			String addrFirstLine, String addrSecondLine, String addrCity, String addrState, String addrZip) {
		
		UserBean userBean = new UserBean();
		userBean.setEmailAddress(emailAddress);
		userBean.setFirstName(firstName);
		userBean.setLastName(lastName);
		userBean.setAddress(new Address(addrFirstLine, addrSecondLine, addrCity, addrState, addrZip));
		return userBean;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setEmailAddress(String emailAddress) {
		System.out.println("Email2: " + emailAddress);
		emailAddressMissing = (emailAddress == null || emailAddress.isEmpty()) ? true : false;
		if (!emailAddressMissing) {
			emailAddressDupl = (UserDB.emailExists(emailAddress)) ? true : false;
		}
		this.emailAddress = emailAddress;
	}
	
	public boolean isEmailAddressMissing() {
		return (userStatus == UserStatus.NEW) ? false: emailAddressMissing;
	}
	
	public boolean isEmailAddressDupl() {
		return emailAddressDupl;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isPasswordMissing() {
		return (userStatus == UserStatus.NEW) ? false: passwordMissing;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		this.firstNameMissing = (firstName == null || firstName.isEmpty()) ? true : false;
	}
	
	public boolean isFirstNameMissing() {
		return (userStatus == UserStatus.NEW) ? false: firstNameMissing;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		this.lastNameMissing = (lastName == null || lastName.isEmpty()) ? true : false;
	}
	
	public boolean isLastNameMissing() {
		return (userStatus == UserStatus.NEW) ? false: lastNameMissing;
	}
	
	public String getAddrFirstLine() {
		return this.address.getAddrFirstLine();
	}

	public void setAddrFirstLine(String addrFirstLine) {
		this.address.setAddrFirstLine(addrFirstLine);
	}

	public boolean isAddrFirstLineMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : this.address.isAddrFirstLineMissing();
	}
	
	public String getAddrSecondLine() {
		return this.address.getAddrSecondLine();
	}

	public void setAddrSecondLine(String addrSecondLine) {
		this.address.setAddrSecondLine(addrSecondLine);
	}

	public boolean isAddrSecondLineMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : this.address.isAddrSecondLineMissing();
	}
	
	public String getAddrCity() {
		return this.address.getAddrCity();
	}

	public void setAddrCity(String addrCity) {
		this.address.setAddrCity(addrCity);
	}

	public boolean isAddrCityMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : this.address.isAddrCityMissing();
	}
	
	public String getAddrState() {
		return this.address.getAddrState();
	}

	public void setAddrState(String addrState) {
		this.address.setAddrState(addrState);
	}

	public boolean isAddrStateMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : this.address.isAddrStateMissing();
	}
	
	public String getAddrZip() {
		return this.address.getAddrZip();
	}

	public void setAddrZip(String addrZip) {
		this.address.setAddrZip(addrZip);
	}
	
	public boolean isAddrZipMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : this.address.isAddrZipMissing();
	}
	
	public boolean validateUser () {
		
		if (!emailAddressMissing) {
			emailAddressDupl = (UserDB.emailExists(emailAddress)) ? true : false;
		}
		
		if (emailAddressMissing ||
				emailAddressDupl ||
				firstNameMissing ||
				lastNameMissing) {
			userStatus = UserStatus.INVALID;
			return true;
		}
		else {
			userStatus = UserStatus.VALID;
			return true;
		}
	}
	
	public boolean isUserValid () {
		return (userStatus == UserStatus.INVALID) ? false : true;
	}
	
	public boolean isUserLoggedIn () {
		return (userStatus == UserStatus.LOGGED_IN) ? true : false;
	}
	
	public void createUser () {
		UserDB.insertUser(this);
		userStatus = UserStatus.LOGGED_IN;
	}
	
	public void updateUser () {
		UserDB.updateUser (this);
	}
	
	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}
