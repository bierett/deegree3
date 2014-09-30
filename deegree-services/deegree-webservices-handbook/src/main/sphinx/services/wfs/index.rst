.. _anchor-configuration-wfs:

-------------------------
Web Feature Service (WFS)
-------------------------

A deegree WFS setup consists of a WFS configuration file and any number of feature store configuration files. Feature stores provide access to the actual data (which may be stored in any of the supported backends, e.g. in shapefiles or spatial databases such as PostGIS or Oracle Spatial). In transactional mode (WFS-T), feature stores are also used for modification of stored features:

.. figure:: ../../images/workspace-wfs.png
    :figwidth: 80%
    :width: 80%
    :target: ../../_images/workspace-wfs.png

    A WFS resource is connected to any number of feature store resources

^^^^^^^^^^^^^^^
Minimal example
^^^^^^^^^^^^^^^

The only mandatory option is ``QueryCRS``, therefore, a minimal WFS configuration example looks like this:

.. topic:: WFS config example 1: Minimal configuration

    .. literalinclude:: ../../xml/wfs_basic.xml
        :language: xml

This will create a deegree WFS with the feature types from all configured feature stores in the workspace and ``urn:ogc:def:crs:EPSG::4258`` as coordinate system for returned GML geometries.

^^^^^^^^^^^^^^^^^^^^
More complex example
^^^^^^^^^^^^^^^^^^^^

A more complex configuration example looks like this:

.. topic:: WFS config example 2: More complex configuration

    .. literalinclude:: ../../xml/wfs_complex.xml
        :language: xml

^^^^^^^^^^^^^^^^^^^^^^
Configuration overview
^^^^^^^^^^^^^^^^^^^^^^

The deegree WFS config file format is defined by schema file http://schemas.deegree.org/services/wfs/3.4.0/wfs_configuration.xsd. The root element is ``deegreeWFS`` and the config attribute must be ``3.4.0``. The following table lists all available configuration options (complex ones contain nested options themselves). When specifiying them, their order must be respected.

.. table:: Options for ``deegreeWFS``

+-------------------------+-------------+---------+------------------------------------------------------------------+
| Option                  | Cardinality | Value   | Description                                                      |
+=========================+=============+=========+==================================================================+
| SupportedVersions       | 0..1        | Complex | Activated OGC protocol versions, default: all                    |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| FeatureStoreId          | 0..n        | String  | Feature stores to attach, default: all                           |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| EnableTransactions      | 0..1        | Complex | Enable transactions (WFS-T operations), default: false           |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| EnableResponseBuffering | 0..1        | Boolean | Enable response buffering (expensive), default: false            |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| QueryCRS                | 1..n        | String  | Announced CRS, first element is the default CRS                  |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| QueryMaxFeatures        | 0..1        | Integer | Limit of features returned in a response, default: 15000         |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| QueryCheckAreaOfUse     | 0..1        | Boolean | Check spatial query constraints against CRS area, default: false |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| StoredQuery             | 0..n        | String  | File name of StoredQueryDefinition                               |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| GMLFormat               | 0..n        | Complex | GML format configuration                                         |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| CustomFormat            | 0..n        | Complex | Custom format configuration                                      |
+-------------------------+-------------+---------+------------------------------------------------------------------+

The remainining sections describe these options and their sub-options in detail.

^^^^^^^^^^^^^^^
General options
^^^^^^^^^^^^^^^

* ``SupportedVersions``: By default, all implemented WFS protocol versions (1.0.0, 1.1.0 and 2.0.0) will be activated. You can control offered WFS protocol versions using element ``SupportedVersions``. This element allows any combination of the child elements ``<Version>1.0.0</Version>``, ``<Version>1.1.0</Version>`` and ``<Version>2.0.0</Version>``.
* ``FeatureStoreId``: By default, all feature stores in your deegree workspace  will be used for serving feature types. In some cases, this may not be what you want, e.g. because you have two different WFS instances running, or you don't want all feature types used in your WMS for rendering to be available via your WFS. Use the ``FeatureStoreId`` option to explicitly set the feature stores that this WFS should use.
* ``EnableResponseBuffering``: By default, WFS responses are directly streamed to the client. This is very much recommended and even a requirement for transferring large responses efficiently. The only drawback happens if exceptions occur, after a partial response has already been transferred. In this case, the client will receive part payload and part exception report. By specifying ``false`` here, you can explicitly force buffering of the full response, before it is written to the client. Only if the full response could be generated successfully, it will be transferred. If an exception happens at any time the buffer will be discarded, and an exception report will be sent to the client. Buffering is performed in memory, but switches to a temp file in case the buffer grows bigger than 1 MiB.
* ``QueryCRS``: Coordinate reference systems for returned geometries. This element can be specified multiple times, and the WFS will announce all CRS in the GetCapabilities response (except for WFS 1.0.0 which does not officially support using multiple coordinate reference systems). The first element always specifies the default CRS (used when no CRS parameter is present in a request).
* ``QueryMaxFeatures``: By default, a maximum number of 15000 features will be returned for a single ``GetFeature`` request. Use this option to override this setting. A value of ``-1`` means unlimited.
* ``QueryCheckAreaOfUse``: By default, spatial query constraints are not checked with regard to the area of validity of the CRS. Set this option to ``true`` to enforce this check.

