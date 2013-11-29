/*
 *  Copyright (C) 2012-2013 CloudJee, Inc. All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.wavemaker.tools.data;

import com.wavemaker.runtime.data.util.DataServiceConstants;
import com.wavemaker.runtime.data.util.JDBCUtils;
import com.wavemaker.tools.data.util.DataServiceUtils;

import java.util.Collection;
import java.util.Properties;

/**
 * @author Simon Toens
 */
public class SpringCfgGenerator extends BaseDataModelSetup {

    @Override
    protected boolean customInit(Collection<String> requiredProperties) {

        checkServiceName(false, requiredProperties);
        checkDestdir(requiredProperties);
        checkPackage();
        checkClassName(requiredProperties);
        checkServiceName(true, requiredProperties);
        checkDataPackage();

        return true;
    }

    @Override
    protected void customRun() {
        getConfigurationExporter().execute();
        writeConnectionPropertiesTemplate();
    }

    @Override
    protected void customDispose() {
    }

    private void writeConnectionPropertiesTemplate() {
        Properties p = new Properties();

        p.setProperty(this.serviceName + DataServiceConstants.DB_USERNAME, getUsername() == null ? "" : getUsername());
        p.setProperty(this.serviceName + DataServiceConstants.DB_PASS, getPassword() == null ? "" : getPassword());
        String connectionUrl = "";
        if(getConnectionUrl() != null){
               connectionUrl = getConnectionUrl().toString();
             if(JDBCUtils.getMySQLDatabaseName(connectionUrl) == null){
                 connectionUrl = connectionUrl + "/" + this.serviceName;
             }
        }
        p.setProperty(this.serviceName + DataServiceConstants.DB_URL, getConnectionUrl() == null ? "" : connectionUrl);
        p.setProperty(this.serviceName + DataServiceConstants.DB_DRIVER_CLASS_NAME, getDriverClassName() == null ? "" : getDriverClassName());
        p.setProperty(this.serviceName + DataServiceConstants.DB_DIALECT, getDialect() == null ? "" : getDialect());

        DataServiceUtils.writeProperties(p, this.destdir, this.serviceName);
    }
}
