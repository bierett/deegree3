^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Workspace files and resources
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

In order to clarify the relation between workspace files and resources, let's have a look at an example:

.. figure:: ../../images/workspace-example.png
    :target: ../../_images/workspace-example.png

    Example workspace directory

As noted, deegree scans the well-known resource directories for XML files (``*.xml``) on startup (note that it will omit directory ``manager``, as it is not a well-known resource directory). For every file found, deegree will check the type of configuration format (by determining the name of the XML root element). If it is a recognized format, deegree will try to create and initialize a corresponding resource. For the example, this results in the following setup:

* A metadata store with id ``iso19115``
* A JDBC connection pool with id ``conn1``
* A web service with id ``csw``

The individual XML resource formats and their options are described in the later chapters of the documentation.

.. tip::
    You may wonder why the ``main.xml`` and ``metadata.xml`` files are not considered as web service resource files. These two filenames are reserved and treated specifically. See :ref:`anchor-configuration-service` for details.

.. tip::
    The configuration format has to match the workspace subdirectory, e.g. metadata store configuration files are only considered when they are located in ``datasources/metadata``.