^^^^^^^^^^^^
Transactions
^^^^^^^^^^^^

By default, WFS-T requests will be rejected. Setting the ``EnableTransactions`` option to ``true`` will enable transaction support. This option has the optional attribute ``idGenMode`` which controls how ids of inserted features (the values in the gml:id attribute) are treated. There are three id generation modes available:

* **UseExisting**: The original gml:id values from the input are stored. This may lead to errors if the provided ids are already in use.
* **GenerateNew** (default): New and unique ids are generated. References in the input GML (xlink:href) that point to a feature with an reassigned id are fixed as well, so reference consistency is maintained.
* **ReplaceDuplicate**: The WFS will try to use the original gml:id values that have been provided in the input. In case a certain identifier already exists in the backend, a new and unique identifier will be generated. References in the input GML (xlink:href) that point to a feature with an reassigned id are fixed as well, so reference consistency is maintained.

.. hint::
    Currently, transactions can only be enabled if your WFS is attached to a single feature store.

.. hint::
    Not every feature store implementation supports transactions, so you may encounter that transactions are rejected, even though you activated them in the WFS configuration.

.. hint::
    The details of the id generation depend on the feature store implementation/configuration.

.. hint::
    In a WFS 1.1.0 insert, the id generation mode can be overridden by attribute *idGenMode* of the ``Insert`` element. WFS 1.0.0 and WFS 2.0.0 don't support to specify the id generation mode on a request basis.


^^^^^^^^^^^^^^^^^^^^^^^^^^^
Adapting GML output formats
^^^^^^^^^^^^^^^^^^^^^^^^^^^

By default, a deegree WFS will offer GML 2, 3.0, 3.1, and 3.2 as output formats and announce those formats in the GetCapabilities responses (except for WFS 1.0.0, as this version of the standard has no means of announcing other formats than GML 2). The element for GetFeature responses is ``wfs:FeatureCollection``, as mandated by the WFS specification.

In some cases, you may want to alter aspects of the offered output formats. For example, if you want your WFS to serve a specific application schema (e.g. INSPIRE Data Themes), you should restrict the announced GML versions to the one used for the application schema. These and other output-format related aspects can be controlled by element ``GMLFormat``.

.. topic:: Example for WFS config option ``GMLFormat``

    .. literalinclude:: ../../xml/wfs_gmlformat.xml
        :language: xml

The ``GMLFormat`` option has the following sub-options:

+------------------------------+--------------+---------+------------------------------------------------------------------------------+
| Option                       | Cardinality  | Value   | Description                                                                  |
+==============================+==============+=========+==============================================================================+
| @gmlVersion                  | 1..1         | String  | GML version (GML_2, GML_30, GML_31 or GML_32)                                |
+------------------------------+--------------+---------+------------------------------------------------------------------------------+
| MimeType                     | 1..n         | String  | Mime types associated with this format configuration                         |
+------------------------------+--------------+---------+------------------------------------------------------------------------------+
| GenerateBoundedByForFeatures | 0..1         | Boolean | Forces output of gml:boundedBy property for every feature                    |
+------------------------------+--------------+---------+------------------------------------------------------------------------------+
| GetFeatureResponse           | 0..1         | Complex | Options for controlling GetFeature responses                                 |
+------------------------------+--------------+---------+------------------------------------------------------------------------------+
| DecimalCoordinateFormatter/  | 0..1         | Complex | Controls the formatting of geometry coordinates                              |
| CustomCoordinateFormatter    |              |         |                                                                              |
+------------------------------+--------------+---------+------------------------------------------------------------------------------+
| GeometryLinearization        | 0..1         | Complex | Activates/controls the linearization of exported geometries                  |
+------------------------------+--------------+---------+------------------------------------------------------------------------------+

""""""""""""""""""""""""
Basic GML format options
""""""""""""""""""""""""

