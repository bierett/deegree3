^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Resource identifiers and dependencies
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

It has already been hinted that resources have an identifier, e.g. for file ``jdbc/conn1.xml`` a JDBC connection pool with identifier ``conn1`` is created. You probably have guessed that the identifier is derived from the file name (file name minus suffix), but you may wonder what purpose the identifier serves. The identifier is used for wiring resources. For example, an ISO metadata store resource requires a JDBC pool, because it provides the actual connections to the SQL database. Therefore, the corresponding resource configuration format has an element to specify it:

.. topic:: Example for wiring workspace resources

    .. literalinclude:: ../../xml/workspace_dependencies.xml
    :language: xml

In this example, the ISO metadata store is wired to JDBC connection pool ``conn1``. Many deegree resource configuration files contain such references to dependent resources. Some resources perform auto-wiring. For example, every CSW instance needs to connect to a metadata store for accessing stored metadata records. If the CSW configuration omits the reference to the metadata store, it is assumed that there's exactly one metadata store defined in the workspace and deegree will automatically connect the CSW to this store.

.. tip::
    The required dependencies are specific to every type of resource and are documented for each resource configuration format.
