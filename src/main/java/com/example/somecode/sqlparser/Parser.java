package com.example.somecode.sqlparser;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;

import java.util.List;

public class Parser {

    public static void main(String[] args) {
        String sql = "select name from db.table001;";
        List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.mysql);

        if(sqlStatements.get(0) instanceof SQLSelectStatement) {
            SQLSelectStatement statement = (SQLSelectStatement) sqlStatements.get(0);
            System.out.println(statement.toString());
        }
    }
}
