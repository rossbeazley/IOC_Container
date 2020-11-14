# IOC_Container
exploring an android ioc container

1. Invert control of basic dependencies in an Activity

- given an activity is created, can we inject simple dependencies based off of class type

Some basic functionality has been implemented, to start to drive out the system.
The implications to date so far are:
  1. The application will need to register an activity lifecycle callback object and build up the IoC container.
     This is less than ideal at the moment and we can refactor towards an easier dev experience.
  2. There is no magic (so far?)
  3. We instantly have tech debt with the use of activity test rule but we can make the system more functional and come back to this after we have something approaching MVP

Next up we can move to pure JVM land and drive out futher features around IoCContainer leaving ActivityIoCContainer for now
 - realised that the android code could be simplified further, its acting as an adaptor more than anything integrating into the system components
 - moved the feature test to jvm tests leaving behind a test to show mediation of the "injection" usecase for activities

Next up we work on making injection of Thing dependencies work for any type of object that needs a Thing ie remove the cast in the IoCContainer


