Hmm... Seems that GReader will be closed, so unfortunately need to rewrite all this code :) Looking for another free rss reader...

---

# Common information #
Allows to send your rss data from Google Reader to Kindle. Now there is done only console version of application - see only **'gruz.core'** folder, all other features are only for future implementation.

---

### TODO: ###
  1. Include images from feeds
  1. Add different parsers of html pages (habrahabr, livejournal, etc.).
  1. Make web service, which allows to work separately and by timer. (OAuth, GAE, Click, etc.).
  1. May be add not only html document for sending?..

---

### BUILD and RUN: ###
  * Use your google account or create new one.
  * Go to the google reader and create new folder with name 'kindle' (or any else, but don't forget to change it in 'auth.properties' after)
  * Add some subscriptions to this folder
![http://gruz.googlecode.com/files/sample-reader.png](http://gruz.googlecode.com/files/sample-reader.png)
  * Go to the http://kindle.amazon.com and click to 'Personal Document Settings'
  * Add your google email to 'Approved Personal Document E-mail List '
![http://gruz.googlecode.com/files/sample-settings-amazon.png](http://gruz.googlecode.com/files/sample-settings-amazon.png)
  * Edit '/src/main/resources/auth.properties'.
  * Build project by 'mvn package'.
  * Go to the ./target/ folder and run application by 'java -jar -Dfile.encoding=UTF-8 gruz-0.0.1-SNAPSHOT-jar-with-dependencies.jar'
  * Check your kindle email and enjoy =)