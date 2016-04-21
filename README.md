Mytechia Commons
================


The Mytechia Commons library is a collection of general utilities that can be useful when programming in JAVA.

Currently Mytechia Commons is divided into three different maven projects.

Mytechia Commons Library:
* __com.mytechia.commons.framework.i18n__: Very light internationalization framework.
* __com.mytechia.commons.patterns.observer__: Implementation of the observer pattern in JAVA.
* __com.mytechia.commons.patterns.prototype__: Implementation of the prototype pattern using the *com.mytechia.commons.di.container*.
* __com.mytechia.commons.util__: General purpose utilities. Take a look.

Mytechia Commons DI:
* __com.mytechia.commons.di.container__: Implements part of the Dependency Injection architectural pattern, providing runtime access and configuration of software dependencies

Mytechia Commons ModelAction:
* __com.mytechia.commons.framework.modelaction__: An action oriented framework to implement the business model of applications.

Latest releases are uploaded to Maven Central.

For the general utilities:
```xml
<dependency>
	<groupId>com.mytechia</groupId>
	<artifactId>mytechia-commons-library</artifactId>
	<version>1.3.0</version>
</dependency>
```

For the DI container:
```xml
<dependency>
	<groupId>com.mytechia</groupId>
	<artifactId>mytechia-commons-di</artifactId>
	<version>1.3.0</version>
</dependency>
```

For the "modelaction" framework:
```xml
<dependency>
	<groupId>com.mytechia</groupId>
	<artifactId>mytechia-commons-modelaction</artifactId>
	<version>1.3.0</version>
</dependency>
```
