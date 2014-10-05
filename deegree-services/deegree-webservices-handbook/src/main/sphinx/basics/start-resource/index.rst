^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Find out which resources you need
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The first step is to identify the types of workspace resources that you need for your use-case. You probably know already which types of services your setup requires. The next step is to identify the dependencies for every service by having a look at the respective chapter in the documentation.  Let's say you want a setup with a transactional WFS, a WMS and a CSW:

* A WFS instance requires 1..n feature stores
* A WMS instance requires 1..n themes
* A CSW instance requires a single metadata store

Now you have to dig deeper: What kinds of feature stores exist? Maybe you will find out that what you want is an SQL feature store. So you read the respective part of the documentation and see that an SQL feature store requires a JDBC connection pool resource. Do the same research for the WMS dependencies. A WMS depends on a theme. Find out what a theme is and what it requires. In short, you have to answer the following questions for every encountered resource:

* What does resource do?
* How is it configured?
* On which resources does this resource depend?

At the end of this process you should know about the resources that you will have to configure for your setup.

.. tip::
    Alternatively, you can approach the resources question bottom-up. Let's say you have your data ready in a PostGIS database. You want to visualize it using a WMS. So you would require a JDBC resource pool that connects to your database. You need a simple SQL feature store (or an SQL feature store) that uses the new connection pool. You create one or more feature layers that are wired to the feature store and a theme based on the layers. At the end of the chain is the WMS resource which has to be configured to use the theme resource. Rendering styles can be created later (references have to be added to the layers configuration).
