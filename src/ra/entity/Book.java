package ra.entity;

import java.io.Serializable;
import java.util.Scanner;

import static ra.run.BookImp.bookList;
import static ra.run.BookImp.scanner;

public class Book implements Serializable {
    private String bookId;
    private String bookName;
    private float Price;

    public Book() {
    }

    public Book(String bookId, String bookName, float price) {
        this.bookId = bookId;
        this.bookName = bookName;
        Price = price;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public void inputData() {
        boolean checkId = true;
        do {
            System.out.print("Nhập vào mã sách: ");
            String nameId = scanner.nextLine();
            boolean idExists = false;

            if (bookList.size() > 0) {
                for (Book book : bookList) {
                    if (book.getBookId().equals(nameId)) {
                        System.err.println("Mã này đã tồn tại");
                        idExists = true;
                        break;
                    }
                }
            }

            if (!idExists) {
                this.bookId = nameId;
                checkId = false;
            }
        } while (checkId);
        System.out.print("Nhập vào tên sách: ");
        this.bookName = scanner.nextLine();
        System.out.print("Nhập vào giá sách: ");
        do {
            try {
                this.Price = Float.parseFloat(scanner.nextLine());
                break;
//
            } catch (NumberFormatException nfe) {
                System.err.println("Lỗi khi nhập Giá (không phải chữ cái)");
            } catch (Exception ex) {
                System.err.println("Lỗi chưa rõ khi nhập giá");
            }
        } while (true);


    }

    @Override
    public String toString() {
        return "Mã sách: " + bookId + " -- Tên sách: " + bookName + " -- Giá: " + Price;
    }
}
