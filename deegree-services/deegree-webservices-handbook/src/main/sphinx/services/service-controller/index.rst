.. _anchor-configuration-service-service-controller:

------------------
Service controller
------------------

The controller configuration is used to configure various global aspects that affect all services.

Since it's a global configuration file for all services, it's called ``main.xml``, and located in the ``services`` directory. All of the options are optional, if you want the default behaviour, just omit the file completely.

An empty example file looks like follows:

.. code-block:: xml

  <?xml version='1.0'?>
  <deegreeServiceController xmlns='http://www.deegree.org/services/controller' configVersion='3.2.0'>
  </deegreeServiceController>

The following table lists all available configuration options. When specifiying them, their order must be respected.

.. table:: Options for ``deegreeServiceController``

+-------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| Option                  | Cardinality  | Value   | Description                                                                                  |
+=========================+==============+=========+==============================================================================================+
| ReportedUrls            | 0..1         | Complex | Hardcode reported URLs in service responses                                                  |
+-------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| PreventClassloaderLeaks | 0..1         | Boolean | TODO                                                                                         |
+-------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| RequestLogging          | 0..1         | Complex | TODO                                                                                         |
+-------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| ValidateResponses       | 0..1         | Boolean | TODO                                                                                         |
+-------------------------+--------------+---------+----------------------------------------------------------------------------------------------+

The following sections describe the available options in detail.

^^^^^^^^^^^^^
Reported URLs
^^^^^^^^^^^^^

Some web service responses contain URLs that refer back to the service, for example in capabilities documents (responses to GetCapabilities requests). By default, deegree derives these URLs from the incoming request, so you don't have to think about this, even when your server has multiple network interfaces or hostnames. However, sometimes it is required to override these URLs, for example when using deegree behind a proxy or load balancer.

.. tip::
    If you don't have a proxy setup that requires it, don't configure the reported URLs. In standard setups, the default behaviour works best.

To override the reported URLs, put a fragment like the following into the ``main.xml``:

.. code-block:: xml

  <ReportedUrls>
    <Services>http://www.mygeoportal.com/ows</Services>
    <Resources>http://www.mygeoportal.com/ows-resources</Resources>
  </ReportedUrls>

For this example, deegree would report ``http://www.mygeoportal.com/ows`` as service endpoint URL in capabilities responses, regardless of the real connection details of the deegree server. If a specific service is contacted on the deegree server, for example via a request to ``http://realnameofdeegreemachine:8080/deegree-webservices/services/inspire-wfs-ad``, deegree would report ``http://www.mygeoportal.com/ows/inspire-wfs-ad``.

The URL configured by ``Resources`` relates to the reported URL of the ``resources`` servlet, which allows to access parts of the active deegree workspace via HTTP. Currently, this is only used in WFS DescribeFeatureType responses that access GML application schema directories.
