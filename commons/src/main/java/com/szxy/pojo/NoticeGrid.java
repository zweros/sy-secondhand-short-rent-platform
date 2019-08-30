package com.szxy.pojo;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/30 13:20
 * @Description:com.szxy.pojo
 * @Version:1.0
 **/
public class NoticeGrid {
    private int current;//当前页面号
    private int rowCount;//每页行数
    private int total;//总行数
    private List<Notice> rows;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Notice> getRows() {
        return rows;
    }

    public void setRows(List<Notice> rows) {
        this.rows = rows;
    }
}
