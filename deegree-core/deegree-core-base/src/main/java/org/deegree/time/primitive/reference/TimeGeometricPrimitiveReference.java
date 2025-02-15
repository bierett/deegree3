/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
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
package org.deegree.time.primitive.reference;

import java.util.List;

import org.deegree.commons.tom.ReferenceResolver;
import org.deegree.commons.tom.gml.GMLReference;
import org.deegree.time.primitive.RelatedTime;
import org.deegree.time.primitive.TimeGeometricPrimitive;

public class TimeGeometricPrimitiveReference<T extends TimeGeometricPrimitive> extends GMLReference<T>
		implements TimeGeometricPrimitive {

	public TimeGeometricPrimitiveReference(final ReferenceResolver resolver, final String uri, final String baseURL) {
		super(resolver, uri, baseURL);
	}

	@Override
	public String getFrame() {
		return getReferencedObject().getFrame();
	}

	@Override
	public List<RelatedTime> getRelatedTimes() {
		return getReferencedObject().getRelatedTimes();
	}

}
