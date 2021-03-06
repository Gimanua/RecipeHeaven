# RecipeHeaven
This is a project that builds a deployable web application which focuses heavily on real life cooking recipes. The main uses are to:
* View uploaded Recipes
* Post Recipes
* Comment on the Recipe
* Like Recipes

## Setup
To set up this project you need a database with the format supplied in the sql file attached to this repository.
Then you also need a file called database.properties placed in the projects resources folder, 
this file should contain values for the following keys:
* host - Where the database is hosted.
* db_name - The name of the database to use.
* user - The user to use for the database.
* password - The password for the user.

Another file that's needed is github-oauth.properties, this file should contain values for the following keys:
* client_id - The client ID issued by GitHub.
* client_secret - The secret issued by GitHub.

You also need a file called image.properties, this file should containg values for the following keys:
* path - The path to where you want images to be saved.

Then you should build the frontend by issuing the command npm run build in the frontend folder. (This requires Node and npm)
Then you can just build the whole project to a war file to deploy.
