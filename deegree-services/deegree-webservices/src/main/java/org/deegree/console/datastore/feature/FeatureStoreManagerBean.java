/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2013 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package org.deegree.console.datastore.feature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.deegree.console.AbstractResourceManagerBean;
import org.deegree.console.Config;
import org.deegree.feature.persistence.FeatureStoreManager;
import org.deegree.workspace.ResourceMetadata;

@ManagedBean
@ViewScoped
public class FeatureStoreManagerBean extends AbstractResourceManagerBean<FeatureStoreManager> implements Serializable {

	private static final long serialVersionUID = -7258840439551611498L;

	public FeatureStoreManagerBean() {
		super(FeatureStoreManager.class);
	}

	@Override
	public List<Config> getConfigs() {
		List<Config> configs = new ArrayList<Config>();
		for (ResourceMetadata<?> md : resourceManager.getResourceMetadata()) {
			configs.add(new FeatureStoreConfig(md, resourceManager));
		}
		Collections.sort(configs);
		return configs;
	}

}
