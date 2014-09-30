.. _anchor-configuration-wmts:

---------------------------
Web Map Tile Service (WMTS)
---------------------------

In deegree terminology, a deegree WMTS provides access to tiles stored in tile stores. The WMTS is configured using so-called *themes*. A theme can be thought of as a collection of layers, organized in a tree structure.

.. figure:: ../../images/workspace-wmts.png
    :figwidth: 80%
    :width: 80%
    :target: ../../_images/workspace-wmts.png

    A WMTS resource is connected to any number of theme resources (with tile layers)

.. tip::
    In order to fully understand deegree WMTS configuration, you will have to learn configuration of other workspace aspects as well. Chapter :ref:`anchor-configuration-tilestore` describes the configuration of tile data access. Chapter :ref:`anchor-configuration-layers` describes the configuration of layers (only tile layers are usable for the WMTS). Chapter :ref:`anchor-configuration-themes` describes how to create a theme from layers.

^^^^^^^^^^^^^^^
Minimal example
^^^^^^^^^^^^^^^

The only mandatory section is ``ServiceConfiguration`` (which can be empty), therefore a minimal WMTS configuration example looks like this:

.. topic:: WMTS config example 1: Minimal configuration

    .. literalinclude:: ../../xml/wmts_basic.xml
        :language: xml

This will create a deegree WMTS resource that connects to all configured themes of the workspace.

^^^^^^^^^^^^^^^^^^^^
More complex example
^^^^^^^^^^^^^^^^^^^^

A more complex configuration that restricts the offered themes looks like this:

.. topic:: WMTS config example 2: More complex configuration

    .. literalinclude:: ../../xml/wmts_complex.xml
        :language: xml

^^^^^^^^^^^^^^^^^^^^^^
Configuration overview
^^^^^^^^^^^^^^^^^^^^^^

The deegree WMTS config file format is defined by schema file http://schemas.deegree.org/services/wmts/3.2.0/wmts.xsd. The root element is ``deegreeWMTS`` and the config attribute must be ``3.2.0``.

The following table lists all available configuration options. When specifying them, their order must be respected.

.. table:: Options for ``deegreeWMTS``

+--------------------------+--------------+---------+------------------------------------------------------------------------------+
| Option                   | Cardinality  | Value   | Description                                                                  |
+==========================+==============+=========+==============================================================================+
| MetadataURLTemplate      | 0..1         | String  | Template for generating URLs to layer metadata                               |
+--------------------------+--------------+---------+------------------------------------------------------------------------------+
| ThemeId                  | 0..n         | String  | Limits themes to use                                                         |
+--------------------------+--------------+---------+------------------------------------------------------------------------------+

Below the ``ServiceConfiguration`` section you can specify custom featureinfo format handlers:

.. code-block:: xml
  ...
  </ServiceConfiguration>
  <FeatureInfoFormats>
  ...
  </FeatureInfoFormats>

Have a look at section :ref:`anchor-featureinfo-configuration` (in the WMS chapter) to see how custom featureinfo formats are configured. Take note that the GetFeatureInfo operation is currently only supported for remote WMS tile store backends.
