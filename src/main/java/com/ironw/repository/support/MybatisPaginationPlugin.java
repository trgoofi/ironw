package com.ironw.repository.support;

import com.ironw.domain.Page;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author trgoofi
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class MybatisPaginationPlugin implements Interceptor {
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object target = invocation.getTarget();
    if (target instanceof RoutingStatementHandler) {
      StatementHandler delegate = (StatementHandler) reflectiveGet("delegate", target);
      Object paramObject = delegate.getBoundSql().getParameterObject();
      if (Page.class.isAssignableFrom(paramObject.getClass())) {
        Connection conn = (Connection) invocation.getArgs()[0];
        Page page = (Page) paramObject;
        long count = count(conn, delegate);
        page.setTotal(count);
        changeSqlToPageSql(delegate, page);
      }
    }

    return invocation.proceed();
  }


  private long count(Connection conn, StatementHandler delegate) throws SQLException {
    BoundSql boundSql = delegate.getBoundSql();
    MappedStatement ms = (MappedStatement) reflectiveGet("mappedStatement", delegate);
    String countSql = makeCountSql(boundSql.getSql());
    List<ParameterMapping> paramMappings = boundSql.getParameterMappings();
    Object paramObject = boundSql.getParameterObject();
    BoundSql boundCountSql = new BoundSql(ms.getConfiguration(), countSql, paramMappings, paramObject);
    PreparedStatement stmt = conn.prepareStatement(countSql);
    DefaultParameterHandler paramHandler = new DefaultParameterHandler(ms, paramObject, boundCountSql);
    paramHandler.setParameters(stmt);

    long count = 0;
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      count = rs.getLong(1);
    }
    closeQuietly(rs, stmt);

    return count;
  }

  private void changeSqlToPageSql(StatementHandler delegate, Page page) {
    String sql = delegate.getBoundSql().getSql();
    String pageSql = makePageSql(sql, page);
    reflectiveSet("sql", delegate.getBoundSql(), pageSql);
  }

  private String makePageSql(String sql, Page page) {
    StringBuilder sb = new StringBuilder(sql);
    sb.append(" limit ").append(page.getOffset()).append(", ").append(page.getSize());
    return sb.toString();
  }


  private String makeCountSql(String sql) {
    int fromIndex = sql.indexOf("from");
    if (fromIndex == -1) {
      fromIndex = sql.indexOf("FROM");
    }

    sql = sql.substring(fromIndex);

    int orderByIndex = sql.indexOf("order by");
    if (orderByIndex == -1) {
      orderByIndex = sql.indexOf("ORDER BY");
    }
    if (orderByIndex != -1) {
      sql = sql.substring(0, orderByIndex);
    }

    return "select count(*) " + sql;
  }



  private static Object reflectiveGet(String fieldName, Object target) {
    Field field = ReflectionUtils.findField(target.getClass(), fieldName);
    ReflectionUtils.makeAccessible(field);
    return ReflectionUtils.getField(field, target);
  }

  private static void reflectiveSet(String fieldName, Object target, Object value) {
    Field field = ReflectionUtils.findField(target.getClass(), fieldName);
    ReflectionUtils.makeAccessible(field);
    ReflectionUtils.setField(field, target, value);
  }

  private void closeQuietly(AutoCloseable... closeables) {
    for(AutoCloseable target : closeables){
      try {
        target.close();
      } catch (Exception ignore) {
      }
    }
  }


  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {
  }
}
