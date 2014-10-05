^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Global configuration files and the active workspace
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

If you downloaded all four example workspaces (as described in :ref:`anchor-getting-started`), set a console password and the proxy parameters, your ``.deegree`` directory will look like this:

.. figure:: ../../images/workspace-root.png
    :target: ../../_images/workspace-root.png

    Example ``.deegree`` directory

As you see, this ``.deegree`` directory contains four subdirectories. Every subdirectory corresponds to a deegree workspace. Besides the configuration files inside the workspace, three global configuration files exist:

.. raw:: latex

   \begin{table}
   \begin{center}

.. table:: Global configuration files and workspace directories
+------------------------+------------------------------------------+
| File name              | Function                                 |
+========================+==========================================+
| <subdirectory>         | Workspace directory                      |
+------------------------+------------------------------------------+
| console.pw             | Password for services console            |
+------------------------+------------------------------------------+
| proxy.xml              | Proxy settings                           |
+------------------------+------------------------------------------+
| webapps.properties     | Selects the active workspace             |
+------------------------+------------------------------------------+

.. raw:: latex

   \end{center}
   \caption{Global configuration files and workspace directories}
   \end{table}

Note that only a single workspace can be active at a time. The information on the active one is stored in file ``webapps.properties``.

.. tip::
    Usually, you don't need to care about the three files that are located at the top level of this directory. The service console creates and modifies them as required (e.g. when switching to a different workspace). In order to create a deegree webservices setup, you will need to create or edit resource configuration files in the active workspace directory. The remaining documentation will always refer to files in the (active) workspace directory.

.. tip::
    When multiple deegree webservices instances run on a single machine, every instance can use a different workspace. The file ``webapps.properties`` stores the active workspace for every deegree webapp separately.
