^^^^^^^^^^^^^^^^^^^^^^^^^^^
Use a validating XML editor
^^^^^^^^^^^^^^^^^^^^^^^^^^^

All deegree XML configuration files have a corresponding XML schema, which allows to detect syntactical errors easily. The editor built into the services console performs validation when you save a configuration file. If the contents is not valid according to the schema, the file will not be saved, but an error message will be displayed:

.. figure:: ../../images/console_edit_error.png
    :figwidth: 80%
    :width: 70%
    :target: ../../_images/console_edit_error.png

    The services console displays an XML syntax error

If you prefer to use a different editor for editing deegree's configuration files, it is highly recommended to choose a validating XML editor. Successfully tested editors are Eclipse and Altova XML Spy, but any schema-aware editor should work.

.. tip::
    In case you are able to understand XML schema, you can also use the schema file to find out about the available config options. deegree's schema files are hosted at http://schemas.deegree.org.
