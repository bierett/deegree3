-------------------------------------------
Location of the deegree workspace directory
-------------------------------------------

The active deegree workspace is part of the ``.deegree`` directory which stores a few global configuration files along with the workspace. The location of this directory depends on your operating system.

^^^^^^^^^^^^^^^^^^^^^^
Linux/Solaris/Mac OS X
^^^^^^^^^^^^^^^^^^^^^^

On UNIX-like systems (Linux/Solaris/MacOS X), deegree's configuration files are located in folder ``$HOME/.deegree/``. Note that ``$HOME`` is determined by the user that started the web application container that runs deegree. If you started the ZIP version of deegree as user "kelvin", then the directory will be something like ``/home/kelvin/.deegree``.

.. tip::
    In order to use a different folder for deegree's configuration files, you can set the system environment variable ``DEEGREE_WORKSPACE_ROOT``. Note that the user running the web application container must have read/write access to this directory.

^^^^^^^
Windows
^^^^^^^

On Windows, deegree's configuration files are located in folder ``%USERPROFILE%/.deegree/``. Note that ``%USERPROFILE%`` is determined by the user that started the web application container that runs deegree. If you started the ZIP version of deegree as user "kelvin", then the directory will be something like ``C:\Users\kelvin\.deegree`` or ``C:\Dokumente und Einstellungen\kelvin\.deegree``.

.. tip::
    In order to use a different folder for deegree's configuration files, you can set the system environment variable ``DEEGREE_WORKSPACE_ROOT``.  Note that the user running the web application container must have read/write access to this directory.
