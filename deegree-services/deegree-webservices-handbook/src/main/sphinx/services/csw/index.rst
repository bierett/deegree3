.. _anchor-configuration-csw:

-----------------------------------
Catalogue Service for the Web (CSW)
-----------------------------------

In deegree terminology, a deegree CSW provides access to metadata records stored in a metadata store. If the metadata store is transaction-capable, CSW transactions can be used to modify the stored records.

.. figure:: ../../images/workspace-csw.png
    :figwidth: 80%
    :width: 80%
    :target: ../../_images/workspace-csw.png

    A CSW resource is connected to exactly one metadata store resource

.. tip::
    In order to fully understand deegree CSW configuration, you will have to learn configuration of other workspace aspects as well. Chapter :ref:`anchor-configuration-metadatastore` describes the configuration of metadatastores.

^^^^^^^^^^^^^^^
Minimal example
^^^^^^^^^^^^^^^

There is no mandatory element, therefore a minimal CSW configuration example looks like this:

.. topic:: CSW config example 1: Minimal configuration

    .. literalinclude:: ../../xml/csw_basic.xml
        :language: xml

^^^^^^^^^^^^^^^^^^^^^^
Configuration overview
^^^^^^^^^^^^^^^^^^^^^^

The deegree CSW config file format is defined by schema file http://schemas.deegree.org/services/csw/3.2.0/csw_configuration.xsd. The root element is ``deegreeCSW`` and the config attribute must be ``3.2.0``.

The following table lists all available configuration options. When specifiying them, their order must be respected.

.. table:: Options for ``deegreeCSW``

+--------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| Option                   | Cardinality  | Value   | Description                                                                                  |
+==========================+==============+=========+==============================================================================================+
| SupportedVersions        | 0..1         | String  | Supported CSW Version (Default: 2.0.2)                                                       |
+--------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| MaxMatches               | 0..1         | Integer | Not negative number of matches (Default:0)                                                   |
+--------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| MetadataStoreId          | 0..1         | String  | Id of the meradatastoreId to use as backenend. By default the only configured store is used. |
+--------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| EnableTransactions       | 0..1         | Boolean | Enable transactions (CSW operations) default: disabled. (Default: false)                     |
+--------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| EnableInspireExtensions  | 0..1         |         | Enable the INSPIRE extensions, default: disabled                                             |
+--------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| ExtendedCapabilities     | 0..1         | anyURI  | Include referenced capabilities section.                                                     |
+--------------------------+--------------+---------+----------------------------------------------------------------------------------------------+
| ElementNames             | 0..1         |         |  List of configured return profiles. See following xml snippet for detailed informations.    |
+--------------------------+--------------+---------+----------------------------------------------------------------------------------------------+

    .. literalinclude:: ../../xml/csw_elementNames.snippet
        :language: xml

^^^^^^^^^^^^^^^^^^^^^^^^^^
Extended Functionality
^^^^^^^^^^^^^^^^^^^^^^^^^^
* deegree3 CSW (up to 3.2-pre11) supports JSON as additional output format. Use *outputFormat="application/json"* in your GetRecords or GetRecordById Request to get the matching records in JSON.
