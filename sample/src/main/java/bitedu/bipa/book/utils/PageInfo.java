package bitedu.bipa.book.utils;

public class PageInfo {
	private int itemsPerPage; // 페이지당 나타나는 contents의 갯수
	private int groupsPerPage; // 한페이지에 나타나는 그룹의 갯수
	private int endPage;	// 마지막 페이지

	public PageInfo(int itemsPerPage, int groupsPerPage) {
		this.itemsPerPage = itemsPerPage;
		this.groupsPerPage = groupsPerPage;
	}
	
	public PageInfo(int itemsPerPage, int groupsPerPage, int totalCount) {
		this.itemsPerPage = itemsPerPage;
		this.groupsPerPage = groupsPerPage;
		this.endPage = (int)(Math.ceil(totalCount/(float)itemsPerPage));
	}
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public int getGroupsPerPage() {
		return groupsPerPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setCount(int count) {
		this.endPage = (int)(Math.ceil(count/(float)itemsPerPage));
	}
	
	public int calcuOrderOfPage(String page) {//Limit 시작 index
		int result = 0;
		page = page==null?"1":page;
		result = (Integer.parseInt(page)-1)*this.itemsPerPage;
		return result;
	}
	
	@Override
	public String toString() {
		return "{'itemsPerPage':'" + itemsPerPage + "', groupsPerPage':'" + groupsPerPage + "', endPae':'" + endPage
				+ "}";
	}
	
}
