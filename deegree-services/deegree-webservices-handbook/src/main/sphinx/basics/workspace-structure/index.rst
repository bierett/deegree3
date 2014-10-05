--------------------------------------------
Structure of the deegree workspace directory
--------------------------------------------

The workspace directory is a container for resource files with a well-defined directory structure. When deegree starts up, the active workspace directory is determined and the following subdirectories are scanned for XML resource configuration files:

.. table:: Well-known workspace resource directories
+------------------------+---------------------------------+
| Directory              | Resource type                   |
+========================+=================================+
| services/              | Web services                    |
+------------------------+---------------------------------+
| datasources/coverage/  | Coverage Stores                 |
+------------------------+---------------------------------+
| datasources/feature/   | Feature Stores                  |
+------------------------+---------------------------------+
| datasources/metadata/  | Metadata Stores                 |
+------------------------+---------------------------------+
| datasources/tile/      | Tile Stores                     |
+------------------------+---------------------------------+
| layers/                | Map Layers (Layer)              |
+------------------------+---------------------------------+
| styles/                | Map Layers (Style)              |
+------------------------+---------------------------------+
| themes/                | Map Layers (Theme)              |
+------------------------+---------------------------------+
| processes/             | Processes                       |
+------------------------+---------------------------------+
| jdbc/                  | Server Connections (JDBC)       |
+------------------------+---------------------------------+
| datasources/remoteows/ | Server Connections (Remote OWS) |
+------------------------+---------------------------------+

A workspace directory may contain additional directories to provide additional files along with the resource configurations. The major difference is that these directories are not scanned for resource files. Some common ones are:

.. table:: Additional workspace directories
+-----------------------+-------------------------------------------+
| Directory             | Used for                                  |
+=======================+===========================================+
| appschemas/           | GML application schemas                   |
+-----------------------+-------------------------------------------+
| data/                 | Datasets (GML, GeoTIFF, ...)              |
+-----------------------+-------------------------------------------+
| manager/              | Example requests (for the generic client) |
+-----------------------+-------------------------------------------+
