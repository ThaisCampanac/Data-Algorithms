
public class Book {

	String ISBN;
	String title;
	String author;
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String ISBN, String title, String author) {
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
//		System.out.println("New Book");
	}

	public String getISBN() {
		return ISBN;
	}
}
