# Seems that GReader will be closed, so unfortunately need to rewrite all this code :) 
# Looking for another free rss reader...
============

## Common information

Allows to send your rss data from Google Reader to Kindle. Now there is done only console version of application - see only 'gruz.core' folder, all other features are only for future implementation.

### TODO:
~~~ bash
• Include images from feeds
• Add different parsers of html pages (habrahabr, livejournal, etc.).
• Make web service, which allows to work separately and by timer (OAuth, GAE, Click, etc.).
• May be support not only html documents
~~~ 

### BUILD and RUN (see README.pdf):
~~~ bash
• Use your google account or create new one.
• Go to the google reader and create new folder with name 'kindle' (or any else, but don't forget to change it in 'auth.properties' after)
• Add some subscriptions to this folder
• Go to the http://kindle.amazon.com and click to 'Personal Document Settings'
• Add your google email to 'Approved Personal Document E-mail List'
• Edit '/src/main/resources/auth.properties'.
• Build project by 'mvn package'.
• Go to the ./target/ folder and run application by 'java -jar -Dfile.encoding=UTF-8 gruz-0.0.1-SNAPSHOT-jar-with-dependencies.jar'
• Check your kindle email with sync tool on device and enjoy reading feeds =)
~~~ 