* ``@gmlVersion``: This attribute defines the GML version (GML_2, GML_30, GML_31 or GML_32)
* ``MimeType``: Mime types associated with this format configuration (and announced in GetCapabilities)
* ``GenerateBoundedByForFeatures``: By default, the ``gml:boundedBy`` property will only be exported for the member features if the feature store provides it. By setting this option to ``true``, the WFS will calculate the envelope and include it as a ``gml:boundedBy`` property. Please note that this setting does not affect the inclusion of the ``gml:boundedBy`` property for on the feature collection level (see DisableStreaming for that).

""""""""""""""""""""""""""""
GetFeature response settings
""""""""""""""""""""""""""""

Option ``GetFeatureResponse`` has the following sub-options:

+--------------------------+--------------+-----------+------------------------------------------------------------------------------+
| Option                   | Cardinality  | Value     | Description                                                                  |
+==========================+==============+===========+==============================================================================+
| ContainerElement         | 0..1         | QName     | Qualified root element name, default: wfs:FeatureCollection                  |
+--------------------------+--------------+-----------+------------------------------------------------------------------------------+
| FeatureMemberElement     | 0..1         | QName     | Qualified feature member element name, default: gml:featureMember            |
+--------------------------+--------------+-----------+------------------------------------------------------------------------------+
| AdditionalSchemaLocation | 0..1         | String    | Added to xsi:schemaLocation attribute of wfs:FeatureCollection               |
+--------------------------+--------------+-----------+------------------------------------------------------------------------------+
| DisableDynamicSchema     | 0..1         | Complex   | Controls DescribeFeatureType strategy, default: regenerate schema            |
+--------------------------+--------------+-----------+------------------------------------------------------------------------------+
| DisableStreaming         | 0..1         | Boolean   | Disables output streaming, include numberOfFeature information/gml:boundedBy |
+--------------------------+--------------+-----------+------------------------------------------------------------------------------+

* ``ContainerElement``: By default, the container element of a GetFeature response is ``wfs:FeatureCollection``. Using this option, you can specify an alternative element name. In order to bind the namespace prefix, use standard XML namespace mechanisms (xmlns attribute). This option is ignored for WFS 2.0.0.
* ``FeatureMemberElement``: By default, the member features are included in ``gml:featureMember`` (WFS 1.0.0/1.1.0) or ``wfs:member`` elements (WFS 2.0.0). Using this option, you can specify an alternative element name. In order to bind the namespace prefix, use standard XML namespace mechanisms (xmlns attribute). This option is ignored for WFS 2.0.0.
* ``AdditionalSchemaLocation``: By default, the ``xsi:schemaLocation`` attribute in a GetFeature response is auto-generated and refers to all schemas necessary for validation of the response. Using this option, you can add additional namespace/URL pairs for adding additional schemas. This may be required when you override the returned container or feature member elements in order to achieve schema-valid output.
* ``DisableDynamicSchema``: By default, the GML application schema returned in DescribeFeatureType reponses (and referenced in the ``xsi:schemaLocation`` of query responses) will be generated dynamically from the internal feature type representation. This allows generation of application schemas for different GML versions and is fine for simple feature models (e.g. feature types served from shapefiles or flat database tables). However, valid re-encoding of complex GML application schema (such as INSPIRE Data Themes) is technically not feasible. In these cases, you will have to set this option to ``false``, so the WFS will produce a response that refers to the original schema files used for configuring the feature store. If you want the references to point to an external copy of your GML application schema files (instead of pointing back to the deegree WFS), use the optional attribute ``baseURL`` that this element provides.
* ``DisableStreaming``: By default, returned features are not collected in memory, but directly streamed from the backend (e.g. an SQL database) and individually encoded as GML. This enables the querying of huge numbers of features with only minimal memory footprint. However, by using this strategy, the number of features and their bounding box is not known when the WFS starts to write out the response. Therefore, this information is omitted from the response (which is perfectly valid according to WFS 1.0.0 and 1.1.0, and a change request for WFS 2.0.0 has been accepted). If you find that your WFS client has problems with the response, you may set this option to ``false``. Features will be collected in memory first and the generated response will include numberOfFeature information and gml:boundedBy for the collection. However, for huge response and heavy server load, this is not recommended as it introduces significant overhead and may result in out-of-memory errors.

"""""""""""""""""""""
Coordinate formatters
"""""""""""""""""""""

By default, GML geometries will be encoded using 6 decimal places for CRS with degree axes and 3 places for CRS with metric axes. In order to override this, two options are available:

