package bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class User implements Serializable{
	public enum UserStatus {
		NEW, INVALID, VALID, LOGGED_IN
	};
	
	private String addressFirstLine = "";
	private boolean addressFirstLineMissing=true;
	private String addressSecondLine = "";
	private boolean addressSecondLineMissing=true;
	
	private String city = "";
	private Boolean cityMissing;
	
	//@Column(unique = true)
	//@Email
	private String email = "";
	boolean emailAddressDupl = true;
	boolean emailAddressMissing = true;
	//@NotEmpty
	private String firstName = "";
	boolean firstNameMissing = true;
	@javax.persistence.Id
	@GeneratedValue
	private Long Id;
	//@NotEmpty
	private String lastName = "";
	boolean lastNameMissing = true;
	//@NotEmpty
	private String password = "";
	boolean passwordMissing = true;
	private String state = "";
	private boolean stateMissing = true;
	private UserStatus userStatus = UserStatus.NEW;
	private String zipcode = "";
	private boolean zipcodeMissing = true;
	
	//No arg constructor needed for jpa
	public User(){}
	
	public User(String addressFirstLine, String addressSecondLine, String city,
			String email, String firstName, String lastName,
			String password, String state, String zipcode) {
		super();
		this.addressFirstLine = addressFirstLine;
		this.addressSecondLine = addressSecondLine;
		this.city = city;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (addressFirstLine == null) {
			if (other.addressFirstLine != null)
				return false;
		} else if (!addressFirstLine.equals(other.addressFirstLine))
			return false;
		if (addressSecondLine == null) {
			if (other.addressSecondLine != null)
				return false;
		} else if (!addressSecondLine.equals(other.addressSecondLine))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	};
	
	/**
	 * @return the addressFirstLine
	 */
	public String getAddressFirstLine() {
		return addressFirstLine;
	}
	
	public boolean isAddrStateMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : stateMissing;
	}
	
	public boolean isAddrZipMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : this.zipcodeMissing;
	}

	/**
	 * @return the addressSecondLine
	 */
	public String getAddressSecondLine() {
		return addressSecondLine;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	public UserStatus getUserStatus() {
		return userStatus;
	}
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((addressFirstLine == null) ? 0 : addressFirstLine.hashCode());
		result = prime
				* result
				+ ((addressSecondLine == null) ? 0 : addressSecondLine
						.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}
	public boolean isAddrCityMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : cityMissing;
	}
	public boolean isAddrFirstLineMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : addressFirstLineMissing;
	}
	
	public boolean isAddrSecondLineMissing() {
		return (this.userStatus == UserStatus.NEW) ? false : addressSecondLineMissing;
	}
	public boolean isEmailAddressMissing() {
		return (userStatus == UserStatus.NEW) ? false: emailAddressMissing;
	}
	public boolean isFirstNameMissing() {
		return (userStatus == UserStatus.NEW) ? false: firstNameMissing;
	}
	public boolean isPasswordMissing() {
		return (userStatus == UserStatus.NEW) ? false: passwordMissing;
	}
	public boolean isUserLoggedIn () {
		return (userStatus == UserStatus.LOGGED_IN) ? true : false;
	}
	public boolean isUserValid () {
		return (userStatus == UserStatus.INVALID) ? false : true;
	}
	
	/**
	 * @param addressFirstLine the addressFirstLine to set
	 */
	public void setAddressFirstLine(String addressFirstLine) {
		this.addressFirstLine = addressFirstLine;
	}
	/**
	 * @param addressSecondLine the addressSecondLine to set
	 */
	public void setAddressSecondLine(String addressSecondLine) {
		this.addressSecondLine = addressSecondLine;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
		this.emailAddressMissing = (email == null || email.isEmpty()) ? true : false;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		this.firstNameMissing = (firstName == null || firstName.isEmpty()) ? true : false;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		this.lastNameMissing = (lastName == null || lastName.isEmpty()) ? true : false;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
		this.passwordMissing = (password == null || password.isEmpty()) ? true : false;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [addressFirstLine=" + addressFirstLine
				+ ", addressSecondLine=" + addressSecondLine + ", city=" + city
				+ ", email=" + email + ", firstName=" + firstName + ", Id="
				+ Id + ", lastName=" + lastName + ", password=" + password
				+ ", state=" + state + ", zipcode=" + zipcode + "]";
	}
	
	public boolean validateUser () {
		
		if (!emailAddressMissing) {
			//Jared's not sure what the best way to do this is.
			//emailAddressDupl = (UserDB.emailExists(emailAddress)) ? true : false;
			emailAddressDupl =false;
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
}
