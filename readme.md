Cabrillo file parser/validator library.

* Initially handles Winter Field Day
* Rules-based for each kind of tag
	* Checks that:
	* each tags rules are satisfied
		* unexpected rules (not considered fatal)
	* Cardinality rules (how often)
		* One time
		* Zero or one
		* One or more
		* Any
	* Tag detail rules
		*  Mostly for QSO tags
			* WFD Rule
				* QSO syntax
				* Correct category 
				* Correct ARRL section
				* Call sign matches call sign tag
	* Easy to hookup to database to drive contest QSO matching applications.
	* Written in Scala
	* Useable from command line or as an API from any JVM language.
	* Open source, license to be determined.
* Building
    * SNAPSHOT build and publish the jar to the local ivy repository.
        $ sbt packageLocal
    * Release build
        build.sbt will not, currently publish to github packages. todo use https://github.com/sbt/sbt-release
        * Remove -SNAPSHOT from version in build.sbt
        * Commit & tag build
        * sbt package create jar: target/scala-2.13/cabrillo_2.13-0.2.1.jar
        * bump version number and add back -SNAPSHOT suffix.
        * Commit
