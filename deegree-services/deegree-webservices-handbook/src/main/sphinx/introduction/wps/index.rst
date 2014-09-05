.. _anchor-configuration-index:

------------------------------
Characteristics of deegree WPS
------------------------------

deegree WPS is an implementation of the `OGC Processing Service specification <http://www.opengeospatial.org/standards/wps>`_. Notable features:

* Implements WPS standard 1.0.0
* Supports KVP, XML and SOAP requests
* Pluggable process provider layer
* Easy-to-use API for implementing Java processes
* Supports all variants of input/output parameters: literal, bbox, complex (binary and xml)
* Streaming access for complex input/output parameters
* Processing of huge amounts of data with minimal memory footprint
* Supports storing of response documents/output parameters
* Supports input parameters given inline and by reference
* Supports RawDataOutput/ResponseDocument responses
* Supports asynchronous execution (with polling of process status)

.. tip::
  In order to learn the setup and configuration of a deegree-based WPS, we recommend to read:ref:`anchor-installation` and :ref:`anchor-lightly` first. Check out :ref:`anchor-workspace-wps` for an example deegree WPS configuration. Continue with :ref:`anchor-configuration-basics` and :ref:`anchor-configuration-wps`.
