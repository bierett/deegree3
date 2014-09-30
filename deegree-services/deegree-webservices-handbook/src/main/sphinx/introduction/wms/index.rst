.. _anchor-configuration-wms:

------------------------------
Characteristics of deegree WMS
------------------------------

deegree WMS is an implementation of the `OGC Web Map Service specification <http://www.opengeospatial.org/standards/wms>`_. Notable features:

* Official OGC reference implementation for WMS 1.1.1
* Implements WMS standards 1.1.1 and 1.3.0 [#f1]_
* Extensive support for styling languages SLD/SE versions 1.0.0 and 1.1.0
* High performance and excellent scalability
* High quality rendering
* Scale dependent styling
* Support for SE removes the need for a lot of proprietary extensions
* Easy configuration of HTML and other output formats for GetFeatureInfo responses
* Uses stream-based data access, minimal memory footprint
* Nearly complete support for raster symbolizing as defined in SE (with some extensions)
* Complete support for TIME/ELEVATION and other dimensions for both feature and raster data
* Supports numerous backends, such as PostGIS, Oracle Spatial, Shapefiles or GML instance documents
* Can render rich data models directly

.. tip::
  In order to learn the setup and configuration of a deegree-based WMS, we recommend to read chapters :ref:`anchor-installation` and :ref:`anchor-lightly` first. Check out :ref:`anchor-workspace-utah` and :ref:`anchor-workspace-inspire` for example deegree WMS configurations. Continue with :ref:`anchor-configuration-basics` and :ref:`anchor-configuration-wms`.

.. rubric:: Footnotes

.. [#f1] Passes OGC WMS CITE test suites (including all optional tests)
