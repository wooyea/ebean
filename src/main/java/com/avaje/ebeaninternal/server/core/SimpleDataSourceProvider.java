package com.avaje.ebeaninternal.server.core;

import com.avaje.ebeaninternal.server.transaction.DataSourceSupplier;
import org.avaje.datasource.DataSourcePool;

import javax.sql.DataSource;

/**
 * Simple DataSource supplier when no multi-tenancy used.
 */
class SimpleDataSourceProvider implements DataSourceSupplier {

  private final DataSource dataSource;

  SimpleDataSourceProvider(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public DataSource getDataSource() {
    return dataSource;
  }

  @Override
  public void shutdown(boolean deregisterDriver) {
    if (dataSource instanceof DataSourcePool){
      ((DataSourcePool) dataSource).shutdown(deregisterDriver);
    }
  }
}
