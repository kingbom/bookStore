package bookstore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;




@Entity
@Data
public class Book {
	public enum Category{Comedy, History, Romance, ScienceFiction}
	private String author;
	Category category;
	String description;
	
	@Id
	private String isbn;
	
	Boolean isSpecial;

	private Integer pageCount;
	private Double price;
	private Double rating;

	private String title;
	
	//No arg constructor needed for JPA
	public Book(){}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	
	@Override
	public String toString() {
		return "Book [author=" + author + ", category=" + category
				+ ", description=" + description + ", isbn=" + isbn
				+ ", isSpecial=" + isSpecial + ", pageCount=" + pageCount
				+ ", price=" + price + ", rating=" + rating + ", title="
				+ title + "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((isSpecial == null) ? 0 : isSpecial.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result
				+ ((pageCount == null) ? 0 : pageCount.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isSpecial == null) {
			if (other.isSpecial != null)
				return false;
		} else if (!isSpecial.equals(other.isSpecial))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (pageCount == null) {
			if (other.pageCount != null)
				return false;
		} else if (!pageCount.equals(other.pageCount))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	public Book(String isbn) {
		super();
		this.isbn = isbn;
	}
	
	
	public Book(String author, Category category, String description,
			String isbn, Boolean isSpecial, Integer pageCount, Double price,
			Double rating, String title) {
		super();
		this.author = author;
		this.category = category;
		this.description = description;
		this.isbn = isbn;
		this.isSpecial = isSpecial;
		this.pageCount = pageCount;
		this.price = price;
		this.rating = rating;
		this.title = title;
	}


	public Book(String title, String author, Double price, Double rating,
			String isbn, Integer pageCount, Category category,
			String description, Boolean isSpecial) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.rating = rating;
		this.isbn = isbn;
		this.pageCount = pageCount;
		this.category = category;
		this.description = description;
		this.isSpecial = isSpecial;
	}
}
