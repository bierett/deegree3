.. _anchor-configuration-csw:

------------------------------
Characteristics of deegree CSW
------------------------------

deegree CSW is an implementation of the `OGC Catalogue Service specification <http://www.opengeospatial.org/standards/cat>`_. Notable features:

* Implements CSW standard 2.0.2
* Fully transactional
* Supports KVP, XML and SOAP requests
* High performance and excellent scalability
* ISO Metadata Application Profile 1.0.0
* Pluggable and modular dataaccess layer allows to add support for new APs and backends
* Modular inspector architecture allows to validate records to be inserted against various criteria
* Standard inspectors: schema validity, identifier integrity, INSPIRE requirements
* Handles all defined queryable properties (for Dublin Core as well as ISO profile) 
* Complex filter expressions

.. tip::
  In order to learn the setup and configuration of a deegree-based CSW, we recommend to read :ref:`anchor-installation` and :ref:`anchor-lightly` first. Check out :ref:`anchor-workspace-csw` for an example deegree CSW configuration. Continue with :ref:`anchor-configuration-basics` and :ref:`anchor-configuration-csw`.
