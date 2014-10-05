---------------------
The deegree workspace
---------------------

The deegree workspace is the modular, resource-oriented and extensible configuration concept used by deegree webservices. The following diagram shows the different types of resources that it contains:

.. figure:: ../../images/workspace-overview.png
    :target: ../../_images/workspace-overview.png

    Configuration aspects of deegree workspaces

The following table provides a short description of the different types of workspace resources:

.. table:: Workspace resource types

+---------------------------------+------------------------------------------------------------------------------+
| Resource type                   | Description                                                                  |
+=================================+==============================================================================+
| Web Services                    | Web services (WFS, WMS, WMTS, CSW, WPS)                                      |
+---------------------------------+------------------------------------------------------------------------------+
| Data Stores (Coverage)          | Coverage (raster) data access (GeoTIFFs, raster pyramids, etc.)              |
+---------------------------------+------------------------------------------------------------------------------+
| Data Stores (Feature)           | Feature (vector) data access (Shapefiles, PostGIS, Oracle Spatial, etc.)     |
+---------------------------------+------------------------------------------------------------------------------+
| Data Stores (Metadata)          | Metadata record access (ISO records stored in PostGIS, Oracle, etc.)         |
+---------------------------------+------------------------------------------------------------------------------+
| Data Stores (Tile)              | Pre-rendered map tiles (GeoTIFF, image hierarchies in the file system, etc.) |
+---------------------------------+------------------------------------------------------------------------------+
| Map Layers (Layer)              | Map layers based on data stores and styles                                   |
+---------------------------------+------------------------------------------------------------------------------+
| Map Layers (Style)              | Styling rules for features and coverages                                     |
+---------------------------------+------------------------------------------------------------------------------+
| Map Layers (Theme)              | Layer trees based on individual layers                                       |
+---------------------------------+------------------------------------------------------------------------------+
| Processes                       | Geospatial processes for the WPS                                             |
+---------------------------------+------------------------------------------------------------------------------+
| Server connections (JDBC)       | Connections to SQL databases                                                 |
+---------------------------------+------------------------------------------------------------------------------+
| Server connections (remote OWS) | Connections to remote OGC web services                                       |
+---------------------------------+------------------------------------------------------------------------------+

Physically, every configured resource corresponds to an XML configuration file in the active workspace directory.
