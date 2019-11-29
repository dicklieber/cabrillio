Cabrillo file parser/validator library.

* Initially handles Winter Field Day
* Rules-based for each kind of tag
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
