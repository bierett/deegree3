/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
 Department of Geography, University of Bonn
 and
 lat/lon GmbH

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

package org.deegree.rendering.r3d.model.geometry;

import java.util.ArrayList;

import org.deegree.rendering.r3d.model.QualityModel;

/**
 * The <code>GeometryQualityModel</code> is kind of a marker class for QualityModels with
 * geometries.
 *
 * @author <a href="mailto:bezema@lat-lon.de">Rutger Bezema</a>
 *
 */
public class GeometryQualityModel extends QualityModel<SimpleAccessGeometry> {

	/**
	 *
	 */
	private static final long serialVersionUID = 6512563995304520151L;

	/**
	 * Creates a GeometryQualityModel with an empty list of geometry patches
	 *
	 */
	public GeometryQualityModel() {
		super();
	}

	/**
	 * Creates a GeometryQualityModel with the given qualityModelParts
	 * @param qualityModelParts
	 */
	public GeometryQualityModel(ArrayList<SimpleAccessGeometry> qualityModelParts) {
		super(qualityModelParts);
	}

}
