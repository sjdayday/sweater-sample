FROM tomcat:8.0
MAINTAINER Steve Doubleday "stevedoubleday@gmail.com"

ADD tomcat-users.xml $CATALINA_HOME/conf

ADD sweater-sample.war $CATALINA_HOME/webapps

CMD ["catalina.sh", "run"]

EXPOSE 8080
