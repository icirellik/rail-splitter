# Rail Splitter

A Java log reducer.

## What is this?

This is my basic attempt at writing a Java Logger that only writes out messages
after certain trip write conditions have been hit. The first pass will likely
only write messages either always or when an exception occurs (error, log, or
global uncaught exception handler?).

In the future it would also be cool to have message log only when conditional
operating states have been hit.
