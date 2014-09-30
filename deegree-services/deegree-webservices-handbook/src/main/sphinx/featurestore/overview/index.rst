-----------------------------------------------
Features, feature types and application schemas
-----------------------------------------------

Features are abstractions of real-world objects, such as rivers, buildings, streets or state boundaries. They are the geo objects of a particular application domain.

A feature types defines the data model for a class of features. For example, a feature type ``River`` could define a class of river features that all have the same properties.

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Simple vs. rich features and feature types
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Some feature types have a more complex structure than others. Traditionally, GIS software copes with "simple" feature types:

* Every property is either simple (string, number, date, etc.) or a geometry
* Only a single property with one name is allowed

Basically, a simple feature type is everything that can be represented using a single database table or a single shape file. In contrast, "rich" feature types additionally allow the following:

* Multiple properties with the same name
* Properties that contain other features
* Properties that reference other features or GML objects
* Properties that contain GML core datatypes which are not geometries (e.g. code types or units of measure)
* Properties that contain generic XML

.. topic:: Example of a rich feature instance encoded in GML

    .. literalinclude:: ../../xml/feature_complex.xml
        :language: xml

.. hint::
    All deegree feature stores support simple feature types, but only the SQL feature store and the memory feature store support rich feature types.

^^^^^^^^^^^^^^^^^^^
Application schemas
^^^^^^^^^^^^^^^^^^^

An application schema defines a number of feature types for a particular application domain. When referring to an application schema, one usually means a GML application schema that defines a hierarchy of rich feature types. Examples for GML application schemas are:

* INSPIRE Data Themes (Annex I, II and III)
* GeoSciML
* CityGML
* XPlanung
* AAA

The following diagram shows a part of the INSPIRE Annex I application schema in UML form:

.. figure:: ../../images/address_schema.png
    :figwidth: 60%
    :width: 50%
    :target: ../../_images/address_schema.png

.. hint::
    The SQL feature store or the memory feature store can be used with GML application schemas.
