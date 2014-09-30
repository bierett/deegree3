.. _anchor-configuration-wfs:

------------------------------
Characteristics of deegree WFS
------------------------------

deegree WFS is an implementation of the `OGC Web Feature Service specification <http://www.opengeospatial.org/standards/wfs>`_. Notable features:

* Official OGC reference implementation fo WFS 1.1.0 and WFS 2.0.0 Simple
* Implements WFS standards 1.0.0, 1.1.0 and 2.0.0 [#f1]_
* Fully transactional (even for rich data models)
* Supports KVP, XML and SOAP requests
* GML 2/3.0/3.1/3.2 output/input
* Support for GetGmlObject requests and XLinks
* High performance and excellent scalability
* On-the-fly coordinate transformation
* Designed for rich data models from the bottom up
* Backends support flexible mapping of GML application schemas to relational models
* ISO 19107-compliant geometry model: Complex geometries (e.g. non-linear curves)
* Advanced filter expression support based on XPath 1.0
* Supports numerous backends, such as PostGIS, Oracle Spatial, MS SQL Server, Shapefiles or GML instance documents

.. tip::
  In order to learn the setup and configuration of a deegree-based WFS, we recommend to read chapters :ref:`anchor-installation` and :ref:`anchor-lightly` first. Check out :ref:`anchor-workspace-inspire` and :ref:`anchor-workspace-utah` for example deegree WFS configurations. Continue with :ref:`anchor-configuration-basics` and :ref:`anchor-configuration-wfs`.

.. rubric:: Footnotes

.. [#f1] Passes OGC WFS CITE test suites (including all optional tests)
