BASE 42
=======

.. image:: https://travis-ci.org/tube42/base42.svg
    :target: https://travis-ci.org/tube42/base42

My libgdx android/desktop boilerplate code. It downloads and combines a number of 
different open source projects into a single JAR file.

Note that due to its complexity, libgdx itself is not built from source. Instead we will download the
official binary release known to be built from source and identified by its SHA-256 checksum.


Building
--------

.. code:: shell

    ant setup
    ant dist
