package bitedu.bipa.book.utils;

public class PagingbarGenerator {//마지막 페이지 37 | 한 페이지 그룹 5 | 현재 1페이지 이 클래스는 현재 페이지 그룹의 html 태그 리턴해주는 용도
	public static String generatePagingInfo(int group,int page,PageInfo info) {
		String result = null;
		StringBuilder sb = new StringBuilder();
		String space = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		int endGroup = (int)Math.ceil((float)info.getEndPage()/info.getGroupsPerPage());//마지막 페이지/그룹 갯수 올림 = 8
		System.out.println("endGroup "+endGroup);
		int temp1 = info.getEndPage()%info.getGroupsPerPage()==0?5:info.getEndPage()%info.getGroupsPerPage();//마지막 페이지 % 그룹 갯수 == 0 ? 5 : 마지막 페이지 % 그룹 갯수 = 2
		int limit = endGroup==group?temp1:(info.getGroupsPerPage());//(마지막 페이지 / 그룹 갯수 올림) == 현재 그룹 ? temp1 : 그룹 갯수 = 5
		System.out.println(limit+","+info.getEndPage()+","+endGroup);
		if(group>1) {
			String prev = "<a href='./list.do?group="+(group-1)+"&page="+((group-1)*info.getGroupsPerPage())+"'>Prev</a>\n";
			sb.append(prev);
			//sb.append(space);
		}
		//((itemCount-1)*info.getItemsPerPage())
		for(int i=1;i<=limit;i++) {//현재 페이지 그룹의 갯수만큼 for문
			String temp = "<a href='./list.do?&group="+group+"&page="+((group-1)*info.getGroupsPerPage()+i)+"'>"+((group-1)*info.getGroupsPerPage()+i)+"</a>\n";
			//sb.append(space);
			sb.append(temp);
		}
		if(group<endGroup) {
			//sb.append(space);
			String next = "<a href='./list.do?group="+(group+1)+"&page="+((group*info.getGroupsPerPage())+1)+"'>Next</a>\n";
			sb.append(next);
		}
		result = sb.toString();
		return result;
	}
}
