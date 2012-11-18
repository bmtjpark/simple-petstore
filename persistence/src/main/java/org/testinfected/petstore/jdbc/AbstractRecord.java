package org.testinfected.petstore.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class AbstractRecord<T> implements Record<T> {

    protected int indexOf(final String column) {
        return columns().indexOf(column) + 1;
    }

    protected int findColumn(ResultSet rs, final String columnName) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            if (metaData.getColumnName(i).equalsIgnoreCase(columnName) &&
                    metaData.getTableName(i).equalsIgnoreCase(table()))
                return i;
        }

        throw new SQLException("Result set has no column '" + columnName + "'");
    }
}
