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
package org.deegree.observation.model;

/**
 * This class stores double result values.
 * 
 * @author <a href="mailto:tonnhofer@lat-lon.de">Oliver Tonnhofer</a>
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * 
 * 
 */
public class SimpleDoubleResult implements Result {

    private final double value;

    private final Property property;

    /**
     * @param value
     * @param property
     */
    public SimpleDoubleResult( double value, Property property ) {
        this.value = value;
        this.property = property;
    }

    public String getResultAsString() {
        return Double.toString( value );
    }

    public Property getProperty() {
        return property;
    }

    @Override
    public String toString() {
        return getResultAsString() + property.getOptionValue( "uom" ) + " (" + property.getColumnName() + ")";
    }

}
