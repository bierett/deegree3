.. _anchor-configuration-wps:

----------------------------
Web Processing Service (WPS)
----------------------------

A deegree WPS allows the invocation of geospatial processes. The offered processes are determined by the attached process provider resources.

.. figure:: ../../images/workspace-wps.png
    :figwidth: 90%
    :width: 90%
    :target: ../../_images/workspace-wps.png

    Workspace components involved in a deegree WPS configuration

.. tip::
    In order to fully master deegree WPS configuration, you will have to understand :ref:`anchor-configuration-processproviders` as well.

^^^^^^^^^^^^^^^
Minimal example
^^^^^^^^^^^^^^^

A minimal valid WPS configuration example looks like this:

.. code-block:: xml

  <deegreeWPS configVersion="3.1.0" xmlns="http://www.deegree.org/services/wps" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.deegree.org/services/wps http://schemas.deegree.org/services/wps/3.1.0/wps_configuration.xsd">
  </deegreeWPS>

This will create a WPS resource with the following properties:

* All WPS protocol versions are enabled. Currently, this is only 1.0.0.
* The WPS resource will attach to all process provider resources in the workspace.
* Temporary files (e.g. for process results) are stored in the standard Java temp directory of the deegree webapp.
* The last 100 process executions are tracked.
* Memory buffers (e.g. for inline XML inputs) are limited to 1 MB each. If this limit is exceeded, buffering is switched to use a file in the storage directory.

^^^^^^^^^^^^^^^
Complex example
^^^^^^^^^^^^^^^

A more complex configuration example looks like this:

.. code-block:: xml

  <deegreeWPS configVersion="3.1.0" xmlns="http://www.deegree.org/services/wps" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.deegree.org/services/wps http://schemas.deegree.org/services/wps/3.1.0/wps_configuration.xsd">

    <SupportedVersions>
      <Version>1.0.0</Version>
    </SupportedVersions>

    <DefaultExecutionManager>
      <StorageDir>../var/wps/</StorageDir>
      <TrackedExecutions>1000</TrackedExecutions>
      <InputDiskSwitchLimit>1048576</InputDiskSwitchLimit>
    </DefaultExecutionManager>

  </deegreeWPS>

This will create a WPS resource with the following properties:

* Enabled WPS protocol versions: 1.0.0
* The WPS resource will attach to all process provider resources in the workspace.
* Storage directory for temporary files (e.g. for process results) is ``/var/wps`` inside the workspace.
* The last 1000 process executions will be tracked.
* Memory buffers (e.g. for inline XML inputs) are limited to 1 MB each. If this limit is exceeded, buffering is switched to use a file in the storage directory.

^^^^^^^^^^^^^^^^^^^^^^
Configuration overview
^^^^^^^^^^^^^^^^^^^^^^

The deegree WPS config file format is defined by schema file http://schemas.deegree.org/services/wps/3.1.0/wps_configuration.xsd. The root element is ``deegreeWPS`` and the config attribute must be ``3.1.0``. The following table lists all available configuration options (complex ones contain nested options themselves). When specifiying them, their order must be respected.

.. table:: Options for ``deegreeWPS``

+-------------------------+-------------+---------+-----------------------------------------------+
| Option                  | Cardinality | Value   | Description                                   |
+=========================+=============+=========+===============================================+
| SupportedVersions       | 0..1        | Complex | Activated OGC protocol versions, default: all |
+-------------------------+-------------+---------+-----------------------------------------------+
| DefaultExecutionManager | 0..1        | Complex | Settings for tracking process executions      |
+-------------------------+-------------+---------+-----------------------------------------------+

The remainder of this section describes these options and their sub-options in detail.

* ``SupportedVersions``: By default, all implemented WMS protocol versions are activated. Currently, this is just 1.0.0 anyway. Alternatively you can control offered WPS protocol versions using the element ``SupportedVersions``. This element allows the child element ``<Version>1.0.0</Version>`` for now.

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
DefaultExecutionManager section
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

This section controls aspects that are related to temporary storage (for input and output parameter values) during the execution of processes. The ``DefaultExecutionManager`` option has the following sub-options:

.. table:: Options for ``DefaultExecutionManager``

+----------------------+-------------+---------+-------------------------------------------------------------------------------+
| Option               | Cardinality | Value   | Description                                                                   |
+======================+=============+=========+===============================================================================+
| StorageDir           | 0..1        | String  | Directory for storing execution-related data, default: Java tempdir           |
+----------------------+-------------+---------+-------------------------------------------------------------------------------+
| TrackedExecutions    | 0..1        | Integer | Number of executions to track, default: 100                                   |
+----------------------+-------------+---------+-------------------------------------------------------------------------------+
| InputDiskSwitchLimit | 0..1        | Integer | Limit in bytes, before a ComplexInputInput is written to disk, default: 1 MiB |
+----------------------+-------------+---------+-------------------------------------------------------------------------------+
