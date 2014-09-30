.. _anchor-configuration-wms:

---------------------
Web Map Service (WMS)
---------------------

In deegree terminology, a deegree WMS renders maps from data stored in feature, coverage and tile stores. The WMS is configured using a layer structure, called a *theme*. A theme can be thought of as a collection of layers, organized in a tree structure. *What* the layers show is configured in a layer configuration, and *how* it is shown is configured in a style file. Supported style languages are StyledLayerDescriptor (SLD) and Symbology Encoding (SE).

.. figure:: ../../images/workspace-wms.png
    :figwidth: 80%
    :width: 80%
    :target: ../../_images/workspace-wms.png

    A WMS resource is connected to exactly one theme resource

.. tip::
    In order to fully understand deegree WMS configuration, you will have to learn configuration of other workspace aspects as well. Chapter :ref:`anchor-configuration-renderstyles` describes the creation of layers and styling rules. Chapter :ref:`anchor-configuration-featurestore` describes the configuration of vector data access and chapter :ref:`anchor-configuration-coveragestore` describes the configuration of raster data access.

^^^^^^^^^^^^^^^^^^^^^^^^^^^
A word on layers and themes
^^^^^^^^^^^^^^^^^^^^^^^^^^^

Readers familiar with the WMS protocol might be wondering why layers can not be configured directly in the WMS configuration file. Inspired by WMTS 1.0.0 we found the idea to separate structure and content very appealing. Thinking of a layer store that just offers a set of layers is an easy concept. Thinking of a theme as a structure that may contain layers at certain points also makes sense. But when thinking of WMS the terms begin clashing. We suggest to avoid confusion as much as possible by using the same name for each corresponding theme, layer and possibly even tile/feature/coverage data sources. We believe that once you work a little with the concept of themes, and seeing them exported as WMS layer trees, the concepts fit well enough so you can appreciate the clean cut.

^^^^^^^^^^^^^^^^^^^^^^
Configuration overview
^^^^^^^^^^^^^^^^^^^^^^

The configuration can be split up in six sections. Readers familiar with other deegree service configurations may recognize some similarities, but we'll describe the options anyway, because there may be subtle differences. A document template looks like this:

.. code-block:: xml

  <?xml version='1.0'?>
  <deegreeWMS xmlns='http://www.deegree.org/services/wms'>
    <!-- actual configuration goes here -->
  </deegreeWMS>

The following table shows what top level options are available.

.. table:: Options for ``deegreeWMS``

+--------------------------+--------------+---------+------------------------------------------------------------------------------+
| Option                   | Cardinality  | Value   | Description                                                                  |
+==========================+==============+=========+==============================================================================+
| SupportedVersions        | 0..1         | Complex | Limits active OGC protocol versions                                          |
+--------------------------+--------------+---------+------------------------------------------------------------------------------+
| MetadataStoreId          | 0..1         | String  | Configures a metadata store to check if metadata ids for layers exist        |
+--------------------------+--------------+---------+------------------------------------------------------------------------------+
| MetadataURLTemplate      | 0..1         | String  | Template for generating URLs to feature type metadata                        |
+--------------------------+--------------+---------+------------------------------------------------------------------------------+
| ServiceConfiguration     | 1            | Complex | Configures service content                                                   |
+--------------------------+--------------+---------+------------------------------------------------------------------------------+
| FeatureInfoFormats       | 0..1         | Complex | Configures additional feature info output formats                            |
+--------------------------+--------------+---------+------------------------------------------------------------------------------+
| ExtendedCapabilities     | 0..n         | Complex | Extended Metadata reported in GetCapabilities response                       |
+--------------------------+--------------+---------+------------------------------------------------------------------------------+

^^^^^^^^^^^^^
Basic options
^^^^^^^^^^^^^

* ``SupportedVersions``: By default, all implemented WMS protocol versions (1.1.1 and 1.3.0) are activated. You can control offered WMS protocol versions using the element ``SupportedVersions``. This element allows any of the child elements ``<Version>1.1.1</Version>`` and ``<Version>1.3.0</Version>``.
* ``MetadataStoreId``: If set to a valid metadata store, the store is queried upon startup with all configured layer metadata set ids. If a metadata set does not exist in the metadata store, it will not be exported as metadata URL in the capabilties. This is a useful option if you want to automatically check for configuration errors/typos. By default, no checking is done.
* ``MetadataURLTemplate``: By default, no metadata URLs are generated for layers in the capabilities. You can set this option either to a unique URL, which will be exported as is, or to a template with a placeholder. In any case, a metadata URL will only be exported if the layer has a metadata set id set. A template looks like this: http://discovery.eu/csw?service=CSW&amp;request=GetRecordById&amp;version=2.0.2&amp;id=${metadataSetId}&amp;outputSchema=http://www.isotc211.org/2005/gmd&amp;elementSetName=full. Please note that you'll need to escape the & symbols with &amp; as shown in the example. The ${metadataSetId} will be replaced with the metadata set id from each layer.

