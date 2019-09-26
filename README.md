# Command line TODO application

A simple TODO application that can be used from CLI.
## Getting Started
**Setting up an alias**

To be able to run the application regardless from which directory you are in, you can add an alias to your .bash_profile file:

alias todo *(or any aliasname of your choice)* = "cd *local project directory*/***src*** && java Main -file *local project directory*/***tasks.txt***"

### To run the application

In your Console, type:
```
todo
```

the usage instructions will get printed to the Console:

```
Command Line Todo application
=============================

Command line arguments:
-l   Lists all the tasks
-a   Adds a new task
-r   Removes an task
-c   Completes an task

```