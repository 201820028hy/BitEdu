package javastudy.book.service;

import javastudy.book.dao.BookDAO;
import javastudy.book.dao.RentalHistoryDAO;
import javastudy.book.dto.BookDTO;
import javastudy.book.exception.CategoryNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class BookService {
    private final Scanner scanner;
    private final RentalHistoryDAO rentalHistoryDAO;
    private final BookDAO bookDAO;
    private static final int RENTAL_MENU_NO = 4;//수정(추가)
    private static final String RENTAL_WORD = "F";//수정(추가)

    public BookService() {
        this.bookDAO = new BookDAO();
        this.rentalHistoryDAO = new RentalHistoryDAO();
        this.scanner = new Scanner(System.in);
    }

    public void findCategories() {
        boolean resultFlag = true;
        while (resultFlag) {
            try {
                List<String> categories = bookDAO.selectCategories();
                printCategoryList(categories);

                System.out.println("검색할 카테고리 번호 입력 (로그아웃 : 0): ");//기존 : "검색할 카테고리명 입력 (로그아웃 : 0): " | 수정  
                int checkCategoryName = scanner.nextInt();//기존 : String checkCategoryName = scanner.next() | 수정
                scanner.nextLine();//기존 : String checkCategoryName = scanner.next() | 수정

                if (checkCategoryName == 0) {//기존 : if (checkCategoryName.equals("0")) { | 수정
                    System.out.println("로그아웃 합니다.");
                    resultFlag = false;
                } else {
                    String categoryInfo = findCategoryByName(categories, categories.get(checkCategoryName-1));//기존 : String categoryInfo = findCategoryByName(categories, checkCategoryName); | 수정

                    List<BookDTO> books = findBooksByCategory(categoryInfo);
                    if (books.isEmpty()) {
                        resultFlag = false;
                    } else {
                    	resultFlag = searchBook(books);
                    }
                }
            } catch (CategoryNotFoundException | SQLException e) {
                System.err.println("에러 : " + e.getMessage());
            }
        }
    }

    private void printCategoryList(List<String> categories) {
        System.out.println("도서 카테고리 목록:");
        for (int i = 1; i < categories.size()+1; i++) {//기존 : String category : categories | 수정
            System.out.println(i + ". " + categories.get(i-1));//기존 : category | 수정
        }
    }

    private String findCategoryByName(List<String> categories, String categoryName) throws CategoryNotFoundException {
        return categories.stream()
            .filter(name -> name.equals(categoryName))
            .findFirst()
            .orElseThrow(CategoryNotFoundException::new);
    }

    public List<BookDTO> findBooksByCategory(String category) throws SQLException {
        List<BookDTO> list = bookDAO.selectSearchList(0, category);
        printBookList(list);
        return list;
    }

    private void printBookList(List<BookDTO> books) {
        System.out.println("[도서 목록]");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("%-6s%-30s%-20s%n", "no", "제목", "저자");
        System.out.println("-----------------------------------------------------------------------------------------------");
        if (books.size() > 0) {
            for (int i = 1; i <= books.size(); i++) {
                BookDTO book = books.get(i - 1);
                System.out.printf("%-6s%-30s%-20s%n",
                    i,
                    truncateString(book.getTitle(), 20),
                    truncateString(book.getAuthor(), 10)
                );
            }
            System.out.println();
        } else {
            System.out.println("조회된 도서가 없습니다.\n");
        }
    }

    private String truncateString(String str, int maxLength) {
        if (str.length() >= maxLength) {
            return str.substring(0, maxLength - 3) + "..";
        }
        return str;
    }

    public void searchType() throws SQLException {
        boolean flagResult = true;
        while (flagResult) {
            System.out.println();
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("검색 조건: 1.제목 | 2.내용 | 3.저자 | 4.대출 가능 도서 ");
            System.out.print("검색 조건 선택: ");
            int menuNo = scanner.nextInt();//기존 : Integer.parseInt(scanner.nextLine()) 수정
            String word = null;
            
            scanner.nextLine();//기존 : Integer.parseInt(scanner.nextLine()) 수정

        /*
        TODO 4: maic number 의 사용은 지양합니다 ~ (혜윤)
         */
            if (menuNo != RENTAL_MENU_NO) {//기존 : 4 수정
                System.out.println();
                System.out.print("검색어: ");
                word = scanner.nextLine();
            } else {
                word = RENTAL_WORD;//기존 : "F" 수정
            }

            List<BookDTO> list = bookDAO.selectSearchList(menuNo, word);
            printBookList(list);
            flagResult = searchBook(list);
        }
    }

    private boolean searchBook(List<BookDTO> books) throws SQLException {
        System.out.print("검색할 책 번호 입력: ");
        int bookNo = scanner.nextInt();
        int realBookNo = books.get(bookNo-1).getBookNo();

        if (bookNo > 0 && bookNo <= books.size()) {
            BookDTO bookDTO = bookDAO.selectBookByBookNo(realBookNo);//기존 : bookNo 수정
            System.out.println(bookDTO.toString());
            System.out.println("0. 나가기");
            System.out.println("1. 대여하기");
            System.out.println("2. 예약하기");
            System.out.print("사용자 입력: ");

            int rentNo = scanner.nextInt();
            switch (rentNo) {
                case 0:
                    System.out.println("나갑니다.\n");//수정
                    break;
                case 1:
                    return rentalHistoryDAO.rentBook(realBookNo);//기존 : bookNo 수정
                case 2:
                    System.out.println("예약은 안만듭니다.\n");//수정
                    break;
                default:
                    break;
            }
        }
        return false;
    }
}
