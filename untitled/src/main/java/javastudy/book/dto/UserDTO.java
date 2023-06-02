package javastudy.book.dto;

import java.util.Date;

public class UserDTO {

    private int userNo;
    private int gradeCode;
    private Date overdueDate;
    private String gradeName;//수정(추가)

	public UserDTO(int userNo, int gradeCode, Date overdueDate, String gradeName) {//수정 , String gradeName 추가
		this.userNo = userNo;
		this.gradeCode = gradeCode;
		this.overdueDate = overdueDate;
		this.gradeName = gradeName;//수정 gradeName 추가
	}

	public Date getOverdueDate() {
        return overdueDate;
    }
    public int getUserNo() {
        return userNo;
    }
    public int getGradeCode() {
        return gradeCode;
    }
    public String getGradeName() {//수정(추가)
        return gradeName;
    }

    @Override //수정(추가)
    public String toString() {
        return "유저 이름 : " + userNo + "\t" +
            "유저 등급 : " + gradeName + "\t" +
            "유저 일자 : " + overdueDate + "\n";
    }

}