* ``DecimalCoordinatesFormatter``: Empty element, attribute ``places`` specifies the number of decimal places.
* ``CustomCoordinateFormatter``: By specifiying this element, an implementation of Java interface ``org.deegree.geometry.io.CoordinateFormatter`` can be instantiated. Child element ``JavaClass`` contains the qualified name of the Java class (which must be on the classpath).

""""""""""""""""""""""
Geometry linearization
""""""""""""""""""""""

Some feature stores (e.g. the SQL feature store when connected to an Oracle Spatial database) can deliver non-linear geometries (e.g. arcs). Here's an example for the GML 3.1.1 encoding of such a geometry as it would be returned by the WFS:

.. topic:: Example for a non-linear GML geometry

    .. literalinclude:: ../../xml/wfs_linearization_curve1.xml
        :language: xml

This is perfectly valid GML, but there are two reasons why you may not want your WFS to return non-linear GML geometries:

* There's no encoding for non-linear GML geometries in GML version 2
* Currently available WFS clients (e.g. QGIS, uDig, ...) cannot cope with them

Option ``GeometryLinearization`` will ensure that GML responses will only contain linear geometries. Curves with non-linear segments and surfaces with non-linear boundary segments will be converted before they are encoded to GML. Here's an example usage of this GML format option:

.. topic:: Example config snippet for activating geometry linearization

    .. literalinclude:: ../../xml/wfs_linearization_example.xml
        :language: xml

``GeometryLinearization`` has a single mandatory option ``Accuracy``. It defines the numerical accuracy of the linear approximation in units of the coordinate reference system used by the feature store. If the coordinate reference system is based on meters, a value of 0.1 will ensure that the maximum error between the original and the linearized geometry does not exceed 10 centimeters.

Here's an example of a linearized version of the example geometry as it would be generated by the WFS:

.. topic:: Example for linearized GML output

    .. literalinclude:: ../../xml/wfs_linearization_curve2.xml
        :language: xml

^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Adding custom output formats
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Using option element ``CustomFormat``, it possible to plug-in your own Java classes to generate the output for a specific mime type (e.g. a binary format)

+-----------+-------------+---------+------------------------------------------------------+
| Option    | Cardinality | Value   | Description                                          |
+===========+=============+=========+======================================================+
| MimeType  | 1..n        | String  | Mime types associated with this format configuration |
+-----------+-------------+---------+------------------------------------------------------+
| JavaClass | 1..1        | String  | Qualified Java class name                            |
+-----------+-------------+---------+------------------------------------------------------+
| Config    | 0..1        | Complex | Value to add to xsi:schemaLocation attribute         |
+-----------+-------------+---------+------------------------------------------------------+

* ``MimeType``: Mime types associated with this format configuration (and announced in GetCapabilities)
* ``JavaClass``: Therefore, an implementation of interface ``org.deegree.services.wfs.format.CustomFormat`` must be present on the classpath.
* ``Config``:

^^^^^^^^^^^^^^
Stored queries
^^^^^^^^^^^^^^

Besides standard ('ad hoc') queries, WFS 2.0.0 introduces so-called stored queries. When WFS 2.0.0 support is activated, your WFS will automatically support the well-known stored query ``urn:ogc:def:storedQuery:OGC-WFS::GetFeatureById`` (defined in the WFS 2.0.0 specification). It can be used to query a feature instance by specifying it's gml:id (similar to GetGmlObject requests in WFS 1.1.0). In order to define custom stored queries, use the ``StoredQuery`` element to specify the file name of a StoredQueryDefinition file. The given file name (can be relative) must point to a valid WFS 2.0.0 StoredQueryDefinition file. Here's an example:

.. topic:: Example for a WFS 2.0.0 StoredQueryDefinition file

    .. literalinclude:: ../../xml/wfs_storedquerydefinition.xml
        :language: xml

This example is actually usable if your WFS is set up to serve the ad:Address feature type from INSPIRE Annex I. It defines the stored query ``urn:x-inspire:storedQuery:GetAddressesForStreet`` for retrieving ad:Address features that are located in the specified street. The street name is passed using parameter ``streetName``. If your WFS instance can be reached at ``http://localhost:8080/services``, you could use the request ``http://localhost:8080/services?request=GetFeature&storedquery_id=urn:x-inspire:storedQuery:GetAddressesForStreet&streetName=Madame%20Curiestraat`` to fetch the ad:Address features in street Madame Curiestraat.

.. tip::
    deegree WFS supports the execution of stored queries using ``GetFeature`` and ``GetPropertyValue`` requests. It also implements the ``ListStoredQueries`` and the ``DescribeStoredQueries`` operations. However, there is no support for ``CreateStoredQuery`` and ``DropStoredQuery`` at the moment.
