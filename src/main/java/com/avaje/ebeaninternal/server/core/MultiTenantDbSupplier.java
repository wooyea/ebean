package com.avaje.ebeaninternal.server.core;

import com.avaje.ebean.config.CurrentTenantProvider;
import com.avaje.ebean.config.TenantDataSourceProvider;
import com.avaje.ebeaninternal.server.transaction.DataSourceSupplier;

import javax.sql.DataSource;

/**
 * DataSource supplier based on DataSource per Tenant.
 */
class MultiTenantDbSupplier implements DataSourceSupplier {

  private final CurrentTenantProvider tenantProvider;

  private final TenantDataSourceProvider dataSourceProvider;

  MultiTenantDbSupplier(CurrentTenantProvider tenantProvider, TenantDataSourceProvider dataSourceProvider) {
    this.tenantProvider = tenantProvider;
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public DataSource getDataSource() {
    return dataSourceProvider.dataSource(tenantProvider.currentId());
  }

  @Override
  public void shutdown(boolean deregisterDriver) {
    dataSourceProvider.shutdown(deregisterDriver);
  }
}
