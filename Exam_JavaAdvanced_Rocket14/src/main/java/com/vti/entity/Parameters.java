package com.vti.entity;

public class Parameters {
	public int page;
	public int pageSize;
	// search by name
	public String search;

	// filter by id
	public int minId;
	public int maxId;

	// Sort
	public String typeSort;
	// typeSort ADC, DESC
	public String field;

	public Parameters() {
		super();
	}

	public Parameters(int page, int pageSize, String search, int minId, int maxId, String typeSort, String field) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.search = search;
		this.minId = minId;
		this.maxId = maxId;
		this.typeSort = typeSort;
		this.field = field;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getMinId() {
		return minId;
	}

	public void setMinId(int minId) {
		this.minId = minId;
	}

	public int getMaxId() {
		return maxId;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public String getTypeSort() {
		return typeSort;
	}

	public void setTypeSort(String typeSort) {
		this.typeSort = typeSort;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "Parameters [page=" + page + ", pageSize=" + pageSize + ", search=" + search + ", minId=" + minId
				+ ", maxId=" + maxId + ", typeSort=" + typeSort + ", field=" + field + "]";
	}

}
