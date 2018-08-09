package cn.msss.util;

public class PageUtil {

    /*
        分页
     */

    private Integer pageIndex;//当前页
    private Integer pageSize=2;//页大小
    private Integer pageCount;//总页数
    private Integer totalCount;//总记录数

    public PageUtil(Integer pageIndex, Integer pageSize, Integer pageCount, Integer totalCount) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
    }

    public PageUtil() {

    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", totalCount=" + totalCount +
                '}';
    }


    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    /*

     */
    public void setTotalCount(Integer totalCount) {
        if (totalCount>0){
          this.totalCount=totalCount;//获取总记录数
            //总页数=总记录数余页大小？总记录数除页大小：总记录数除页大小+1
          this.pageCount=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
        }
    }
}
