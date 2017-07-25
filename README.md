# rest-offenders
A REST API for the Rhode Island Sex Offender Database

rest-offenders builds a RESTful back-end for the Rhode Island sex offender database (RISOD).

How it works:
- Spring Boot w/ jpa, web, h2, and thymeleaf
- jsoup, for html parsing

  also:
  - guava
  - lombok
  - skeleton css
  - jquery
  
When run, rest-offenders crawls the RISOD website for offender pages. Each page is visited and offender data is parsed out and used to create an Offender object which is then added to the back-end. 
