# mchfc

A Leiningen template to start a web-app based on
* Mongo DB (Monger)
* Compojure
* Hiccup
* Friend
* Compass/Sass
* Skeleton - responsive web template

and the rec-mong library to add an appengine-magic style API to Monger for Clojure records.

## Usage

To start a new web-app project:

    lein new mchfc <app-name>

This will set up a code structure

    public/css
    public/css/img
    public/images
    public/js
    sass/
    sass/partials
    src/myapp
    src/ring

The sass folder contains the Sass-ified Skeleton files and requires ruby and the compass gem  
In src there are the basic Clojure files for a web-app:

    core controller view model dal

The starting model defines a record User which is Friend authenticated and authorised, as well as 
factory functions for creating instances of model types  
The data access layer (dal file) holds all other functions for manipulating data from Mongo DB (through rec-mong and Monger)  
In controller you define the Compojure routes, usually calling a function to parse the request and call a view function.
The view contains the overall template and views for the pages

Deployment of the app to Heroku with a MongoLab is easy by providing the MONGOLAB\_URI as environment variable  
while developing locally it defaults to the URI mongodb://localhost:27017/myapp


## License

Copyright © 2013 Harry Binnendijk

Distributed under the Eclipse Public License, the same as Clojure.
