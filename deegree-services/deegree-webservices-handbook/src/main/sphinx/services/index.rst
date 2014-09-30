.. _anchor-configuration-service:

============
Web services
============

This chapter describes the configuration of web service resources. You can access this configuration level by clicking the **web services** link in the administration console. The corresponding configuration files are located in the ``services/`` subdirectory of the active deegree workspace directory.

.. figure:: ../images/workspace-overview-services.png
    :figwidth: 80%
    :width: 80%
    :target: ../_images/workspace-overview-services.png

    Web services are the top-level resources of the deegree workspace

.. tip::
The identifier of a web service resource has a special purpose. If your deegree instance can be reached at ``http://localhost:8080/deegree-webservices``, the common endpoint for connecting to your services is ``http://localhost:8080/deegree-webservices/services``. However, if you define multiple service resources of the same type in your workspace (e.g. two WMS instances with identifiers ``wms1`` and ``wms2``), you cannot use the common URL, as deegree cannot determine the targeted WMS instance from the request. In this case, simply append the resource identifier to the common endpoint URL (e.g. ``http://localhost:8080/deegree-webservices/services/wms2``) to choose the service resource that you want to connect to explicitly.

.. toctree::
    :glob:
    :maxdepth: 1

    wfs/index
    wms/index
    wmts/index
    csw/index
    wps/index
    metadata/index
    service-controller/index