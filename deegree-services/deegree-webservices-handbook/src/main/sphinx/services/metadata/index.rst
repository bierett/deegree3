.. _anchor-configuration-service-metadata:

--------
Metadata
--------

This section describes the configuration for the different types of metadata that a service reports in the ``GetCapabilities`` response. These options don't affect the data that the service offers or the behaviour of the service. It merely changes the descriptive metadata that the service reports.

In order to configure the metadata for a web service instance ``xyz``, create a corresponding ``xyz_metadata.xml`` file in the ``services`` directory of the workspace. The actual service type does not matter, the configuration works for all types of service alike.

.. topic:: Example for ``deegreeServicesMetadata``

    .. literalinclude:: xml/service_metadata.xml
        :language: xml

The metadata config file format is defined by schema file http://schemas.deegree.org/services/metadata/3.2.0/metadata.xsd. The root element is ``deegreeServicesMetadata`` and the config attribute must be ``3.2.0``. The following table lists all available configuration options (complex ones contain nested options themselves). When specifiying them, their order must be respected.

.. table:: Options for ``deegreeServicesMetadata``

+-------------------------+-------------+---------+------------------------------------------------------------------+
| Option                  | Cardinality | Value   | Description                                                      |
+=========================+=============+=========+==================================================================+
| ServiceIdentification   | 1..1        | Complex | Metadata that describes the service                              |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| ServiceProvider         | 1..1        | Complex | Metadata that describes the provider of the service              |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| DatasetMetadata         | 0..1        | Complex | Metadata on the datasets provided by the service                 |
+-------------------------+-------------+---------+------------------------------------------------------------------+
| ExtendedCapabilities    | 0..n        | Complex | Extended Metadata reported in OperationsMetadata section         |
+-------------------------+-------------+---------+------------------------------------------------------------------+

The remainder of this section describes these options and their sub-options in detail.

^^^^^^^^^^^^^^^^^^^^^^
Service identification
^^^^^^^^^^^^^^^^^^^^^^

The ``ServiceIdentification`` option has the following sub-options:

.. table:: Options for ``ServiceIdentification``

+----------------------+-------------+---------+-------------------------------------------------------------------------------+
| Option               | Cardinality | Value   | Description                                                                   |
+======================+=============+=========+===============================================================================+
| Title                | 0..n        | String  | Title of the service                                                          |
+----------------------+-------------+---------+-------------------------------------------------------------------------------+
| Abstract             | 0..n        | String  | Abstract                                                                      |
+----------------------+-------------+---------+-------------------------------------------------------------------------------+
| Keywords             | 0..n        | Complex | Keywords that describe the service                                            |
+----------------------+-------------+---------+-------------------------------------------------------------------------------+
| Fees                 | 0..1        | String  | Fees that apply for using this service                                        |
+----------------------+-------------+---------+-------------------------------------------------------------------------------+
| AccessConstraints    | 0..n        | String  | Access constraints for this service                                           |
+----------------------+-------------+---------+-------------------------------------------------------------------------------+

^^^^^^^^^^^^^^^^
Service provider
^^^^^^^^^^^^^^^^

The ``ServiceProvider`` option has the following sub-options:

.. table:: Options for ``ServiceProvider``

+----------------+-------------+---------+-------------------------------------+
| Option         | Cardinality | Value   | Description                         |
+================+=============+=========+=====================================+
| ProviderName   | 0..1        | String  | Name of the service provider        |
+----------------+-------------+---------+-------------------------------------+
| ProviderSite   | 0..1        | String  | Website of the service provider     |
+----------------+-------------+---------+-------------------------------------+
| ServiceContact | 0..1        | Complex | Contact information                 |
+----------------+-------------+---------+-------------------------------------+

^^^^^^^^^^^^^^^^
Dataset metadata
^^^^^^^^^^^^^^^^

This type of metadata is attached to the datasets that a service offers (e.g. layers for the WMS or feature types for the WFS). The services themselves may have specific mechanisms to override this metadata, so make sure to have a look at the appropriate service sections. However, some metadata configuration can be done right here.

