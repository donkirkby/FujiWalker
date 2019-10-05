# FujiWalker
Search for interesting layouts of Fujisan solitaire

To run, open the project in Eclipse, then follow the prompt to install the
AspectJ nature. Finally, add new run configurations: 

1. FujiSwapper.main - use evolutionary search to find the layout with the
  longest optimal solution. Writes layouts and solutions to solutions.txt file.
2. FujiWalker.main - count total possible layouts, and unique layouts
3. FujiFinder.main - similar, but maybe try to solve each layout?
