# Rail Splitter

A Java log reducer.

## What is this?

This is my basic attempt at writing a Java Logger that only writes out messages
after certain trip write conditions have been hit. The first pass will likely
only write messages either always or when an exception occurs (error, log, or
global uncaught exception handler?).

Additionally, logs in the form `log(format, Object[])` store the object arrays
using soft references this allows the system to reclaim memory when under
pressure though may cause some logs to lose their associated metadata.

## Statistics

Additional functionality has been added to provide insight into the logger's
execution state. With statistics tracking enabled you can check the number of
logs function calls that have been executed.

## Future Ideas

In the future it would also be cool to have message log only when conditional
operating states have been hit.

Automatically log the next x messages after an exception.

Transaction points.

Level class to int and bitwise operations

## TODO:

When statistics are enabled there is a chance of overflow in long running
applications. This is partially mitigated due to the fact that statistics
should not be enabled in production.

* Track the number of messages flushed to disk.
