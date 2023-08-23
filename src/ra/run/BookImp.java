package ra.run;

import ra.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookImp {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        do {
            System.out.println("\n*************************MENU************************\n" +
                    "1. Nhập thông tin các sách\n" +
                    "2. In thông tin các sách ra file demo.txt\n" +
                    "3. Đọc file demo.txt và in ra các sách có giá trong khoảng 10000 đến 20000\n" +
                    "4. Thoát");
            try {

                System.out.print("Lựa chọn của bạn: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        inputBook();
                        break;
                    case 2:
                        printDataToFile();
                        break;
                    case 3:
                        List<Book> readBookList = readFileDemo();
                        if (readBookList != null) {
                            for (Book bk : readBookList) {
                                if (bk.getPrice() >= 10000 && bk.getPrice() <= 20000) {
                                    System.out.println(bk);
                                }
                            }
                        } else {
                            System.err.println("hiện tại file chưa có dữ liệu");
                        }
                        readFileDemo();
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.err.println("please choice a number in 1 -4 ");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Lỗi khi nhập số (không phải chữ cái)");
            } catch (Exception e) {
                System.err.println("Lỗi chưa rõ khi chọn số");
            }
        } while (true);
    }
    public static void inputBook() {
        try {
            System.out.print("nhập vào số sách muốn nhập: ");
            int numBook = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numBook; i++) {
                Book book = new Book();
                book.inputData();
                bookList.add(book);
            }
        } catch (NumberFormatException nfe) {
            System.err.println("Lỗi khi nhập số (không phải chữ cái)");
        } catch (Exception e) {
            System.err.println("Lỗi chưa rõ khi thêm book");
        }
    }

    public static void printDataToFile() {
        File file = new File("demo.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(bookList);
            oos.flush();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("File không tồn tại");
        } catch (IOException ioException) {
            System.err.println("Lỗi khi ghi dữ liệu ra file");
        } catch (Exception exception) {
            System.err.println("Xảy ra lỗi trong quá trình ghi dữ liệu ra file");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ioException) {
                System.err.println("Xảy ra lỗi khi đóng các stream");
            } catch (Exception exception) {
                System.err.println("Xảy ra lỗi trong quá trình đóng các stream");
            }
        }
    }

    public static List<Book> readFileDemo() {
        File file = new File("demo.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Book> readBookList = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            readBookList = (List<Book>) ois.readObject();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Không tồn tại file");
        } catch (IOException ioException) {
            System.err.println("Lỗi khi đọc file");
        } catch (Exception exception) {
            System.err.println("Có lỗi trong quá trình đọc dữ liệu từ file");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ioException) {
                System.err.println("Có lỗi khi đóng stream");
            } catch (Exception exception) {
                System.err.println("Có lỗi trong quá trình đóng các stream");
            }
        }
        return readBookList;
    }
}
