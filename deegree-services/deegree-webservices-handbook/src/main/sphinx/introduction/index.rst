.. _anchor-configuration-index:

============
Introduction
============

deegree webservices are implementations of the geospatial webservice specifications of the `Open Geospatial Consortium (OGC) <http://www.opengeospatial.org>`_ and the `INSPIRE Network Services <http://inspire.jrc.ec.europa.eu>`_. deegree webservices 3.2 includes the following services:

* `Web Feature Service (WFS) <http://www.opengeospatial.org/standards/wfs>`_: Provides access to raw geospatial data objects
* `Web Map Service (WMS) <http://www.opengeospatial.org/standards/wms>`_: Serves maps rendered from geospatial data
* `Web Map Tile Service (WMTS) <http://www.opengeospatial.org/standards/wmts>`_: Serves pre-rendered map tiles
* `Catalogue Service for the Web (CSW) <http://www.opengeospatial.org/standards/cat>`_: Performs searches for geospatial datasets and services
* `Web Processing Service (WPS) <http://www.opengeospatial.org/standards/wps>`_: Executes geospatial processes

With a single deegree webservices installation, you can set up one of the above services, all of them or even multiple services of the same type. The remainder of this chapter introduces some notable features of the different service implementations and provides learning trails for learning the configuration of each service.

.. toctree::
   :glob:
   :maxdepth: 2

   wfs/index
   wms/index
   wmts/index
   csw/index
   wps/index
