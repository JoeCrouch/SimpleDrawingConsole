Simple Console Drawing Program
================================

An application to take text commands from the user to create simple drawings.

Current available drawing tools:
* Create new canvas e.g.'C 10 20' would create a new 10 x 20 canvas space for working on - this must be run first
* Add Line e.g. 'L 1 2 1 10' adds lines to the canvas denoted with an 'x' symbol - this currently only supports non diagonal lines
* Add Rectangles e.g. 'R 2 2 10 9' adds a rectangle with one corner at 2 2 and another at 10 9. This is also denoted by 'x' symbols.
* Fill spaces e.g. 'B 4 4 p' fills all empty space around point 4 4 with symbol p. Like a bucket fill.
* Quit - Q closes the application

New Canvas
===========
* Canvas dimensions must be strictly positive
* Creating new canvas on top of an existing canvas overwrites it as there is currently no save feature.

Adding Lines
=============
* Cannot add diagonal lines
* Adding lines with part of the line outside of the canvas will only add the part of the line that is within the canvas.

Adding Rectangles
==================
* Similar to adding lines; adding rectangles with part of the rectangle outside of the canvas will only add the part of the rectangle within the canvas.

Filling Spaces
===============
* When filling a space that is currently not empty will only change that locations symbol and nowhere else.

Exceptions
===========
* Exceptions are currently passed to the user to allow diagnosis. This could be updated to only pass certain exceptions to the user.
* If the canvas is not created before trying to edit it a message will be displayed to the user.
* If an invalid command is passed then a list of valid commands will be presented to the user. This could be improved to give examples along with each of the commands.

