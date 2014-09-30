.. _anchor-configuration-featurestore:

==============
Feature stores
==============

Feature stores are workspace resources that provide access to stored features. The two most common use cases for feature stores are:

* Accessing via :ref:`anchor-configuration-wfs`
* Providing of data for :ref:`anchor-configuration-feature-layers`

The remainder of this chapter describes some relevant terms and the feature store configuration files in detail. You can access this configuration level by clicking **feature stores** in the service console. The corresponding resource configuration files are located in subdirectory ``datasources/feature/`` of the active deegree workspace directory.

.. figure:: ../images/workspace-overview-feature.png
    :figwidth: 80%
    :width: 80%
    :target: ../_images/workspace-overview-feature.png

    Feature store resources provide access to geo objects

.. toctree::
    :glob:
    :maxdepth: 2

    overview/index
    shape-featurestore/index
    memory-featurestore/index
    simple-sql-featurestore/index
    sql-featurestore/index

