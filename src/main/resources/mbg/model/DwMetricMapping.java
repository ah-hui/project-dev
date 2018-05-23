package model;

import java.util.Date;

public class DwMetricMapping {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private String sourceTable;

    private String sourceColumn;

    private String columnName;

    private String columnType;

    private String columnComment;

    private String expression;

    public DwMetricMapping(Integer id, Date gmtCreate, Date gmtModified, String sourceTable, String sourceColumn, String columnName, String columnType, String columnComment, String expression) {
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.sourceTable = sourceTable;
        this.sourceColumn = sourceColumn;
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnComment = columnComment;
        this.expression = expression;
    }

    public DwMetricMapping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable == null ? null : sourceTable.trim();
    }

    public String getSourceColumn() {
        return sourceColumn;
    }

    public void setSourceColumn(String sourceColumn) {
        this.sourceColumn = sourceColumn == null ? null : sourceColumn.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment == null ? null : columnComment.trim();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression == null ? null : expression.trim();
    }
}