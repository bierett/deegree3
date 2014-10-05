-----------------------------------------------
Dependencies of the deegree configuration files
-----------------------------------------------

The following diagram shows the different types of resources and their dependencies. The deegree configuration can be divided into several sections:

 * web sevrices
 * data stores
 * map layers
 * server connections

For example, to offer a Web Feature Service, a feature store (based on a shapefile, database, etc) must be configured. With a rasterfile, like a GeoTIFF, you can configured a tile store and a coverage store to offer a Web Map Service.

.. figure:: ../../images/workspace-configuration-dependencies.png
    :target: ../../_images/workspace-configuration-dependencies.png

    Workspace configuration dependencies