To start with, you'll need to add a ``DatasetMetadata`` container element:

.. code-block:: xml

  <DatasetMetadata>
  ...
  </DatasetMetadata>

Apart from the descriptive metadata (title, abstract etc.) for each dataset, you can also configure ``MetadataURL``s, external metadata links and metadata as well as external metadata IDs.

For general ``MetadataURL`` configuration, you can configure the element ``MetadataUrlTemplate``. Its content can be any URL, which may contain the pattern ``${metadataSetId}``. For each dataset (layer, feature type) the service will output a ``MetadataURL`` based on that pattern, if a ``MetadataSetId`` has been configured for that dataset (see below). The template is optional, if omitted, no ``MetadataURL`` will be produced.

Configuration for the template looks like this:

.. code-block:: xml

  <DatasetMetadata>
    <MetadataUrlTemplate>http://some.url.de/csw?request=GetRecordById&amp;service=CSW&amp;version=2.0.2&amp;outputschema=http://www.isotc211.org/2005/gmd&amp;elementsetname=full&amp;id=${metadataSetId}</MetadataUrlTemplate>
  ...
  </DatasetMetadata>

You can also configure ``ExternalMetadataAuthority`` elements, which are currently only used by the WMS. You can define multiple authorities, with the authority URL as text content and a unique ``name`` attribute. For each dataset you can define an ID for an authority by refering to that name. This will generate an ``AuthorityURL`` and ``Identifier`` pair in WMS capabilities documents (version 1.3.0 only).

Configuration for an external authority looks like this:

.. code-block:: xml

  <DatasetMetadata>
    <ExternalMetadataAuthority name="myorg">http://www.myauthority.org/metadataregistry/</ExternalMetadataAuthority>
  ...
  </DatasetMetadata>

Now follows the list of the actual dataset metadata. You can add as many as you need:

.. code-block:: xml

  <DatasetMetadata>
    <MetadataUrlTemplate>...</MetadataUrlTemplate>
    ...
    <Dataset>
    ...
    </Dataset>
    <Dataset>
    ...
    </Dataset>
    ...
  </DatasetMetadata>

For each dataset, you can configure the metadata as outlined in the following table:

.. table:: Metadata options for ``Dataset``

+-------------------------+--------------+---------------+----------------------------------------------------------------------------------------------+
| Option                  | Cardinality  | Value         | Description                                                                                  |
+=========================+==============+===============+==============================================================================================+
| Name                    | 1            | String/QName  | the layer/feature type name you refer to                                                     |
+-------------------------+--------------+---------------+----------------------------------------------------------------------------------------------+
| Title                   | 0..n         | String        | can be multilingual by using the ``lang`` attribute                                          |
+-------------------------+--------------+---------------+----------------------------------------------------------------------------------------------+
| Abstract                | 0..n         | String        | can be multilingual by using the ``lang`` attribute                                          |
+-------------------------+--------------+---------------+----------------------------------------------------------------------------------------------+
| MetadataSetId           | 0..1         | String        | is used to generate ``MetadataURL`` s, see above                                             |
+-------------------------+--------------+---------------+----------------------------------------------------------------------------------------------+
| ExternalMetadataSetId   | 0..n         | String        | is used to generate ``AuthorityURL`` s and ``Identifier`` s for WMS, see above. Refer to an  |
|                         |              |               | authority using the ``authority`` attribute.                                                 |
+-------------------------+--------------+---------------+----------------------------------------------------------------------------------------------+

^^^^^^^^^^^^^^^^^^^^^
Extended capabilities
^^^^^^^^^^^^^^^^^^^^^

Extended capabilities are generic metadata sections below the ``OperationsMetadata`` element in the ``GetCapabilities`` response. They are not defined by the OGC service specifications, but by additional guidance documents, such as the INSPIRE Network Service TGs. deegree treats this section as a generic XML element and includes it in the output. If your service supports multiple protocol versions (e.g. a WFS that supports 1.1.0 and 2.0.0), you may include multiple ``ExtendedCapabilities`` elements in the metadata configuration and use attribute ``protocolVersions`` to indicate the version that you want to define the extended capabilities for.