Here is a snippet for quick copy & paste:

.. code-block:: xml

  <SupportedVersions>
    <SupportedVersion>1.1.1</SupportedVersion>
  </SupportedVersions>
  <MetadataStoreId>mdstore</MetadataStoreId>
  <MetadataURLTemplate>http://discovery.eu/csw?service=CSW&amp;request=GetRecordById&amp;version=2.0.2&amp;id=${metadataSetId}&amp;outputSchema=http://www.isotc211.org/2005/gmd&amp;elementSetName=full</MetadataURLTemplate>

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Service content configuration
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

You can configure the behaviour of layers using the ``DefaultLayerOptions`` element.

Have a look at the layer options and their values:

.. table:: Layer options

+------------------------+-------------------+-----------+---------------------------------------------------------------------------------------------------+
| Option                 | Cardinality       | String    | Description                                                                                       |
+========================+===================+===========+===================================================================================================+
| Antialiasing           | 0..1              | String    | Whether to antialias NONE, TEXT, IMAGE or BOTH, default is BOTH                                   |
+------------------------+-------------------+-----------+---------------------------------------------------------------------------------------------------+
| RenderingQuality       | 0..1              | String    | Whether to render LOW, NORMAL or HIGH quality, default is HIGH                                    |
+------------------------+-------------------+-----------+---------------------------------------------------------------------------------------------------+
| Interpolation          | 0..1              | String    | Whether to use BILINEAR, NEAREST_NEIGHBOUR or BICUBIC interpolation, default is NEAREST_NEIGHBOUR |
+------------------------+-------------------+-----------+---------------------------------------------------------------------------------------------------+
| MaxFeatures            | 0..1              | Integer   | Maximum number of features to render at once, default is 10000                                    |
+------------------------+-------------------+-----------+---------------------------------------------------------------------------------------------------+
| FeatureInfoRadius      | 0..1              | Integer   | Number of pixels to consider when doing GetFeatureInfo, default is 1                              |
+------------------------+-------------------+-----------+---------------------------------------------------------------------------------------------------+

You can configure the WMS to use one or more preconfigured themes. In WMS terms, each theme is mapped to a layer in the WMS capabilities. So if you use one theme, the WMS root layer corresponds to the root theme. If you use multiple themes, a synthetic root layer is exported in the capabilities, with one child layer corresponding to each root theme. The themes are configured using the ``ThemeId`` element.

Here is an example snippet of the content section:

.. code-block:: xml

  <ServiceConfiguration>

    <DefaultLayerOptions>
      <Antialiasing>NONE</Antialiasing>
    </DefaultLayerOptions>

    <ThemeId>mytheme</ThemeId>

  </ServiceConfiguration>

.. _anchor-featureinfo-configuration:

^^^^^^^^^^^^^^^^^^^^^^^^^^^
Custom feature info formats
^^^^^^^^^^^^^^^^^^^^^^^^^^^

Any mime type can be configured to be available as response format for GetFeatureInfo requests, although the most commonly used is probably ``text/html``. There are two alternative ways of controlling how the output is generated (besides using the default HTML output). One involves a deegree specific templating mechanism, the other involves writing an XSLT script. The deegree specific mechanism has the advantage of being considerably less verbose, making common use cases very easy, while the XSLT approach gives you all the freedom.

This is how the configuration section looks like for configuring a deegree templating based format:

.. code-block:: xml

  <FeatureInfoFormats>
    <GetFeatureInfoFormat>
      <File>../customformat.gfi</File>
      <Format>text/html</Format>
    </GetFeatureInfoFormat>
  </FeatureInfoFormats>

The configuration for the XSLT approach looks like this:

.. code-block:: xml

  <FeatureInfoFormats>
    <GetFeatureInfoFormat>
      <XSLTFile gmlVersion="GML_32">../customformat.xsl</XSLTFile>
      <Format>text/html</Format>
    </GetFeatureInfoFormat>
  </FeatureInfoFormats>

Of course it is possible to define as many custom formats as you want, as long as you use a different mime type for each (just duplicate the ``GetFeatureInfoFormat`` element). If you use one of the default formats, the default output will be overridden with your configuration.

In order to write your XSLT script, you'll need to develop it against a specific GML version (namespaces between GML versions may differ, GML output itself will differ). The default is GML 3.2, you can override it by specifying the ``gmlVersion`` attribute on the ``XSLTFile`` element. Valid GML version strings are ``GML_2``, ``GML_30``, ``GML_31`` and ``GML_32``.

