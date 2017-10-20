# spotHeroApp
DateTime Simple Rest Application(WIP) --- Work In Progress, still need to correct the TODOs to finalize the date/time checks.
Along with add the actual unit test cases.

Application was designed using GlassFish 4.1.2, setup a localhost deployment, and push the .war file to the server, after the application is built.
TO curl against: "curl -d \"2015-07-01T07:00:00Z to 2015-07-01T12:00:00Z\" http://localhost:8080/spotHeroApp_war_exploded/spottimes" +
                "this should yeild a return of 1500"