If you want to learn more about the templating format, read the following sections.

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
FeatureInfo templating format
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The templating format can be used to create text based output formats for featureinfo output. It uses a number of definitions, rules and special constructs to replace content with other content based on feature and property values. Please note that you should make sure your file is UTF-8 encoded if you're using umlauts.

""""""""""""""""""""
Introduction/Example
""""""""""""""""""""

This section gives a quick overview how the format works and demonstrates the development of a small sample HTML output.

On top level, you can have a number of *template definitions*. A template always has a name, and there always needs to be a template named ``start`` (yes, it's the one we start with).

A simple valid templating file that does not actually depend on the features coming in looks like this:

.. code-block:: xml

  <?template start>
  <html>
  <body>
    <p>Hello</p>
  </body>
  </html>

A featureinfo request will now always yield the body of this template. In order to use the features coming in, you need to define other templates, and call them from a template. So let's add another template, and call it from the ``start`` template:

.. code-block:: xml

  <?template start>
  <html>
  <body>
  <ul>
  <?feature *:myfeaturetemplate>
  </ul>
  </body>
  </html>

  <?template myfeaturetemplate>
  <li>I have a feature</li>

What happens now is that first the body of the ``start`` template is being output. In that output, the ``<?feature *:myfeaturetemplate>`` is replaced with the content of the ``myfeaturetemplate`` template for each feature in the feature collection. So if your query hits five features, you'll get five ``li`` tags like in the template. The asterisk is used to select all features, it's possible to limit the number of objects matched. See below in the reference section for a detailed explanation on how it works.

Within the ``myfeaturetemplate`` template you have switched context. In the ``start`` template your context is the feature collection, and you can call *feature templates*. In the ``myfeaturetemplate`` you 'went down' the tree and are now in a feature context, where you can call *property templates*. So what can we do in a feature context? Let's start simple by writing out the feature type name. Change the ``myfeaturetemplate`` like this:

.. code-block:: xml

  <?template myfeaturetemplate>
  <li>I have a <?name> feature</li>

What happens now is that for each use of the ``myfeaturetemplate`` the ``<?name>`` part is being replaced with the name of the feature type of the feature you hit. So if you hit two features, each of a different type, you get two different ``li`` tags in the document, each with its name written in it.

So deegree only replaces the *template call* in the ``start`` template with its replacement once the special constructs in the *called* template are all replaced, and all the special constructs/calls within *that* template are all replaced, ... and so on.

Let's take it to the next level. What's you really want to do in featureinfo responses is of course get the value of the features' properties. So let's add another template, and call it from the ``myfeaturetemplate`` template:

.. code-block:: xml

  <?template myfeaturetemplate>
  <li>I have a <?name> feature and properties: <?property *:mypropertytemplate></li>

  <?template mypropertytemplate>
  <?name>=<?value>

Now you also get all property names and values in the ``li`` item. Note that again you switched the context in the template, now you are at property level. The ``<?name>`` and ``<?value>`` special constructs yield the property name and value, respectively (remember, we're at property level here).

While that's already nice, people often put non human readable values in properties, even property names are sometimes not human readable. In order to fix that, you often have code lists mapping the codes to proper text. To use these, there's a special kind of template called a *map*. A map is like a simple property file. Let's have a look at how to define one:

.. code-block:: xml

  <?map mycodelistmap>
  code1=Street
  code2=Highway
  code3=Railway

  <?map mynamecodelistmap>
  tp=Type of way

Looks simple enough. Instead of ``template`` we use map, after that comes the name. Then we just map codes to values. So how do we use this? Instead of just using the ``<?name>`` or ``<?value>`` we push it through the map:

.. code-block:: xml

  <?template mypropertytemplate>
  <?name:map mynamecodelistmap>=<?value:map mycodelistmap>

Here the name of the property is replaced with values from the ``mynamecodelistmap``, the value is replaced with values from the ``mycodelistmap``. If the map does not contain a fitting mapping, the original value is used instead.

That concludes the introduction, the next section explains all available special constructs in detail.

"""""""""""""""""""""""""""""
Templating special constructs
"""""""""""""""""""""""""""""

This section shows all available special constructs. The selectors are explained in the table below. The validity describes in which context the construct can be used (and where the description applies). The validity can be one of *top level* (which means it's the definition of something), *featurecollection* (the ``start`` template), *feature* (a template on feature level), *property* (a template on property level) or *map* (a map definition).

+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| Construct                     | Validity          | Description                                                                                     |
+===============================+===================+=================================================================================================+
| <?template *name*>            | top level         | defines a template with name *name*                                                             |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?map *name*>                 | top level         | defines a map with name *name*                                                                  |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?feature *selector*:*name*>  | featurecollection | calls the template with name *name* for features matching the selector *selector*               |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?property *selector*:*name*> | feature           | calls the template with name *name* for properties matching the selector *selector*             |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?name>                       | feature           | evaluates to the feature type name                                                              |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?name>                       | property          | evaluates to the property name                                                                  |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?name:map *name*>            | feature           | uses the map *name* to map the feature type name to a value                                     |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?name:map *name*>            | property          | uses the map *name* to map the property name to a value                                         |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?value>                      | property          | evaluates to the property's value                                                               |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?value:map *name*>           | property          | uses the map *name* to map the property's value to another value                                |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?index>                      | feature           | evaluates to the index of the feature (in the list of matches from the previous template call)  |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?index>                      | property          | evaluates to the index of the property (in the list of matches from the previous template call) |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?gmlid>                      | feature           | evaluates to the feature's gml:id                                                               |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?odd:*name*>                 | feature           | calls the *name* template if the index of the current feature is odd                            |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?odd:*name*>                 | property          | calls the *name* template if the index of the current property is odd                           |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?even:*name*>                | feature           | calls the *name* template if the index of the current feature is even                           |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?even:*name*>                | property          | calls the *name* template if the index of the current property is even                          |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?link:*prefix*:>             | property          | if the value of the property is not an absolute link, the prefix is prepended                   |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+
| <?link:*prefix*:*text*>       | property          | the text of the link will be *text* instead of the link address                                 |
+-------------------------------+-------------------+-------------------------------------------------------------------------------------------------+

The selector for properties and features is a kind of pattern matching on the object's name.

+--------------------------+----------------------------------------------------------+
| Selector                 | Description                                              |
+==========================+==========================================================+
| \*                       | matches all objects                                      |
+--------------------------+----------------------------------------------------------+
| \* *text*                | matches all objects with names ending in *text*          |
+--------------------------+----------------------------------------------------------+
| *text* \*                | matches all objects with names starting with *text*      |
+--------------------------+----------------------------------------------------------+
| not(*selector*)          | matches all objects not matching the selector *selector* |
+--------------------------+----------------------------------------------------------+
| *selector1*, *selector2* | matches all objects matching *selector1* and *selector2* |
+--------------------------+----------------------------------------------------------+

^^^^^^^^^^^^^^^^^^^^^
Extended capabilities
^^^^^^^^^^^^^^^^^^^^^

Important for applications like INSPIRE, it is often desirable to include predefined blocks of XML in the extended capabilities section of the WMS' capabilities output. This can be achieved simply by adding these blocks to the extended capabilities element of the configuration:

.. code-block:: xml

  <ExtendedCapabilities>
    <MyCustomOutput xmlns="http://www.custom.org/output">
      ...
    </MyCustomOutput>
  </ExtendedCapabilities>

^^^^^^^^^^^^^^^^^^^^^^^^^^
Vendor specific parameters
^^^^^^^^^^^^^^^^^^^^^^^^^^

The deegree WMS supports a number of vendor specific parameters. Some parameters are supported on a per layer basis while some are applied to the whole request. Most of the parameters correspond to the layer options above.

The parameters which are supported on a per layer basis can be used to set an option globally, eg. ``...&REQUEST=GetMap&ANTIALIAS=BOTH&...``, or for each layer separately (using a comma separated list): ``...&REQUEST=GetMap&ANTIALIAS=BOTH,TEXT,NONE&LAYERS=layer1,layer2,layer3&...`` Most of the layer options have a corresponding parameter with a similar name: ``ANTIALIAS``, ``INTERPOLATION``, ``QUALITY`` and ``MAX_FEATURES``. The feature info radius can currently not be set dynamically.

The ``PIXELSIZE`` parameter can be used to dynamically adjust the resolution of the resulting image. The default is the WMS default of 0.28 mm. So to achieve a double resolution, you can double the ``WIDTH`` / ``HEIGHT`` parameter values and set the ``PIXELSIZE`` parameter to 0.14.

Using the ``QUERYBOXSIZE`` parameter you can include features when rendering that would normally not intersect the envelope specified in the ``BBOX`` parameter. That can be useful if you have labels at point symbols out of the envelope which would be rendered partly inside the map. Normal GetMap behaviour will exclude such a label. With the ``QUERYBOXSIZE`` parameter you can specify a factor by which to enlarge the original bounding box, which is used solely for querying the data store (the actual extent returned will not be changed!). Use values like 1.1 to enlarge the envelope by 5% in each direction (this would be 10% in total).
